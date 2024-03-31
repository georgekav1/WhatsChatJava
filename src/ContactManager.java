import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

	/**
 	* Method that controls all contact operations.
 	*/
	public class ContactManager {
    public List<Contact> contacts;
    private String contactsFile = "contacts.txt";

    /**
     * Initialises contacts using data saved in contacts.txt
     */
    public ContactManager() {
        contacts = loadContactsFromFile();
    }
    
    /**
     * Method to return the list of contacts.
     * 
     * @return contacts The list of contacts.
     */
    public List<Contact> getContacts() {
		return contacts;
	}

    public Contact getContact(String name) {
        for(Contact contact : contacts) {
            if(contact.getName().equals(name)) return contact;
        }

        return null;
    }

    /**
     * Method to add and save new contact.
     * 
     * @param contact
     */
    public void addContact(Contact contact) {
    	contact.setAddedDate(new Date());    	
        contacts.add(contact);
        saveContactsToFile();      
    }

    /**
     * Method to remove contact from list.
     * 
     * @param name The name of the contact to be removed.
     */
    public boolean removeContactByName(String name) {
        // Iterate through contacts to find and remove the contact by name
        Contact contact = getContact(name);
        if(contact != null) {
            contacts.remove(contact);
            return true;
        }
        return false; // Contact not found or failed to remove
    }

    /**
     * Method to edit details of a contact.
     */
    public boolean editContactByName(String name, String newName, String newPhoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setName(newName);
                contact.setPhoneNumber(newPhoneNumber);
                return true; 
            }
        }
        return false;
    }

    /**
     * Method used to display contacts A-Z.
     */
    public void displayContactsAlphabetically() {
        Collections.sort(contacts, Comparator.comparing(Contact::getName));       
    }

    /**
     * Method used to display contacts by most recently added.
     */
    public void displayContactsByRecent() {
    	Collections.sort(contacts, Comparator.comparing(Contact::getAddedDate).reversed());     
    }

    /**
     * Method used to display contacts.
     */
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact.getName() + " - " + contact.getPhoneNumber() + " - " + contact.getAddedDate());
        }
    }

    /**
     * Method used to retrieve the contacts from contacts.txt
     * 
     * @return loadedContacts The contacts taken from contacts.txt
     */
    private List<Contact> loadContactsFromFile() {
        List<Contact> loadedContacts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(contactsFile))) {
            loadedContacts = (List<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Contacts file not found. Creating new file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedContacts;
    }
    
    /**
     * Method used to save the contacts to contacts.txt
     */
    private void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(contactsFile, false))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//public List<String> getRecentChatMessages(Contact contact, int i) {
		//return recentMessages;
	//}

}
