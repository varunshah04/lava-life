
public class AddressBookTests {
	
	public static void main(String[] args) {

	    Testing.setVerbose(true);
	    System.out.println("Starting Tests");

	    testCreate();
	    testAdd();

	    System.out.println("Tests Complete");
	  }
	
	private static void testCreate()	{
		Testing.testSection("Creation Tests");
		
		AddressBook ab = new AddressBook();
		Testing.assertEquals("Creating an address book", "{}", ab.toString());
	}
	
	private static void testAdd()	{
		Testing.testSection("Add Tests");
		
		AddressBook ab = new AddressBook();
		
		ab.add();
		Testing.assertEquals("Adding a person to an empty book", "{Varun Shipping}", ab.toString());
		
		ab.add();
		ab.add();
		Testing.assertEquals("Adding to a non-empty book", "{Andy, Benny, Varun Shipping}",
				ab.toString());
	}
}