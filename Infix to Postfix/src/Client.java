/**
 * @author Varun Shah
 * @version 2/24/16
 */
public class Client {
    public static void main(String[] args)  {
        Converter con = new Converter("data/input.txt");
        con.convert();
        
        System.out.println("");
        
        //Testing with a different file
        Converter con2 = new Converter("data/input2.txt");
        con2.convert();
    }
}