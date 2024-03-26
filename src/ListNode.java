public class ListNode {
    // instance variables

    private int data;
    private ListNode next;

    /**
     * Constructor for objects of class List
     * @param the value to be in the node
     */
    public ListNode(int data)
    {
        // initialise instance variables
        this.data = data;
        this.next = null;
    }

    /**
     * Default onstructor for objects of class List
     */
    public ListNode()
    {
        // initialise instance variables
        this.data = 0;
        this.next = null;
    }

    /**
     * Get the next node
     *
     * @return the next node
     */
    public ListNode getNext()
    {
        return next;
    }

    /**
     * Get the mark
     *
     * @return mark at this node
     */
    public int getData()
    {
        return data;
    }


    /**
     * Set the next node
     *
     * @param  the node to be added at this node's next
     */
    public void setNext(ListNode next)
    {
        this.next = next;
    }

    /**
     * Return a string containing the data from this node, formatted
     */
    public String printInfo()
    {
        String info;

        info = "data: "+data;

        return info;
    }
}
