package week03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Description of DataLayer class
*
* @author Chris Hurst
*/
public class DataLayer
{
	
	private List<Employee> employeeList;
	
	
	public DataLayer(List<Employee> list){ 	
	}
	
	/**
	 * get employee by last name from the data stored.
	 * @param lastName
	 * @return employee last name if found
	 */
	public Employee getEmployeeByName(String lastName)
	{
		Employee emp = null;
		if (m_map.containsKey(lastName))
		{
			emp = m_map.get(lastName);
		}
		return emp;
	}
	
	/**
	 * Remove an employee
	 * @param employee
	 * @return true if successfully removed else false
	 * @throws CollectionAppDataException
	 */
	
	public boolean deleteEmployee(Employee employee)
	{
		boolean result = false;
		if(m_map.containsKey(employee.getLastName()))
		{Employee emp = m_map.remove(employee.getLastName());
		result = emp == null ? false: true;			//remember this is an if else statement.
		}
		return result;
	}
	
	/**
	 * copy the values from the map and them to the list
	 */
	public List<Employee> getEmployees()
	{
		List<Employee> list = new ArrayList<Employee>();
		for(Employee e : m_map.values())
		{
			list.add(e);
		}
		return list;
	}
	
	/**
	 * return the number of employees
	 */
	
	
	public int getSize()
	{
		return m_map.size();
	}
	
	/** try to load the data file if available
	 * 
	 */
	private void initialize(List<Employee> employeeList)
	{
		for (Employee emp : employeeList)
		{
			m_map.put(emp.getLastName(), emp);
			}
	}
	
	/**
	 * collection map for the employee
	 */
	private HashMap<String, Employee> m_map;

/**
 * add employee
 * @param newEmp
 * @return 
 */
	public Employee addEmployee(Employee newEmp)
	{
		
		return newEmp;
	}

	public List<Employee> getEmployeeList()
	{
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList)
	{
		this.employeeList = employeeList;
	}
		
}//end of DataLayer class
