package week10;

/**
 * @author Timothy Herlihy Jr. 
 */
public enum NFLEnumeration {

	/** 
	 * enum list with only cities
	 */
	
	PACKERS("Green Bay"),
	PATRIOTS("Boston"),			
	SEAHAWKS("Seattle"),
	COWBOYS("Dallas"),
	BILLS("Buffalo"),
	BEARS("Chicago"),
	STEELERS("Pittsburgh"),
	CARDINALS("Phoenix"),
	BROWNS("Cleveland"),
	GIANTS("New York"),
	JETS("New York"),
	FORTYNINERS("San Francisco"),
	RAIDERS("Oakland"),
	JAGUARS("Jacksonville"),
	DOLPHINS("Miami"),
	BRONCOS("Denver"),
	TEXANS("Houston"),
	RAVENS("Baltimore"),
	REDSKINS("Washington"),
	RAMS("Los Angeles"),
	TITANS("Tennessee"),
	VIKINGS("Minneapolis"),
	LIONS("Detroit"),
	COLTS("Indianapolis"),
	BENGALS("Cincinnati"),
	EAGLES("Philadelphia"),
	CHIEFS("Kansas City"),
	PANTHERS("Charlotte"),
	BUCCANEERS("Tampa Bay"),
	FALCONS("Atlanta"),
	CHARGERS("San Diego"),
	SAINTS("New Orleans");
	
	//private final String team; //var for team name
	private final String m_city; //var for city name
	
	/**
	 * enum constructor
	 * @param city name of city associated with team 
	 */
	private NFLEnumeration(String city)
	{
		this.m_city = city;
	}
	
	/**
	 * return the city name
	 * @return m_city = name of the city
	 */
	public String city()
	{
		return m_city;
	}
	
	
	/** 
	 * enum list with both name and city 

	PACKERS("PACKERS","Green Bay"),
	PATRIOTS("PATRIOTS","Boston"),			
	SEAHAWKS("SEAHAWKS","Seattle"),
	COWBOYS("COWBOYS","Dallas"),
	BILLS("BILLS","Buffalo"),
	BEARS("BEARS","Chicago"),
	STEELERS("STEELERS","Pittsburgh"),
	CARDINALS("CARDINALS","Phoenix"),
	BROWNS("BROWNS","Cleveland"),
	GIANTS("GIANTS","New York"),
	JETS("JETS","New York"),
	FORTYNINERS("FORTYNINERS","San Francisco"),
	RAIDERS("RAIDERS","Oakland"),
	JAGUARS("JAGUARS","Jacksonville"),
	DOLPHINS("DOLPHINS","Miami"),
	BRONCOS("BRONCOS","Denver"),
	TEXANS("TEXANS","Houston"),
	RAVENS("RAVENS","Baltimore"),
	REDSKINS("REDSKINS","Washington"),
	RAMS("RAMS","Los Angeles"),
	TITANS("TITANS","Tennessee"),
	VIKINGS("VIKINGS","Minneapolis"),
	LIONS("LIONS","Detroit"),
	COLTS("COLTS","Indianapolis"),
	BENGALS("BENGALS","Cincinnati"),
	EAGLES("EAGLES","Philadelphia"),
	CHIEFS("CHIEFS","Kansas City"),
	PANTHERS("PANTHERS","Charlotte"),
	BUCCANEERS("BUCCANEERS","Tampa Bay"),
	FALCONS("FALCONS","Atlanta"),
	CHARGERS("CHARGERS","San Diego"),
	SAINTS("SAINTS","New Orleans");
	**/
	
	/**
	 * constructor for team and city
	 * @param team = name of the team 
	 * @param city = name of the city
	
	private NFLEnumeration(String team, String city)
	{
		this.team = team;
		this.m_city = city;
	}
	 */
	
	
	/**
	 * return the team nane
	 * @return team = name of the team
	
	public String getTeam() 
	{
		return team;
	}
	 */
}
