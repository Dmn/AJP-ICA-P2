package ica;
public class Message
{
    private String sender, receiver, Content;

    public Message(//String sender, String receiver,
                   String Content) {
        // this.sender = sender;
        // this.receiver = receiver;
        this.Content = Content;
    }


    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return Content;
    }
    public void setContent(String Content) {
        this.Content = Content;
    }

    public String wrap(){
        return (getSender()+"###"+getReceiver()+"###"+getContent());
    }
}