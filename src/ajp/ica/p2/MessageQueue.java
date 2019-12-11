package ajp.ica.p2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;


public class MessageQueue extends ArrayBlockingQueue implements Runnable
{
    private static MessageQueue msgQ = null;
    
    private MessageQueue()
    {
        super(100);
    }
    
    public static MessageQueue getMsgQ()
    {
        if (msgQ == null)
            msgQ = new MessageQueue();
        return msgQ;
    }

    @Override
    public void run() {
        
    }
}
