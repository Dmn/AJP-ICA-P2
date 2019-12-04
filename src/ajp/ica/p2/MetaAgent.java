package ajp.ica.p2;

import java.util.concurrent.BlockingQueue;

public class MetaAgent implements Runnable
{
    private final BlockingQueue queue;
    private final String name;
    private final Portal portal;

    public MetaAgent(String name, Portal portal, BlockingQueue queue) {
        this.name = name;
        this.portal = portal;
        this.queue = queue;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public synchronized void msgHandler(Message msg)
    {
        //Handle the messages.
    }
}
