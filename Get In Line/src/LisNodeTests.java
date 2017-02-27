/**
 * Class that tests the methods of ListNode
 * @author Varun Shah
 * @version 2/11/16
 */
public class LisNodeTests {
	public static void main(String[] args)	{
		Testing.setVerbose(true);
		System.out.println("Start testing");
		testCreate();
		System.out.println("Tests complete");
	}
	
	public static void testCreate()	{
		Testing.testSection("Creation tests");
		
		ListNode node = new ListNode(null);
		Testing.assertEquals("Checking the data of a default node", null, node.toString());
		Testing.assertEquals("Checking what the default node points to", "null", node.next+"");
		
		ListNode newNode = new ListNode("Varun", node);
		Testing.assertEquals("Checking the data of a non-default node", "Varun", newNode.toString());
		Testing.assertEquals("Checking what the non-default node points to", "null", newNode.next+"");
	}
}