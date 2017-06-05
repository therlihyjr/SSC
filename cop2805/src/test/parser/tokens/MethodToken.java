package test.parser.tokens;

import java.util.List;

public class MethodToken extends FunctionToken
{
	public MethodToken(String name, OverrideToken override,
			CommentToken comment, String visiblity, String returnType,
			List<ParameterToken> parameters)
	{
		super(name, comment, visiblity, parameters);

		m_override = override;
		m_returnType = returnType;
	}

	/**
	 * Get the return type
	 * 
	 * @return the return type minus the qualifed name
	 */
	public String getReturnType()
	{
		// need to normalize i.e. strip off any fully qualified 
		// package information java.util.Set will return as Set
		return normalizeType(m_returnType);
	}

	public OverrideToken geOverride()
	{
		return m_override;
	}

	public void setAbstract()
	{
		m_isAbstract = true;
	}

	public void setStatic()
	{
		m_isStatic = true;
	}

	public boolean isAbstract()
	{
		return m_isAbstract;
	}

	public boolean isStatic()
	{
		return m_isStatic;
	}
	
	private String normalizeType(String type)
	{
		String normalized = type;
		
		//String temp = "";
		int lastIndex = 0;
		for(int index = 0; index < type.length(); index++)
		{
			char ch = type.charAt(index);
			if(ch == '<' || ch == '(' || ch == '<')
			{
				// reached the end of potential parsing
				// we don't want to keep going and possibly encounter
				// a valid period in the return type declaration
				break;
			}
			else if(ch == '.')
			{
				// drop anything prior
				//temp = "";
				lastIndex = index+1;
			}
			
			//temp += type.charAt(index);
		}
		
		normalized = type.substring(lastIndex);
		
		return normalized;
	}

	private OverrideToken m_override;
	private String m_returnType;
	private boolean m_isAbstract;
	private boolean m_isStatic;
}
