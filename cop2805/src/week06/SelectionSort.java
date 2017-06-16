package week06;

/**
 * @author Tim Herlihy Jr. 
 */

public class SelectionSort extends AbstractSort {

	/**
	 * The subclass takes only an array of integers. It initializes the AbstractSort super class by providing a name of the algorithm, so two parameters are provided to the super class as required by the AbstractSort 
	 * This is an example of polymorphic behavior.
	 * @param list primary list of integers
	 */
	public SelectionSort(int[] list) {
		super("SelectionSort", list);
	}
	
	/**
	 * This implements the method defined in AbstractSort. The appropriate sort algorithm is implemented here
	 */
	public void sort()
	{
		int[] list = getList();
		
		for (int i = 0; i < list.length -1; i++) 
		{
			int index = i;
			for (int j = i + 1; j < list.length; j++) 
			{
				if (list[j] < list[index] )
				{
					swap(list,j,index);
				}	
			}
		}
	}
	
	/**
	 * Private helper method to swap the two numbers.
	 * @param list; primary list of ints
	 * @param index; index of list
	 * @param i; loop index
	 */
	private void swap(int[] list, int j, int index)
	{
		int pHolder = list[j];
		list[j] = list[index];
		list[index] = pHolder;
	}	
}
