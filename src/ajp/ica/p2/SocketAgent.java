package ajp.ica.p2;

import java.util.concurrent.BlockingQueue;

public class SocketAgent extends MetaAgent{
    
    public SocketAgent(String name, Portal portal) {
        super(name, portal);
        //super(name, portal, queue);
    }

    public void msgHandler(Message msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
