public class PersonTests {

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

    Person p1 = new Person("Obama", "Barack");
    Testing.assertEquals("Person created with a last and first name", "Obama, Barack", p1.toString());

    Person p2 = new Person("Bush");
    Testing.assertEquals("Person created with just a last name", "Bush", p2.toString());
  }

  private static void testToStringAndPrettyString() {
    Testing.testSection("Testing toString and prettyString");

    Person p1 = new Person("Obama", "Barack");
    p1.setAddress("White House");
    p1.setPhone("202-456-1111");
    p1.setEmail("president@whitehouse.gov");
    p1.setBirthday("08/04/1961");

    Testing.assertEquals("toString output:", "Obama, Barack", p1.toString());

    String expected = "NAME: Obama, Barack\n" + "-----\n" + "address: White House\n" + "phone: 202-456-1111\n"
        + "email: president@whitehouse.gov\n" + "birthday: 08/04/1961";
    Testing.assertEquals("prettyString output:", expected, p1.prettyString());

    Person p2 = new Person("Claus", "Santa");
    p2.setAddress("Northpole");
    expected = "NAME: Claus, Santa\n" + "-----\n" + "address: Northpole" + "\n";
    Testing.assertEquals("prettyString output:", expected, p2.prettyString());
  }
  
  private static void testCompareTo()	{
	  Testing.testSection("Testing compareTo");
	  
	  Person p1 = new Person("Shah");
	  Person p2 = new Person("Anderson");
	  Testing.assertEquals("Comparing 2 names", 18, p1.compareTo(p2));
	  Testing.assertEquals("Comparing them in reverse", -18, p2.compareTo(p1));
  }
}