import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class MessageStore {
    private HashSet<Message> messages;
    private final String fileName = "messages.txt";
    private Contact contact;
    private ContactManager contactManager;


    public MessageStore(Contact contact) {
        messages = new HashSet();
        this.contact = contact;
        contactManager = new ContactManager();
    }

    public HashSet<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public Iterator<Message> getIterator() {
        return messages.iterator();
    }

    public void loadMessages() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String nextLine;

        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            nextLine = bufferedReader.readLine();

            while(nextLine != null) {
                if(nextLine.contains(contact.getName())) {
                    String[] splitString = nextLine.split(";", 5);
                    Message newMsg = new Message();

                    if (splitString.length == 5) {
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
                } else if(nextLine.contains("You")) {
                    String[] splitString = nextLine.split(";", 6);
                    Message newMsg = new Message();

                    if (splitString.length == 6) {
                        newMsg.setContactName(splitString[0]);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date date = format.parse(splitString[1]);
                        String dateTime = format.format(date);

                        newMsg.setTime(dateTime);
                        newMsg.setLiked(Boolean.parseBoolean(splitString[2]));
                        newMsg.setRead(Boolean.parseBoolean(splitString[3]));
                        newMsg.setContent(splitString[4]);

                        String contactString = splitString[5];
                        Contact contact = contactManager.getContact(contactString);

                        MessageStoreManager messageStoreManager = new MessageStoreManager();

                        if(contact != null) {
                            messageStoreManager.getMessageStore(contact).addMessage(newMsg);
                        }
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

    public void saveMessages() {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;

        try {
            outputStream = new FileOutputStream(fileName);
            printWriter = new PrintWriter(outputStream);

            for(Message message : messages) {
                String line = message.getContactName() + ";" + message.getTime() + ";" + message.isLiked() + ";" + message.isRead() + ";" + message.getContent();

                if(message.getContact() != null) {
                    line += ";" + message.getContact().get();
                }

                printWriter.println(line);
                System.out.println(line);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            if(printWriter != null) {
                printWriter.close();
            }
        }
    }
}
