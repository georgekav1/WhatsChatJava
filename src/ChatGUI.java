import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

/**
 * Class that will allow the user to create a new chat.
 */
public class ChatGUI extends JFrame {
    private JPanel buttonPanel;
    private ContactManager contactManager;
    private LandingGUI landingGUI;
    private Message message;

    public ChatGUI(ContactGUI contactGUI, ContactManager contactManager, LandingGUI landingGUI) {  
        this.contactManager = contactManager;
        this.landingGUI = landingGUI;

    	setTitle("WhatsChat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);

        //Navigation bar
        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");

        JMenuItem home = new JMenuItem("Home");
        home.addActionListener(e -> returnToHome());
                              
        menuBar.add(title);
        title.add(home);
               
        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());

        //Contents of GUI
        JLabel staticLabel = new JLabel("Select a contact to message:", SwingConstants.CENTER);
        buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        List<Contact> contacts = contactManager.getContacts();
        for (Contact contact : contacts) {
            JButton contactButton = new JButton(contact.getName());
            contactButton.addActionListener(e -> messageContact(contact));
            buttonPanel.add(contactButton);
        }

        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER);
    
        //for(Contact contact : contactManager.getContacts()) {
            // TODO: check the message files for contacts which DO NOT have any chats and then add them to a list here
        //}

        List<Contact> contactsWithoutChats = new ArrayList<>();

        for (Contact contact : contactManager.getContacts()) {
            boolean hasChat = false;

            // Retrieve the message store for the current contact using an instance of MessageStoreManager
            MessageStoreManager messageStoreManager = new MessageStoreManager();
            MessageStore messageStore = messageStoreManager.getMessageStore(contact);

            // Iterate over the messages associated with the current contact
            for (Message message : messageStore.getMessages()) {
                // If any message is found for this contact, set hasChat to true and break the loop
                if (message.getContactName().equals(contact.getName())) {
                    hasChat = true;
                    break;
                }
            }

            // If no chat is found for this contact, add it to the list
            if (!hasChat) {
                contactsWithoutChats.add(contact);
            }
        }
    }

	/**
     * Method used to navigate back to the LandingGUI.
     */
    public void returnToHome() {
        dispose(); 
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }       
    
    public void messageContact(Contact contact) {
        landingGUI.chatPanel(contact); 

        JTextArea messageArea = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton submitBtn = new JButton("Send");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message1 = messageArea.getText();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String currentDateTime = dateFormat.format(currentDate);
                Message message = new Message("You", currentDateTime, false, false, messageArea.getText());

                landingGUI.addChatToList(contact);

                landingGUI.addChatEntry(message, contact, true); 

                landingGUI.chatPanel.revalidate(); 
                landingGUI.chatPanel.repaint();  
            }
        });

        panel.add(submitBtn, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Send Message to " + contact.getName(),
                JOptionPane.PLAIN_MESSAGE);
    }
}
