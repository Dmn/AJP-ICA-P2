package ajp.ica.p2;

import java.util.concurrent.ArrayBlockingQueue;

public class Message extends ArrayBlockingQueue {
    final private String sender, receiver, content;

    public Message(String sender, String receiver, String content) {
        super(10);
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }
    
    public String wrap(){
       return (getSender()+"###"+getReceiver()+"###"+getContent());
    }   
}
