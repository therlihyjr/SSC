package week06;

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
		
		// InsertionSort.java
		List<MethodTestData> insertionMethods = new ArrayList<MethodTestData>();
		insertionMethods.add(new MethodTestData("sort",0,"void","public"));
		
		List<ConstructorTestData> constructors = new ArrayList<ConstructorTestData>();
		constructors.add(new ConstructorTestData("InsertionSort", 1, "public"));

		testFiles.add(new FileTestData("week06", "InsertionSort.java", insertionMethods,
				constructors));

		// MergeSort.java
		List<MethodTestData> mergeMethods = new ArrayList<MethodTestData>();
		mergeMethods.add(new MethodTestData("sort",0,"void","public"));
		
		List<ConstructorTestData> mergeConstructors = new ArrayList<ConstructorTestData>();
		mergeConstructors.add(new ConstructorTestData("MergeSort", 1, "public"));

		testFiles.add(new FileTestData("week06", "MergeSort.java", mergeMethods, mergeConstructors));

		// SelectionSort.java
		List<MethodTestData> selectionMethods = new ArrayList<MethodTestData>();
		selectionMethods.add(new MethodTestData("sort",0,"void","public"));
		
		List<ConstructorTestData> selectionConstructors = new ArrayList<ConstructorTestData>();
		selectionConstructors.add(new ConstructorTestData("SelectionSort", 1, "public"));

		testFiles.add(new FileTestData("week06", "SelectionSort.java", selectionMethods, selectionConstructors));

		m_validator = new JUnitJavadocValidationUtility("Week06 Javadoc Test",
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
