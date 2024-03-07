import javax.swing.*;

public class Menu extends JFrame {
	public Menu() {
		setTitle("WhatsChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		JMenuBar menuBar = new JMenuBar();
		JMenu title = new JMenu("WhatsChat");
		JMenu menu = new JMenu("Edit");
		JMenuItem user = new JMenu("User");
		menu.add(user);
		menuBar.add(title);
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		
	}
}
