/**
 * Class used to store tokens
 * @author Varun Shah
 * @version 2/22/16
 * @param <T>
 */
public class Stack<T> {
    private GenericLinkedList<T> ll;
    private int size;
    
    /**
     * Default Constructor
     */
    public Stack() {
        ll = new GenericLinkedList<T>();
        size = 0;
    }
    
    /**
     * Pushes an item to the top of the stack
     * @param toPush
     */
    public void push(T toPush) {
        ll.add(toPush, 0);
        size++;
    }
    
    /**
     * Removes data at the start of the stack
     * @return removed data
     */
    public T pop() {
        T head = ll.remove(0);
        size--;
        return head;
    }
    
    /**
     * Returns the elements at the top of the stack
     * @return top element
     */
    public T peek() {
        return ll.get(0);
    }
    
    /**
     * Returns a printable form of the stack
     * @return output
     */
    public String toString() {
        String output = ll.toString();
        return output;
    }
    
    /**
     * Returns the size of the stack
     * @return size
     */
    public int size()   {
        return this.size;
    }
}