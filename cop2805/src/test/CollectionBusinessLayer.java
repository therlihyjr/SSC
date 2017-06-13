package week03;

import java.util.List;

/** Description of CollectionBusiness Class
*
* @author Chris Hurst
*/

public class CollectionBusinessLayer
{
	/**
	 * @param Default Constructor
	 * */
	public CollectionBusinessLayer()
	{
	}
	
	/**
	 * @param Constructor 
	 * */
	
	public CollectionBusinessLayer(List<Employee> employeeList)
	{
		m_data = new DataLayer(employeeList);
	}
	
	/**
	 * 
	 * @param m_firstName
	 * @param m_lastName
	 * @param m_salary
	 * @return
	 */
	public Employee addEmployee(String m_firstName, String m_lastName, double m_salary)
	{
		Employee newEmp = new Employee(m_firstName, m_lastName, m_salary);
		m_data.addEmployee(newEmp);
		return newEmp;
	}
	
	/**
	 * 
	 * @param lastName
	 * @param newSalary
	 * @return
	 */
	public boolean changeEmployeeSalary(String m_lastName, double m_salary)
	{
		
		Employee emp;
		
		emp = m_data.getEmployeeByName(m_lastName);
		/**
		 * check to see if m_lastName is equal
		 */
		if (emp.getLastName().equals(m_lastName))
		{
			
			emp.setSalary(m_salary);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * Delete Employee
	 * @param last
	 * @return
	 */
	public Employee deleteEmployee(String m_lastName)
	{
		Employee employeeToDelete = null;
		boolean employeeToDeleteResults;
		
		employeeToDelete = m_data.getEmployeeByName(m_lastName);
		
		/**
		 * check LastName equals lastName
		 */
		if (employeeToDelete.getLastName().equals(m_lastName))
		{
			/**
			 * check for a match
			 */
			employeeToDeleteResults = m_data.deleteEmployee(employeeToDelete);
			
			if(employeeToDeleteResults == true)
				{
					
				employeeToDelete = m_data.getEmployeeByName(m_lastName);
				}
		}
		return employeeToDelete;
	}
	
	
	/**
	 * list All Employees
	 * @return
	 */
	public List<Employee> listAllEmployees()
	{
		return m_data.getEmployees();
	}
	
	/**
	 * list Employee
	 * @param lastName
	 * @return Employee by lastName
	 */
	public Employee listEmployee(String m_lastName)
	{
		Employee employeeToList;
		employeeToList = m_data.getEmployeeByName(m_lastName);
		return employeeToList;
	}
	
	
	private DataLayer m_data;
	
}
