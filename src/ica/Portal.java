package ica;
import java.util.HashMap;

public class Portal extends MetaAgent {
    /**
     * Hashmap containing:
     * <String> key (UserAgent name)
     * <UserAgent> Value (UserAgent object)
     */
    private static HashMap<String,UserAgent> userAgentList = new HashMap<>();
    private static HashMap<String,Portal> portalList = new HashMap<>();
    
    private HashMap<UserAgent,Portal> userPortalList = new HashMap<>();
    private HashMap<Portal,Router> portalRouting = new HashMap<>();
    
    private HashMap<String, UserAgent> routing;

    /**
     * Constructor for Portal which calls its superclass MetaAgent.
     * @param name Name of the Portal.
     * @param portal A Portal this instance of Portal is connected to.
     */
    public Portal(String name, Portal portal) {
        super(name, portal);
        routing = new HashMap<>();
        //ZIDS
        if(portalList.containsKey(name) == false)
        {
            portalList.put(name, this);
            syncPortalList();
        }
        
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
     *  @Author V8117091 : This method will be called by the router, once called
     * it will add the routing of Portal to Router, this method calls upon another
     * method userAgentSync(). This method will also remove the routing of portal
     * and router. This is never called by itself only the router calls this.
     * 
     * IF Router is added on, it will also automatically update the router's userAgentList
     * && Routing
     * 
     *
     * @param router the router that calls this will be passed on as router in
     * this method.
     * @param add if User wishes to remove this boolean will be false, if they
     * want to add more then this boolean will be true.
     */
    public void syncPortalRouting(Router router,Boolean add)
    {
        if(add == true)
        {
            if (portalRouting.isEmpty() == true)
            {
                portalRouting.put(this, router);
                router.userAgentSync(userPortalList, routing);
            }
            else
            {
                System.out.println("This portal is already connected to a router.");
            }
        }
        else
        {
            if(portalRouting.get(this) == router)
            {
                portalRouting.remove(this,router);
            }
            else if(portalRouting.isEmpty() == true)
            {
                System.out.println("This portal is not connected to a router");
            }
            else if (portalRouting.get(this) != router)
            {
                System.out.println("This portal is not connected to the inputted router");
            }
            
        }
        
    }
        //Syncs this portal list to the Router

    /**
     * @Author V8117091 : Once a Portal is created it updates the portal list in
     * this class and updates the router.
     */
        public void syncPortalList()
        {
            Router tempRouter = new Router(null,null);
            tempRouter.syncPortalList(this,this.name);
            tempRouter=null;
        }
        
    /**
     * @Author V8117091 :Just returns the portal routing.
     * @return
     */
    public HashMap<Portal,Router> getPortalRouting()
        {
            
            return portalRouting;
        }
        
    /**
     * @Author V8117091 : Syncs the userAgent with this portal creating a list
     * of userAgents that are connected to this portal and creates a portal to
     * UserAgent connection in HashMap.
     * @param userAgentName
     * @param userAgent
     */
    public void userAgentSync(String userAgentName,UserAgent userAgent)
    {
        routing.put(userAgentName, userAgent);
        userPortalList.put(userAgent,this); 
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
    private void sync(Portal p, boolean b) {
        if (p.portal != this) {
            HashMap<String, UserAgent> newRoutingTable = new HashMap<>();
            newRoutingTable.putAll(p.routing);
            if (!b) {
                newRoutingTable.putAll(p.portal.getRoutingTable());
                p.routing = newRoutingTable;
                p.portal.setRoutingTable(newRoutingTable);
                sync(p.portal, false);
            }
            else if (b) {
                p.portal.setRoutingTable(newRoutingTable);
                sync(p.portal, true);
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
            sync(this, false);
        }
        else
        {
            this.portal = portal;
            portal.portal = this;
            sync(this, false);
        }
    }

    /**
     * @Author Jason: Routes messages being sent from one UserAgent to the receiving UserAgent.
     * @Author V8117091 : Added a Message handeler that, if userAgent is not
     * within the portal it will forward it to the router and router will direct
     * it to the appropriate portal where the UserAgent is located.
     * @param msg Message contains the Content, Sender and Receiver of the message.
     */
    @Override
    public void msgHandler(Message msg) {
        if (routing.containsKey(msg.getReceiver())) {
            routing.get(msg.getReceiver()).msgQueue.add(msg);
        }
        else if(routing.containsKey(msg.getReceiver())== false && portalRouting.isEmpty() == false)
        {
            portalRouting.get(this).msgHandler(msg);
        }
        else
        {
            System.out.println("Agent not found");
        }
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
                sync(this, false);
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
                sync(this, true);
            return null;
        }
        return agent;
    }
}