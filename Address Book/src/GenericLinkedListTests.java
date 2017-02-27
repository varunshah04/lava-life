public class GenericLinkedListTests {

  public static void main(String[] args) {

    Testing.setVerbose(true);
    System.out.println("Starting Tests");

    testCreate();
    testAdd();
    testInsertSorted();

    System.out.println("Tests Complete");
  }

  private static void testCreate() {
    Testing.testSection("Creation tests");

    GenericLinkedList<String> ll = new GenericLinkedList<String>();
    Testing.assertEquals("A new list is empty", "{}", ll.toString());

  }

  private static void testAdd() {

    Testing.testSection("Testing adding elements to the LinkedList");

    GenericLinkedList<String> ll = new GenericLinkedList<String>();
    ll.add("A", 0);
    Testing.assertEquals("{} + A", "{A}", ll.toString());

    ll = new GenericLinkedList<String>();
    ll.add("A", 0);
    ll.add("B", 0);
    Testing.assertEquals("{A} + B", "{B, A}", ll.toString());
  }
  
  private static void testInsertSorted()	{
	  
	  Testing.testSection("Testing insertSorted");
	  
	  GenericLinkedList<String> ll = new GenericLinkedList<String>();
	  
	  ll.insertSorted("A");
	  Testing.assertEquals("Inserting into an empty list", "{A}", ll.toString());
	  
	  ll.insertSorted("C");
	  Testing.assertEquals("Inserting into a non-empty list", "{A, C}", ll.toString());
	  
	  ll.insertSorted("B");
	  Testing.assertEquals("Inserting into a 2-element list", "{A, B, C}", ll.toString());
	  
	  ll.insertSorted("B");
	  Testing.assertEquals("Inserting into a 3-element list", "{A, B, B, C}", ll.toString());
  }
}