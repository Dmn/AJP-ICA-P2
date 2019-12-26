package ica;
import java.util.HashMap;

public class Portal extends MetaAgent {
    /**
     * Hashmap containing:
     * <String> key (UserAgent name)
     * <UserAgent> Value (UserAgent object)
     */
    private HashMap<String, UserAgent> routing;

    /**
     * Constructor for Portal which calls its superclass MetaAgent.
     * @param name Name of the Portal.
     * @param portal A Portal this instance of Portal is connected to.
     */
    public Portal(String name, Portal portal) {
        super(name, portal);
        routing = new HashMap<>();

        //Can be connected to another portal. This connects the portals routing table to this one.
        if (portal != null)
        {
            portal.setPortal(this);
            routing = portal.getRoutingTable();
        }

    }

    /**
     * Accessor method for the routing table instance variable.
     * @return Returns the entire routing table.
     */
    public HashMap<String, UserAgent> getRoutingTable() {
        return routing;
    }

    /**
     * Accessor method for the routing table instance variable toString method.
     * Makes grabbing a set out of the routing table easier outside of the class.
     * @return A formatted string of the keys contained in the routing table.
     */
    public String routingTableToString() {
        return routing.keySet().toString();
    }

    /**
     * Mutator method for the instance variable routing table.
     * Only triggered when synchronizing routing tables.
     * @param routing HashMap routing table containing <String> name and <UserAgent> routing.
     */
    public void setRoutingTable(HashMap<String, UserAgent> routing) {
        this.routing = routing;
    }

    /**
     * Mutator method for the instance variable portal.
     * Only accessible in class. This is purely used in method addPortal.
     * @param portal Portal for this instance of Portal to connect to.
     */
    private void setPortal(Portal portal) {
        this.portal = portal;
    }

    /**
     * Accessor method to obtain UserAgent value from a Key.
     * Makes obtaining Values from keys easier outside of Class.
     * @param key A String key to obtain a UserAgent out of the routing table.
     * @return Returns the UserAgent obtained from the Routing table.
     */
    public UserAgent getKeyAgent(String key)
    {
        if (routing.containsKey(key))
            return routing.get(key);
        return null;
    }

    /**
     * This method synchronises the routing tables of this to the connected portals.
     * This happens if there is a new Agent in any of the connected portals or if there is one removed.
     * @param p Portal that needs their routing table to be updated.
     */
    private void sync(Portal p, UserAgent a) {
        if (p.portal != this) {
            HashMap<String, UserAgent> newRoutingTable = new HashMap<>();
            newRoutingTable.putAll(p.routing);
            if (a == null) {
                newRoutingTable.putAll(p.portal.getRoutingTable());
                p.routing = newRoutingTable;
                p.portal.setRoutingTable(newRoutingTable);
                sync(p.portal, null);
            }
            else if (a != null) {
                p.portal.setRoutingTable(newRoutingTable);
                sync(p.portal, a);
            }
        }
    }

    /**
     * Connects this instance of Portal to another.
     * This functions differently when there is already a connected portal in the "portal" instance variable.
     * @param portal Portal to connect to this instance of Portal.
     */
    public void addPortal(Portal portal) {
        if (this.portal != null) {
            Portal temp = this.portal; //P1 portal = P2
            this.portal = portal; //this.portal = P3
            portal.portal = temp; //P3 portal = P1
            sync(this, null);
        }
        else
        {
            this.portal = portal;
            sync(this, null);
            portal.setPortal(this);
        }
    }

    /**
     * Routes messages being sent from one UserAgent to the receiving UserAgent.
     * @param msg Message contains the Content, Sender and Receiver of the message.
     */
    @Override
    public void msgHandler(Message msg) {
        routing.get(msg.getReceiver());
    }

    /**
     * Adds UserAgents to the Portals routing table.
     * @param agent UserAgent being passed in. Allows adding to the routing table.
     * @return True/False if the UserAgent was successfully added.
     */
    public boolean addAgent(UserAgent agent) {
        if (!routing.containsKey(agent.getName()) && agent.getPortal() == this) {
            routing.put(agent.getName(), agent);
            if (portal != null)
                sync(this, null);
            return true;
        }
        return false;
    }

    /**
     * Removes UserAgents from the Portals routing table.
     * @param agent UserAgent being passed in. Allows removing from the routing table.
     * @return Returns null if UserAgent was removed and this value is assigned to the UserAgent itself. Or returns itself if fails.
     */
    public UserAgent removeAgent(UserAgent agent) {
        if (routing.containsValue(agent) && agent.getPortal() == this) {
            routing.remove(agent.getName());
            if (portal != null)
                sync(this, agent);
            return null;
        }
        return agent;
    }
}