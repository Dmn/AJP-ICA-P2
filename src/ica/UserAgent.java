package ica;
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

        /**
         * TODO: Implement Runnable and check on every tick whether we are sending or receiving a message.
         */
    }

    /**
     * Reactive method to send or receive messages.
     * @param msg Message containing the Content, Sender and Receiver of the message.
     */
    @Override
    public void msgHandler(Message msg)
    {
        //If we are sending a message (message sender == user agent name)
        if (msg.getSender() == name) {
            //If the portals routing table contains the receiver.
            if (portal.getRoutingTable().containsKey(msg.getReceiver())) {

            }
        }
        //If we are receiving the message
        else if (msg.getSender() != name) {
            System.out.printf("%s recieved message: %s. From: %s", msg.getReceiver(), msg.getContent(), msg.getSender());
        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * toString method detailing the UserAgents name and which Portal it's connected to.
     * @return
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Portal: " + portal.getName();
    }
}