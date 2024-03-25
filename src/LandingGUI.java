import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LandingGUI extends JFrame {
	private Profile userProfile;
	private JPanel panel;
	
	public LandingGUI() {
		userProfile = Profile.loadProfile();
		setTitle("WhatsChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu title = new JMenu("WhatsChat");		
					
		menuBar.add(title);		
			
		setJMenuBar(menuBar);
		panel();
		add(panel, BorderLayout.CENTER);
	}
	
	public void panel() {
	    panel = new JPanel(new GridLayout(5, 1, 5, 5)); 
		
	    JLabel welcome = new JLabel("Welcome back, " + userProfile.getName() , SwingConstants.LEFT);		
		panel.add(welcome);
	    
		JLabel title = new JLabel("WhatsChat", SwingConstants.CENTER);		
		panel.add(title);
		
		JButton contacts = new JButton("Contacts");
		panel.add(contacts);
		contacts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openContactsGUI();
			}
		});

		JButton profile = new JButton("View/Edit Profile");
		panel.add(profile);
		profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openProfileGUI();
			}
		});
		
		JButton chats = new JButton("Chats"); 
		panel.add(chats);
		chats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openChatsGUI();
			}
		});	
	}
	
	public void openContactsGUI() {
        ContactManager contactManager = new ContactManager();

        //Hard-coding contacts
    	Contact john = new Contact("John", "1234567890", new Date());
    	Contact steven = new Contact("Steven", "3243334890", new Date());
    	Contact alex = new Contact("Alex", "2342949853", new Date());
    	Contact emma = new Contact("Emma", "9673573444", new Date());
  	
    	contactManager.addContact(john);
    	sleepSeconds(1);
    	contactManager.addContact(steven);
    	sleepSeconds(1);
    	contactManager.addContact(alex);
    	sleepSeconds(1);
    	contactManager.addContact(emma);
    	
		ContactGUI contactGUI = new ContactGUI(contactManager);
		contactGUI.setVisible(true);
	}
	
	public void openProfileGUI() {
		ProfileGUI profileGUI = new ProfileGUI();
		profileGUI.setVisible(true);
	}
	
	public void openChatsGUI() {
		ChatsGUI chatsGUI = new ChatsGUI();
		chatsGUI.setVisible(true);
	}
	
	public void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            System.err.println("Sleep interrupted");
        }
    }

}
