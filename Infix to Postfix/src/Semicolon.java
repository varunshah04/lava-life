/**
 * Class that handles the semicolon token
 * @author Varun Shah
 * @version 2/24/16
 */
public class Semicolon implements Token
{
    private int precedence = 0;
    
    /**
     * Returns the token in a printable form
     * @return output
     */
    public String toString()
    {
        String output = ";";
        return output;
    }
	
    /**
     * Handles the token accordingly
     * @return append
     */
    public String handle(Stack<Token> s)
    {   
        String append = "";
        while(s.size()>0)   {
            append += s.pop();
        }
        append += "\n";
        return append;
    }
    
    /**
     * Gets the order of priority of the token
     * @return precedence
     */
    public int getPrecedence()  {
        return this.precedence;
    }
}