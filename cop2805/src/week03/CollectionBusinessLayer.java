package week03;

import java.util.List;

/**
 * @author Tim Herlihy Jr.
 */

public class CollectionBusinessLayer{
	
	/**
	 * Class Vars
	 */
	private DataLayer m_data = null;
	/**
	 * Default Constructor
	 */
	public CollectionBusinessLayer() 
	{
	}
	
	/**
	 * Parameterized constructor. After the constructor has executed, an instance of the DataLayer should be contained in the class.
	 * @param list; list of employees
	 */
	public CollectionBusinessLayer(List<Employee> list)
	{
		m_data = new DataLayer(list);
	}
	
	/**
	 * Add a new employee	
	 * This method converts the parameters to an employee instance and adds the employee to the data layer.
	 * @param first; first name
	 * @param last; last name
	 * @param salary; salary
	 * @return newEmployee; return the new employee object
	 */
	public Employee addEmployee(String first, String last,double salary)
	{
		Employee newEmployee = new Employee(first, last, salary);
		m_data.addEmployee(newEmployee);
		return newEmployee;
	}
	
	/**
	 * Updates a specific employee's salary. Returns true if successful, otherwise false	
	 * Return false if employee does not exist or there may be an error.
	 * @param last; last name
	 * @param newSalary; new salary
	 * @return f/t if employee found
	 */
	public boolean changeEmployeeSalary(String last,double newSalary)
	{
		Employee employee = m_data.getEmployeeByName(last);
		if (employee != null)
		{
		employee.setSalary(newSalary);
		return true;
		}
		else
		{
		return false;
		}
	}
	
	/**
	 * Delete the requested employee. Return the old Employee instance. May be null if not found	
	 * @param last last name
	 * @return return employee that was deleted
	 */
	public Employee deleteEmployee(String last)
	{
		Employee employee = m_data.getEmployeeByName(last);
		m_data.deleteEmployee(employee);
		return employee;
	}
	
	/**
	 * Get the list of all employees	
	 * @return list of employees
	 */
	public List<Employee> listAllEmployees()
	{
		return m_data.getEmployees();
	}
	
	/**
	 * Get the specified employee by their last name	
	 * @param last last name
	 * @return employee object
	 */
	public Employee listEmployee(String last)
	{
		Employee employee = m_data.getEmployeeByName(last);
		return employee;
	}
	
}
