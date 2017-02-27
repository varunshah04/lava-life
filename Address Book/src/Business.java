/**
 * Class that deals with the information of the business
 * @author Varun Shah
 * @version 2/17/16
 */
public class Business implements Contact {
	private String name;
	private String address;
	private String email;
	private String phone;
	private String hours;
	
	/**
	 * Non-default constructor that takes the business
	 * name as a parameter 
	 * @param name
	 */
	public Business(String name)	{
		this.name = name;
		this.address = "";
		this.email = "";
		this.phone = "";
		this.hours = "";
	}
	
	/**
	 * Gets the business name
	 * @return name
	 */
	public String getName()	{
		return name;
	}
	
	/**
	 * Gets the business address
	 * @return address
	 */
	public String getAddress()	{
		return address;
	}
	
	/**
	 * Gets the business email address
	 * @return email
	 */
	public String getEmail()	{
		return email;
	}
	
	/**
	 * Gets the business phone number
	 * @return phone
	 */
	public String getPhone()	{
		return phone;
	}
	
	/**
	 * Gets the business hours
	 * @return hours
	 */
	public String getHours()	{
		return hours;
	}
	
	/**
	 * Sets the name
	 * @param name
	 */
	public void setName(String name)	{
		this.name = name;
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
	public void setHours(String hours)	{
		this.hours = hours;
	}
	
	/**
	 * Returns the name of the business as a printable string
	 * @return output
	 */
	public String toString()	{
		String output = getName();
		return output;
	}
	
	/**
	 * Returns the information of the business as a printable string
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
		if(getHours()!="")	{
			output += "hours: " + getHours();
		}
		return output;
	}
	
	/**
	 * Compares the names of two contacts
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