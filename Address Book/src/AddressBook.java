import java.util.Scanner;
/**
 * Class that models the behavior of an address book
 * @author Varun Shah
 * @version 2/18/16
 */
public class AddressBook {
	GenericLinkedList<Contact> contact;
	Scanner sc = new Scanner(System.in);
	private String last;
	private String first;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String birthday;
	private String hours;
	
	/**
	 * Default constructor
	 */
	public AddressBook()	{
		contact = new GenericLinkedList<Contact>();
		last = "";
		first = "";
		name = "";
		email = "";
		address = "";
		phone = "";
		birthday = "";
		hours = "";
	}
	
	/**
	 * Adds a new contact to the address book
	 */
	public void add()	{
		System.out.println("Enter p for person, or b for business");
		String choice = sc.next();
		if(choice.equalsIgnoreCase("p"))	{
			System.out.print("Enter last name: ");
			while(last.equals(""))	{
				last = sc.nextLine();
			}
			
			Person newPer = new Person(last);
			
			System.out.print("Enter first name: ");
			first = sc.nextLine();
			newPer.setFirst(first);
			
			System.out.print("Enter address: ");
			address = sc.nextLine();
			newPer.setAddress(address);
			
			System.out.print("Enter phone number: ");
			phone = sc.nextLine();
			newPer.setPhone(phone);
			
			System.out.print("Enter email address");
			email = sc.nextLine();
			newPer.setEmail(email);
			
			System.out.print("Enter date of birth: ");
			birthday = sc.nextLine();
			newPer.setBirthday(birthday);
			
			contact.insertSorted(newPer);
		}
		else if(choice.equalsIgnoreCase("b"))	{
			System.out.print("Enter name of the business: ");
			while(name.equals(""))	{
				name = sc.nextLine();
			}
			
			Business newB = new Business(name);
			
			System.out.print("Enter the address: ");
			address = sc.nextLine();
			newB.setAddress(address);
			
			System.out.print("Enter the phone number: ");
			phone = sc.nextLine();
			newB.setPhone(phone);
			
			System.out.print("Enter the email address: ");
			email = sc.nextLine();
			newB.setEmail(email);
			
			System.out.print("Enter business hours: ");
			hours = sc.nextLine();
			newB.setHours(hours);
			
			contact.insertSorted(newB);
		}
		else	{
			System.out.println("Invalid Input!");
			add();
		}
	}
	
	/**
	 * Finds and prints out the contact information of the
	 * given business or person
	 * @param toFind
	 */
	public void find(String name)	{
		//we use business because find only compares the name
		//so it does not matter is it is a business or person
		Business newB = new Business(name);
		Contact con = contact.find(newB);
		if(con==null)	{
			System.out.println("Not found!");
		}
		else	{
			System.out.println(con.prettyString());
		}
	}
	
	/**
	 * Finds and remove the given contact and prints what
	 * has been removed
	 * @param toRemove
	 */
	public void remove(String name)	{
		Business newB = new Business(name);
		Contact con = contact.find(newB);
		if(con==null)	{
			System.out.println("Not found!");
		}
		else	{
			System.out.println("The following was removed from address book:");
			System.out.println(contact.remove(con));
		}
	}
	
	/**
	 * Returns the address book as a printable String
	 */
	public String toString()	{
		return contact.toString();
	}
}