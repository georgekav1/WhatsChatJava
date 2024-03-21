

import javax.swing.*;
import java.awt.*;

public class ContactGUI extends JFrame {
    private JPanel buttonPanel;

    public ContactGUI() {
        setTitle("WhatsChat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");
        
        JMenuItem home = new JMenuItem("Home");      

        home.addActionListener(e -> returnToHome());
      
        title.add(home);
        
        menuBar.add(title);

        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());

        JLabel staticLabel = new JLabel("Here are your Contacts:", SwingConstants.CENTER);
        buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // Vertical layout for buttons
        
        // Add buttons or other components as needed
        JButton btn1 = new JButton("John Doe");
        JButton btn2 = new JButton("Alice");
        JButton btn3 = new JButton("Bob");
        JButton btn4 = new JButton("Emma");

        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);

        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER);
    }

    private void returnToHome() {
        dispose(); 
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }
    
}
