package week02;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitIteratorTest
{

	@Test
	public void test1000()
	{
		trace("Testing test1000");
		IteratorTest test = new IteratorTest(1000);
		executeTest(test);
	}
	
	@Test
	public void test100000()
	{
		trace("Testing test100000");
		IteratorTest test = new IteratorTest(100000);
		executeTest(test);
	}
	
	@Test
	public void test500000()
	{
		trace("Testing test500000");
		IteratorTest test = new IteratorTest(500000);
		executeTest(test);
	}
	
	@Test
	public void test50000()
	{
		trace("Testing test50000");
		IteratorTest test = new IteratorTest(50000);
		executeTest(test);
	}

	private void executeTest(IteratorTest test)
	{
		trace(" -- initialized");
		test.execute();
		
		String msg = String.format("List size: %d, Iterator: %d ms, get(index): %d ms", 
				test.getListSize(),
				test.getIteratorElapsedTimeInMilliseconds(),
				test.getElapsedTimeInMilliseconds());
		
		trace(msg);
	}

	/**
	 * Trace the msg to a PrintStream Provides the method for tests to report
	 * status
	 * 
	 * @param msg
	 */
	protected void trace(String msg)
	{
		System.out.println("    " + msg);
	}
}
