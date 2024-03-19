package GUIs;
import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {
	
	private JPanel buttonPanel;
	
	public Profile() {
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
		profilePanel();
		add(buttonPanel, BorderLayout.NORTH);
	}
	
	private void profilePanel() {
		 buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		    
		 //fields stating name, phone number, etc.
	}
}

