package week03;

import java.text.DecimalFormat;

/** Description of Employee Class
*
* @author Chris Hurst
*/
public class Employee
{

	private String m_firstName;
	private String m_lastName;
	private double m_salary;
	private static String MONEY_PATTERN = "000,000.00";
	private DecimalFormat m_decimalformatter;

    public Employee()
    {
        this("New First", "New Last");
    }

	/** Copy constructor
	 *
	 */
	public Employee(Employee rhs)
	{ this.m_decimalformatter = rhs.m_decimalformatter;
	this.m_firstName = rhs.m_firstName;
	this.m_lastName = rhs.m_lastName;
	this.m_salary = rhs.m_salary;
	}

	/**Default constructor
	 *
	 * @param newFirstName
	 * @param newLastName
	 */

	public Employee(String newFirstName, String newLastName)
	{this(newFirstName, newLastName, 0.00);
	}

	/**overload constructor(3)
	 *
	 * @param newFirstName
	 * @param newLastName
	 * @param newSalary
	 */
	public Employee(String newFirstName, String newLastName, double newSalary)
	{this.setFirstName(newFirstName);
		m_firstName = newFirstName;
	m_lastName = newLastName;
	m_salary = newSalary;
	m_decimalformatter = new DecimalFormat(MONEY_PATTERN);
		}



	@Override
	public boolean equals(Object obj)
	{
		boolean result = false;
	if (obj instanceof Employee)
	{
		Employee rhs = (Employee)obj;
		if(this.getFirstName().equals(rhs.getFirstName()) && this.getLastName().equals(rhs.getLastName())
				&& this.equals(getFormattedSalary().equals(rhs.getFormattedSalary())))

				{
			result = true;
				}


	}
	return result;
}

	//GETTERS AND SETTERS
	public String getDisplayName(){  //????????
		return m_lastName + "" + m_firstName;
	}

	public String getFormattedSalary(){   // ?????????????
		return null;

	}



	//getters and setters
	public String getFirstName()
	{
		return m_firstName;
	}

	public void setFirstName(String newFirstName)
	{
		if(newFirstName != null && newFirstName.length() > 0)
	this.m_firstName = newFirstName;
	}

	public String getLastName()
	{
		return m_lastName;
	}

	public void setLastName(String newLastName)
	{
		if(newLastName != null && newLastName.length() > 0)
		this.m_lastName = newLastName;
	}

	public double getSalary()
	{
		return m_salary;
	}

	public void setSalary(double newSalary)
	{

		this.m_salary = newSalary;
	}


	@Override
	public String toString(){
		String output = m_decimalformatter.format(getSalary());
		return String.format("%s Salary: s%", getDisplayName(), output);
	}









}//end Employee class
