package week07;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class JUnitMyArrayTest
{

	@Test
	public void test()
	{
		MyList<String> list = new MyArrayList<String>();

		list.add("America");

		// add test
		String actual = list.get(0);
		assertEquals(String.format("Failed: expected: %s, actual %s", "America",
				actual), "America", actual);

		list.add("Canada");
		list.add("Russia");
		list.add("France");
		list.add("Germany");
		list.add("England");

		// get size test
		int expectedSize = 6;
		assertEquals(String.format("Failed: expected size: %s, actual size %s",
				expectedSize, list.size()), expectedSize, list.size());
		
		// get(index) test
		String expectedCountry = "France";
		String actualCountry = list.get(3);
		assertEquals(String.format("Failed - get(index): index: %d, expected: %s, actual %s",
				3, expectedCountry, actualCountry), expectedCountry, actualCountry);
		
		// iterate test
		int count = 0;
		trace("Testing iterator");
		Iterator<String> iter = list.iterator();
		while(iter.hasNext())
		{
			count++;
			trace(iter.next());
		}
		
		assertEquals(String.format("Failed - iterator: expected size: %s, actual suze %s",
				expectedSize, count), expectedSize, count);
		
		// indexOf test
		int actualIndex = list.indexOf("Germany");
		int expectedIndex = 4;
		assertEquals(String.format("Failed: expected index: %d, actual index %d",
				expectedIndex, actualIndex), expectedIndex, actualIndex);
		
		// failed indexOf test
		actualIndex = list.indexOf("German"); // invalid country
		expectedIndex = -1;
		assertEquals(String.format("Failed indexOf not found: expected index: %d, actual index %d",
				expectedIndex, actualIndex), expectedIndex, actualIndex);
		

		// lastIndexOf test
		actualIndex = list.lastIndexOf("Russia");
		expectedIndex = 2;
		assertEquals(String.format("Failed - lastIndexOf: expected index: %d, actual index %d",
				expectedIndex, actualIndex), expectedIndex, actualIndex);
		
		// set test  
		String old = list.set(1, "Belgium");
		assertEquals(String.format("Failed - set return: expected country: %s, actual country %s",
				"Canada", old), "Canada", old);
		String newCountry  = list.get(1);
		assertEquals(String.format("Failed - set/get: expected country: %s, actual country %s",
				"Belgium", newCountry), "Belgium", newCountry);
		
		// remove 
		int originalSize = list.size();
		String removedCountry = list.remove(0);
		int actualSize = list.size();
		assertEquals(String.format("Failed - remove size: expected size: %d, actual size %d",
				originalSize-1, actualSize), originalSize-1, actualSize);
		assertEquals(String.format("Failed - remove return: expected country: %s, actual country %s",
				"America", removedCountry), "America", removedCountry);
		
		trace("toString()");
		trace(list.toString());
		
		// clear test
		list.clear();
		assertEquals(String.format("Failed - clear size: expected size: %d, actual size %d",
				0, list.size()), 0, list.size());
		
		
	}

	private void trace(String msg)
	{
		System.out.println(msg);
	}
}
