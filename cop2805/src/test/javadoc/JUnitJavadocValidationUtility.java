package test.javadoc;

//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
//import java.util.regex.MatchResult;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import test.TestResult;
import test.parser.JavaParser;
import test.parser.tokens.ClassToken;
import test.parser.tokens.CommentToken;
import test.parser.tokens.ConstructorToken;
import test.parser.tokens.FileToken;
import test.parser.tokens.FunctionToken;
import test.parser.tokens.MethodToken;
import test.parser.tokens.ParameterToken;

/**
 * Implements the logic to generically process a java source file and evaluate
 * its compliance with the course Javadoc rules
 * 
 * Engineering note: While using the internal Java libraries to parse the code
 * would be a good implementation, the dependency on tools.js means another
 * library dependency required for the course project. I haven't determined if
 * this is really OK. The complexity of the API set makes it difficult to
 * integrate right now so this is an intermediate option (more brute force).
 *
 * 
 * Validates the following:
 * <ul>
 * <li>file package name</li>
 * <li>@author attribute with student name (not instructor)</li>
 * <li>Validates all the public methods have javadoc comments</li>
 * <li>Matches the required methods and signatures for the specified files</li>
 * </ul>
 * 
 * @author Scott LaChance
 *
 */
public class JUnitJavadocValidationUtility
{
	public JUnitJavadocValidationUtility(String testeNam,
			List<FileTestData> testFiles)
	{
		m_testeName = testeNam;
		m_testFiles = testFiles;
		m_stream = System.out; // Standard out
		m_testResult = new JavadocTestResults();
	}

	/**
	 * Name provided for this test
	 * 
	 * @return
	 */
	public String getTestName()
	{
		return m_testeName;
	}
	
	public void allowMainMethod()
	{
		m_allowMain = true;
	}
	
	/**
	 * True disables parser traces, false displays parser tracing
	 * @param suppress true to suppress, otherwise false;
	 */
	public void suppressParserTrace(boolean suppress)
	{
		m_suppressParserTrace = suppress;
	}

	/**
	 * Execute the test
	 * 
	 * @return
	 */
	public TestResult runTest()
	{
		trace("Running " + getTestName());
		m_testResult.setPassed();
		boolean result = true;
		for(FileTestData testFile : m_testFiles)
		{
			trace(" -- Running test: " + testFile.getFileName());
			try
			{
				if(!validateJavadoc(testFile))
				{
					result = false;// && validateJavadoc(testFile);
				}
			}
			catch(Exception ex)
			{
				trace("******** Error processing " + testFile.getFileName() + " ********");
				trace(ex.getMessage());
				trace("*****************************************************");
				result = false;
			}
		}

		m_testResult.setResult(result);
		return m_testResult;
	}

	/**
	 * Set the list of expected method results.
	 * 
	 * @param list
	 */
	public void setTestFiles(List<FileTestData> list)
	{
		m_testFiles = list;
	}

