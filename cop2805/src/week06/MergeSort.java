package week06;

/**
 * 
 * @author Tim Herlihy Jr. 
 */

public class MergeSort extends AbstractSort  {

	/**
	 * Class Constructor to Abstract
	 * @param list  primary list of integers
	 */
	public MergeSort(int[] list) {
		super("MergeSort", list);
	
	}
	
	/**
	 * Method to be called by abstract class for sorting
	 */
	public void sort() 
	{
		int[] list = getList();
		
		SortArray(list);

	}
	
	/**
	 * primary sorting algorithm
	 * @param list array of ints
	 */
	public static void SortArray(int[] list) {
		if (list.length > 1) {

		// Merge sort the first half
		int[] firstHalf = new int[list.length / 2];
		System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
		SortArray(firstHalf);
		 // Merge sort the second half
		 int secondHalfLength = list.length - list.length / 2;
		 int[] secondHalf = new int[secondHalfLength];
		 System.arraycopy(list, list.length / 2,
		 secondHalf, 0, secondHalfLength);
		 SortArray(secondHalf);

		 // Merge firstHalf with secondHalf into list
		 merge(firstHalf, secondHalf, list);
		 }
		 }
	
	/**
	 * helper class to merge arrays
	 * @param list1 fist half
	 * @param list2 second half
	 * @param temp temporary place holder
	 */
	 public static void merge(int[] list1, int[] list2, int[] temp) {
		 int current1 = 0; // Current index in list1
		 int current2 = 0; // Current index in list2
		 int current3 = 0; // Current index in temp

		 while (current1 < list1.length && current2 < list2.length) {
		 if (list1[current1] < list2[current2])
		 temp[current3++] = list1[current1++];
		 else
		 temp[current3++] = list2[current2++];
		 }

		 while (current1 < list1.length)
		 temp[current3++] = list1[current1++];

		 while (current2 < list2.length)
		 temp[current3++] = list2[current2++];
		 }

}

