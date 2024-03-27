import java.util.Date;
import java.util.Scanner;

public class Chat {
    private String message;
    List chatLog;

    public Chat(){
        chatLog = new List();
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }


    public void sendChat(){
        //replace with GUI once made
        Scanner s = new Scanner(System.in);
        System.out.println("please enter your message");
        setMessage(s.nextLine());
        chatLog.addToList(getMessage(), new Date());
    }

}
