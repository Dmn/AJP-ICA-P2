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
    }

    /**
     * Reactive method to send or receive messages.
     * @param msg Message containing the Content, Sender and Receiver of the message.
     */
    @Override
    public void msgHandler(Message msg)
    {
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