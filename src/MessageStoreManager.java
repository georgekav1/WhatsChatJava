import java.util.HashMap;
import java.util.HashSet;

/**
 * Class to manage all message operations.
 */
public class MessageStoreManager {
    private HashMap<Contact, MessageStore> contactStores;
    private ContactManager contactManager;

    /**
     * Contructor method
     */
    public MessageStoreManager() {
        contactStores = new HashMap<>();
        contactManager = new ContactManager();

        for(Contact contact : contactManager.getContacts()) {
            MessageStore msgStore = new MessageStore(contact);
            //msgStore.loadMessages();
            contactStores.put(contact, msgStore);
        }
    }

    /**
     * Retrieves the message store.
     * @param contact The contact the message was sent to.
     * @return The message store.
     */
    public MessageStore getMessageStore(Contact contact) {
        if(contactStores.get(contact) != null) {
            return contactStores.get(contact);
        } else {
            MessageStore msgStore = new MessageStore(contact);
            contactStores.put(contact, msgStore);
            return msgStore;
        }
    }
}
