package ica;

import java.util.ArrayList;

/**
 * This Monitor should allow agents to display messages in the GUI. 
 * Please note the following class is not fully implemented. 
 * The monitor should be used in other classes in order to get the results required. 
 * The monitor should be used in classes like portal, UserAgent, Message...etc.
 * @author v8039087.
 */
public abstract class Monitor extends MetaAgent {

    /**
     * variable to create new GUI and thread.
     */
    protected GUI monitorGui;
    private Thread thread;

    /**
     * The array list should get the agents.
     */
    protected ArrayList<MetaAgent> Agents = new ArrayList<>();

    /**
     * @param Name the name for the monitor.
     * @param portal the portal its going to be connected to. 
     */
    public Monitor(String Name, Portal portal) {
        super(Name, portal);
        monitorGui = new GUI(name, this);
        monitorGui.setVisible(true);
        monitorGui.dispose();
    }

    /**
     * The following method should start the thread.
     * It could also be run method in order to handle the messages.
     */
    private void start() {
        thread = new Thread(() -> {
            while (true) {
                try {
                    msgHandler(msgQueue.take());
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            }
        });
        thread.start();
    }

    /**
     * should stop or close the GUI.
     */
    public void CloseGUI() {
        monitorGui.close();
    }

    // @Override
    // public abstract void msgHandler(Message msg);
    
    /**
     * should display the message.
     * @param msg
     */
    @Override
    public void msgHandler(Message msg) {

        msgHandler(msg);
    }
    
  

}
