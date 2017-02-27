/**
 * Class that tests the methods of Sequence
 * @author Varun Shah
 * @version 2/11/16
 */
public class SequenceTests {

    public static void main(String[] args) {
		
	Testing.setVerbose(true);
	System.out.println("Starting Tests");

	testCreate();
	testAddBefore();
	testAddAfter();
	testAddAll();
	testAdvance();
	testRemoveCurrent();
	testClone();
	testConcatenation();
	testEquals();

	System.out.println("Tests Complete");
    }

    private static void testCreate() {
    	
	Testing.testSection("Creation tests");

	Sequence s1 = new Sequence();
	Testing.assertEquals("Default constructor", "{} (capacity = 10)", s1.toString());
	Testing.assertEquals("Default constructor, initial size", 0, s1.size());

	Sequence s2 = new Sequence(20);
	Testing.assertEquals("Non-default constructor", "{} (capacity = 20)",
			     s2.toString());
	Testing.assertEquals("Non-default constructor, initial size", 0, s2.size());
    }

    private static void testAddBefore() {

	Testing.testSection("Testing addBefore");
	
	Sequence s1 = new Sequence();
	
	s1.addBefore("A");
	Testing.assertEquals("Adding A to empty sequence", "{>A} (capacity = 10)", s1.toString());
	Testing.assertEquals("Size after adding one item", 1, s1.size());

	s1.addBefore("B");
	Testing.assertEquals("Adding A and then B to empty sequence", "{>B, A} (capacity = 10)", s1.toString());
	Testing.assertEquals("Size after adding two items", 2, s1.size());
	
	s1.advance(); //makes A the current element
	s1.removeCurrent(); //removes A so there is no current element
	s1.addBefore("C");
	Testing.assertEquals("Removing current and adding C", "{>C, B} (capacity = 10)", s1.toString());
	Testing.assertEquals("Size after adding D", 2, s1.size());

	s1.addBefore("D");
	s1.advance(); //makes "C" the current element
	s1.addBefore("E");
	Testing.assertEquals("Making A current and then adding C", "{D, >E, C, B} (capacity = 10)", s1.toString());
	Testing.assertEquals("Size after adding C", 4, s1.size());
    }
    
    private static void testAddAfter()	{
    	
    	Testing.testSection("Testing addAfter");
    	
    	Sequence s1 = new Sequence();
    	
    	s1.addAfter("A");
    	Testing.assertEquals("Adding A to empty sequence", "{>A} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after adding one item", 1, s1.size());

    	s1.addAfter("B");
    	Testing.assertEquals("Adding A and then B to empty sequence", "{A, >B} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after adding two items", 2, s1.size());

    	s1.removeCurrent(); //removes B so there is no current element
    	s1.addAfter("C");
    	Testing.assertEquals("Removing current and then adding C", "{A, >C} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after removing current and adding C", 2, s1.size());
    }
    
    private static void testAddAll()	{
    	
    	Testing.testSection("Testing addAll");
    	
    	Sequence s1 = new Sequence(2);
    	Sequence s2 = new Sequence(2);
    	Sequence s3 = new Sequence(2);
    	
    	s1.addAfter("cat");
    	s1.addAfter("dog");
    	
    	s1.addAll(s2);
    	Testing.assertEquals("Adding an empty sequence to a non-empty one", "{cat, >dog, null, null} (capacity = 4)", s1.toString());
    	Testing.assertEquals("Size after adding sequence", 4, s1.size());
    	
    	s2.addAfter("rat");
    	s2.addAfter("bone");
    	
    	s1.addAll(s2);
    	Testing.assertEquals("Adding a non-empty sequence to a non-empty one", "{cat, >dog, null, null, rat, bone} (capacity = 6)", s1.toString());
    	Testing.assertEquals("Size after adding sequence", 6, s1.size());
    	
    	s3.addAll(s1);
    	Testing.assertEquals("Adding a non-empty sequence to an empty one", "{cat, >dog, null, null, rat, bone} (capacity = 6)", s1.toString());
    	Testing.assertEquals("Size after adding sequence", 6, s1.size());
    }
    
    private static void testAdvance()	{
    	
    	Testing.testSection("Testing advance");
    	
    	Sequence s1 = new Sequence();
    	
    	s1.addAfter("A");
    	s1.addBefore("B");
    	s1.advance();
    	Testing.assertEquals("Advancing the current element", "{B, >A} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after advance", 2, s1.size());
    	
    	s1.advance();
    	Testing.assertEquals("Advancing when current element is at the end", "{B, A} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after advance", 2, s1.size());
    }
    
    private static void testRemoveCurrent()	{
    	Testing.testSection("Testing removeCurrent");
    	
    	Sequence s1 = new Sequence();
    	
    	s1.addAfter("A");
    	s1.removeCurrent();
    	Testing.assertEquals("Removing the current element in a 1-element sequence", "{} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after removing current element", 0, s1.size());
    	
    	s1.addAfter("A");
    	s1.addAfter("B");
    	s1.removeCurrent();
    	Testing.assertEquals("Removing the current element in a 2-element sequence", "{A} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after removing current element", 1, s1.size());
    	
    	s1.addBefore("B");
    	s1.removeCurrent();
    	Testing.assertEquals("Removing the current element in a 2-element sequence", "{>A} (capacity = 10)", s1.toString());
    	Testing.assertEquals("Size after removing current element", 1, s1.size());
    }
    
    private static void testClone()	{
    	Testing.testSection("Testing clone");
    	
    	Sequence s1 = new Sequence(2);
    	
    	Testing.assertEquals("Cloning an empty sequence", "{null, null} (capacity = 2)", s1.clone().toString());
    	Testing.assertEquals("Size of clone", 2, s1.clone().size());
    	
    	s1.addAfter("A");
    	s1.addAfter("B");
    	Testing.assertEquals("Cloning a non-empty sequence", "{A, >B} (capacity = 2)", s1.clone().toString());
    	Testing.assertEquals("Size of clone", 2, s1.clone().size());
    }
    
    private static void testConcatenation()	{
    	Testing.testSection("Testing concatenation");
    	
    	Sequence s1 = new Sequence(2);
    	Sequence s2 = new Sequence(2);
    	
    	Testing.assertEquals("Concatenating two empty sequences", "{} (capacity = 4)", Sequence.concatenation(s1,s2).toString());
    	Testing.assertEquals("Size of new sequence", 0, Sequence.concatenation(s1,s2).size());
    	
    	s1.addAfter("A");
    	s2.addAll(s1);
    	System.out.println(s2.toString());
    	
    	Testing.assertEquals("Concatenating two non-empty sequences", "{A, A} (capacity = 4)", Sequence.concatenation(s1, s2).toString());
    	Testing.assertEquals("Size of new sequence", 3, Sequence.concatenation(s1, s2).size());
    }
    
    private static void testEquals()	{
    	Testing.testSection("Testing equals");
    	
    	Sequence s1 = new Sequence(2);
    	Sequence s2 = new Sequence(2);
    	
    	s1.addAfter("A");
    	s2.addAfter("A");;
    	Testing.assertEquals("Equating two equal sequences", true, s1.equals(s2));
    	
    	s1.addBefore("B");
    	s2.addAfter("B");
    	Testing.assertEquals("Equating sequences with same elements in different order", false, s1.equals(s2));
    	
    	s1.removeCurrent();
    	s1.addAfter("B");
    	s2.advance();
    	Testing.assertEquals("Equating two sequences with the same elements but different current elements", false, s1.equals(s2));
    }
}