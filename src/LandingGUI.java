import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.security.DigestException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Class that serves as the main menu of the program, displaying chats and buttons used to access other GUIs.
 */
public class LandingGUI extends JFrame {
	private Profile userProfile;
	private JPanel buttonPanel;

	public JPanel chatPanel;

	private JPanel chat;
	private ContactManager contactManager;
	private JSplitPane splitPane;
	private final MessageStoreManager messageStoreManager = Main.getMessageStoreManager();

	/**
	 * Method to display the GUI with all of its properties.
	 */
	public LandingGUI() {
		userProfile = Profile.loadProfile();
		setTitle("WhatsChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu title = new JMenu("WhatsChat");

		contactManager = new ContactManager();

//		chatPanel();
		chatListPanel();
		buttonPanel();
		
		menuBar.add(title);		
			
		setJMenuBar(menuBar);
		
		add(buttonPanel, BorderLayout.CENTER);
		addSplitPane();
	}

	public void addSplitPane() {
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(buttonPanel), new JScrollPane(chatPanel));
		splitPane.setDividerLocation(300);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(splitPane, BorderLayout.CENTER);
	}

	/**
	 * Method outlining the panel that includes the buttons that allow the user to navigate through the program.
	 */
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

	public void chatListPanel() {
		
		List<Contact> contacts = contactManager.getContacts();
		chatPanel = new JPanel(new GridLayout(contacts.size(), 1, 10, 10));

		if(contacts.size() > 0) {
			for(Contact contact : contacts) {
				// TODO: Check is the user has an existing chat. Right now all contacts are loaded.
				addChatToList(contact);
			}
		} else {

		}
	}

	public void addChatToList(Contact contact) {
		JButton contactButton = new JButton("<html><div> " + contact.getName() + "</div><br /><div>" + contact.getPhoneNumber() + "</div>");

		contactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//messageStoreManager.getMessageStore(contact).loadMessages();
				chatPanel(contact);
				splitPane.setRightComponent(new JScrollPane(chatPanel));
				splitPane.setDividerLocation(300);
			}
		});

		chatPanel.add(contactButton);
	}
	
	/**
	 * Method outlining the panel that displays details of chats received from different contacts.
	 */
	public void chatPanel(Contact contact) {
		chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		JButton closeButton = new JButton("Close Chat");

		chat = new JPanel();
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		chat.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		JScrollPane scrollChat = new JScrollPane(chat);
		scrollChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollChat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollChat.setPreferredSize(new Dimension(400, 400));
		chatPanel.add(scrollChat);

		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chatListPanel();
				splitPane.setRightComponent(new JScrollPane(chatPanel));
				splitPane.setDividerLocation(300);
			}
		});


        // Simulate chat entries with labels
//        addChatEntry(contact.getName(), "Hello there!", "10:00 AM", true);
//        addChatEntry(contact.getName(), "Hi, how are you?", "11:30 AM", false);
//        addChatEntry(contact.getName(), "Good morning!", "12:15 PM", true);
//        addChatEntry(contact.getName(), "What's up?", "1:00 PM", false);
//		Message testMessage = new Message(contact.getName(), new Date(), false, false, "hitya");
//		addChatEntry(testMessage, contact);

		HashSet<Message> messages = messageStoreManager.getMessageStore(contact).getMessages();
		for(Message message : messages) {
			addChatEntry(message, contact, contact.getName().equals("You"));
		}

		JPanel inputPanel = new JPanel(new GridBagLayout());
		JTextField chatInput = new JTextField();
		JButton submitBtn = new JButton("<html><div style='display:inline;'>Submit</div></html>");
		GridBagConstraints gbc = new GridBagConstraints();

		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatInput.getText();

				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String currentDateTime = dateFormat.format(currentDate);

				Message newMessage = new Message("You", currentDateTime, false, false, message);
				addChatEntry(newMessage, contact, true);
				chatPanel.revalidate();
				chatPanel.repaint();
				messageStoreManager.getMessageStore(contact).saveMessages();
			}
		});

		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		inputPanel.add(chatInput, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;

		inputPanel.add(submitBtn, gbc);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0;

		inputPanel.add(closeButton, gbc);
		chatPanel.add(inputPanel);
		

	}
	
	/**
	 * Method used to format and display the chats with each contact.
	 * 
	 * @param message The contents of the chat.
	 * @param contact The contact that sent the message.
	 */
	public void addChatEntry(Message message, Contact contact, Boolean youOrNo) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        JLabel senderLabel = new JLabel(youOrNo ? "You: " :message.getContactName()  + ": ");
        JLabel messageLabel = new JLabel(message.getContent());

        JLabel timeLabel = new JLabel(message.getTime());
        JLabel readLabel = new JLabel(message.isRead() ? "Read" : "Unread");

		JPanel menuPanel = new JPanel(new GridBagLayout());

		JButton deleteButton = new JButton("Delete");
		JButton likeButton = new JButton(!message.isLiked() ? "Like ♡" : "Unlike ❤");

		if(youOrNo == true) {
			message.setContactName("You");
			message.setContact(Optional.ofNullable(contact.getName()));
		}

		likeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messageStoreManager.getMessageStore(contact).removeMessage(message);
				if(likeButton.getText().contains("Like")) {
					likeButton.setText("Unlike ❤");
					message.setLiked(true);
				} else {
					likeButton.setText("Like ♡");
					message.setLiked(false);
				}
				messageStoreManager.getMessageStore(contact).addMessage(message);
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chat.remove(entryPanel);
				messageStoreManager.getMessageStore(contact).removeMessage(message);
				chat.revalidate();
				chat.repaint();
			}
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.weightx = 1.0;

		menuPanel.add(readLabel, gbc);

		gbc.gridx = 1;
		menuPanel.add(likeButton, gbc);

		gbc.gridx = 2;
		menuPanel.add(deleteButton, gbc);

        entryPanel.add(senderLabel, BorderLayout.WEST);
        entryPanel.add(messageLabel, BorderLayout.CENTER);
        entryPanel.add(timeLabel, BorderLayout.EAST);
		entryPanel.add(menuPanel, BorderLayout.SOUTH);

		entryPanel.setPreferredSize(new Dimension(350, 50));

        chat.add(entryPanel);
		messageStoreManager.getMessageStore(contact).saveMessages();
		messageStoreManager.getMessageStore(contact).addMessage(message);
    }
	
	/**
	 * Method to open the Contacts GUI.
	 */
	public void openContactsGUI() {
        ContactManager contactManager = new ContactManager();

		ContactGUI contactGUI = new ContactGUI(contactManager);
		contactGUI.setVisible(true);
	}
	
	/**
	 * Method to open the Profile GUI.
	 */
	public void openProfileGUI() {
		ProfileGUI profileGUI = new ProfileGUI();
		profileGUI.setVisible(true);
	}
	
	/**
	 * Method to open the Chats GUI.
	 */
	public void openChatsGUI() {
		ContactGUI contactGUI = new ContactGUI(contactManager);
		LandingGUI landingGUI = new LandingGUI();

		ChatGUI chatsGUI = new ChatGUI(contactGUI, contactManager, landingGUI);
		chatsGUI.setVisible(true);
	}
		
}
