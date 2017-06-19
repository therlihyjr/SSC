package week07;

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
		
		// MyAbstractList.java
		List<MethodTestData> methods1 = new ArrayList<MethodTestData>();
		methods1.add(new MethodTestData("add",1,"void","public"));
		methods1.add(new MethodTestData("isEmpty",0,"boolean","public"));
		methods1.add(new MethodTestData("size",0,"int","public"));
		
		List<ConstructorTestData> constructors1 = new ArrayList<ConstructorTestData>();
		constructors1.add(new ConstructorTestData("MyAbstractList", 0, "protected"));
		constructors1.add(new ConstructorTestData("MyAbstractList", 1, "protected"));

		testFiles.add(new FileTestData("week07", "MyAbstractList.java", methods1,
				constructors1));

		// MyList.java - interface
		List<MethodTestData> myListMethods = new ArrayList<MethodTestData>();
		myListMethods.add(new MethodTestData("add",1,"void","public"));
		myListMethods.add(new MethodTestData("add",2,"void","public"));
		myListMethods.add(new MethodTestData("clear",0,"void","public"));
		myListMethods.add(new MethodTestData("contains",1,"boolean","public"));
		myListMethods.add(new MethodTestData("get",1,"E","public"));
		myListMethods.add(new MethodTestData("indexOf",1,"int","public"));
		myListMethods.add(new MethodTestData("isEmpty",0,"boolean","public"));
		myListMethods.add(new MethodTestData("lastIndexOf",1,"int","public"));
		myListMethods.add(new MethodTestData("remove",1,"E","public"));
		myListMethods.add(new MethodTestData("set",2,"E","public"));
		myListMethods.add(new MethodTestData("size",0,"int","public"));
		
		List<ConstructorTestData> myListConstructors = new ArrayList<ConstructorTestData>();
		testFiles.add(new FileTestData("week07", "MyList.java", myListMethods,
				myListConstructors));
		
		// MyArrayList.java - interface
		List<MethodTestData> myArrayListMethods = new ArrayList<MethodTestData>();		
		myArrayListMethods.add(new MethodTestData("add",2,"void","public"));
		myArrayListMethods.add(new MethodTestData("clear",0,"void","public"));
		myArrayListMethods.add(new MethodTestData("contains",1,"boolean","public"));
		myArrayListMethods.add(new MethodTestData("get",1,"E","public"));
		myArrayListMethods.add(new MethodTestData("indexOf",1,"int","public"));
		myArrayListMethods.add(new MethodTestData("iterator",0,"Iterator<E>","public"));
		myArrayListMethods.add(new MethodTestData("lastIndexOf",1,"int","public"));
		myArrayListMethods.add(new MethodTestData("remove",1,"E","public"));
		myArrayListMethods.add(new MethodTestData("set",2,"E","public"));
		myArrayListMethods.add(new MethodTestData("toString",0,"String","public"));
		myArrayListMethods.add(new MethodTestData("trimToSize",0,"void","public"));
		
		List<ConstructorTestData> myArrayListConstructors = new ArrayList<ConstructorTestData>();
		myArrayListConstructors.add(new ConstructorTestData("MyArrayList", 0, "public"));
		myArrayListConstructors.add(new ConstructorTestData("MyArrayList", 1, "public"));
		testFiles.add(new FileTestData("week07", "MyArrayList.java", myArrayListMethods,
				myArrayListConstructors));
		
		m_validator = new JUnitJavadocValidationUtility("Week07 Javadoc Test",
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
