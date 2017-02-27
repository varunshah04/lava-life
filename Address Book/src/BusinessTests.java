public class BusinessTests {

  public static void main(String[] args) {

    Testing.setVerbose(true);
    System.out.println("Starting Tests");

    testCreate();
    testToStringAndPrettyString();
    testCompareTo();

    System.out.println("Tests Complete");
  }

  private static void testCreate() {
    Testing.testSection("Creation tests");

    Business b1 = new Business("Rudi's Bike Repair");
    Testing.assertEquals("Newly created business", "Rudi's Bike Repair", b1.toString());
  }

  private static void testToStringAndPrettyString() {
    Testing.testSection("Testing toString and prettyString");
    
    Business b1 = new Business("Rudi's Bike Repair");
    b1.setAddress("Somewhere in Florida");
    b1.setPhone("813-997-4121");
    b1.setEmail("rudi@bikerepair.com");
    b1.setHours("Mo-Fri 10-7");

    Testing.assertEquals("toString output:", "Rudi's Bike Repair", b1.toString());

    String expected = "NAME: Rudi's Bike Repair\n" + "-----\n" + "address: Somewhere in Florida\n" + "phone: 813-997-4121\n"
        + "email: rudi@bikerepair.com\n" + "hours: Mo-Fri 10-7";
    Testing.assertEquals("prettyString output:", expected, b1.prettyString());

    Business b2 = new Business("Kangaroo Club");
    b2.setAddress("Australia");
    expected = "NAME: Kangaroo Club\n" + "-----\n" + "address: Australia" + "\n";
    Testing.assertEquals("prettyString output:", expected, b2.prettyString());
  }
  
  private static void testCompareTo()	{
	  Testing.testSection("Testing compareTo");
	  
	  Business b1 = new Business("Shah");
	  Business b2 = new Business("Anderson");
	  Testing.assertEquals("Comparing 2 names", 18, b1.compareTo(b2));
	  Testing.assertEquals("Comparing them in reverse", -18, b2.compareTo(b1));
  }
}