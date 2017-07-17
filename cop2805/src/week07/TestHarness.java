package week07;

import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestHarness
{
	public static void main(String[] args)
	{
		new TestHarness().runTests();

	}
	
	private void runTests()
	{
		try
		{
			trace("Running tests version 2");
			boolean sortTest = executeTest(JUnitMyArrayTest.class);
			boolean javadocTest = executeTest(JUnitJavadocValidation.class);
			boolean result = sortTest && javadocTest;

			trace(result ? "Tests Passed" : "Tests Failed");
		}
		catch(Exception ex)
		{
			trace(ex.getMessage());
		}
	}
	
	private boolean executeTest(@SuppressWarnings("rawtypes") Class c)
	{
		//trace("");
		trace("===============================================");
		trace(" -- executing " + c.getName());
		trace("===============================================");
		trace("");
		boolean success = true;
		Result result = org.junit.runner.JUnitCore
				.runClasses(c);
		int failCount = result.getFailureCount();
		if(failCount > 0)
		{
			List<Failure> failures = result.getFailures();
			for(Failure fail : failures)
			{
				trace("FAILED: " + fail.getTestHeader() + " - " + fail.getMessage());
				success = false;
			}
		}
		
		trace("-----------------------------------------------");
		trace(" -- " + (success ? "Success" : "Failed"));
		trace("===============================================");
		trace("");
		return success;			
	}
	
	static private void trace(String msg)
	{
		System.out.println(msg);
	}

}
