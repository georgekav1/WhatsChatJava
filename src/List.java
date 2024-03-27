import java.util.*;
public class List {
    private ListNode head;

    /**
     * Constructor for objects of class List
     * Create a head
     */
    public List() {
        head = null;
    }

    /**
     * Add a new node at the start of a list
     *
     * @param data data in list
     */
    public void addToList(String data, Date time) {
        ListNode marker;
        ListNode newOne;

        newOne = new ListNode(data, time);
        newOne.setNext(head);

        head = newOne;
    }

    /**
     * Print the list, starting at head
     */
    public void printList() {
        ListNode marker;

        if (head == null) {
            System.out.println("The stack is empty.");
        } else {
            System.out.println("The stack contains:");
            for (marker = head; marker != null; marker = marker.getNext()) {
                System.out.println(marker.printInfo());
            }
        }
    }

    /**
     * remove the data from the top of the stack
     */
    public ListNode deleteFromStart() {
        ListNode temp = new ListNode();
        if (head == null) {
            temp = null;
        } else {
            temp = head;
            head = temp.getNext();
        }
        return temp;
    }

    /**
     * @return head as null
     */
    public boolean isEmpty() {
        return head == null;
    }
}
