package week06;

/**
 * 
 * @author Tim Herlihy Jr. 
 */

public class MergeSort {

	private void merge(int[] list, int a, int b, int c, int d)
	{
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
	
	public MergeSort(int[] list)
	{
		/** The method for sorting the numbers */
		public static void mergeSort(int[] list) {
		if (list.length > 1) {
		int[] firstHalf = new int[list.length / 2];
		System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
		mergeSort(firstHalf);

		//Merge sort the second half
		int secondHalfLength = list.length - list.length / 2;
		int[] secondHalf = new int[secondHalfLength];
		System.arraycopy(list, list.length / 2,
		secondHalf, 0, secondHalfLength);
		mergeSort(secondHalf);

		//Merge firstHalf with secondHalf into list
		merge(firstHalf, secondHalf, list);
	}
	
	public void sort() 
	{
		
	}
	
	public void sortArray(int[] list, int a, int b)
	{
		
	}
	
}

