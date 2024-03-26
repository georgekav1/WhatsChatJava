import java.util.EmptyStackException;
public class Stack {
    // instance variables - replace the example below with your own
    List theStack;

    /**
     * Constructor for objects of class Stack
     */
    public Stack()
    {
        theStack = new List();

    }

    /**
     * method to check if stack is empty
     */
    public boolean emptyCheck()
    {
        return theStack.isEmpty();

    }

    /**
     * method to a number onto stack
     * @param data to be pushed onto stack
     */
    public void push(int data)
    {
        theStack.addToList(data);
    }

    /**
     * method to pop values from the stack
     * @returns value from top of stack if stack is npt empty
     */
    public int pop()
    {
        ListNode popped = theStack.deleteFromStart();
        if (popped != null) {
            return popped.getData();
        } else {
            // Stack is empty, throw EmptyStackException
            System.out.println("Error: Trying to pop from an empty stack.\nYou likely entered your equation incorreclty please try again.");
            throw new EmptyStackException();

        }
        // return popped.getData();

    }

    /**
     * method to print a number
     */
    public void print()
    {
        theStack.printList();
    }
}
