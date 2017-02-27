/**
 * Class that handles the right parenthesis token
 * @author Varun Shah
 * @version 2/24/16
 */
public class RightPar implements Token  {
    private int precedence = 0;
    
    /**
     * Returns the token in a printable form
     * @return output
     */
    public String toString()    {
        String output = ")";
        return output;
    }
    
    /**
     * Handles the token accordingly
     * @return append
     */
    public String handle(Stack<Token> s)    {
        String append = "";
        while(!s.peek().toString().equals("(")) {
            append += s.pop();
        }
        s.pop();
        return append;
    }
    
    /**
     * Returns the order of priority of the token
     * @return precedence
     */
    public int getPrecedence()  {
        return this.precedence;
    }
}