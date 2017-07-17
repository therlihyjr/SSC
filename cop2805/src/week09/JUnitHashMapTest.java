package week09;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitHashMapTest
{

	@Test
	public void test()
	{
		MyMap<String, Integer> map = new MyHashMapLinear<>();
		map.put("Smith", 30);
		map.put("Anderson", 31);
		map.put("Lewis", 29);
		map.put("Cook", 29);
		map.put("Smith", 65);
		
		trace("Map entries\n" + map.toString());
		trace("Get age for Lewis: " + map.get("Lewis"));
		trace("Smith in map? " + map.containsKey("Smith"));
		trace("Is age 33 in map? " + map.containsValue(33));
		
		map.remove("Smith");
		trace("Map entries\n" + map.toString());
		
		map.clear();

		trace("Map entries\n" + map.toString());
	}

	private void trace(String msg)
	{
		System.out.println(msg);
	}
}
