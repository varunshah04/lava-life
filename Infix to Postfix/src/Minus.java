/**
 * Class that handles the minus token
 * @author Varun Shah
 * @version 2/24/16
 */
public class Minus implements Token {
    private int precedence = 1;
    
    /**
     * Returns the token in a printable form
     * @return output
     */
    public String toString()    {
        String output = "-";
        return output;
    }
    
    /**
     * Handles the token accordingly
     * @return append
     */
    public String handle(Stack<Token> s)   {
        String append = "";
        while(s.size()>0 &&
                s.peek().getPrecedence()>=precedence &&
                s.peek().toString()!="(")   {
            append += s.pop().toString();
        }
        s.push(this);
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