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
            if(Main.messageStoreManager.getMessageStore(contact).getMessageLength() < 1) {
                JButton contactButton = new JButton(contact.getName());
                contactButton.addActionListener(e -> messageContact(contact));
                buttonPanel.add(contactButton);
            }
        }

        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER);
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

        JTextArea chatInput = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(chatInput);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton submitBtn = new JButton("Send");
        submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatInput.getText();

				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String currentDateTime = dateFormat.format(currentDate);

				Message newMessage = new Message("You", currentDateTime, false, false, message);
				landingGUI.addChatEntry(newMessage, contact);
				landingGUI.addChatToList(contact);

				landingGUI.chatListPanel();
                landingGUI.chatPanel.revalidate();
                landingGUI.chatPanel.repaint();
                landingGUI.getSplitPane().setRightComponent(landingGUI.chatPanel);
				Main.messageStoreManager.getMessageStore(contact).saveMessages();
			}
		});

        panel.add(submitBtn, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Send Message to " + contact.getName(),
                JOptionPane.PLAIN_MESSAGE);
    }
}
