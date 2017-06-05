package week03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CollectionBusinessLayerTest extends TestCase
{

	@Before
	public void setUp() throws Exception
	{
		m_testData = new ArrayList<Employee>();
		m_testData.add(new Employee("Jodi", "Boeddeker", 90000));
		m_testData.add(new Employee("Jim", "Taubitz", 85000));
		m_testData.add(new Employee("Josh", "Gordon", 65000));
		m_testData.add(new Employee("Dave", "Stevens", 45000));
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testListAllEmployees()
	{
		CollectionBusinessLayer biz = new CollectionBusinessLayer(m_testData);
		List<Employee> employees = biz.listAllEmployees();
		int count = employees.size();
		assertTrue(count > 0);
	}

	@Test
	public void testListEmployee()
	{
		CollectionBusinessLayer biz = new CollectionBusinessLayer(m_testData);

		Employee expected = new Employee("Joe", "Groszek", 10500.25);
		biz.addEmployee("Joe", "Groszek", 10500.25);
		Employee actual = biz.listEmployee("Groszek");
		assertEquals(expected, actual);
	}

	@Test
	public void testDeleteEmployee()
	{
		CollectionBusinessLayer biz = new CollectionBusinessLayer(m_testData);

		Employee expected = new Employee("Jeff", "Sample", 10500.25);
		biz.addEmployee("Jeff", "Sample", 10500.25);
		Employee actual = biz.listEmployee("Sample");
		assertEquals(expected, actual);
		biz.deleteEmployee("Sample");
		actual = biz.listEmployee("Sample");
		assertNull(actual);

	}

	/**
	 * Adds a new employee and changes the salary Uses listEmployee and
	 * addEmployee
	 */
	@Test
	public void testChangeEmployeeSalary()
	{
		CollectionBusinessLayer biz = new CollectionBusinessLayer(m_testData);

		Employee expected = new Employee("John", "Wayne", 10500.25);
		biz.addEmployee("John", "Wayne", 10500.25);
		Employee actual = biz.listEmployee("Wayne");
		assertEquals(expected, actual);
		biz.changeEmployeeSalary("Wayne", 12005.32);
		actual = biz.listEmployee("Wayne");
		assertEquals(actual.getFormattedSalary(), "12,005.32");

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
