/**
 * Class that gets tokens and handles them
 * @author Varun Shah
 * @version 2/22/16
 */
public class Converter {
    private String file;
    
    /**
     * Non-default constructor
     * @param file
     */
    public Converter(String file)   {
        this.file = file;
    }
    
    /**
     * Converts the infix expression to postfix
     */
    public void convert()   {
        FileReader fr = new FileReader(file);
        Stack<Token> s = new Stack<Token>();
        String post = "";
        String token = fr.nextToken();
        String infix = "";
        while(!token.equals("EOF")) {
        	if(!token.equals(";"))	{
        		infix += token;
        	}
            if(token.equals("+"))   {
                post += (new Plus()).handle(s);
            }
            else if(token.equals("-"))  {
                    post += (new Minus().handle(s));
            }
            else if(token.equals("*"))  {
                post += (new Multiply()).handle(s);
            }
            else if(token.equals("/"))  {
                post += (new Divide().handle(s));
            }
            else if(token.equals("^"))  {
                post += (new Exponentiate()).handle(s);
            }
            else if(token.equals("("))  {
                post += (new LeftPar()).handle(s);
            }
            else if(token.equals(")"))  {
                post += (new RightPar()).handle(s);
            }
            else if(token.equals(";"))  {
                post += (new Semicolon()).handle(s);
                if(!infix.equals(""))	{
                	infix += " --> " + post;
                }
                System.out.println(infix);
                infix = "";
                post = "";
            }
            else    {
                post += token;
            }
            token = fr.nextToken();
        }
    }
}