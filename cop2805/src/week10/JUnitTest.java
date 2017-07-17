package week10;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the GameEntry class
 * 
 * @author Scott
 *
 */
public class JUnitTest
{
	@Before
	public void initialize()
	{
		
	}
	
	/////////// Tests for Week11Enum (NFL Teams) //////////////////////////////////
	@Test
	public void testNFLEnumeration()
	{
		int expectedCount = 32;
		int actualCount = NFLEnumeration.values().length;
		assertEquals(expectedCount,actualCount);
		
		boolean validEnumerationNames = true;
		StringBuilder msg = new StringBuilder();
		
		// Verify the enumerations
		for(NFLEnumeration team : NFLEnumeration.values())
		{
			if(!findTeamName(team.name()))
			{
				validEnumerationNames = false;
				msg.append(String.format("%s team not found\n", team.name()));
			}
		}
		
		if(!validEnumerationNames)
		{
			trace(msg.toString());
			fail(msg.toString());
		}
	}	
	
	@Test
	public void testNFLEnumerationCities()
	{
		int expectedCount = 32;
		int actualCount = NFLEnumeration.values().length;
		assertEquals(expectedCount,actualCount);
		
		boolean validEnumerationNames = true;
		StringBuilder msg = new StringBuilder();
		
		// Verify the enumerations
		for(NFLEnumeration team : NFLEnumeration.values())
		{
			NFLTeam nflTeam = getTeam(team.name());
			String expectedCity = nflTeam.getCity();
			String actualCity = team.city();
			if(!expectedCity.equals(actualCity))
			{
				validEnumerationNames = false;
				msg.append(String.format("team city '%s' not found or matched\n", actualCity));
			}
		}
		
		if(!validEnumerationNames)
		{
			trace(msg.toString());
			fail(msg.toString());
		}
	}
	
	static private void trace(String msg)
	{
		System.out.println(msg);
	}
	
	private boolean findTeamName(String teamName)
	{
		boolean result = false;
		for(NFLTeam team : nflTeams)
		{
			// case sensitive
			String name = team.getName();
			if(teamName.equals(name))
			{
				result = true;
				break; // early out
			}
		}
		
		return result;
	}
	
	private NFLTeam getTeam(String teamName)
	{
		NFLTeam team = null;
		
		for(NFLTeam aTeam : nflTeams)
		{
			// case sensitive
			String name = aTeam.getName();
			if(teamName.equals(name))
			{
				team = aTeam;
				break; // early out
			}
		}
		
		return team;
	}
	
	private static NFLTeam[] nflTeams = 
			{new NFLTeam("PACKERS","Green Bay"),
				new NFLTeam("PATRIOTS","Boston"),			
				new NFLTeam("SEAHAWKS","Seattle"),
					new NFLTeam("COWBOYS","Dallas"),
					new NFLTeam("BILLS","Buffalo"),
					new NFLTeam("BEARS","Chicago"),
					new NFLTeam("STEELERS","Pittsburgh"),
					new NFLTeam("CARDINALS","Phoenix"),
					new NFLTeam("BROWNS","Cleveland"),
					new NFLTeam("GIANTS","New York"),
					new NFLTeam("JETS","New York"),
					new NFLTeam("FORTYNINERS","San Francisco"),
					new NFLTeam("RAIDERS","Oakland"),
					new NFLTeam("JAGUARS","Jacksonville"),
					new NFLTeam("DOLPHINS","Miami"),
					new NFLTeam("BRONCOS","Denver"),
					new NFLTeam("TEXANS","Houston"),
					new NFLTeam("RAVENS","Baltimore"),
					new NFLTeam("REDSKINS","Washington"),
					new NFLTeam("RAMS","Los Angeles"),
					new NFLTeam("TITANS","Tennessee"),
					new NFLTeam("VIKINGS","Minneapolis"),
					new NFLTeam("LIONS","Detroit"),
					new NFLTeam("COLTS","Indianapolis"),
					new NFLTeam("BENGALS","Cincinnati"),
					new NFLTeam("EAGLES","Philadelphia"),
					new NFLTeam("CHIEFS","Kansas City"),
					new NFLTeam("PANTHERS","Charlotte"),
					new NFLTeam("BUCCANEERS","Tampa Bay"),
					new NFLTeam("FALCONS","Atlanta"),
					new NFLTeam("CHARGERS","San Diego"),
					new NFLTeam("SAINTS","New Orleans")
			};
}

class NFLTeam
{
	public NFLTeam(String name, String city)
	{
		m_name = name;
		m_city = city;
	}
	
	public String getName() { return m_name; }
	public String getCity() { return m_city; }
	private String m_name;
	private String m_city;
}


