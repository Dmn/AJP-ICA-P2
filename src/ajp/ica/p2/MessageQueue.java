package ajp.ica.p2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;


public class MessageQueue extends ArrayBlockingQueue
{
    static MessageQueue msgQ = null;
    
    private final Message msg;
    MessageQueue(Message msg, int amt)
    {
        super(amt);
        this.msg = msg;
    }
    
    static MessageQueue setMsgQ(MessageQueue msgQ)
    {
        return null;
    }
}
