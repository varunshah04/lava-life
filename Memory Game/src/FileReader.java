import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * FileReader takes a text file and parses it into individual words,
 * which are either words (separated by spaces or other punctuation)
 * or the # symbol, indicating a page break.
 * 
 * @author Diego Bazan
 * @version 6/6/2017
 */
public class FileReader {

    private Scanner sc;
	
    /** 
     * Constructor specifying the file to read from.  
     * 
     * @param filename absolute or relative path to the desired file.
     * Examples: "input.txt" or "C:/input.txt"
     */
    public FileReader(String filename)
    {
    	try 
    	{
    		sc = new Scanner(new File(filename));

    		sc.useDelimiter("[\\.,\\-;:]*\\s+|\\-");	 
    	} catch (FileNotFoundException e) 
    	{
    		System.out.println(filename + " not found");
    	}
    }
    
    /**
     * @return the next token, if there is one, and null otherwise.
     */
    public String nextToken()
    {
    	if (sc.hasNext()) {
    		return sc.next();
    	} else {
    		return null;
    	}
    }
    
    /**
     * @return true if the file has a next token, false if it does not
     */
    public Boolean hasNextToken()
    {
    	Boolean hasNext;
    	if (sc.hasNext())
    	{
    		hasNext = true;
    	}
    	else
    	{
    		hasNext = false;
    	}
    	return hasNext;
    }   
}