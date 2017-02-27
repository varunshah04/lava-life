/**
 * A class that represents the ADT of a sequence
 * @authors Varun Shah, Kristina Striegntiz
 * @version 2/11/16
 *
 */
public class Sequence {
	private final LinkedList data;
	private String curElement;
	private int curIndex;
	private int capacity;
	private static final int INITIAL_CAPACITY = 10;
	private static final int CURRENT_INDEX = -1;
	
	/**
	 * Creates a new sequence with initial capacity 10.
	 */
	public Sequence() {
		data = new LinkedList();
		curIndex = CURRENT_INDEX;
		curElement = null;
		capacity = INITIAL_CAPACITY;
	}

	/**
	 * Creates a new sequence.
	 * 
	 * @param initialCapacity
	 *            the initial capacity of the bag.
	 */
	public Sequence(int initialCapacity) {
		data = new LinkedList();
		curIndex = CURRENT_INDEX;
		curElement = null;
		capacity = initialCapacity;
	}

	/**
	 * Adds a string to the sequence in the location before the current element.
	 * If the sequence has no current element, the string is added to the
	 * beginning of the sequence.
	 * 
	 * The added element becomes the current element.
	 * 
	 * If the sequences's capacity has been reached, the sequence will expand to
	 * twice its current capacity plus 1.
	 * 
	 * @param value
	 *            the string to add.
	 */
	public void addBefore(String value) {
		if(size()==getCapacity())	{
			setCapacity((getCapacity()*2)+1);
		}
		if(isCurrent())	{
			data.add(value, curIndex);
		}
		else	{
			data.add(value, 0);
			setCurIndex(0);
		}
		setCurElement(value);
	}
	
	/**
	 * Adds a string to the sequence in the location after the current element.
	 * If the sequence has no current element, the string is added to the end of
	 * the sequence.
	 * 
	 * The added element becomes the current element.
	 * 
	 * If the sequences's capacity has been reached, the sequence will expand to
	 * twice its current capacity plus 1.
	 * 
	 * @param value
	 *            the string to add.
	 */
	public void addAfter(String value) {
		if(size()==getCapacity())	{
			setCapacity((getCapacity()*2)+1);
		}
		if(isCurrent())	{
			data.add(value, curIndex+1);
			curIndex++;
		}
		else	{
			data.add(value, data.size());
			setCurIndex((data.size())-1);
		}
		setCurElement(value);
	}

	/**
	 * Places the contents of another sequence at the end of this sequence.
	 * 
	 * If adding all elements of the other sequence would exceed the capacity of
	 * this sequence, the capacity is changed to make room for all of the
	 * elements to be added.
	 * 
	 * @param addend
	 *            the sequence whose contents should be added.
	 */
	public void addAll(Sequence addend) {
		if((addend.size()+this.data.size())>getCapacity())	{
			ensureCapacity(this.size()+addend.size());
		}
		for(int i=0;i<addend.size();i++)	{
			this.data.add(addend.data.get(i),(this.size()));
		}
	}

	/**
	 * Move forward in the sequence so that the current element is now the next
	 * element in the sequence.
	 * 
	 * If the current element was already the end of the sequence, then
	 * advancing causes there to be no current element.
	 * 
	 * Precondition: should only be called when there is a current element.
	 */
	public void advance() {
		if(isCurrent())	{
			if(curLast())	{
				setCurElement(null);
				setCurIndex(CURRENT_INDEX);
			}
			else	{
				curIndex++;
				setCurElement(data.get(curIndex));
			}
		}
	}

	/**
	 * Make a copy of this sequence. Subsequence changes to the copy do not
	 * affect the current sequence, and vice versa.
	 * 
	 * @return the copy of this sequence.
	 */
	public Sequence clone() {
		Sequence clone = new Sequence(getCapacity());
		for(int i=0;i<data.size();i++)	{
			clone.addAfter(data.get(i));
		}
		clone.curIndex = this.curIndex;
		clone.curElement = this.curElement;
		return clone;
	}

	/**
	 * Create a new sequence that contains all of the elements of one sequence
	 * followed by all of the elements of another sequence.
	 * 
	 * The new sequence does not have a current element. The new sequence has
	 * capacity equal to the sum of the capacities of the sequences being
	 * concatenated.
	 * 
	 * @param s1
	 *            the sequence whose elements should come first in the
	 *            concatenation
	 * @param s2
	 *            the sequence whose elements should come second
	 * @return newSeq
	 * 				   the new sequence 
	 */
	public static Sequence concatenation(Sequence s1, Sequence s2)	{
		Sequence newSeq = new Sequence();
		newSeq.ensureCapacity((s1.getCapacity()+s2.getCapacity()));
		newSeq.addAll(s1);
		newSeq.addAll(s2);
		while(newSeq.isCurrent())	{
			newSeq.advance(); //eventually ensures that there is no current element
		}
		return newSeq;
	}

