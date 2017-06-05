package week04;

/**
 * @author Tim Herlihy Jr.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class CountUtility {

	/**
	 * Evaluates the provided reader to find individual words and the number of occurrences of the word. If there are processing errors the exception is thrown to the caller.
	 * The TestHarness (user) provides the reader to the class. The class will not query the user or read from a file. It only evaluates the provided stream.
	 * This should thrown an exception! 	
	 * @param reader; the reader
	 * @return the list
	 * @throws IOException 
	 */
	public List<WordCountResult> countWordOccurrences(BufferedReader reader) throws IOException {
		String textLine = null;
		String[] m_wordArray = null;
		Map<String, Integer> map = new TreeMap<>();
		List<WordCountResult> wcrList = new ArrayList();
		//HashSet<WordCountResult> wcrList = new HashSet<>();
		
		while((textLine = reader.readLine()) != null)
			{
				m_wordArray = textLine.split("[ \n\t\r.,;:!?(){}]");
				for (String s : m_wordArray)
				{
				
				for (WordCountResult curWCR : wcrList)
				{
					if (s.equals(curWCR.getWord()))
					{
						curWCR.incrementCount();
					}
					else 
					{
						WordCountResult newWCR = new WordCountResult(1,s);
						wcrList.add(newWCR);
					}
				}
			}
		}
		return wcrList;
	}
	
	/**
	 * Evaluate line.
	 * This is a private method and not required to be implemented. The reference implementation use it to evaluate individual lines by splitting into the words.
	 * Checks to see if each word has previously been mapped and if so, increment the occurrence count, otherwise add and set count to 1.
	 * @param line; the line to check
	 * @param occurances; the occurrences 
	 */
	@SuppressWarnings("unused")
	private void evaluateLine(String line, Map<String, WordCountResult> occurances)
	{

	}	
}
