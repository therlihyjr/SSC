package week08;

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
		methods1.add(new MethodTestData("clear",0,"void","public"));
		methods1.add(new MethodTestData("clone",0,"Object","public"));
		methods1.add(new MethodTestData("createNewNode",1,"TreeNode<E>","protected"));
		methods1.add(new MethodTestData("delete",1,"boolean","public"));
		methods1.add(new MethodTestData("getRoot",0,"TreeNode<E>","public"));
		methods1.add(new MethodTestData("getSize",0,"int","public"));
		methods1.add(new MethodTestData("inorder",0,"void","public"));
		methods1.add(new MethodTestData("inorder",1,"void","protected"));
		methods1.add(new MethodTestData("insert",1,"boolean","public"));
		methods1.add(new MethodTestData("iterator",0,"Iterator<E>","public"));
		methods1.add(new MethodTestData("path",1,"ArrayList<TreeNode<E>>","public"));
		methods1.add(new MethodTestData("postorder",0,"void","public"));
		methods1.add(new MethodTestData("postorder",1,"void","protected"));
		methods1.add(new MethodTestData("preorder",0,"void","public"));
		methods1.add(new MethodTestData("preorder",1,"void","protected"));
		methods1.add(new MethodTestData("search",1,"boolean","public"));
		methods1.add(new MethodTestData("getLeafData",0,"List<E>","public"));
		
		List<ConstructorTestData> constructors1 = new ArrayList<ConstructorTestData>();
		constructors1.add(new ConstructorTestData("BST", 0, "public"));
		constructors1.add(new ConstructorTestData("BST", 1, "public"));

		testFiles.add(new FileTestData("week08", "BST.java", methods1,
				constructors1));

		m_validator = new JUnitJavadocValidationUtility("Week08 Javadoc Test",
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
