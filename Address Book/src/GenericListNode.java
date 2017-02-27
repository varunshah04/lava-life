/**
 * Class that defines a single list node
 * @author Varun Shah
 * @version 2/17/16
 */
public class GenericListNode<T extends Comparable <T>>  {
	public T data;
	public GenericListNode<T> next;
	
	/**
	 * Non-default constructor creating a node pointing to null
	 * @param data
	 */
	public GenericListNode(T data)	{
		this.data = data;
		next = null;
	}
	
	/**
	 * Non-default constructor that takes a String and creates
	 * a node containing the given String and its data 
	 * @param data
	 * @param node
	 */
	public GenericListNode(T data, GenericListNode<T> next)	{
		this.data = data;
		this.next = next;
	}
	
	/**
	 * Returns the node's data value
	 * @return data
	 */
	public String toString()	{
		
		return this.data.toString();
	}
}