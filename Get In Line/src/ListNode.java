/**
 * Class that defines a single list node
 * @author Varun Shah
 * @version 2/11/16
 */
public class ListNode {
	public String data;
	public ListNode next;
	
	/**
	 * Default constructor creating a node pointing to null
	 * @param data
	 */
	public ListNode(String data)	{
		this.data = data;
		next = null;
	}
	
	/**
	 * Non-default constructor that takes a String and creates
	 * a node containing the given String and its data 
	 * @param data
	 * @param node
	 */
	public ListNode(String data, ListNode node)	{
		this.data = data;
		this.next = node;
	}
	
	/**
	 * Returns the node's data value
	 * @return data
	 */
	public String toString()	{
		return this.data;
	}
}