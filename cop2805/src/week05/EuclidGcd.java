package week05;

/**
 * @author Tim Herlihy Jr.
 */

public class EuclidGcd
{

	/**
	 * Constructor
	 * There are no class attributes to initialize
	 * @param m; first number to search
	 * @param n; second number to search
	 */
	public EuclidGcd() {}

	/**
	 * Implements the GCD algorithm. this is a private method that the start method calls in the reference implementation. Consider it a hint.	
	 * Based on section 22.6. This method is not static and if you make it so it will result in a lower evaluation.
	 * @param m; first number to search
	 * @param n; second number to search
	 * @return gcd of m and n
	 */
	private long getEuclidGcdBreak(long m, long n)
	{
		long gcd = 1;
		if (m % n == 0) return n;
		
		for (long k = n / 2; k >= 1; k--)
		{
			if (m % k == 0 && n % k == 0)
			{
				gcd = k;
				break;
			}
		}
		return gcd;
	}
	
	/**
	 * The public method that is called by the TestHarness and other users	
	 * This is the actual method used by the TestHarness
	 * @param m
	 * @param n
	 * @return gcd from EuclidGcdBreak method 
	 */
	public long start(long m, long n)
	{
		if (m % n == 0) return n;
		else
		return getEuclidGcdBreak(n, m % n);
	}
	
}
