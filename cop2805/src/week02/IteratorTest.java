package week02;
/** 
 * @author Tim Herlihy Jr.
 */

import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IteratorTest extends java.lang.Object
{

	public long sizeOfList;
	public LinkedList newList;
	public StopWatch m_watch; 
	
	/**
	 * Constructor: An instance of the StopWatch class should be instantiated here The size of the list to be created should be persisted in a class attribute.
	 * @param listSize
	 */
	public IteratorTest(int listSize)
	{
		m_watch = new StopWatch();
		newList = new LinkedList();
		
		for(int i = 0; i < listSize; i++)
		{
			newList.add(0);
		}
				
	}
	
	/**
	 * Executes the iterator/get timing test This method should use the StopWatch class created in the constructor to capture start/stop/elapsed time for each run.
	 */
	public void execute()
	{
		Iterator<Object> intListIterator = newList.listIterator();
		
		m_watch.start();
		while (intListIterator.hasNext()) {
			intListIterator.next();
		}
		m_watch.stop();
		getElapsedTimeInMilliseconds();
	
		m_watch.start();
		for (int i = 0; i < newList.size(); i++) 
		{
			newList.get(i);
		}
		m_watch.stop();
		getIteratorElapsedTimeInMilliseconds();
	}
	
	/**
	 * Getter method that returns the get(index) elapsed time.
	 * @return long
	 */
	public long getElapsedTimeInMilliseconds()
	{
		return m_watch.getElapsedTimeMilliSeconds();
	}
	
	/**
	 * Getter method that returns the iterator elapsed time
	 * @return long
	 */
	public long getIteratorElapsedTimeInMilliseconds()
	{
		return m_watch.getElapsedTimeMilliSeconds();
	}
	
	/**
	 * The size of the linked list after the integers have been added.
	 * @return long
	 */
	public long getListSize()
	{
		return newList.size();
	}

}
