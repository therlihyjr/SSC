package week05;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.javadoc.JUnitJavadocValidationUtility;
import test.javadoc.MethodTestData;
import test.TestResult;
import test.TestResultDetail;
import test.javadoc.ConstructorTestData;
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

		// setup files to analyze (constructors/methods)
		List<FileTestData> testFiles = new ArrayList<FileTestData>();
		List<MethodTestData> gcdMethods = new ArrayList<MethodTestData>();

		// EuclidGcd.java
		gcdMethods.add(new MethodTestData("start", 2, "long", "public"));

		List<ConstructorTestData> constructors = new ArrayList<ConstructorTestData>();
		constructors.add(new ConstructorTestData("EuclidGcd", 0, "public"));

		testFiles.add(new FileTestData("week05", "EuclidGcd.java", gcdMethods,
				constructors));

		// Fibonacci.java
		List<MethodTestData> fibMethods = new ArrayList<MethodTestData>();
		fibMethods.add(new MethodTestData("fib", 1, "long", "public"));

		testFiles.add(new FileTestData("week05", "Fibonacci.java", fibMethods));

		m_validator = new JUnitJavadocValidationUtility("Week05 Javadoc Test",
				testFiles);
		m_validator.suppressParserTrace(true);
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
	private static String CRLF = System.getProperty("line.separator");
}
