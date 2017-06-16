package week02;

/**
 * @author Scott
 * @version 1.0
 * @created 13-Mar-2016 9:12:01 PM
 */
public class StopWatch
{
	public void finalize() throws Throwable
	{

	}

	/**
	 * Default constructor
	 */
	public StopWatch()
	{

	}

	/**
	 * Returns the elapsed time for the stopwatch in milliseconds
	 */
	public long getElapsedTimeMilliSeconds()
	{
		return m_stopTime - m_startTime;
	}

	public long getStartTime()
	{
		return m_startTime;
	}

	public long getStopTime()
	{
		return m_stopTime;
	}

	/**
	 * Resets the start time to the current time
	 */
	public void start()
	{
		m_startTime = System.currentTimeMillis();
	}

	/**
	 * Sets the end time to the current time
	 */
	public void stop()
	{
		m_stopTime = System.currentTimeMillis();
	}
	
	/**
	 * The start time
	 */
	private long m_startTime = 0;
	/**
	 * The end time
	 */
	private long m_stopTime = 0;
}// end StopWatch