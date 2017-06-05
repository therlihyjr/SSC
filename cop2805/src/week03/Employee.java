package week03;

import java.text.DecimalFormat;

/**
 * @author Tim Herlihy Jr.
 */

public class Employee {

	/**
	 * @param m_first first name
	 */
	private DecimalFormat m_decimalFormatter; 
	private String m_first = "New First"; /** first name */
	private String m_last = "New Last"; /** first name */
	private double m_salary  = 0.00; /** first name */
	private String MONEY_PATTERN = "###,##0.00"; /** first name */
	
	/**
	 * default constructor
	 */
	public Employee() 
	{
		
	}
	
	/**
	 * Parameterized constructor
	 * @param m_first first name
	 * @param m_last last name
	 */
	public Employee(String m_first, String m_last)
	{
		this.m_first = m_first;
		this.m_last = m_last;
	}
	

	/**
	 * Parameterized constructor
	 * @param m_first first name
	 * @param m_last last name
	 * @param m_salary salary
	 */
	public Employee(String m_first, String m_last, double m_salary)
	{
		this.m_first = m_first;
		this.m_last = m_last;
		this.m_salary = m_salary;
	}
	
	/**
	 * Override the toString() method for comparison
	 * @param compEmployee employee for comparison
	 * @return 	t/f
	 */
	public boolean equals(Object compEmployee)
	{
		if (compEmployee == this)
		{
			return true;
		}
		if (compEmployee instanceof Employee)
		{
			Employee employee = (Employee)compEmployee;
			return employee.getDisplayName().equals(getDisplayName()) && employee.getFormattedSalary().equals(getFormattedSalary());			
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * getter; display name
	 * @return display name
	 */
	public String getDisplayName()
	{
		return m_last +", " + m_first;
	}
	
	/**
	 * getter; first name
	 * @return first name
	 */
	public String getFirstName() 
	{
		return m_first;
	}
	
	/**
	 * getter; formatted salary
	 * @return formatted salary
	 */
	public String getFormattedSalary()
	{
		m_decimalFormatter = new DecimalFormat(MONEY_PATTERN);
		String formattedSalary = m_decimalFormatter.format(getSalary());
		return formattedSalary;
	}
	
	/**
	 * getter; last name
	 * @return last name
	 */
	public String getLastName()
	{
		return m_last;
	}
	
	/**
	 * getter; salary
	 * @return salary
	 */
	public double getSalary()
	{
		return m_salary;
	}
	
	/**
	 * setter; first name
	 * @param m_first first name
	 */
	public void setFirstName(String m_first)
	{
	this.m_first = m_first;	
	}
	
	/**
	 * setter; last name
	 * @param m_last last name
	 */
	public void setLastName(String m_last)
	{
	this.m_last = m_last;	
	}
	
	/**
	 * setter; salary
	 * @param m_salary salary
	 */
	public void setSalary(double m_salary)
	{
	this.m_salary = m_salary;	
	}
	
	/**
	 * toString method
	 * @return object toString
	 */
	
	@Override
	public String toString() 
	{
		return getDisplayName() + " Salary: $" + getFormattedSalary();
	}
	

	
}
