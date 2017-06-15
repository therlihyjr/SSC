package week06;

/**
 * 
 * @author Tim Herlihy Jr. 
 */

public class InsertionSort extends AbstractSort 
{
	
	/**
	 * Class Constructor to Abstract
	 * @param list  primary list of integers
	 */
	public InsertionSort(int[] list) {
		super("InsertionSort", list);
	}
	
	/**
	 * Method to be called by abstract class for sorting
	 */
	public void sort()
	{
		{
			for (int i = 0; i < getList().length; i++) 
			{
			int[] currentList = getList();	
			int currentElement = currentList[i];
			int k;
				for (k = i - 1; k >= 0 && currentList[k] > currentElement; k--)
				{
					currentList[k+1] = currentList[k];
				}
			currentList [k + 1] = currentElement;
			}
		}
	}
}	

