package ajp.ica.p2;

import java.util.concurrent.BlockingQueue;

public class Socket extends MetaAgent{
    
    public Socket(String name, Portal portal, BlockingQueue queue) {
        super(name, portal, queue);
    }
    
}
