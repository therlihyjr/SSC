package week00;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Tests the weekly assignment
 * Verifies the execution of the HelloWorld class
 * and the required formatting rules
 * 
 * Include this class in week00.
 * Verify HelloWorld compiles and executes within the test harness
 * 
 * @author Scott LaChance
 *
 */
public class TestHarness
{
	public TestHarness()
	{
		m_stream = System.out; // Standard out
	}
	/** 
	 * Command line entry point
	 * @param args
	 */
	public static void main(String[] args)
	{
		TestHarness test = new TestHarness();
		test.runTest();
	}
	
	/**
	 * Executes the test
	 */
	private void runTest()
	{
		boolean result = false;
		try
		{
			// execute the test
			boolean executeResult = executeCode();
			
			// verify the minimum file specs 
			boolean validateResult = validateCode();
			
			result = executeResult && validateResult;
		}
		catch(Exception ex)
		{
			trace(ex.getMessage());
			result = false;
		}
		
		if( result )
		{
			trace("Test SUCCESS ");
		}
		else
		{
			trace("Test FAILED");
		}
	}
	
	/**
	 * Executes the HelloWorld api
	 * @return true if expected response is received
	 */
	private boolean executeCode()
	{
		trace(" -- executeCode --");
		boolean startOK = false;
		boolean nameOK = false;
		HelloWorld hello = new HelloWorld();
		String response = hello.display();
		
		// verify the response format
		if( response.startsWith(EXPECTED_RESPONSE_PART1))
		{
			startOK = true;
			
			int index = response.indexOf(EXPECTED_RESPONSE_PART1) + EXPECTED_RESPONSE_PART1.length();
			String nameResponse = response.substring(index);
			nameResponse = nameResponse.trim();
			if(nameResponse.length() > 0)
			{
				nameOK = true;
			}
			else
			{
				trace(" ** Expected a name but found empty string");
			}
		}
		else
		{
			trace(" ** Expected string to start with 'Hello from ' but found " + response);
		}
		
		return startOK && nameOK;
	}
	
	/**
	 * Validates @author has been inserted into code and 
	 * no void main is contained in the file
	 * 
	 * @return true if code is properly implemented
	 */
	private boolean validateCode()
	{
		trace(" -- validateCode --");
		boolean result = true;

		String codeFilePath = "";
		try
		{
			codeFilePath = initFilePath("HelloWorld.java");
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
	 * Verifies no void main method is defined.
	 * 
	 * @param file File to evaluate
	 * @return true if code review is successful, otherwise false.
	 * @throws IOException Thrown when on error accessing file 
	 */
	private boolean evaluateCodeFile(File file) throws IOException
	{
		trace("  -- evaluateCodeFile() --");
		boolean result = false;
		boolean authorResult = false;
		boolean mainResult = false;

		FileReader reader = null;//new FileReader(file);
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
			if( authorIndex < 0 )
			{
				trace("Code missing @author tag");
				authorResult = false;
			}
			else
			{
				authorResult = true;
			}
			
			// verify there is no void main
			if( mainIndex < 0 )
			{
				mainResult = true;
			}
			else
			{
				trace(" ** Code has unexpected void main method");
				mainResult = false;
			}
			
			result = authorResult && mainResult;
		}
		catch(IOException ex)
		{
			trace(ex.getMessage());			
		}
		finally
		{
			if( buffer != null ) try{buffer.close();}catch(IOException ex){}
		}
		
		return result;
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
			filePath = srcFolder.getCanonicalPath() + /*"\\week00*/ "\\" + fileName;//HelloWorld.java";
		}
		else
		{
			File[] fileList = curDir1.listFiles();
			srcFolder = findSrcFolder(fileList);
			
			filePath = srcFolder.getCanonicalPath() + File.separator + "week00" + File.separator + fileName;
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
	 * Trace the msg to a PrintStream
	 * Provides the method for tests to report status
	 * @param msg
	 */
	protected void trace(String msg)
	{
		m_stream.println("    "  + msg);
	}	
	
	protected PrintStream m_stream;
	private static String EXPECTED_RESPONSE_PART1 = "Hello from";
}
