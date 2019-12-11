package ajp.ica.p2;

import java.util.HashMap;

public class Portal implements MetaAgent 
{
    /**
     * The variable routing queue will be a HashMap mapping the
     * agent names to their blocking queue.
     */
    HashMap<String, UserAgent> routing = new HashMap<>();
    private final String name;
    private Portal portal;

    public Portal(String name, Portal portal) {
        this.name = name;
        this.portal = portal;
    }
    
    public Portal (String name){
        this.name = name;
    }

    public Portal getPortal() {
        return portal;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }
    
    @Override
    public void msgHandler(Message msg)
    {
        
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