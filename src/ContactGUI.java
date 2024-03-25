
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ContactGUI extends JFrame {
    private JPanel buttonPanel;
    private ContactManager contactManager; 

    public ContactGUI(ContactManager contactManager) {
        this.contactManager = contactManager;

        setTitle("WhatsChat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

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
        
        title.add(home);
        displayByMenu.add(alphabeticallyItem);
        displayByMenu.add(recentlyAddedItem);
        displayByMenu.add(recentlyChattedItem);
        
        menuBar.add(title);
        menuBar.add(displayByMenu);
        
        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());

        JLabel staticLabel = new JLabel("Here are your Contacts:", SwingConstants.CENTER);
        buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        populateContactButtons(); 

        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER);
    }

    public void populateContactButtons() {
        List<Contact> contacts = contactManager.getContacts();
        for (Contact contact : contacts) {
            JButton contactButton = new JButton(contact.getName() + " - " + contact.getPhoneNumber());
            contactButton.addActionListener(e -> openChatWithContact(contact));
            buttonPanel.add(contactButton);
        }
    }
    
    public void refreshContactButtons() {
        buttonPanel.removeAll();
        populateContactButtons();
        revalidate();
        repaint();
    }

    public void returnToHome() {
        dispose();
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }

    public void openChatWithContact(Contact contact) {
        System.out.println("Opening chat with " + contact.getName());
    }
}