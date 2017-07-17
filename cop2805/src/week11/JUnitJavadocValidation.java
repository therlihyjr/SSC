package week11;

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
 * @author Scott LaChance
 */
public class JUnitJavadocValidation
{
	public JUnitJavadocValidation()
	{
		m_stream = System.out; // Standard out

		// setup files to analyze (constructors/methods)
		
		List<FileTestData> testFiles = new ArrayList<FileTestData>();
		
		// BST.java
		List<MethodTestData> methods1 = new ArrayList<MethodTestData>();
		
		List<ConstructorTestData> constructors1 = new ArrayList<ConstructorTestData>();
		constructors1.add(new ConstructorTestData("BackgroundVowelCounter", 2, "public"));

		testFiles.add(new FileTestData("week11", "BackgroundVowelCounter.java", methods1,
				constructors1));

		List<MethodTestData> methods2 = new ArrayList<MethodTestData>();
		methods2.add(new MethodTestData("accept",1,"boolean","public"));
		methods2.add(new MethodTestData("getDescription",0,"String","public"));
		
		List<ConstructorTestData> constructors2 = new ArrayList<ConstructorTestData>();
		constructors2.add(new ConstructorTestData("VowelFileFilter", 0, "public"));

		testFiles.add(new FileTestData("week11", "VowelFileFilter.java", methods2,
				constructors2));
		
		m_validator = new JUnitJavadocValidationUtility("Week11 Javadoc Test",
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
