package ajp.ica.p2;

import java.util.concurrent.BlockingQueue;

public class Socket implements MetaAgent{
    
    public Socket(String name, Portal portal, BlockingQueue queue) {
        //super(name, portal, queue);
    }

    @Override
    public void msgHandler(Message msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
