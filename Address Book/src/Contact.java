
public interface Contact extends Comparable<Contact> {

	public String getName ();
	public String getAddress ();
	public String getEmail ();
	public String getPhone ();
	
	public String toString ();
    public String prettyString ();
	
}