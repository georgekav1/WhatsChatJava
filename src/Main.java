
/**
 * The Main class contains the main method to start the application.
 */
public class Main {

    public static MessageStoreManager messageStoreManager = new MessageStoreManager();
    /**
     * The main method is the entry point of the application.
     * It creates a new instance of the Landing class and makes it visible.
     *
     */
    public static void main(String[] args) {

        LandingGUI frame = new LandingGUI();

        frame.setVisible(true);
    }

    public static MessageStoreManager getMessageStoreManager() {
        return messageStoreManager;
    }
}
