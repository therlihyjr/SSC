package week01;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests the Javadoc in the source file.
 * 
 * @author Scott
 *
 */
public class JUnitJavadocValidation
{
	public JUnitJavadocValidation()
	{
		m_stream = System.out; // Standard out
		m_methods = new ArrayList<MethodData>();

		m_methods.add(new MethodData("getSize", 0, "int"));
		m_methods.add(new MethodData("isEmpty", 0, "boolean"));
		m_methods.add(new MethodData("peek", 0, "T"));
		m_methods.add(new MethodData("pop", 0, "T"));
		m_methods.add(new MethodData("push", 1));
	}

	@Test
	public void testJavadoc()
	{
		trace("Validating Javadoc");

		// Update these values for each assignment
		m_packageName = "week01";
		assertTrue(validateJavadoc("GenericStack.java"));
	}

	/**
	 * Validates @author has been inserted into code and no void main is
	 * contained in the file
	 * 
	 * @return true if code is properly implemented
	 */
	private boolean validateJavadoc(String fileName)
	{
		trace(" -- validateCode --");
		boolean result = true;

		String codeFilePath = "";
		try
		{
			codeFilePath = initFilePath(fileName);
			trace("codeFilePath: " + codeFilePath);

			if(codeFilePath == null)
			{
				trace(" ** No file found");
				result = false;
			}
			else
			{
				File srcFile = new File(codeFilePath);
				if(!srcFile.exists())
				{
					trace(" ** Source file doesn't exist at " + codeFilePath);
					result = false;
				}
				else
				{
					// evaluate file
					result = evaluateCodeFile(srcFile);
				}
			}
		}
		catch(IOException ex)
		{
			trace(ex.getMessage());
			result = false;
		}

		return result;
	}

	/**
	 * Verifies the @author Javadoc attribute is in the file and has a value
	 * Verifies no void main method is defined. Verifies the public methods have
	 * Javadoc comments
	 * 
	 * @param file
	 *            File to evaluate
	 * @return true if code review is successful, otherwise false.
	 * @throws IOException
	 *             Thrown when on error accessing file
	 */
	private boolean evaluateCodeFile(File file) throws IOException
	{
		trace("  -- evaluateCodeFile() --");
		boolean result = false;
		boolean authorResult = false;
		boolean mainResult = false;

		FileReader reader = null;
		BufferedReader buffer = null;
		try
		{
			reader = new FileReader(file);
			buffer = new BufferedReader(reader);
			StringBuilder content = new StringBuilder();
			String line = "";
			while((line = buffer.readLine()) != null)
			{
				content.append(line);
				content.append("\r\n");
			}

			int authorIndex = content.indexOf("@author");
			int mainIndex = content.indexOf("void main");

			// verify the @author tag exists
			if(authorIndex < 0)
			{
				trace("Code missing @author tag");
				authorResult = false;
			}
			else
			{
				authorResult = true;
			}

			// verify there is no void main
			if(mainIndex < 0)
			{
				mainResult = true;
			}
			else
			{
				trace(" ** Code has unexpected void main method");
				mainResult = false;
			}

			boolean validMethods = validateMethods(content);

			result = authorResult && mainResult && validMethods;
		}
		catch(IOException ex)
		{
			trace(ex.getMessage());
		}
		finally
		{
			if(buffer != null)
				try
				{
					buffer.close();
				}
				catch(IOException ex)
				{
				}
		}

		return result;
	}

	private boolean validateMethods(StringBuilder content)
	{
		boolean isValid = true;
		for(MethodData mData : m_methods)
		{
			String methodName = mData.getMethodName();
			String expectSignature = mData.getReturnType() + " " + methodName;
			trace(" -- checking method signature: " + expectSignature);

			int index = content.indexOf(expectSignature);

			// look back to see if there is Javadoc

			if(index != -1)
			{
				// does it have the right number or parameters
				String fragment = getLineFragment(content, index);
				trace("  fragment: " + fragment);
				switch(mData.getParameterCount())
				{
					case 0:
						mData.setValid();
						break;
						
					case 1:
						trace("Testing single parameters");
						fragment = fragment.trim();
						if( fragment.matches(ONEPARAM) )
						{
							trace(" valid single parameter");
							mData.setValid();
						}
						else
						{
							trace("Invalid single parameter: expected pattern: " + ONEPARAM
									+ " actual: " + fragment);
						}
						break;
				}
			}

			isValid = mData.isValid();
		}

		return isValid;
	}

