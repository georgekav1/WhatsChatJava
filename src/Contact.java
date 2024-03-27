import java.io.Serializable;
import java.util.Date;

/**
 * The Contact class represents a contact with a name and phone number.
 */
public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private Date addedDate;
    
    /**
     * Default constructor for a contact.
     * 
     * @param name
     * @param phoneNumber
     */
    public Contact (String name, String phoneNumber, Date addedDate) {
    	this.name = name;
    	this.phoneNumber = phoneNumber;
    	this.addedDate = addedDate;
    }

    /**
     * Retrieves the name of the contact.
     *
     * @return The name of the contact.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the contact.
     *
     * @param name The new name of the contact.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the contact.
     *
     * @return The phone number of the contact.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the contact.
     *
     * @param phoneNumber The new phone number of the contact.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
    * Retrieves the time the contact was added.
    *
    * @return The time the contact was added.
    */
    public Date getAddedDate() {
        return addedDate;
    }

    /**
     * Sets the time the contact was added.
     *
     * @param addedDate The time the contact was added.
     */
    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
