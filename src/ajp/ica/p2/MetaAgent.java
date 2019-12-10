package ajp.ica.p2;

import java.util.concurrent.BlockingQueue;

public class MetaAgent implements Runnable
{
    private BlockingQueue queue;
    private final String name;
    private final Portal portal;

    public MetaAgent(String name, Portal portal, BlockingQueue queue) {
        this.name = name;
        this.portal = portal;
        this.queue = queue;
    }
    
    public void setQueue(BlockingQueue queue){
        this.queue=queue;
    }

    public BlockingQueue getQueue() {
        return queue;
    }

    public String getName() {
        return name;
    }

    public Portal getPortal() {
        return portal;
    }

    @Override
    public void run() {
        Thread messageFling=new Thread(this);
        
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public synchronized void msgHandler(Agent receiver ,Message msg)
    {
        //Handle the messages.
        Agent r= receiver;
        BlockingQueue q= r.getQueue();
        q.add(msg);
        r.setQueue(q);
    }
}
