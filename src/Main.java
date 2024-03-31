import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * The Main class contains the main method to start the application.
 */
public class Main {

    public static MessageStoreManager messageStoreManager = new MessageStoreManager();
    public static LandingGUI frame;
    /**
     * The main method is the entry point of the application.
     * It creates a new instance of the Landing class and makes it visible.
     *
     */
    public static void main(String[] args) {
        File file = new File("profile.txt");
        if(!file.exists()) {
            try
            {
                file.createNewFile();

                Profile profile = new Profile();
                profile.setName("Welcome new user");
                profile.setUsername("newUser");
                profile.setEmail("newUser@WhatsChat.app");
                LocalDate date = LocalDate.parse("2024-03-31");
                profile.setBday(date);

                profile.saveProfile();
            } catch(IOException e) {
                System.out.println("Error occured");
            }
        }

        frame = new LandingGUI();
        frame.setVisible(true);
    }

    public static MessageStoreManager getMessageStoreManager() {
        return messageStoreManager;
    }
    public static LandingGUI getFrame() {
        return frame;
    }
}
