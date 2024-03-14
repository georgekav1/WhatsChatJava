package GUIs;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Landing extends JFrame {
	
	private JPanel panel;
	
	public Landing() {
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
		panel();
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, (getHeight() / 2)));
		add(panel, BorderLayout.CENTER);
		
	}
	
	private void panel() {
		panel = new JPanel(new GridBagLayout());
		
		JLabel title = new JLabel("WhatsChat", SwingConstants.CENTER);
		GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);


        
        JButton contacts = new JButton("Contacts");
        
        panel.add(contacts);
        JButton chats = new JButton("Chats");
        
        panel.add(Box.createHorizontalStrut(10));
        panel.add(chats);
	}
	
}