	/**
	 * Validates @author has been inserted into code and no void main is
	 * contained in the file
	 * 
	 * @return true if code is properly implemented
	 */
	private boolean validateJavadoc(FileTestData file)
	{
		// trace(" -- validateCode --");
		boolean result = true;

		String codeFilePath = "";
		try
		{
			codeFilePath = initFilePath(file);
			trace("  -- codeFilePath: " + codeFilePath);

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
					JavadocTestResultDetail detail = new JavadocTestResultDetail(
							"Getting source file");
					detail.addResultDetail("   ** Source file doesn't exist at "
							+ codeFilePath);
					m_testResult.addTestResultDetail(detail);
					result = false;
				}
				else
				{
					// evaluate file
					trace("Reading file " + srcFile);

					trace("parsing ... ");
					JavaParser parser = new JavaParser();
					parser.suppressTrace(m_suppressParserTrace);
					FileToken fileToken = parser.parse(srcFile);

					trace("evaluating ... ");
					try
					{
						result = evaluateFileToken(fileToken, file);
					}
					catch(Exception ex)
					{
						trace(" -- error evaluating: " + ex.getMessage());
						result = false;
					}
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
	private String initFilePath(FileTestData file) throws IOException
	{
		String filePath = "";
		String fileName = file.getFileName();
		String packageName = file.getPackageName();
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
					+ packageName + File.separator + fileName;
		}

		return filePath;
	}

	/**
	 * Searches the file list to find the src folder. Used for both assignment
	 * evaluation and Eclipse JUnit processing
	 * 
	 * @param fileList
	 *            File array to examine
	 * @return the src File reference if found, otherwise null
	 */
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

	private boolean evaluateFileToken(FileToken fileToken, FileTestData file)
	{
		boolean fileNameMatch = verifyFileNameMatch(fileToken, file);
		boolean fileNameClassNameMatch = verifyFileNameClassNameMatch(
				fileToken);
		boolean hasClassComment = verifyClassComment(
				fileToken.getClassToken().getComment(), fileToken);
		boolean correctPackage = verifyPackage(fileToken, file);
		boolean validAuthorTag = verifyAuthorTag(fileToken);
		boolean fileNoMain = verifyMain(fileToken);
		boolean methodsMatch = verifyMethods(fileToken, file);
		;
		boolean validConstructors = verifyConstructors(fileToken, file);

		return fileNameMatch && fileNoMain && fileNameClassNameMatch
				&& hasClassComment && methodsMatch && correctPackage
				&& validConstructors && validAuthorTag;
	}

	private boolean verifyFileNameMatch(FileToken fileToken, FileTestData file)
	{
		trace("  -- filename verification");
		String expectedFileName = file.getFileName();
		String actualFileName = fileToken.getFileName();

		boolean fileNameMatch = expectedFileName.equals(actualFileName);
		if(!fileNameMatch)
		{
			// no name match
			String msg = String.format(
					"    -- File name mismatch - expected: %s, actual: %s",
					expectedFileName, actualFileName);
			updateResultStatus("file name validation", msg);
		}

		return fileNameMatch;
	}

	private boolean verifyMain(FileToken fileToken)
	{
		trace("  -- void main verification");
		boolean fileNoMain = true;
		if(fileToken.hasMain() && !m_allowMain)
		{
			String msg = "    -- " + fileToken.getFileName() + ": " + INVALID_MAIN + CRLF;
			updateResultStatus("file validation", msg);
			fileNoMain = false;
		}
		return fileNoMain;
	}

	private boolean verifyAuthorTag(FileToken fileToken)
	{
		trace("  -- @author verification");
		boolean hasAuthor = true;
		if(!fileToken.hasAuthor())
		{
			String msg = "    -- " + MISSING_AUTHOR_TAG + CRLF;
			updateResultStatus("class comment validation", msg);
			hasAuthor = false;
		}

		return hasAuthor;
	}

	private boolean verifyPackage(FileToken fileToken,
			FileTestData fileTestData)
	{
		trace("  -- package verification");
		String expectedPackageName = fileTestData.getPackageName();
		String actualPackageName = fileToken.getPackageName();
		boolean correctPackage = expectedPackageName.equals(actualPackageName);
		if(!correctPackage)
		{
			String msg = String.format(
					"    -- Package mismatch - expected: %s, actual: %s",
					expectedPackageName, actualPackageName) + CRLF;
			updateResultStatus("package validation", msg);
		}

		return correctPackage;
	}

	private boolean verifyMethods(FileToken fileToken,
			FileTestData fileTestData)
	{
		boolean methodsMatch = true;
		trace("  -- method verification");

		ClassToken classToken = fileToken.getClassToken();
		List<MethodTestData> expectedMethodsList = fileTestData
				.getExpectedMethods();

		// verify the parsed methods match the expected methods
		for(MethodTestData expectedMethod : expectedMethodsList)
		{
			// find the corresponding method
			MethodToken methodToken = classToken.findMethodTokenByName(
					expectedMethod.getMethodName(),
					expectedMethod.getParameterCount());

			if(methodToken != null)
			{
				// String methodName = methodToken.getName();
				boolean nameMatch = true;
				boolean visbilityMatch = methodToken.getVisibility()
						.equals(expectedMethod.getVisibility());
				boolean returnTypeMatch = methodToken.getReturnType()
						.equals(expectedMethod.getReturnType());
				boolean validComment = verifyMethodComment(methodToken);
				boolean parameterCountMatch = methodToken
						.getParameterCount() == expectedMethod
								.getParameterCount();

				boolean currentMethod = nameMatch && visbilityMatch
						&& returnTypeMatch && validComment
						&& parameterCountMatch;
				methodsMatch = methodsMatch && currentMethod;
				if(!currentMethod)
				{
					StringBuilder msg = new StringBuilder();
					if(!visbilityMatch)
					{
						msg.append(String.format(
								"    -- %s: Visibility mismatch - expected: %s, actual: %s",
								expectedMethod.getMethodName(),
								expectedMethod.getVisibility(),
								methodToken.getVisibility()));
						msg.append(CRLF);

						methodsMatch = false;
					}

					if(!returnTypeMatch)
					{
						msg.append(String.format(
								"    -- %s: Return type mismatch - expected: %s, actual: %s",
								expectedMethod.getMethodName(),
								expectedMethod.getReturnType(),
								methodToken.getReturnType()));
						msg.append(CRLF);

						methodsMatch = false;
					}

					if(!parameterCountMatch)
					{
						msg.append(String.format(
								"    -- %s: Parameter count mismatch - expected: %d, actual: %d",
								expectedMethod.getMethodName(),
								expectedMethod.getParameterCount(),
								methodToken.getParameterCount()));
						msg.append(CRLF);

						methodsMatch = false;
					}

					updateResultStatus("method validation", msg.toString());
				}
			}
			else
			{
				// no name match
				String msg = String.format("    -- ** Missing method - %s **",
						expectedMethod.getMethodName());
				updateResultStatus("method validation", msg);
				methodsMatch = false;
			}
		}

		return methodsMatch;
	}

	private boolean verifyConstructors(FileToken fileToken,
			FileTestData fileTestData)
	{
		boolean isValid = true;
		StringBuilder msg = new StringBuilder();
		try
		{
			trace("  -- constructor verification");
			List<ConstructorTestData> expectedConstructorsList = fileTestData
					.getExpectedConstructors();
			ClassToken classToken = fileToken.getClassToken();

			for(ConstructorTestData expectedConstructor : expectedConstructorsList)
			{
				String name = expectedConstructor.getName();
				int paramCount = expectedConstructor.getParameterCount();

				ConstructorToken constructorToken = classToken
						.findConstructorTokenByName(name, paramCount);

				if(constructorToken != null)
				{
					// String methodName = methodToken.getName();
					boolean nameMatch = true;

					isValid = isValid && nameMatch;

					boolean visbilityMatch = constructorToken.getVisibility()
							.equals(expectedConstructor.getVisibility());

					if(!visbilityMatch)
					{
						msg.append(String.format(
								"    -- %s - Visibility mismatch - expected: %s, actual: %s\n",
								expectedConstructor.getName(),
								expectedConstructor.getVisibility(),
								constructorToken.getVisibility()));
						updateResultStatus("constructor validation",
								msg.toString());
					}

					isValid = isValid && visbilityMatch;

					// TODOD
					boolean validComment = verifyMethodComment(
							constructorToken);

					isValid = isValid && validComment;
				}
				else
				{
					// expected constructor not found
					msg.append(String.format(
							"    -- expected constructor - name: %s, param count: %d\n",
							name, paramCount));
					updateResultStatus("constructor validation",
							msg.toString());
					isValid = false;
				}
			}
		}
		catch(Exception ex)
		{

		}

		return isValid;
	}

	private void updateResultStatus(String description, String msg)
	{

		JavadocTestResultDetail detail = new JavadocTestResultDetail(
				description);
		detail.addResultDetail(msg);
		m_testResult.addTestResultDetail(detail);
	}

	private boolean verifyFileNameClassNameMatch(FileToken fileToken)
	{
		trace("  -- Classname verification");
		String className = fileToken.getClassToken().getClassName();
		String fileName = fileToken.getFileName();
		int index = fileName.indexOf(".");
		String fileNameOnly = fileName.substring(0, index);
		boolean match = className.equals(fileNameOnly);
		if(!match)
		{
			String msg = String.format(
					"    -- Class name / File name mismatch - expected %s",
					className);
			updateResultStatus("class name / file name validation", msg);
		}

		return match;
	}

	private boolean validateParamerters(FunctionToken token,
			List<ParameterToken> parameterList)
	{
		boolean validParams = true;
		StringBuilder msg = new StringBuilder();

		CommentToken comment = token.getComment();
		String javadoc = comment.getJavadoc();
		int expectedCount = parameterList.size();
		int actualCount = token.getParameterCount();
		boolean parameterCountMatch = expectedCount == actualCount;

		if(!parameterCountMatch)
		{
			msg.append(String.format(
					"    -- Parameter count mismatch - expected: %d, actual: %d",
					expectedCount, actualCount));
			msg.append(CRLF);
		}

		for(ParameterToken param : parameterList)
		{
			String paramName = param.getName();
			String lookFor = "@param " + paramName;
			// trace(token.getName() + " param type: " + param.getType() + ",
			// param name: " + param.getName());
			boolean foundParam = javadoc.contains(lookFor);
			if(!foundParam)
			{
				String errMsg = String.format(
						"Expected @param missing from %s: param %s",
						token.getName(), paramName);
				msg.append(errMsg);
				updateResultStatus("constructor parameter validation", errMsg);

			}
			validParams = validParams && foundParam;
		}

		return validParams;
	}

	private boolean validateReturnType(MethodToken token)
	{
		boolean validReturn = true;

		String returnType = token.getReturnType();
		CommentToken comment = token.getComment();
		String javadoc = comment.getJavadoc();

		if(!returnType.equals("void"))
		{
			String lookFor = "@return";
			boolean foundReturn = javadoc.contains(lookFor);
			if(!foundReturn)
			{
				String msg = String.format(
						"   -- ** Expected @return missing from method %s **",
						token.getName());
				updateResultStatus("method param validation", msg);
			}
			validReturn = validReturn && foundReturn;
		}

		return validReturn;
	}

	private boolean verifyMethodComment(FunctionToken token)
	{
		boolean hasCommentTest = false;
		boolean validParams = false;
		boolean validReturn = true;
		boolean validComment = false;

		// verify a comment with description exists and any parameters
		// have a @param and @return as appropriate
		CommentToken comment = token.getComment();
		if(comment != null)
		{
			validComment = validateComment(token.getName(), comment);
			String javadoc = comment.getJavadoc();
			List<ParameterToken> parameterList = token.getParameters();

			hasCommentTest = javadoc.length() > 0;
			validParams = validateParamerters(token, parameterList);

			if(token instanceof MethodToken)
			{
				MethodToken mToken = (MethodToken)token;
				validReturn = validateReturnType(mToken);
			}
		}
		else
		{
			String msg = "   -- missing method comment for " + token.getName();
			updateResultStatus("comment validation", msg);
		}

		return hasCommentTest && validParams && validReturn;
	}

	private boolean validateComment(String parentName, CommentToken comment)
	{
		boolean hasDescription = comment.getDescription().length() > 0 ? true : false;
		boolean validReturn = true;
		boolean validAuthor = true;
		
		for(CommentToken.ParameterInfo info : comment.getParameters() )
		{
			if(info.m_description.length() == 0)
			{
				String msg = "   -- ** " + parentName + ": parameter '" + info.m_name + "' has no description **";
				updateResultStatus("comment validation", msg);
			}
		}
		
		if(comment.hasReturn())
		{
			if(comment.getReturn().length() == 0)
			{
				String msg = "   -- ** " + parentName + ": @return has no description **";
				updateResultStatus("comment validation", msg);
				validReturn = false;
			}
		}
		
		if(comment.hasAuthor())
		{
			if(comment.getAuthor().length() == 0)
			{
				String msg = "   -- ** " + parentName + ": @author has no description **";
				updateResultStatus("comment validation", msg);
				validAuthor = false;
			}
		}
		
		return hasDescription && validReturn && validAuthor;
	}

	private boolean verifyClassComment(CommentToken comment,
			FileToken fileToken)
	{
		boolean hasClassComment = comment.getJavadoc().length() > 0;
		String javadoc = comment.getJavadoc();
		boolean hasAuthor = javadoc.indexOf("@author") > 0 ? true : false;
		fileToken.setAuthor(hasAuthor);
		return hasClassComment && hasAuthor;
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

	private JavadocTestResults m_testResult;

	// List of fully qualified file names to process
	private List<FileTestData> m_testFiles;

	// Required to build the fully qualified path the requested files
	private String m_testeName;

	protected PrintStream m_stream;
	private boolean m_allowMain = false; 
	private boolean m_suppressParserTrace = false;
	private static String CRLF = System.getProperty("line.separator");
	private static String MISSING_AUTHOR_TAG = " ** Code missing @author tag";
	private static String INVALID_MAIN = " ** Code has unexpected void main method";
}

//class ParserContext
//{
//	ParserContext(String fileName, int curPos, StringBuilder content)
//	{
//		m_fileName = fileName;
//		m_curPos = curPos;
//		m_content = content;
//	}
//
//	public String getFileName()
//	{
//		return m_fileName;
//	}
//
//	public int getCurPos()
//	{
//		return m_curPos;
//	}
//
//	/**
//	 * Update the current position as we move along
//	 * 
//	 * @param newPos
//	 *            new current position
//	 */
//	public void setCurPosition(int newPos)
//	{
//		m_curPos = newPos;
//	}
//
//	public StringBuilder getContent()
//	{
//		return m_content;
//	}
//
//	String m_fileName;
//	StringBuilder m_content;
//	int m_curPos = 0; // analysis character position
//}


