package ica;
public class Message
{
    private final String sender, receiver, content;

    /**
     * Message constructor for what a Message should contain.
     * @param sender Determines which UserAgent is sending the message.
     * @param receiver Determines which UserAgent should receive the message.
     * @param content Contains the actual Message being sent.
     */
    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    /**
     * Accessor method for the instance variable sender.
     * @return The UserAgent that is sending the message.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Accessor method for the instance variable receiver.
     * @return The UserAgent that is receiving the message.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Accessor method for the instance variable content.
     * @return The actual message that is being sent.
     */
    public String getContent() {
        return content;
    }

    /**
     * Acts such as a toString() to show the content within the Message.
     * @return Contains a concatenated string of the sender, receiver and content.
     */
    public String wrap(){
        return sender+":"+receiver+":"+content;
    }
}