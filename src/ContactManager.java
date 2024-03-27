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

public class ContactManager {
    private List<Contact> contacts;
    private String contactsFile = "contacts.txt";


    public ContactManager() {
        contacts = loadContactsFromFile();
    }
    
    public List<Contact> getContacts() {
		return contacts;
	}

    public void addContact(Contact contact) {
    	contact.setAddedDate(new Date());
    	
        if (!contacts.contains(contact)) {
        contacts.add(contact);
        saveContactsToFile();
        } else {
        	System.out.println("Contact already exists!");
        }
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public void editContact(Contact oldContact, Contact newContact) {
        int index = contacts.indexOf(oldContact);
        if (index != -1) {
            contacts.set(index, newContact);
        }
    }

    public void displayContactsAlphabetically() {
        Collections.sort(contacts, Comparator.comparing(Contact::getName));       
    }

    public void displayContactsByRecent() {
    	Collections.sort(contacts, Comparator.comparing(Contact::getAddedDate).reversed());     
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact.getName() + " - " + contact.getPhoneNumber() + " - " + contact.getAddedDate());
        }
    }

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
    
    private void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(contactsFile, false))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
