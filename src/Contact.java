/**
 * The Contact class represents a contact with a name and phone number.
 */
public class Contact {
    private String name;
    private String phoneNumber;

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
}
