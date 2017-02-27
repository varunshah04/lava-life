public class GenericLinkedListTests {

  public static void main(String[] args) {

    Testing.setVerbose(true);
    System.out.println("Starting Tests");

    testCreate();
    testAdd();
    testRemove();
    testGet();

    System.out.println("Tests Complete");
  }

  private static void testCreate() {
    Testing.testSection("Creation tests");

    GenericLinkedList<String> ll = new GenericLinkedList<String>();
    Testing.assertEquals("A new list is empty", "{>}", ll.toString());

  }

  private static void testAdd() {

    Testing.testSection("Testing adding elements to the LinkedList");

    GenericLinkedList<String> ll = new GenericLinkedList<String>();
    ll.add("A", 0);
    Testing.assertEquals("{>} + A", "{>A}", ll.toString());

    ll = new GenericLinkedList<String>();
    ll.add("A", 0);
    ll.add("B", 0);
    Testing.assertEquals("{>A} + B", "{>B,A}", ll.toString());
  }
  
  private static void testRemove()  {
      
      Testing.testSection("Testing remove");
      
      GenericLinkedList<String> ll = new GenericLinkedList<String>();
      Testing.assertEquals("Testing remove for an empty list", null, ll.remove(0));
      Testing.assertEquals("Checking the list", "{>}", ll.toString());
      
      ll.add("A", 0);
      Testing.assertEquals("Removing from a 1-element list", "A", ll.remove(0));
      Testing.assertEquals("Checking the list", "{>}", ll.toString());
      
      ll.add("A", 0);
      ll.add("B", 1);
      ll.add("C", 2);
      Testing.assertEquals("Removing the middle element", "B", ll.remove(1));
      Testing.assertEquals("Checking the list", "{>A,C}", ll.toString());
      Testing.assertEquals("Removing the last element", "C", ll.remove(ll.size()-1));
      Testing.assertEquals("Checking the list", "{>A}", ll.toString());
  }
  
  private static void testGet() {
      
      Testing.testSection("Testing get");
      
      GenericLinkedList<String> ll = new GenericLinkedList<String>();
      Testing.assertEquals("Testing Get for an empty list", null, ll.get(0));
      
      ll.add("A", 0);
      Testing.assertEquals("Testing Get for an invalid index", null, ll.get(1));
      Testing.assertEquals("Testing Get for a negative index", null, ll.get(-1));
      Testing.assertEquals("Testing Get for a 1-element list", "A", ll.get(0));
      
      ll.add("B", 0);
      Testing.assertEquals("Testing Get for a 2-element list", "A", ll.get(1));
  }
}