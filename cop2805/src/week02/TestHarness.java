package week02;

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
			boolean javadocTest = executeTest(JUnitJavadocValidation.class);
			boolean genericTest = executeTest(JUnitIteratorTest.class);
			boolean result = javadocTest && genericTest;

			trace(result ? "Tests Passed" : "Tests Failed");
		}
		catch(Exception ex)
		{
			trace(ex.getMessage());
		}
	}
	
	private boolean executeTest(Class c)
	{
		trace("executing " + c.getName());
		boolean success = true;
		Result result = org.junit.runner.JUnitCore
				.runClasses(c);
		int failCount = result.getFailureCount();
		if(failCount > 0)
		{
			List<Failure> failures = result.getFailures();
			for(Failure fail : failures)
			{
				trace("FAILED: " + fail.getMessage());
				success = false;
			}
		}

		return success;			
	}

	static private void trace(String msg)
	{
		System.out.println(msg);
	}
	


}
