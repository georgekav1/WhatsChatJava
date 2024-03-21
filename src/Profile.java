
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Profile class represents the user and all their details.
 */
	public class Profile implements Serializable {
		private static final String filename = "profile.txt";
		private String name;
		private String username;
		private String phoneNumber;
		private String email;
		private LocalDate bday;
		
		
		public Profile() {
			
		}

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The new phone number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Retrieves the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Retrieves the birthday of the user.
     *
     * @return The birthday of the user.
     */
    public LocalDate getBday() {
        return bday;
    }

    /**
     * Sets the birthday of the user.
     *
     * @param bday The birthday of the user.
     */
    public void setBday(LocalDate bday) {
        this.bday = bday;
    }
        
    public void saveProfile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace(); // Handle file write errors as needed
        }
    }
    
    public static Profile loadProfile() {
        Profile userProfile = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            userProfile = (Profile) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle file read errors or missing file as needed
        }
        return userProfile;
    }
}
