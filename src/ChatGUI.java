import javax.swing.*;

/**
 * Class that will allow the user to create a new chat.
 */
public class ChatGUI extends JFrame {
	
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
