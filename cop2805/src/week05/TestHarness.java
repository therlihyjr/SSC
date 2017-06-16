package week05;

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
			boolean fibTest = testFibonacci();
			boolean gcdTest = testGcd();
			boolean result = fibTest && gcdTest;

			trace(result ? "Tests Passed" : "Tests Failed");
		}
		catch(Exception ex)
		{
			trace(ex.getMessage());
		}
	}
	
	private boolean testGcd()
	{

		trace(" -- testGcd --");
		boolean success = true;
		Result result = org.junit.runner.JUnitCore
				.runClasses(JUnitEuclidGcdTest.class);
		int failCount = result.getFailureCount();
		if(failCount > 0)
		{
			List<Failure> failures = result.getFailures();
			for(Failure fail : failures)
			{
				String msg = 
					String.format("FAILED: %s - %s", 
						fail.getDescription().getDisplayName(), fail.getMessage());				
				trace(msg);
				success = false;
			}
		}

		return success;
	}

	private boolean testFibonacci()
	{
		trace(" -- testFibonacci --");
		boolean success = true;
		Result result = org.junit.runner.JUnitCore
				.runClasses(JUnitFibonacciTest.class);
		int failCount = result.getFailureCount();
		if(failCount > 0)
		{
			List<Failure> failures = result.getFailures();
			for(Failure fail : failures)
			{
				String msg = 
					String.format("FAILED: %s - %s", 
						fail.getDescription().getDisplayName(), fail.getMessage());				
				trace(msg);
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
