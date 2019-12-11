package ajp.ica.p2;

import java.util.concurrent.ArrayBlockingQueue;

public class MessageQueue extends ArrayBlockingQueue
{
    private final Message msg;
    MessageQueue(Message msg, int amt)
    {
        super(amt);
        this.msg = msg;
    }
}
