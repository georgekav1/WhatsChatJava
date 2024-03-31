import java.util.Date;
import java.util.Optional;

public class Message {
    private String content;
    private String contactName;
    private String time;
    private boolean liked;
    private boolean read;

    private Optional<String> contact;

    public Message() {
        
    }

    public Message(String contactName, String time, boolean liked, boolean read, String content) {
        this.contactName = contactName;
        this.time = time;
        this.liked = liked;
        this.read = read;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Optional<String> getContact() {
        return contact;
    }

    public void setContact(Optional<String> contact) {
        this.contact = contact;
    }
}