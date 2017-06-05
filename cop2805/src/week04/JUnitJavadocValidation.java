package week04;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.javadoc.JUnitJavadocValidationUtility;
import test.javadoc.MethodTestData;
import test.TestResult;
import test.TestResultDetail;
import test.javadoc.FileTestData;

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

		List<FileTestData> testFiles = new ArrayList<FileTestData>();

		List<MethodTestData> methods = new ArrayList<MethodTestData>();

		// Employee.java
		methods.add(new MethodTestData("countWordOccurrences", 1,
				"List<WordCountResult>", "public"));
		testFiles.add(new FileTestData("week04", "CountUtility.java", methods));

		// DataLayer.java
		List<MethodTestData> dlMethods = new ArrayList<MethodTestData>();
		dlMethods
				.add(new MethodTestData("incrementCount", 0, "void", "public"));
		dlMethods.add(new MethodTestData("getCount", 0, "int", "public"));
		dlMethods.add(new MethodTestData("getWord", 0, "String", "public"));
		dlMethods.add(new MethodTestData("setCount", 1, "void", "public"));
		dlMethods.add(new MethodTestData("setWord", 1, "void", "public"));
		dlMethods.add(new MethodTestData("toString", 0, "String", "public"));
		testFiles.add(
				new FileTestData("week04", "WordCountResult.java", dlMethods));

		m_validator = new JUnitJavadocValidationUtility("Week04 Javadoc Test",
				testFiles);
	}

	@Test
	public void testJavadoc()
	{
		trace("** Validating Javadoc **");

		// Update these values for each assignment
		// m_packageName = "week02";
		TestResult result = m_validator.runTest();
		StringBuilder details = new StringBuilder();
		if(!result.passed())
		{
			List<TestResultDetail> detailList = result.getTestResultDetails();
			for(TestResultDetail detail : detailList)
			{
				trace(detail.testDetails());
				details.append(detail.testDetails());
				details.append(CRLF);
			}
		}
		trace(String.format("** Test result: %s **",
				result.passed() ? "Passed" : "Failed"));
		assertTrue(details.toString(), result.passed());
	}

	/**
	 * Trace the msg to a PrintStream Provides the method for tests to report
	 * status
	 * 
	 * @param msg
	 */
	protected void trace(String msg)
	{
		m_stream.println(msg);
	}

	private JUnitJavadocValidationUtility m_validator;

	protected PrintStream m_stream;
	// private String m_packageName;
	// private ArrayList<MethodData> m_methods;
	// private static String ONEPARAM = "\\w* \\w*\\(\\w* \\w*\\)";
	private static String CRLF = System.getProperty("line.separator");
}
