package week06;



public class TjTest {
	
	public static void main(String[] args) 
	{
		double[] list = {1, 9, 4.5, 6.6, 5.7, -4.5};
		selectionSort(list);
	}
	

public static void selectionSort(double[] list) {
for (int i = 0; i < list.length - 1; i++) {
// Find the minimum in the list[i..list.length-1]
double currentMin = list[i];
int currentMinIndex = i;
for (int j = i + 1; j < list.length; j++) {
if (currentMin > list[j]) {
	System.out.println("Current Min" + currentMin);
currentMin = list[j];
System.out.println("Current Min" + currentMin);
currentMinIndex = j;
System.out.println("Current Min Index" + currentMinIndex);
}
}
// Swap list[i] with list[currentMinIndex] if necessary
if (currentMinIndex != i) {
	System.out.println(currentMinIndex);
	System.out.println(i);
list[currentMinIndex] = list[i];
System.out.println(currentMinIndex);
System.out.println(list[i]);
list[i] = currentMin;
System.out.println(list[i]);
System.out.println(currentMin);
}
}
}
}	

 /** A test method */
