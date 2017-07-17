package week10;

@SuppressWarnings("unused")
public enum DemoEnum
{
	// declares the enum and provides parameters to the 
	// specific enum
	ENUM1("enum1", 1), // This executes the private constructor below and assigns the parameters to the private variables.
	ENUM2("enum2", 2), // each of these are individual instances, so each one has unique state
	ENUM3("enum3", 3)
	;
	
	private final String m_description;
	private final int m_value;
	/**
	 * Private DemoEnum constructor
	 * @param enumString string description
	 * @param enumValue integer value
	 */
	private DemoEnum(String enumString, int enumValue)
	{
		// initialize an instance of the enum values
		m_description = enumString;
		m_value = enumValue;
	}
	

}

