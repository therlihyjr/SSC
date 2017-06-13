package week06;

/**
 * 
 * @author Tim Herlihy Jr. 
 */

public class InsertionSort {

	/**
	 * Straight from the book.
	 * @param list
	 */
	public InsertionSort(int[] list)
	{
		for (int i = 1; i < list.length; i++) 
		{
			int currentElement = list[i];
			int k;
			for (k = i - 1; k >= 0 && list[k] > currentElement; k--)
			{
				list[k+1] = list[k];
			}
			
			list [k+1] = currentElement;
		}
	}
	
	public void sort()
	{
		
	}
}
