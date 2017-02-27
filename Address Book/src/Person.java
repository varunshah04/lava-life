/**
 * Class that deals with personal information
 * @author Varun Shah
 * @version 2/17/16
 */
public class Person implements Contact {
	private String last;
	private String first;
	private String address;
	private String email;
	private String phone;
	private String birthday;
	
	/**
	 * Non-default constructor that initializes the
	 * last name
	 * @param last
	 */
	public Person(String last)	{
		this.last = last;
		this.first = "";
		this.address = "";
		this.email = "";
		this.phone = "";
		this.birthday = "";
	}
	
	/**
	 * Non-default constructor that initializes both
	 * the first and last names
	 * @param last
	 * @param first
	 */
	public Person(String last, String first)	{
		this.last = last;
		this.first = first;
		this.address = "";
		this.email = "";
		this.phone = "";
		this.birthday = "";
	}
	
	/**
	 * Gets the name of the person
	 * @return name
	 */
	public String getName()	{
		String name = last;
		if(first!="")	{
			name += ", " + first;
		}
		return name;
	}
	
	/**
	 * Gets the personal address
	 * @return address
	 */
	public String getAddress()	{
		return address;
	}
	
	/**
	 * Gets the personal email address
	 * @return email
	 */
	public String getEmail()	{
		return email;
	}
	
	/**
	 * Gets the personal phone number
	 * @return phone
	 */
	public String getPhone()	{
		return phone;
	}
	
	/**
	 * Gets the person's birthday
	 * @return hours
	 */
	public String getBirthday()	{
		return birthday;
	}
	
	/**
	 * Sets the last name of the person
	 * @param last
	 */
	public void setLast(String last)	{
		this.last = last;
	}
	
	/**
	 * Sets the first name of the person
	 * @param first
	 */
	public void setFirst(String first)	{
		this.first = first;
	}
	
	/**
	 * Sets the address
	 * @param address
	 */
	public void setAddress(String address)	{
		this.address = address;
	}
	
	/**
	 * Sets the email address
	 * @param email
	 */
	public void setEmail(String email)	{
		this.email = email;
	}
	
	/**
	 * Sets the phone number
	 * @param phone
	 */
	public void setPhone(String phone)	{
		this.phone = phone;
	}
	
	/**
	 * Sets the business hours
	 * @param hours
	 */
	public void setBirthday(String birthday)	{
		this.birthday = birthday;
	}
	
	/**
	 * Returns the name of the person as a printable String
	 * @return output
	 */
	public String toString()	{
		String output = getName();
		return output;
	}
	
	/**
	 * Returns the personal information as a printable String
	 * @return output
	 */
	public String prettyString()	{
		String output = "NAME: " + getName() + "\n" + "-----\n";
		if(getAddress()!="")	{
			output += "address: " + getAddress() + "\n";
		}
		if(getPhone()!="")	{
			output += "phone: " + getPhone() + "\n";
		}
		if(getEmail()!="")	{
			output += "email: " + getEmail() + "\n";
		}
		if(getBirthday()!="")	{
			output += "birthday: " + getBirthday();
		}
		return output;
	}
	
	/**
	 * Compares the names of two people
	 * return 0 if both strings are equal
	 * 		  >0 if name1 is alphabetically before name2
	 * 		  <0 if name1 is alphabetically after name2
	 */
	public int compareTo(Contact other)	{
		String name1 = this.getName().toUpperCase();
		String name2 = other.getName().toUpperCase();
		return name1.compareTo(name2);
	}
}