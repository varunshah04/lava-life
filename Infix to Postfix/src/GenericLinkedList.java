/**
 * Class that models the overall list structure
 * @author Varun Shah
 * @version 2/24/16
 */
public class GenericLinkedList<T> {
    private GenericListNode<T> head;
    private int size;
    
    /**
     * Default constructor that creates an empty list
     */
    public GenericLinkedList()  {
        this.head = new GenericListNode<T>(null);
        this.size = 0;
    }
    
    /**
     * Returns the number of elements in the linked list
     * @return size
     */
    public int size()   {
        return this.size;
    }
    
    /**
     * Returns the String that is contained in the node
     * with the given index
     * @param index
     * @return node.next.data
     */
    public T get(int index) {
        if(index<0 || index>=size()) {
            return null;
        }
        else    {
            GenericListNode<T> runner = head;
            int i = 0;
            while(i<index)  {
                runner = runner.next;
                i++;
            }
            return runner.next.data;
        }
    }
    
    /**
     * Inserts a node containing the String value that is provided
     * into the linked list so that it ends up in the position with
     * the given index 
     * @param value
     * @param index
     */
    public void add(T toAdd, int index) {
        GenericListNode<T> newNode = new GenericListNode<T>(toAdd);
        GenericListNode<T> runner = head;
        if(index<0) {
            System.out.println("Invalid Index");
        }
        else    {
            int i = 0;
            while(runner.next!=null && i<index) {
                runner = runner.next;
                i++;
            }
            newNode.next = runner.next;
            runner.next = newNode;
            size++;
        }
    }
    
    /**
     * Removes the node with the given index from the linked list
     * @param index
     * @return remNode
     */
    public T remove(int index)  { 
        if(index<0 || index>=size()) {
            return null;
        }
        T remNode = get(index);
        GenericListNode<T> runner = head;
        int i = 0;
        while(i<index)  {
            runner = runner.next;
            i++;
        }
        if(runner.next.next!=null)  {
            runner.next = runner.next.next;
        }
        else    {
            runner.next = null;
        }
        size--;
        return remNode;
    }
    
    /**
     * Returns a string representation of the linked
     * @return list
     */
    public String toString()    {
        GenericListNode<T> runner = head.next;
        String list = "{>";
        if(size()!=0)   {
            while(runner!=null && runner.next!=null)    {
                list += runner.data.toString() + ",";
                runner = runner.next;
            }
            if(runner!=null)    {
                list += runner.data.toString();
            }
        }
        list += "}";
        return list;
    }
}