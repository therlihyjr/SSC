package week03;

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

		List<FileTestData> testFiles = new ArrayList<FileTestData>();

		List<MethodTestData> methods = new ArrayList<MethodTestData>();

		// Employee.java
		methods.add(new MethodTestData("equals", 1, "boolean", "public"));
		methods.add(
				new MethodTestData("getDisplayName", 0, "String", "public"));
		methods.add(new MethodTestData("getFirstName", 0, "String", "public"));
		methods.add(new MethodTestData("getFormattedSalary", 0, "String", "public"));
		methods.add(new MethodTestData("getLastName", 0, "String", "public"));
		methods.add(new MethodTestData("getSalary", 0, "double", "public"));
		methods.add(new MethodTestData("setFirstName", 1, "void", "public"));
		methods.add(new MethodTestData("setLastName", 1, "void", "public"));
		methods.add(new MethodTestData("setSalary", 1, "void", "public"));
		methods.add(new MethodTestData("toString", 0, "String", "public"));
		
		List<ConstructorTestData> constructors = new ArrayList<ConstructorTestData>();
		constructors.add(new ConstructorTestData("Employee", 0, "public"));
		constructors.add(new ConstructorTestData("Employee", 2, "public"));
		constructors.add(new ConstructorTestData("Employee", 3, "public"));

		testFiles.add(new FileTestData("week03", "Employee.java", methods, constructors));
		
		methods = new ArrayList<MethodTestData>();

		// DataLayer.java
		List<MethodTestData> dlMethods = new ArrayList<MethodTestData>();
		dlMethods.add(new MethodTestData("addEmployee", 1, "void", "public"));
		dlMethods.add(new MethodTestData("deleteEmployee", 1, "boolean", "public"));
		dlMethods.add(new MethodTestData("getEmployeeByName", 1, "Employee", "public"));
		dlMethods.add(new MethodTestData("getEmployees", 0, "List<Employee>", "public"));
		dlMethods.add(new MethodTestData("getSize", 0, "int", "public"));
		
		List<ConstructorTestData> dlConstructors = new ArrayList<ConstructorTestData>();
		dlConstructors.add(new ConstructorTestData("DataLayer", 1, "public"));
		testFiles.add(new FileTestData("week03", "DataLayer.java", dlMethods, dlConstructors));

		// CollectionBusinessLayer.java
		List<MethodTestData> blMethods = new ArrayList<MethodTestData>();
		blMethods.add(new MethodTestData("addEmployee", 3, "Employee", "public"));
		blMethods.add(new MethodTestData("deleteEmployee", 1, "Employee", "public"));
		blMethods.add(new MethodTestData("changeEmployeeSalary", 2, "boolean", "public"));
		blMethods.add(new MethodTestData("listAllEmployees", 0, "List<Employee>", "public"));
		blMethods.add(new MethodTestData("listEmployee", 1, "Employee", "public"));
		
		List<ConstructorTestData> blConstructors = new ArrayList<ConstructorTestData>();
		blConstructors.add(new ConstructorTestData("CollectionBusinessLayer", 0, "public"));
		blConstructors.add(new ConstructorTestData("CollectionBusinessLayer", 1, "public"));
		testFiles.add(new FileTestData("week03", "CollectionBusinessLayer.java", blMethods, blConstructors));


		m_validator = new JUnitJavadocValidationUtility("Week03 Javadoc Test",
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
		trace(String.format("** Test result: %s **", result.passed() ? "Passed" : "Failed"));
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
	//private String m_packageName;
	//private ArrayList<MethodData> m_methods;
	//private static String ONEPARAM = "\\w* \\w*\\(\\w* \\w*\\)";
	private static String CRLF = System.getProperty("line.separator");
}
