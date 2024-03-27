import java.util.*;
public class ListNode {
    // instance variables

    private String data;
    private Date time;
    private ListNode next;

    /**
     * Constructor for objects of class List
     * @param data the value to be in the node
     * @param time time of data entry
     */
    public ListNode(String data, Date time)
    {
        // initialise instance variables
        this.data = data;
        this.time = time;
        this.next = null;
    }

    /**
     * Default constructor for objects of class List
     */
    public ListNode()
    {
        // initialise instance variables
        this.data = null;
        this.time = null;
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
    public String getData()
    {
        return data;
    }


    /**
     * Set the next node
     *
     * @param  next the node to be added at this node's next
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

        info = "data: "+data+" time: "+time;

        return info;
    }
}
