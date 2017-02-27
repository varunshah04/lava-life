/**
 * Class that tests the methods of LinkedList
 * @author Varun Shah
 * @version 2/11/16
 */
public class LinkedListTests {

  public static void main(String[] args) {

    Testing.setVerbose(true);
    System.out.println("Starting Tests");

    testCreate();
    testAdd();
    testRemove();
    testSize();
    testGet();
    testSet();
    testInvalidIndex();

    System.out.println("Tests Complete");
  }

  private static void testCreate() {
    Testing.testSection("Creation tests");

    LinkedList ll = new LinkedList();
    Testing.assertEquals("A new list is empty", "{}", ll.toString());
  }

  private static void testAdd() {

    Testing.testSection("Testing adding elements to the LinkedList");

    LinkedList ll = new LinkedList();
    
    ll.add("A", 0);
    Testing.assertEquals("{} + A", "{A}", ll.toString());

    ll = new LinkedList();
    ll.add("A", 0);
    ll.add("B", 0);
    Testing.assertEquals("{A} + B", "{B, A}", ll.toString());
    
    ll.add("C", -1);
    Testing.assertEquals("Adding to an invalid index", "{B, A}", ll.toString());
    
    ll.add("C", 3);
    Testing.assertEquals("Adding to index greater than size", "{B, A, C}", ll.toString());
    
    ll.add("C", 2);
    Testing.assertEquals("Adding to index equal to size", "{B, A, C, C}", ll.toString());

    ll.add("D", 2);
    Testing.assertEquals("Adding an element in between", "{B, A, D, C, C}", ll.toString());
  }
  
  private static void testRemove()	{
	  
	  Testing.testSection("Testing removing elements from the LinkedList");
	  
	  LinkedList ll = new LinkedList();
	  
	  ll.remove(0);
	  Testing.assertEquals("Removing an element from an empty list", "{}", ll.toString());
	  
	  ll.add("A", 0);
	  ll.remove(0);
	  Testing.assertEquals("{A} - A", "{}", ll.toString());
	  
	  ll.add("A", 0);
	  ll.add("B", 1);
	  ll.remove(1);
	  Testing.assertEquals("{A, B} - B", "{A}", ll.toString());
	  
	  ll.add("B", 1);
	  ll.add("C", 2);
	  ll.add("D", 3);
	  ll.remove(2);
	  Testing.assertEquals("{A, B, C, D} - C", "{A, B, D}", ll.toString());  
  }
  
  private static void testSize()	{
	  
	  Testing.testSection("Testing size");
	  
	  LinkedList ll = new LinkedList();
	  Testing.assertEquals("Finding size of an empty list", 0, ll.size());
	  
	  ll.add("Varun", 0);
	  Testing.assertEquals("Finding size of a 1-element list", 1, ll.size());
	  
	  ll.add("varun", 1);
	  Testing.assertEquals("Finding size of a 2-element list", 2, ll.size());
	  
	  ll.remove(0);
	  Testing.assertEquals("Finding size after removing an element", 1, ll.size());
	  
	  ll.remove(0);
	  Testing.assertEquals("Finding size after removing both elements", 0, ll.size());
  }
  
  private static void testGet()	{
	  
	  Testing.testSection("Testing get");
	  
	  LinkedList ll = new LinkedList();
	  Testing.assertEquals("Testing get for an empty list", null, ll.get(0));
	  
	  ll.add("Varun", 0);
	  Testing.assertEquals("Testing get for a 1-element list", "Varun", ll.get(0));
	  
	  ll.add("varun", 1);
	  Testing.assertEquals("Testing get for a 2-element list" , "varun", ll.get(1));
	  
	  ll.add("varoooon", 2);
	  Testing.assertEquals("Testing get for a 3-element list", "varun", ll.get(1));
  }
  
  private static void testSet()	{
	  
	  Testing.testSection("Testing set");
	  
	  LinkedList ll = new LinkedList();
	  
	  ll.set("Varun", 0);
	  Testing.assertEquals("Setting the value to an empty list", "{}", ll.toString());
	  
	  ll.add("Varun", 0);
	  ll.set("varun", 0);
	  Testing.assertEquals("Setting the value of a 1-element list", "{varun}", ll.toString());
	  
	  ll.add("A", 1);
	  ll.add("B", 2);
	  ll.set("C", 1);
	  Testing.assertEquals("Setting the value of a multi-element list", "{varun, C, B}", ll.toString());
  }
  
  private static void testInvalidIndex()	{
	  
	  Testing.testSection("Testing if index is valid");
	  
	  LinkedList ll = new LinkedList();
	  
	  Testing.assertEquals("Testing when index is negative", true, ll.invalidIndex(-1));
	  
	  Testing.assertEquals("Testing when index is greater than size", true, ll.invalidIndex(1));
	  
	  ll.add("A", 0);
	  Testing.assertEquals("Testing when index is valid", false, ll.invalidIndex(0));
  }
}