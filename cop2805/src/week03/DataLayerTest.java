/**
 * 
 */
package week03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author scottl
 *
 */
public class DataLayerTest extends TestCase
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		m_testData = new ArrayList<Employee>();
		m_testData.add(new Employee("Jodi", "Boeddeker", 90000));
		m_testData.add(new Employee("Jim", "Taubitz", 85000));
		m_testData.add(new Employee("Josh", "Gordon", 65000));
		m_testData.add(new Employee("Dave", "Stevens", 45000));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Tests the addEmployee interface
	 */
	@Test
	public void testAddEmployee()
	{
		Employee emp = new Employee("Scott", "LaChance");
		DataLayer dl = new DataLayer(m_testData);

		dl.addEmployee(emp);
		Employee actual = dl.getEmployeeByName("LaChance");
		assertEquals(emp, actual);
	}

	/**
	 * Tests the addEmployee interface Uses getEmployeeByName and getEmployees
	 */
	@Test
	public void testDeletemployee()
	{
		Employee emp = new Employee("Test", "Delete");
		DataLayer dl = new DataLayer(m_testData);

		dl.addEmployee(emp);
		Employee actual = dl.getEmployeeByName("Delete");
		assertEquals(emp, actual);
		List<Employee> list = dl.getEmployees();
		int count = list.size();

		dl.deleteEmployee(emp);
		list = dl.getEmployees();
		int count2 = list.size();
		if(count == count2)
		{
			fail(String.format("Delete failed. Before count %d, after count %d",
					count, count2));
		}
	}

	/**
	 * Writes message to standard out
	 * 
	 * @param msg
	 *            The message to write
	 */
	private void trace(String msg)
	{
		System.out.println(msg);
	}

	private List<Employee> m_testData;
}
