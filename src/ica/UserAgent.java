package ica;

import java.util.HashMap;

public class UserAgent extends MetaAgent {

    private final Portal portal; /** Portal should never change */

    /**
     * Constructor for UserAgent which calls its superclass MetaAgent.
     * @param name Name of the UserAgent.
     * @param portal A Portal this UserAgent is connected to.
     */
    public UserAgent(String name, Portal portal)
    {
        super(name, portal);
        this.portal = portal;
        this.portal.addAgent(this);
        userAgentSync();
    }

    /**
     * Reactive method to send or receive messages.
     * @param msg Message containing the Content, Sender and Receiver of the message.
     */
    @Override
    public void msgHandler(Message msg) {
        System.out.printf("(%s) Message received from: %s.\n Content: %s\n", name, msg.getSender(), msg.getContent());
    }
    
    /**
     * @Author V8117091 : forwards the userAgent and String Name to portal and 
     * adds it to the userAgentList on portals side.
     */
    public void userAgentSync()
    {
        this.portal.userAgentSync(this.name, this);
    }

    public void sendMessage(String receiver, String message) {
        if (name != receiver) {
            Message msg = new Message(name, receiver, message);
            portal.msgQueue.add(msg);
        }
    }
}