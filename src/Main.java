import java.util.Date;

/**
 * The Main class contains the main method to start the application.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It creates a new instance of the Landing class and makes it visible.
     *
     */
    public static void main(String[] args) { 
    	Main openApp = new Main();
    	openApp.initialise();
    	
        LandingGUI frame = new LandingGUI();
        
        frame.setVisible(true);
    }
    
    public void initialise()
    {
    	ContactManager contactManager = new ContactManager();

        //Hard-coding contacts
    	Contact john = new Contact("John", "1234567890", new Date());
    	Contact steven = new Contact("Steven", "3243334890", new Date());
    	Contact alex = new Contact("Alex", "2342949853", new Date());
    	Contact emma = new Contact("Emma", "9673573444", new Date());
  	
    	contactManager.addContact(john);
    	sleepSeconds(1);
    	contactManager.addContact(steven);
    	sleepSeconds(1);
    	contactManager.addContact(alex);
    	sleepSeconds(1);
    	contactManager.addContact(emma);
    }
    
    public static void sleepSeconds(int ms) {
        try {
            Thread.sleep(ms); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            System.err.println("Sleep interrupted");
        }
    }
}
