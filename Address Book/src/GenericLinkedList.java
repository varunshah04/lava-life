/**
 * Class that models the overall list structure
 * @author Varun Shah
 * @version 2/17/16
 */
public class GenericLinkedList<T extends Comparable<T>> {
	private GenericListNode<T> head;
	private int size;
	
	/**
	 * Default constructor that creates an empty list
	 */
	public GenericLinkedList()	{
		this.head = new GenericListNode<T>(null);
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
	public T get(int index)	{
		if(invalidIndex(index))	{
			return null;
		}
		else	{
			GenericListNode<T> runner = head;
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
	public void set(T value, int index)	{
		if(invalidIndex(index))	{
			System.out.println("Invalid index");
		}
		else	{
			GenericListNode<T> runner = head.next;
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
	public void add(T toAdd, int index)	{
		GenericListNode<T> newNode = new GenericListNode<T>(toAdd);
		GenericListNode<T> runner = head;
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
	 * Adds a node to the list in a spot such that the list is in
	 * alphabetic order
	 * @param toAdd
	 */
	public void insertSorted(T toAdd)	{
		int index = 0;
		GenericListNode<T> runner = head;
		if(size()==0)	{
			add(toAdd, 0);
		}
		else	{
			while(runner!=null && runner.next!=null &&
					runner.next.data.compareTo(toAdd)<=0)	{
				runner = runner.next;
				index++;
			}
			add(toAdd, index);
		}
	}
	
	/**
	 * Removes the node with the given index from the linked list
	 * @param index
	 * @return remNode
	 */
	public T remove(int index)	{ 
		if(invalidIndex(index))	{
			return null;
		}
		T remNode = get(index);
		GenericListNode<T> runner = head;
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
	 * Removes the specified node from the list and returns it
	 * @param toRemove
	 * @return remNode
	 */
	public T remove(T toRemove)	{
		if(find(toRemove)==null)	{
			return null;
		}
		GenericListNode<T> runner = head;
		if(runner.data.compareTo(toRemove)==0)	{
			head = runner.next;
			size--;
			return runner.data;
		}
		else	{
			while(runner.data.compareTo(toRemove)!=0 &&
					runner.data!=null)	{
				runner = runner.next;
			}
			GenericListNode<T> remNode = runner;
			runner = runner.next;
			size--;
			return remNode.data;
		}
	}
	
	/**
	 * Finds the specified node in the list and returns it
	 * @param toFind
	 * @return
	 */
	public T find(T toFind)	{
		GenericListNode<T> runner = head;
		if(runner==null)	{
			return null;
		}
		else	{
			while(runner!=null &&
					runner.data.compareTo(toFind)!=0)	{
				runner = runner.next;
			}
			if(runner==null)	{
				return null;
			}
			else	{
				return runner.data;
			}
		}
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
		GenericListNode<T> runner = head.next;
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