	private String getLineFragment(StringBuilder content, int startIndex)
	{
		boolean fStop = false;
		StringBuilder fragementBuilder = new StringBuilder();
		while(!fStop)
		{
			char ch = content.charAt(startIndex++);
			if(ch != '\r')
			{
				fragementBuilder.append(ch);
			}
			else
			{
				fStop = true;
			}
		}

		return fragementBuilder.toString();
	}

	/**
	 * Setup the test file path. Dev environment and runtime environment have
	 * different current directory behavior. For runtime testing, it is the bin
	 * folder For development, it is the current project folder. The test will
	 * create a new File instance for the current directory. If the directory is
	 * bin, the we use the simple file name for the test If it isn't we append
	 * the .\src\week00\ to the file path. That way this will work in
	 * development and testing.
	 * 
	 * @throws IOException
	 */
	private String initFilePath(String fileName) throws IOException
	{
		String filePath = "";
		File curDir = new File(".");
		File curDir1 = new File(curDir.getAbsolutePath());
		boolean exists = curDir1.exists();
		boolean isDir = curDir1.isDirectory();
		String bin = curDir1.getParentFile().getName();
		boolean b = bin.equals("bin");
		File srcFolder = null;
		if(exists && isDir && b)
		{
			// get the src folder
			File binParent = curDir1.getParentFile().getParentFile();
			File[] fileList = binParent.listFiles();
			srcFolder = findSrcFolder(fileList);
			filePath = srcFolder.getCanonicalPath() + File.separator + fileName;
		}
		else
		{
			File[] fileList = curDir1.listFiles();
			srcFolder = findSrcFolder(fileList);

			filePath = srcFolder.getCanonicalPath() + File.separator
					+ m_packageName + File.separator + fileName;
		}

		return filePath;
	}

	private File findSrcFolder(File[] fileList)
	{
		File srcFolder = null;
		for(File f : fileList)
		{
			if(f.getName().equals("src"))
			{
				srcFolder = f;
				break;
			}
		}

		return srcFolder;
	}

	/**
	 * Trace the msg to a PrintStream Provides the method for tests to report
	 * status
	 * 
	 * @param msg
	 */
	protected void trace(String msg)
	{
		m_stream.println("    " + msg);
	}

	protected PrintStream m_stream;
	private String m_packageName;
	private ArrayList<MethodData> m_methods;
	private static String ONEPARAM = "\\w* \\w*\\(\\w* \\w*\\)( \\{)?";//"\\w* \\w*\\(\\w* \\w*\\)( \\w)?";//"\\w* \\w*\\(\\w* \\w*\\)";
}

/**
 * Helper class used to verify the required methods of the class under test are
 * properly declared and have Javadoc comments
 * 
 * @author Scott
 *
 */
class MethodData
{
	MethodData(String name)
	{
		this(name, 0);
	}

	MethodData(String name, int parameterCount)
	{
		this(name, parameterCount, "void");
	}

	MethodData(String name, int parameterCount, String returnType)
	{
		m_methodName = name;
		m_isValid = false;
		m_parameterCount = parameterCount;
		m_returnType = returnType;
	}

	public boolean isValid()
	{
		return m_isValid;
	}

	public int getParameterCount()
	{
		return m_parameterCount;
	}

	public void setValid()
	{
		m_isValid = true;
	}

	String getMethodName()
	{
		return m_methodName;
	}

	String getReturnType()
	{
		return m_returnType;
	}

	private String m_methodName;
	private boolean m_isValid;
	private int m_parameterCount;
	private String m_returnType;
}
