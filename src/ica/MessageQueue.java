package ica;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageQueue extends ArrayBlockingQueue<Message> implements Runnable
{
    private final String name;
    private Thread thread;
    private volatile boolean run;


    public MessageQueue(String name, int run)
    {
        super(100);
        this.name = name;
        this.run = true;

        start();
    }

//    public static MessageQueue getMsgQ(String name)
//    {
//        if (msgQ == null)
//            msgQ = new MessageQueue();
//        return msgQ;
//    }

    private void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    public final void stop()
    {
        try {
            run = false;
            thread.interrupt();
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageQueue.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while(run)
        {
            try {
                msgHandler((Message) this.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(MessageQueue.class.getName())
                        .log(Level.INFO, null, ex);
            }
        }

    }
    public void msgHandler(Message msg)
    {
        System.out.println(this.name + ": " + msg.getContent());
    }
}
