package week04;

// TODO: Auto-generated Javadoc
/**
 * The Class WordCountResult.
 *
 * @author Tim Herlihy Jr.
 */

//this is a comment

public class WordCountResult {

	private String m_word; 	/** word to count */
	private int m_count; 	/** total repetitions of word to count */
	
	/**
	 * Constructor that initializes an instance
	 * Used when a new word has been found that needs to be added to the occurrence list
	 * @param m_count the m count
	 * @param m_word the m word
	 */
	public WordCountResult(int m_count, String m_word)
	{
		this.m_count = m_count;
		this.m_word = m_word;
	}
	
	/**
	 * Increments the occurrence count	
	 * Used when the word has been added already and a new occurrence has been found
	 */
	public void incrementCount()
	{	
		int curCt = this.getCount();
		int newCt = curCt + 1;
		this.m_count = newCt;
	}
	
	/**
	 * Returns the number of occurrences of the word
	 * @return the count
	 */
	public int getCount() {
		return m_count;
	}
	
	/**
	 * returns the word being analyzed
	 * @return the word
	 */
	public String getWord() 
	{
		return m_word;
	}	
		
	/**
	 * 	Used to set the occurrence count. Replaces any existing value.
	 * This isn't used frequently; general usage is to create a new instance with count 1 when a new word is encountered
	 * @param count the new count
	 */
	public void setCount(int count)
	{
	this.m_count = count;	
	}

	/**
	 * Used to set the word. Replaces the existing word
	 * This isn't used frequently.
	 * @param m_word; the new word
	 */
	public void setWord(String m_word) 
	{
		this.m_word = m_word;
	}

	/* 
	 * Custom to string that generates the following text: count <tab> word
	 * @return return project's desired toString method.
	 */
	@Override
	public String toString() {
		return "WordCountResult " + m_count + "\t" + m_word;
	}
	
	
	 
}
