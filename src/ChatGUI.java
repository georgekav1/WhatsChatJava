import javax.swing.*;

/**
 * Class that will allow the user to create a new chat.
 */
public class ChatGUI extends JFrame {

    private ContactManager contactManager;

	
	/**
	 * Method to display the GUI with all of its properties.
	 */
    public ChatGUI() {
        setTitle("Chats");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600); 

        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");
        JMenuItem home = new JMenuItem("Home");

        home.addActionListener(e -> returnToHome());      

        title.add(home);
        menuBar.add(title);

        setJMenuBar(menuBar);

        contactManager = new ContactManager();

        for(Contact contact : contactManager.getContacts()) {
            // TODO: check the message files for contacts which DO NOT have any chats and then add them to a list here
//            boolean hasChat = false;
//            for (Chat chatMessage : messageManager.getMessagesForContact(contact)) {
//                if (chatMessage.getSender().equals(contact) || chatMessage.getReceiver().equals(contact)) {
//                    hasChat = true;
//                    break;
//                }
//            }
//            if (!hasChat) {
//                contactsWithNoChats.add(contact);
//            }
        }

    }
    
    /**
     * Method used to navigate back to the LandingGUI.
     */
    private void returnToHome() {
        dispose(); 
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }   
}
