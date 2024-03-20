package GUIs;

import javax.swing.*;
import java.awt.*;

public class Chats extends JFrame {
    private JPanel chatPanel;

    public Chats() {
        setTitle("Chats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");
        JMenuItem home = new JMenuItem("Home");

        home.addActionListener(e -> returnToHome());
        
        JLabel staticLabel = new JLabel("Here are your Chats:", SwingConstants.CENTER);
        
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS)); // Vertical layout for chat entries
        
        // Simulate chat entries with labels
        addChatEntry("John Doe", "Hello there!", "10:00 AM", true);
        addChatEntry("Alice", "Hi, how are you?", "11:30 AM", false);
        addChatEntry("Bob", "Good morning!", "12:15 PM", true);
        addChatEntry("Emma", "What's up?", "1:00 PM", false);

        title.add(home);
        menuBar.add(title);
        

        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(chatPanel), BorderLayout.CENTER); // Wrap chatPanel in JScrollPane
    }

    private void addChatEntry(String sender, String message, String time, boolean read) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        JLabel senderLabel = new JLabel(sender + ": ");
        JLabel messageLabel = new JLabel(message);
        JLabel timeLabel = new JLabel(time);
        JLabel readLabel = new JLabel(read ? "Read" : "Unread");

        entryPanel.add(senderLabel, BorderLayout.WEST);
        entryPanel.add(messageLabel, BorderLayout.CENTER);
        entryPanel.add(timeLabel, BorderLayout.EAST);
        entryPanel.add(readLabel, BorderLayout.SOUTH);

        chatPanel.add(entryPanel);
    }
    
    private void returnToHome() {
        dispose(); 
        Landing landingPage = new Landing();
        landingPage.setVisible(true);
    }   
}
