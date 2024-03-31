import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Enumeration;
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        
        JMenu manageContacts = new JMenu("Manage:");
        JMenuItem addContact = new JMenuItem("Add New Contact");
        addContact.addActionListener(e -> addNewContact());

        JMenuItem removeContact = new JMenuItem("Remove Contact");
        removeContact.addActionListener(e -> removingContact());

        JMenuItem editContact = new JMenuItem("Edit Contact");
        editContact.addActionListener(e -> editingContact());
               
        menuBar.add(title);
        title.add(home);
        
        menuBar.add(displayByMenu);
        displayByMenu.add(alphabeticallyItem);
        displayByMenu.add(recentlyAddedItem);
       
        
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
            JButton contactButton = new JButton(contact.getName());
            contactButton.addActionListener(e -> selectContact(contact));
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
        JButton contactButton = new JButton(contact.getName());
        contactButton.addActionListener(e -> selectContact(contact));
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
        }
    }
    
    /**
     * Method that is activated once "Remove Contact" tab is selected, options will then be displayed and selected contact will be removed from list.
     */
    public void removingContact() {
    	List<Contact> contacts = contactManager.getContacts();

        if (contacts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No contacts to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Remove Contact", true);
        JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

        ButtonGroup buttonGroup = new ButtonGroup();
        for (Contact contact : contacts) {
            JRadioButton radioButton = new JRadioButton(contact.getName());
            buttonGroup.add(radioButton);
            dialogPanel.add(radioButton);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String selectedContactName = getSelectedContactName(buttonGroup);
            if (selectedContactName != null) {
                boolean removed = contactManager.removeContactByName(selectedContactName);
                if (removed) {
                    refreshContactButtons(); 
                    JOptionPane.showMessageDialog(this, "Contact removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove contact.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Please select a contact to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialogPanel.add(submitButton);
        dialog.add(dialogPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    /**
     * Method that is called in order to perform an action on the contact selected.
     * 
     * @param buttonGroup The options of contacts alongside radio buttons.
     * @return The contact that is selected.
     */
    public String getSelectedContactName(ButtonGroup buttonGroup) {
    	 Enumeration<AbstractButton> buttons = buttonGroup.getElements();
    	    while (buttons.hasMoreElements()) {
    	        AbstractButton button = buttons.nextElement();
    	        if (button.isSelected()) {
    	            return button.getText();
    	        }
    	    }
    	    return null; 
    }
    
    /**
     * Method that is activated once "Edit Contact" tab is selected, options will then be displayed and selected contact will be edited.
     */
    public void editingContact() {
        List<Contact> contacts = contactManager.getContacts();

        if (contacts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No contacts to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Edit Contact", true);
        JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

        ButtonGroup buttonGroup = new ButtonGroup();
        for (Contact contact : contacts) {
            JRadioButton radioButton = new JRadioButton(contact.getName());
            buttonGroup.add(radioButton);
            dialogPanel.add(radioButton);
        }

        JTextField newNameField = new JTextField(20);
        JTextField newPhoneField = new JTextField(20);
        dialogPanel.add(new JLabel("New Name:"));
        dialogPanel.add(newNameField);
        dialogPanel.add(new JLabel("New Phone Number:"));
        dialogPanel.add(newPhoneField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String selectedContactName = getSelectedContactName(buttonGroup);
            if (selectedContactName != null) {
                String newName = newNameField.getText().trim();
                String newPhoneNumber = newPhoneField.getText().trim();
                if (!newName.isEmpty() && !newPhoneNumber.isEmpty()) {
                    boolean edited = contactManager.editContactByName(selectedContactName, newName, newPhoneNumber);
                    if (edited) {
                        refreshContactButtons();
                        JOptionPane.showMessageDialog(this, "Contact edited successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to edit contact.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a contact to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialogPanel.add(submitButton);
        dialog.add(dialogPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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
     *
     * @param contact The contact selected by the user.
     */
    public void selectContact(Contact contact) {
        // Assuming you have a method to get the most recent chat messages of a contact
        //List<String> recentMessages = loadRecentChatMessages(contact, 3);

        // Display profile information and recent messages in a dialog
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Profile Information:\n");
        messageBuilder.append("Name: ").append(contact.getName()).append("\n");
        messageBuilder.append("Phone Number: ").append(contact.getPhoneNumber()).append("\n\n");
        
        messageBuilder.append("Recent Chat Messages:\n");

        for(Message message : Main.messageStoreManager.getMessageStore(contact).getMessages()) {
            if(message.getContactName().equals(contact.getName())) {
                messageBuilder.append(message.getContactName()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, messageBuilder.toString(), "Contact Information", JOptionPane.INFORMATION_MESSAGE);
    }
}