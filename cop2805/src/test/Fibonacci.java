package week05;
/** Description of Fibonacci class
 * This class implements the algorithm
 * @author Chris Hurst
 **/
public class Fibonacci
{
	/**
	 * fib method
	 * @param index
	 * @return 0, 1, fib(index - 1) + fib(index - 2)
	 */
public long fib(long index)
{
	if (index ==0) //base case
		return 0;
	else if (index ==1) //base case
		return 1;
	else // reduction and recursive calls
		return fib(index - 1) + fib(index - 2);
}

	
}// end of Fibonacci class
