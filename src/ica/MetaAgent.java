package ica;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetaAgent extends ArrayBlockingQueue<Message> implements Runnable {
    String name = "";
    Portal portal = null;
    private Thread thread;
    private volatile boolean run;

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

    /**
     * Accessor method for the name instance variable. Returns name of the Agent.
     * @return Name of the Agent in a String type.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for the portal instance variable. Returns portals connected to the current Portal.
     * @return The portal the current instance of Portal is connected to.
     */
    public Portal getPortal() {
        return portal;
    }
}
