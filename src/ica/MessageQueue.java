package ica;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author v8039087
 */
public class MessageQueue extends MetaAgent
{
    private static MessageQueue msgQ;
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    private MessageQueue()
    {
        super("Queue", null);
        //this.name = name;
        //this.run = true;

    }

    /**
     *
     * @return
     */
    public static MessageQueue makeMsgQ()
    {
        if (msgQ == null)
            return msgQ = new MessageQueue();
        return msgQ;
    }

    /**
     *
     * @param msg
     */
    @Override
    public void msgHandler(Message msg) {

    }
}
