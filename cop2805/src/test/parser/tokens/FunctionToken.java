package test.parser.tokens;

import java.util.List;

public abstract class FunctionToken extends Token
{
	protected FunctionToken(String name, CommentToken comment,
			String visibility, List<ParameterToken> parameters)
	{
		super(name, comment, visibility);
		m_parameters = parameters;
	}

	public List<ParameterToken> getParameters()
	{
		return m_parameters;
	}

	public int getParameterCount()
	{
		return m_parameters.size();
	}

	@Override
	public String toString()
	{
		String fmt = String.format("%s\nParameter Count: %d\n",
				super.toString(), getParameterCount());
		String paramList = "";
		for(ParameterToken token : m_parameters)
		{
			paramList += token.toString() + System.lineSeparator();
		}

		return fmt + paramList;
	}

	protected List<ParameterToken> m_parameters;
}

