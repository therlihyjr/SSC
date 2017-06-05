package week03;

import java.util.ArrayList;

/**
 * @author Tim Herlihy Jr.
 */

import java.util.HashMap;
import java.util.List;

/**
 * @author Tim Herlihy Jr.
 */

public class DataLayer {

	/**
	 * Class Vars
	 */
	private HashMap<String,Employee> m_map = new HashMap<String, Employee>();

	/**
	 * Constructor
	 * This takes a List of Employees to initialize the DataLayer. The list can be empty.
	 * @param list list of employees
	 */
	public DataLayer(List<Employee> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Employee employee = list.get(i);			
			m_map.put(employee.getLastName(),employee);
		}
			
	}
	
	/**
	 * Adds an employee	
	 * Add the employ to an internal list. This is not persisted between application executions
	 * @param emp employee object
	 */
	public void addEmployee(Employee emp)
	{
		String m_last = emp.getLastName();
		m_map.put(m_last, emp);
	}

	/**
	 * Removes an employee. Returns true on successful removal, otherwise false.	
	 * If the employee is not in the list, return false.
	 * @param emp employee object
	 * @return t/f if employee found/not found
	 */
	public boolean deleteEmployee(Employee emp)
	{
		String key = emp.getLastName();
		
		
		if (m_map.containsKey(key))
		{
			m_map.remove(key);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Retrieve an employee by their last name	
	 * This implies you have to associate the last name with the employee in your internal storage. The tests will not add Employee instances with the same last name.
	 * @param lastName last name, these comments are getting out of hand.
	 * @return
	 */
	public Employee getEmployeeByName(String lastName)
	{
		return m_map.get(lastName);
	}
	
	/**
	 * Returns the full list of employees	
	 * @return employeelist, a list of the employees
	 */
	public List<Employee> getEmployees()
	{
		List<Employee> employeeList = new ArrayList<Employee>(m_map.values());
		return employeeList;
	}
	
	/**
	 * Returns the number of employees	
	 * @return size, the size of the array
	 */
	public int getSize()
	{
		return m_map.size();
	}
	
}
