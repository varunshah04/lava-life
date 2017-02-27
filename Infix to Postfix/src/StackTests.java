public class StackTests {

    public static void main(String[] args) {
        Testing.setVerbose(true);
        System.out.println("Starting Tests");

        testCreate();
        testToString();
        testPush();
        testPop();
        testPeek();

    }

    private static void testToString() {
        Testing.testSection("Testing toString");

        Stack<String> stack = new Stack<String>();
        Testing.assertEquals("An empty stack", "{>}", stack.toString());

        stack.push("A");
        Testing.assertEquals("A stack with one item", "{>A}", stack.toString());

        stack.push("B");
        stack.push("C");
        Testing.assertEquals("A stack with several items", "{>C,B,A}", stack.toString());
    }
    
    private static void testCreate()    {
        Testing.testSection("Creation Test");
        
        Stack<String> stack = new Stack<String>();
        Testing.assertEquals("Creating an empty stack", "{>}", stack.toString());
    }
    
    private static void testPush()  {
        Testing.testSection("Testing push");
        
        Stack<String> stack = new Stack<String>();
        
        stack.push("A");
        Testing.assertEquals("Pushing onto an empty stack", "{>A}", stack.toString());
        
        stack.push("B");
        Testing.assertEquals("Pushing onto a 1-element stack", "{>B,A}", stack.toString());
        
        stack.push("C");
        Testing.assertEquals("Pushing onto a 2-element stack", "{>C,B,A}", stack.toString());
    }
    
    private static void testPop()   {
        Testing.testSection("Testing pop");
        
        Stack<String> stack = new Stack<String>();
        Testing.assertEquals("Testing pop for an empty stack", null, stack.pop());
        
        stack.push("A");
        Testing.assertEquals("Testing pop for a 1-element stack", "A", stack.pop());
        Testing.assertEquals("Checking stack", "{>}", stack.toString());
        
        stack.push("A");
        stack.push("B");
        Testing.assertEquals("Testing pop for a 2-element stack", "B", stack.pop());
        Testing.assertEquals("Checking stack", "{>A}", stack.toString());
    }
    
    private static void testPeek()  {
        Testing.testSection("Testing peek");
        
        Stack<String> stack = new Stack<String>();
        Testing.assertEquals("Testing peek for an empty stack", null, stack.peek());
        
        stack.push("A");
        Testing.assertEquals("Testing peek for a 1-element stack", "A", stack.peek());
        
        stack.push("B");
        Testing.assertEquals("Testing peek for a 2-element stack", "B", stack.peek());
    }
}