import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
    	contact.setAddedDate(new Date());
        contacts.add(contact);
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

	public List<Contact> getContacts() {
		return contacts;
	}

}
