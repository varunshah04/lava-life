/**
 * Class that models the overall list structure
 * @author Varun Shah
 * @version 2/11/16
 */
public class LinkedList {
	
	/**
	 * Invariants:
	 * 			1) Head represents the first node that either
	 * 			   connects to null or a linked node
	 * 			2) Size represents the number of nodes in the
	 * 			   linked list
	 */
	private ListNode head;
	private int size;
	
	/**
	 * Default constructor that creates an empty list
	 */
	public LinkedList()	{
		this.head = new ListNode(null);
		this.size = 0;
	}
	
	/**
	 * Returns the number of elements in the linked list
	 * @return size
	 */
	public int size()	{
		return this.size;
	}
	
	/**
	 * Returns the String that is contained in the node
	 * with the given index
	 * @param index
	 * @return node.next.data
	 */
	public String get(int index)	{
		if(invalidIndex(index))	{
			return null;
		}
		else	{
			ListNode runner = head;
			int i = 0;
			while(i<index)	{
				runner = runner.next;
				i++;
			}
			return runner.next.data;
		}
	}
	
	/**
	 * Sets the data of the node with the given index 
	 * @param value
	 * @param index
	 */
	public void set(String value, int index)	{
		if(invalidIndex(index))	{
			System.out.println("Invalid index");
		}
		else	{
			ListNode runner = head.next;
			int i = 0;
			while(i<index)	{
				runner = runner.next;
				i++;
			}
			runner.data = value;
		}
	}
	
	/**
	 * Inserts a node containing the String value that is provided
	 * into the linked list so that it ends up in the position with
	 * the given index 
	 * @param value
	 * @param index
	 */
	public void add(String value, int index)	{
		ListNode newNode = new ListNode(value);
		ListNode runner = head;
		if(index<0)	{
			System.out.println("Invalid Index");
		}
		else	{
			int i = 0;
			while(runner.next!=null && i<index)	{
				runner = runner.next;
				i++;
			}
			newNode.next = runner.next;
			runner.next = newNode;
			size++;
		}
	}
	
	/**
	 * Removes the nodes with the given index from the linked list
	 * @param index
	 * @return remNode
	 */
	public String remove(int index)	{
		String remNode = get(index); 
		if(invalidIndex(index))	{
			return null;
		}
		ListNode runner = head;
		int i = 0;
		while(i<index)	{
			runner = runner.next;
			i++;
		}
		if(runner.next.next!=null)	{
			runner.next = runner.next.next;
		}
		else	{
			runner.next = null;
		}
		size--;
		return remNode;
	}
	
	/**
	 * Helper method that checks whether a given index is valid
	 * for remove and get
	 * @param index
	 * @return true if invalid and false is valid
	 */
	public boolean invalidIndex(int index)	{
		return (index<0 || index>=size());	
	}
	
	/**
	 * Returns a string representation of the linked
	 * @return list
	 */
	public String toString()	{
		ListNode runner = head.next;
		String list = "{";
		if(size()!=0)	{
			while(runner!=null && runner.next!=null)	{
				list += runner.data.toString() + ", ";
				runner = runner.next;
			}
			if(runner!=null)	{
				list += runner.data.toString();
			}
		}
		list += "}";
		return list;
	}
}