
/**
 * The Main class contains the main method to start the application.
 */
public class Main {

    public static MessageStoreManager messageStoreManager = new MessageStoreManager();
    public static LandingGUI frame = new LandingGUI();
    /**
     * The main method is the entry point of the application.
     * It creates a new instance of the Landing class and makes it visible.
     *
     */
    public static void main(String[] args) {

        frame.setVisible(true);
    }

    public static MessageStoreManager getMessageStoreManager() {
        return messageStoreManager;
    }
    public static LandingGUI getFrame() {
        return frame;
    }
}
