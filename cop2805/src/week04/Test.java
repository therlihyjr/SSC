package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Test {
	
	public static void main(String[] args) throws IOException 
	{
		StringReader sReader = new StringReader(TEST_DATA);
		BufferedReader reader = new BufferedReader(sReader);
		String textLine = null;
		String[] m_wordArray = null;
		Map<String, Integer> map = new TreeMap<>();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> fullwordlist = new ArrayList();
		List<WordCountResult> wcrList = new ArrayList();

		while((textLine = reader.readLine()) != null)
		{
			m_wordArray = textLine.split("[ \n\t\r.,;:!?(){}]");
			
			for (String s : m_wordArray)
			{
				fullwordlist.add(s);
			}
		}
		
		for (String s : m_wordArray)
		{
			
		}

	}
	private static String TEST_DATA = 
	"Some where over the rainbow, skies are blue. Skies are blue\n"
	+ "over the rainbow, where fairy tales come true\n"
	+ "Some day I'll wish upon a start and wake up where the stars are far behind me.\n"
	+ "Way up on the chimney tops that's where you'll always find me.\n"
	+ "Some where over the rainbow"
	+ "There's a lady whose sure, all that glitters is gold and she's buying a stairway to heaven.\n"
	+ "When she gets there she knows, if the stores are all closed, with a word she can get what she came for.\n"
	+ "There's a sign on the wall\n"
	+ "But she wants to be sure\n"
	+ "'Cause you know sometimes words have two meanings.\n"
	+ "In a tree by the brook\n"
	+ "There's a songbird that sings,\n"
	+ "Sometimes all of our thoughts are misgiven\n"
	+ "And she's buying a stairway to heaven\n"
	+ "There's a feeling I get\n"
	+ "When I look to the west,\n"
	+ "And my spirit is crying for leaving.\n"
	+ "In my thoughts I have seen\n"
	+ "Rings of smoke through the trees,\n"
	+ "When the voices of those who standing looking\n"
	+ "And she's buying a stairway to heaven\n"
	+ "And it's whispered that soon\n"
	+ "If we all call the tune\n"
	+ "Well Then the piper will lead us to reason.\n"
	+ "And a new day will dawn\n"
	+ "For those who stand long\n"
	+ "And the forests will echo with laughter\n";
	
}

/*		
//Set text in a string
String text = "Good morning. Have a good class. " +
"Have a good visit. Have fun!";

//Create a TreeMap to hold words as key and count as value
Map<String, Integer> map = new TreeMap<>();
String[] words = text.split("[ \n\t\r.,;:!?(){}]");
for (int i = 0; i < words.length; i++) {
String key = words[i].toLowerCase();
if (key.length() > 0) {
if (!map.containsKey(key)) {
map.put(key, 1);
}
else {
int value = map.get(key);
value++;
map.put(key, value);
}
}
}
//Get all entries into a set
Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
//Get key and value from each entry
for (Map.Entry<String, Integer> entry: entrySet)
System.out.println(entry.getKey() + "\t" + entry.getValue());
*/
