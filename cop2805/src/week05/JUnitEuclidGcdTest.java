package week05;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class JUnitEuclidGcdTest
{

	@Test
	public void testEuclidGcd()
	{
		int run = 1;
		boolean result = true;
		
		for(TestData test : m_testData)
		{
			trace("test run " + run++);
			try
			{
				EuclidGcd euclid = new EuclidGcd();
				
				trace(" - executing Euclide GCD calculation");
				long startTime = System.nanoTime();
				
				long actual = euclid.start(test.getM(), test.getN());
				long endTime = System.nanoTime();
				long elapsed = endTime - startTime;
				long sec = TimeUnit.NANOSECONDS.toSeconds(elapsed);
				long ms = TimeUnit.NANOSECONDS.toMillis(elapsed);
				
				//System.
				trace("M: " + test.getM() +", N: " + test.getN() + " GCD: " + actual + " Elapsed time (ns): " + elapsed);			
				trace(String.format("Time: %d sec, %d ms", sec, ms));

				long expected = test.getExpected();
				if( actual != expected)
				{
					result = false;
					String msg = String.format("Actual %d, Expected: %d", actual, expected);
					trace(msg);
				}

				trace(" - Completed GCD calculation - " + result);
			}
			catch(ArithmeticException ex )
			{
				trace("Error processing");
				trace("Error for ");
				trace("M: " + test.getM());
				trace("N: " + test.getN());
				trace(" Exception: " + ex.getMessage());
				result = false;
			}
			catch(Exception ex)
			{
				trace("Error processing - Exception");
				trace(ex.getMessage());
			}
		}
		
		if(! result )
		{
			fail("Failed GCD calculation");
		}
	}

	private void trace(String msg)
	{
		System.out.println(msg);
	}
	
	private TestData[] m_testData = new TestData[] { 
			new TestData(4567820, 2147483640, 20),
			new TestData(545690876L, 3456901294L, 2),
			new TestData(546587619L, 21474836121L, 3),
			new TestData(951987545L, 21474836651L, 1),
			new TestData(1542354865L, 3216548445L, 5),
			new TestData(10,5, 5)
			};
}

class TestData
{
	public TestData(long m, long n, long expected)
	{
		m_M = m;
		m_N = n;
		m_expected = expected;
	}

	public long getM()
	{
		return m_M;
	}

	public long getN()
	{
		return m_N;
	}
	
	public long getExpected()
	{
		return m_expected;
	}

	private long m_M;
	private long m_N;
	private long m_expected;
}