package ica;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class MetaAgent {
    protected final String name;
    protected Portal portal;
    private Thread thread;
    protected final ArrayBlockingQueue<Message> msgQueue;

    public MetaAgent(String name, Portal portal) {
        msgQueue = new ArrayBlockingQueue<Message>(100);
        this.name = name;
        this.portal = portal;

        start();
    }

    private void start() {
        thread = new Thread(() -> {
            while (true)
            {
                try {
                    msgHandler(msgQueue.take());
                }
                catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            }
        });
        thread.start();
    }

    public abstract void msgHandler(Message msg);

    /**
     * toString method detailing the UserAgents name and which Portal it's connected to.
     * @return
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Portal: " + portal;
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
