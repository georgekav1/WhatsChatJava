import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * The ContactGUI class is a user interface displaying the users' contacts.
 */
public class ContactGUI extends JFrame {
    private JPanel buttonPanel;
    private ContactManager contactManager; 

    /**
     * This class creates a GUI with buttons and allows the user to sort contacts in whichever way they like.
     * 
     * @param contactManager The class which contains methods used in this GUI.
     */
    public ContactGUI(ContactManager contactManager) {
        this.contactManager = contactManager;

        setTitle("WhatsChat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        //Navigation bar
        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");

        JMenuItem home = new JMenuItem("Home");
        home.addActionListener(e -> returnToHome());
        
        JMenu displayByMenu = new JMenu("Display by:");
        JMenuItem alphabeticallyItem = new JMenuItem("Alphabetically");
        alphabeticallyItem.addActionListener(e -> 
        { contactManager.displayContactsAlphabetically();
          refreshContactButtons();        
        });

        JMenuItem recentlyAddedItem = new JMenuItem("Recently Added");
        recentlyAddedItem.addActionListener(e -> 
        { contactManager.displayContactsByRecent();
        refreshContactButtons();        
        });

        JMenuItem recentlyChattedItem = new JMenuItem("Recently Chatted");
        //recentlyChattedItem.addActionListener(e -> displayContactsByChat());
        
        JMenu manageContacts = new JMenu("Manage:");
        JMenuItem addContact = new JMenuItem("Add New Contact");
        addContact.addActionListener(e -> addNewContact());

        JMenuItem removeContact = new JMenuItem("Remove Contact");
        removeContact.addActionListener(e -> removeContact());

        JMenuItem editContact = new JMenuItem("Edit Contact");
        //editContact.addActionListener(e -> ContactManager.editContact());
               
        menuBar.add(title);
        title.add(home);
        
        menuBar.add(displayByMenu);
        displayByMenu.add(alphabeticallyItem);
        displayByMenu.add(recentlyAddedItem);
        displayByMenu.add(recentlyChattedItem);
        
        menuBar.add(manageContacts);
        manageContacts.add(addContact);
        manageContacts.add(removeContact);
        manageContacts.add(editContact);
        
        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());

        //Contents of GUI
        JLabel staticLabel = new JLabel("Here are your Contacts:", SwingConstants.CENTER);
        buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        populateContactButtons(); 

        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER);
    }

    /**
     * Method that generates the buttons with labels including contact names and phone numbers to buttons on GUI. 
     */
	public void populateContactButtons() {
        List<Contact> contacts = contactManager.getContacts();
        for (Contact contact : contacts) {
            JButton contactButton = new JButton(contact.getName() + " - " + contact.getPhoneNumber());
            contactButton.addActionListener(e -> openChatWithContact(contact));
            buttonPanel.add(contactButton);
        }
    }
    
	/**
	 * Method that refreshes the GUI so that it displays with the sorted order chosen.
	 */
    public void refreshContactButtons() {
        buttonPanel.removeAll();
        populateContactButtons();
        revalidate();
        repaint();
    }
    
    /**
     * Method that adds a new singular contact to the existing list of contacts GUI.
     * 
     * @param contact
     */
    public void addButtonForContact(Contact contact) {
        JButton contactButton = new JButton(contact.getName() + " - " + contact.getPhoneNumber());
        contactButton.addActionListener(e -> openChatWithContact(contact));
        buttonPanel.add(contactButton);
    }
    
    /**
     * Method that is activated once "Add New Contact" tab is selected, bringing up an input box and asking the user for values.
     * Said contact is validated and displayed in the GUI.
     */
    public void addNewContact() {
        String name = JOptionPane.showInputDialog(this, "Enter Contact Name:");
        if (name != null && !name.isEmpty()) {
            String phoneNumber = JOptionPane.showInputDialog(this, "Enter Phone Number:");
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                Contact newContact = new Contact(name, phoneNumber, new Date());
                contactManager.addContact(newContact);
                addButtonForContact(newContact);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Phone number cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Method that is activated once "Remove Contact" tab is selected, options will then be displayed and selected contact will be removed from list.
     */
    public void removeContact() {
    	
	}

    /**
     * Method that is activated once "Home" tab is selected, LandingGUI is then displayed.
     */
    public void returnToHome() {
        dispose();
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }

    /**
     * Method that is activated once a contact is selected. The user will then be shown
     * their profile information and displayed a list of 3 most recent chat messages.
     */
    public void openChatWithContact(Contact contact) {
        System.out.println("Opening chat with " + contact.getName());
    }
}