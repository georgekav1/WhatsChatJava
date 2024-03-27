import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class LandingGUI extends JFrame {
	private Profile userProfile;
	private JPanel buttonPanel;
	private JPanel chatPanel;
	
	public LandingGUI() {
		userProfile = Profile.loadProfile();
		setTitle("WhatsChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu title = new JMenu("WhatsChat");	
		
		JLabel chatLabel = new JLabel("Here are your Chats:                Menu Options:", SwingConstants.CENTER);
		
		chatPanel();
		buttonPanel();
		
		menuBar.add(title);		
			
		setJMenuBar(menuBar);
		
		add(buttonPanel, BorderLayout.CENTER);
		 JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(chatPanel), new JScrollPane(buttonPanel));
	        splitPane.setDividerLocation(400); 
	        
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(chatLabel, BorderLayout.NORTH);
	        getContentPane().add(splitPane, BorderLayout.CENTER);	
	}
	
	public void buttonPanel() {
	    buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5)); 
		
	    JLabel welcome = new JLabel("Welcome back, " + userProfile.getName() , SwingConstants.CENTER);		
		buttonPanel.add(welcome);
	    						
		JButton contacts = new JButton("Contacts");		
		buttonPanel.add(contacts);
		contacts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openContactsGUI();
			}
		});

		JButton profile = new JButton("View/Edit Profile");
		buttonPanel.add(profile);
		profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openProfileGUI();
			}
		});
		
		JButton chats = new JButton("Create Chat"); 
		buttonPanel.add(chats);
		chats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openChatsGUI();
			}
		});	
	}	
	
	public void chatPanel() {
		chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        // Simulate chat entries with labels
        addChatEntry("John", "Hello there!", "10:00 AM", true);
        addChatEntry("Steven", "Hi, how are you?", "11:30 AM", false);
        addChatEntry("Alex", "Good morning!", "12:15 PM", true);
        addChatEntry("Emma", "What's up?", "1:00 PM", false);
	}
	
	private void addChatEntry(String sender, String message, String time, boolean read) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        JLabel senderLabel = new JLabel(sender + ": ");
        JLabel messageLabel = new JLabel(message);
        JLabel timeLabel = new JLabel(time);
        JLabel readLabel = new JLabel(read ? "Read" : "Unread");

        entryPanel.add(senderLabel, BorderLayout.WEST);
        entryPanel.add(messageLabel, BorderLayout.CENTER);
        entryPanel.add(timeLabel, BorderLayout.EAST);
        entryPanel.add(readLabel, BorderLayout.SOUTH);

        chatPanel.add(entryPanel);
    }
	
	public void openContactsGUI() {
        ContactManager contactManager = new ContactManager();

		ContactGUI contactGUI = new ContactGUI(contactManager);
		contactGUI.setVisible(true);
	}
	
	public void openProfileGUI() {
		ProfileGUI profileGUI = new ProfileGUI();
		profileGUI.setVisible(true);
	}
	
	public void openChatsGUI() {
		ChatGUI chatsGUI = new ChatGUI();
		chatsGUI.setVisible(true);
	}
		
}
