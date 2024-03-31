import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class MessageStore {
    private HashSet<Message> messages;
    private final String fileName = "messages.txt";
    private Contact contact;

    public MessageStore(Contact contact) {
        messages = new HashSet();
        this.contact = contact;
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
                        SimpleDateFormat format = new SimpleDateFormat("YYYY m")

                        newMsg.setTime();
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