	/**
	 * Change the current capacity of this sequence. The sequence's capacity
	 * will be changed to be at least a minimum capacity.
	 * 
	 * @param minCapacity
	 *            the minimum capacity that the sequence should now have.
	 */
	public void ensureCapacity(int minCapacity) {
		if(getCapacity()<minCapacity)	{
			setCapacity((getCapacity()*2)+1);
		}
	}

	/**
	 * @return the capacity of the sequence.
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * @return the element at the current location in the sequence, or null if
	 *         there is no current element.
	 */
	public String getCurrent() {
		if(isCurrent())	{
			return curElement;
		}
		else	{
			return null;
		}
	}
	
	/**
	 * @return true if and only if the sequence has a current element.
	 */
	public boolean isCurrent() {
		return curElement!=null;
	}

	/**
	 * Remove the current element from this sequence. The following element, if
	 * there was one, becomes the current element. If there was no following
	 * element (current was at the end of the sequence), the sequence now has no
	 * current element.
	 * 
	 * If there is no current element, does nothing.
	 */
	public void removeCurrent() {
		if(!isCurrent())	{
			if(curLast())	{
				data.remove(curIndex);
				setCurElement(null);
				setCurIndex(CURRENT_INDEX);
			}
			else	{
				data.remove(curIndex);
				setCurElement(data.get(curIndex));
			}
		}
	}

	/**
	 * @return the number of elements stored in the sequence.
	 */
	public int size()	{
		return this.data.size();
	}

	/**
	 * Sets the current element to the start of the sequence. If the sequence is
	 * empty, the sequence has no current element.
	 */
	public void start() {
		if(size()>0)	{
			int startIndex = 0;
			setCurIndex(startIndex);
			setCurElement(this.data.get(curIndex));
		}
		else if(size()==0)	{
			setCurIndex(0);
			curIndex = CURRENT_INDEX;
		}
	}

	/**
	 * Reduce the current capacity to its actual size, so that it has capacity
	 * to store only the elements currently stored.
	 */
	public void trimToSize() {
		setCapacity(size());
	}

	/**
	 * Produce a string representation of this sequence. The current location is
	 * indicated by a >. For example, a sequence with "A" followed by "B", where
	 * "B" is the current element, and the capacity is 5, would print as:
	 * 
	 * {A, >B} (capacity = 5)
	 * 
	 * An empty sequence with a capacity of 10 would print as:
	 * 
	 * {} (capacity = 10)
	 * 
	 * @return a string representation of this sequence.
	 */
	public String toString() {
		String output = "{"; 
		if(size()==0)	{
			output += "} (capacity = " + getCapacity() + ")";
		}
		else	{
			for(int i=0;i<size();i++)	{
				if(i==curIndex)	{
					output += ">" + data.get(i);
				}
				else	{
					output += data.get(i);
				}
				if(i!=size()-1)	{
					output += ", ";
				}
			}
			output += "} (capacity = " + getCapacity() + ")";
		}
		return output;
	}

	/**
	 * Checks whether another sequence is equal to this one. To be considered
	 * equal, the other sequence must have the same elements, in the same order,
	 * and with the same element marked current. The capacity can differ.
	 * 
	 * @param other
	 *            the other Sequence with which to compare
	 * @return true iff the other sequence is equal to this one.
	 */
	public boolean equals(Sequence other) {
		return checkCurrent(this,other) && this.data.toString().equals(other.data.toString());
	}
	
	
	public boolean checkCurrent(Sequence s1, Sequence s2)	{
		if(s1.getCurrent()==null && s2.getCurrent()==null)	{
			return true;
		}
		else if(s1.getCurrent().equalsIgnoreCase(s2.getCurrent()))	{
			return true;
		}
		else	{
			return false;
		}
	}
	
	/**
	 * Sets the index of the current element 
	 * @param index
	 */
	public void setCurIndex(int index)	{
		this.curIndex = index;
	}
	
	/**
	 * Sets the capacity of the sequence
	 * @param capacity
	 */
	public void setCapacity(int capacity)	{
		this.capacity = capacity;
	}
	
	/**
	 * Sets the current element of the sequence
	 * @param element
	 */
	public void setCurElement(String element)	{
		this.curElement = element;
	}
	
	/**
	 * Checks if the current element is at the end of the Sequence
	 * @return true if the current element is at the end
	 * 		   false if not
	 */
	public boolean curLast()	{
		return curIndex==(size()-1);
	}
}