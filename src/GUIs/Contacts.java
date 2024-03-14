package GUIs;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Contacts extends JFrame {
	
	private JPanel buttonPanel;
	
	public Contacts() {
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
		buttonPanel();
		add(buttonPanel, BorderLayout.NORTH);
	}
	
	private void buttonPanel() {
		 buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		    
		 JButton btn1 = new JButton("Contact 1");
		 JButton btn2 = new JButton("Contact 2");
		    
		 buttonPanel.add(btn1);
		 buttonPanel.add(btn2);
		 
		 JButton btn3 = new JButton("Contact 3");
		 JButton btn4 = new JButton("Contact 4");
		    
		 buttonPanel.add(btn3);
		 buttonPanel.add(btn4);
	}
}
