package week05;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitFibonacciTest
{

	@Test
	public void test()
	{
		Fibonacci fib = new Fibonacci();
		for(FibonacciData test : data)
		{
			long d = fib.fib(test.getIndex());
			trace(String.format("index: %d, actual: %d, expected: %d", test.getIndex(), d, test.getExpected()));
			assertEquals(String.format("Failed at index %d, actual: %d, expected: %d", test.getIndex(), d, test.getExpected()), 
					d, test.getExpected());
		}
		
		long term = fib.fib(5);
	}

	private void trace(String msg)
	{
		System.out.println(msg);
	}
	
	FibonacciData[] data = new FibonacciData[]
			{
			    new FibonacciData(1,1),
			    new FibonacciData(2,1),
			    new FibonacciData(3,2),
			    new FibonacciData(5,5),
			    new FibonacciData(6,8),
			    new FibonacciData(8,21),
			    new FibonacciData(10,55),
			    new FibonacciData(12,144),
			    new FibonacciData(14,377),
			    new FibonacciData(16,987),
			    new FibonacciData(20,6765),
			    new FibonacciData(22,17711),
			};
	
}

class FibonacciData
{
	FibonacciData(long index, long expected)
	{
		m_index = index;
		m_expected = expected;
	}
	
	long getIndex(){return m_index; }
	long getExpected(){return m_expected; }
	
	private long m_index;
	private long m_expected;
}
