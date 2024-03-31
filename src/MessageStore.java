import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class that acts as a database for the messages.
 */
public class MessageStore {
    private HashSet<Message> messages;
    private Contact contact;
    private ContactManager contactManager;
    private final MessageStoreManager messageStoreManager;

    /**
     * Constructor for MessageStore.
     * @param contact the contact to send a message to.
     */
    public MessageStore(Contact contact) {
        messageStoreManager = Main.getMessageStoreManager();
        messages = new HashSet();
        this.contact = contact;
        contactManager = new ContactManager();
    }
    
    /**
     * Returns the Set of messages.
     * @return messages The list of messages.
     */
    public HashSet<Message> getMessages() {
        return messages;
    }

    /**
     * Adds a message to a chat.
     * @param message The message details.
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Removes a message from the chat.
     * @param message The message details.
     */
    public void removeMessage(Message message) {
        messages.remove(message);
    }

    /**
     * Gets the tool to cycle through the messages.
     * @return messages The tool to cycle through the messages.
     */
    public Iterator<Message> getIterator() {
        return messages.iterator();
    }

    /**
     * The tool to get the length of the message.
     * @return The size of a message.
     */
    public int getMessageLength() {
        return messages.size();
    }

    /**
     * Method to load messages from a .txt file onto the program.
     */
    public void loadMessages() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String nextLine;
        String fileName = "messages/" + contact.getName() + ".txt";

        try {
            File file = new File(fileName);
            if(!file.exists()) {
                file.createNewFile();
                return;
            }
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            nextLine = bufferedReader.readLine();

            while(nextLine != null) {
                if(nextLine.contains(contact.getName())) {
                    String[] splitString = nextLine.split(";", 6);
                    Message newMsg = new Message();

                    if (splitString.length == 5 || splitString.length == 6) {
                        newMsg.setContactName(splitString[0]);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date date = format.parse(splitString[1]);
                        String dateTime = format.format(date);

                        newMsg.setTime(dateTime);
                        newMsg.setLiked(Boolean.parseBoolean(splitString[2]));
                        newMsg.setRead(Boolean.parseBoolean(splitString[3]));
                        newMsg.setContent(splitString[4]);
                        addMessage(newMsg);
                    }
                }

                nextLine = bufferedReader.readLine();
            }
        } catch(FileNotFoundException e) {
            System.out.println(fileName + " is not a valid file");
        } catch(IOException e) {
            System.out.println("there has been a problem opening or reading from this file");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch(IOException e) {
                    System.out.println("there is an issue closing this file");
                }
            }
        }
    }

    /**
     *  Method to save messages to a .txt file 
     */
    public void saveMessages() {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        String fileName = "messages/" + contact.getName() + ".txt";

        try {
            File file = new File(fileName);
            if(!file.exists()) {
                file.createNewFile();
                return;
            }
            outputStream = new FileOutputStream(file, false);
            printWriter = new PrintWriter(outputStream);

            for(Message message : messages) {
                String line = message.getContactName() + ";" + message.getTime() + ";" + message.isLiked() + ";" + message.isRead() + ";" + message.getContent();

                printWriter.println(line);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(printWriter != null) {
                printWriter.close();
            }
        }
    }
}
