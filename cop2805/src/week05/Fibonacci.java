package week05;
/**
 * @author Tim Herlihy Jr.
 */
public class Fibonacci
{

	/**
	 * Calculates the Fibonacci number at the specified index
	 * This is not a static method
	 * @param index
	 * @return fib number
	 */
	public long fib(long index) {
		if (index == 0) // Base case
		return 0;
		else if (index == 1) // Base case
		return 1;
		else // Reduction and recursive calls
		return fib(index - 1) + fib(index - 2);
		}
}
