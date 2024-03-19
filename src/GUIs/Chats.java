package GUIs;
import javax.swing.*;
import java.awt.*;

public class Chats extends JFrame {
	
	private JPanel buttonPanel;
	
	public Chats() {
		setTitle("WhatsChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		JMenuBar menuBar = new JMenuBar();
		JMenu title = new JMenu("WhatsChat");
		JMenu menu = new JMenu("Edit");
		JMenuItem user = new JMenu("User");
		menu.add(user);
		menuBar.add(title);
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		
		getContentPane().setLayout(new BorderLayout());
		chatPanel();
		add(buttonPanel, BorderLayout.NORTH);
	}
	
	private void chatPanel() {
		 buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		    
		 //layout of chats including who it was sent by, 
		 //whether it has been read/unread, and the time it was received
	}
}
