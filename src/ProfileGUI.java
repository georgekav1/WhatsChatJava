import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that allows the user to view/edit their profile in a user interface.
 */
public class ProfileGUI extends JFrame {
    private JPanel buttonPanel;
    private JTextField nameField;
    private JTextField usernameField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField bdayField;
    
    /**
	 * Method to display the GUI with all of its properties.
	 */
    public ProfileGUI() {   	       	
        setTitle("Profile Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);

        panel();
        
        loadProfileDetails();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDetails();
            }
        });
              
    }
    
    /**
     * Method displaying the GUI with all the profile fields and text areas, allowing the user to edit whatever they please.
     */
    private void panel() {
    	JMenuBar menuBar = new JMenuBar();
        JMenu title = new JMenu("WhatsChat");
        JMenuItem home = new JMenuItem("Home");
        JMenuItem save = new JMenuItem("Save");

        home.addActionListener(e -> returnToHome());
        save.addActionListener(e -> saveDetails());
        
        JLabel staticLabel = new JLabel("Here are your Profile Details:", SwingConstants.CENTER);
        buttonPanel = new JPanel(new GridLayout(0, 2, 0, 5)); 

        buttonPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField(10); 
        buttonPanel.add(nameField);
        
        buttonPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(10); 
        buttonPanel.add(usernameField);

        buttonPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(10); 
        buttonPanel.add(phoneNumberField);
        
        buttonPanel.add(new JLabel("Email Address:"));
        emailField = new JTextField(10); 
        buttonPanel.add(emailField);
        
        buttonPanel.add(new JLabel("Date of Birth: (YYYY-MM-DD)"));
        bdayField = new JTextField(10); 
        buttonPanel.add(bdayField);
                        
        title.add(home);
        title.add(save);
        menuBar.add(title);

        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(staticLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.CENTER); 
	}

    /**
     * Method used to navigate back to the LandingGUI.
     */
	private void returnToHome() {
        dispose(); 
        LandingGUI landingPage = new LandingGUI();
        landingPage.setVisible(true);
    }
    
	/**
     * Method used to retrieve profile details from profile.txt
     */
	private void loadProfileDetails() {
        Profile userProfile = Profile.loadProfile();
        if (userProfile != null) {
            if (nameField != null) nameField.setText(userProfile.getName());
            if (usernameField != null) usernameField.setText(userProfile.getUsername());
            if (phoneNumberField != null) phoneNumberField.setText(userProfile.getPhoneNumber());
            if (emailField != null) emailField.setText(userProfile.getEmail());
            LocalDate bday = userProfile.getBday();
            if (bday != null && bdayField != null) {
                bdayField.setText(bday.toString());
            } else {
                bdayField.setText(""); 
            }                   
        }
    }
    
	/**
     * Method used to save the contacts to profile.txt
     */
    private void saveDetails() {
        try {
            Profile userProfile = new Profile();
            userProfile.setName(nameField.getText());
            userProfile.setUsername(usernameField.getText());
            userProfile.setPhoneNumber(phoneNumberField.getText());
            userProfile.setEmail(emailField.getText());         
            
            String bdayText = bdayField.getText();
            LocalDate bday = LocalDate.parse(bdayText); 
            userProfile.setBday(bday);
            
            userProfile.saveProfile();
            
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter date in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
}
