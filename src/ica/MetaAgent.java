package ica;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetaAgent extends ArrayBlockingQueue<Message> implements Runnable {
    String name = "";
    Portal portal = null;
    private Thread thread;
    private volatile boolean run;

    private MetaAgent() {
        super(100);
    }


    // I belive the MetaAgent needs to have name so we could use this.
    public void setName(String metaName) {
        name = metaName;
    }

    public String getName() {
        return name;
    }


    public MetaAgent(String name, Portal portal) {
        super(100);
        this.name = name;
        this.portal = portal;
    }

    private void start() {
        thread = new Thread(this);
        thread.start();
    }

    public final void stop() {
        try {
            run = false;
            thread.interrupt();
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageQueue.class.getName())
                    .log(Level.WARNING, null, ex);
        }
    }

    @Override
    public void run() {
        Thread messageFling = new Thread(this);

        while (run) {
            try {
                msgHandler(this.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(MessageQueue.class.getName())
                        .log(Level.INFO, null, ex);
            }
        }
    }

    public synchronized void msgHandler(Message msg) {

        System.out.println(this.name + ": " + msg.getContent());

        //Handle the messages.
        //UserAgent r= receiver;
        //BlockingQueue q= r.getQueue();
        //q.add(msg);
        //r.setQueue(q);
    }
}
