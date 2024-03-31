import java.util.Date;

public class Message {
    private String content;
    private String contactName;
    private Date time;
    private boolean liked;
    private boolean read;

    public Message() {
        
    }

    public Message(String contactName, Date time, boolean liked, boolean read, String content) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
}