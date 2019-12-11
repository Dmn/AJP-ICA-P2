package ajp.ica.p2;

import java.util.HashMap;

public class Portal extends MetaAgent 
{
    /**
     * The variable routing queue will be a HashMap mapping the
     * agent names to their blocking queue.
     */
    HashMap<String, UserAgent> routing = new HashMap<>();

    public Portal(String name, Portal portal) {
        super(name, portal);
    }

    public Portal getPortal() {
        return portal;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }
    
    public void msgHandler(Message msg)
    {
        routing.get(msg.getReceiver());
    }

    /**
     * Adds UserAgents to the Portals routing table.
     * @param agent UserAgent being passed in. Allows adding to the routing table.
     */
    public void addAgent(UserAgent agent)
    {
        routing.put(agent.getName(), agent);
    }
}