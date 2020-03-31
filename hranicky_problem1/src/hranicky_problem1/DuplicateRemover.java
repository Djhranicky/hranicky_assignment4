package hranicky_problem1;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class DuplicateRemover 
{	
	private File file;
	private Scanner input;
	private FileWriter out;	
	private String newContent = "";
	private List<String> words = new ArrayList<String>();
	private Set<String> uniqueWords = new HashSet<String>();
	private Map<String, Integer> uniqueMap = new HashMap<String, Integer>();
	
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
	
	public void remove(String dataFile) 
	{
		openFileRead(dataFile);
		while(input.hasNext())
		{
			String currentWord = input.next();
			words.add(currentWord);
			if(!uniqueWords.contains(currentWord.toLowerCase()))
			{
				uniqueWords.add(currentWord.toLowerCase());
			}
		}
		closeFileRead();
		for(String s : uniqueWords)
		{
			uniqueMap.put(s,0);
		}
		for(int i = 0; i < words.size(); i++)
		{
			if(uniqueMap.containsKey(words.get(i)))
			{
				if(uniqueMap.get(words.get(i)) == 0)
					uniqueMap.put(words.get(i), 1);
				else
					words.set(i, "");
			}
			newContent += words.get(i) + " ";
		}
		openFileWrite(dataFile);
		try
		{
			out.write(newContent);
		}
		catch(IOException e)
		{
			System.err.println("Error writing to the file");
			System.exit(1);
		}
		closeFileWrite();
	}
	
	public void write(String outputFile) 
	{
		try
		{
			File uniqueWordFile = new File(outputFile);
			uniqueWordFile.createNewFile();
			openFileWrite(outputFile);
			String[] uniqueWordsArr = new String[uniqueWords.size()];
			System.arraycopy(uniqueWords.toArray(), 0, uniqueWordsArr, 0, uniqueWords.size());
			for(int i = 0; i < uniqueWordsArr.length; i++)
			{
				out.write(uniqueWordsArr[i] + "\n");
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
