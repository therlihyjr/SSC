package week11;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class VowelFileFilter extends FileFilter
{

	public VowelFileFilter() 
	{
		
	}
	
	@Override
	public boolean accept(File file) 
	{
		if(file.isDirectory())
		{
			return true;
		}
		else
		{
			return file.getName().toLowerCase().endsWith(".txt");
		}
	}

	@Override
	public String getDescription() 
	{
		
		return "Text Document (*.txt)";
	} 
	
}


