package hranicky_problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateCounter {
	
	private File file;
	private Scanner input;
	private FileWriter out;	
	private Map<String, Integer> wordCounter = new HashMap<String, Integer>();
	
	public void openFileRead(String dataFile) 
	{
		try
		{
			file = new File(dataFile);
			input = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error opening file for reading");
			System.exit(1);
		}
	}
	
	public void closeFileRead()
	{
		if(input != null)
		{
			input.close();
		}
	}
	
	public void openFileWrite(String dataFile)
	{
		try
		{
			file = new File(dataFile);
			out = new FileWriter(file);
		}
		catch(IOException e)
		{
			System.err.println("Error opening the file for writing");
			System.exit(1);
		}
	}
	
	public void closeFileWrite()
	{
		if(out != null)
		{
			try
			{
				out.flush();
				out.close();
			}
			catch(IOException e)
			{
				System.err.println("Error closing the file for writing");
				System.exit(1);
			}
		}
	}
	
	public void count(String dataFile)
	{
		openFileRead(dataFile);
		while(input.hasNext())
		{
			String currentWord = input.next();
			if(!wordCounter.containsKey(currentWord))
			{
				wordCounter.put(currentWord,1);
			}
			else
				wordCounter.put(currentWord,wordCounter.get(currentWord)+1);
		}
		closeFileRead();
	}
	
	public void write(String outputFile) 
	{
		try
		{
			File uniqueWordFile = new File(outputFile);
			uniqueWordFile.createNewFile();
			openFileWrite(outputFile);
			for (Map.Entry<String, Integer> entry : wordCounter.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    out.write(key + " : " + value.toString() + "\n");
			}
			closeFileWrite();
		}
		catch(IOException e)
		{
			System.err.println("Error writing unique word list to file");
			System.exit(1);
		}
	}
}
