package ajp.ica.p2;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class Portal extends MetaAgent {
    
    private HashMap<String, Agent> routingTable;

    public Portal(String name, Portal portal, BlockingQueue queue) {
        super(name, portal, queue);
    }

    public HashMap<String, Agent> getRoutingTable() {
        return routingTable;
    }

    public void setRoutingTable(HashMap<String, Agent> routingTable) {
        this.routingTable = routingTable;
    }
   
    /**
     * The variable routing queue will be a HashMap mapping the
     * agent names to their blocking queue.
     */
    HashMap<Agent,BlockingQueue> map = new HashMap<>(
    
    );
    
    public void RouteTableAdd(Agent agent){
        HashMap<String, Agent> route= getRoutingTable();
        route.put(agent.getName(), agent);
        setRoutingTable(route);
    }
}
