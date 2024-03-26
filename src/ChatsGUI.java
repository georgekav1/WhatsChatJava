
import javax.swing.*;
import java.awt.*;

public class ChatsGUI extends JFrame {

    public ChatsGUI() {
        setTitle("Chats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600); 

        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");
        JMenuItem home = new JMenuItem("Home");

        home.addActionListener(e -> returnToHome());      

        title.add(home);
        menuBar.add(title);

        setJMenuBar(menuBar);

    }
    
    private void returnToHome() {
        dispose(); 
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }   
}
