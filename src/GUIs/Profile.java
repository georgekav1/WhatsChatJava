package GUIs;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {
    private JPanel buttonPanel;
    private JTextField nameField;
    private JTextField phoneNumberField;
    // Add more fields as needed
    
    public Profile() {
        setTitle("Profile Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");
        JMenuItem home = new JMenuItem("Home");

        home.addActionListener(e -> returnToHome());
        
        JLabel staticLabel = new JLabel("Here are your Profile Details:", SwingConstants.CENTER);
        buttonPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // GridLayout with 2 columns for labels and fields

        // Adding fields for user information
        buttonPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20); // 20 columns width
        buttonPanel.add(nameField);

        buttonPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(20); // 20 columns width
        buttonPanel.add(phoneNumberField);
        
        title.add(home);
        menuBar.add(title);

        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER); // Wrap buttonPanel in JScrollPane
    }
    
    private void returnToHome() {
        dispose(); 
        Landing landingPage = new Landing();
        landingPage.setVisible(true);
    }
   
}
