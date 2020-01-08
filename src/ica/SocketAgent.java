package ica;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author t7082305
 * @author Ashley Hood T7199384
 */
// this is class method that extends to the metaagent
public class SocketAgent extends MetaAgent {

    private Socket socket;
    HashMap<String, SocketAgent> socketList = new HashMap<String, SocketAgent>();
    HashMap<Router, SocketAgent> routerRouting = new HashMap<>();

    /**
     * Constructs a SocketAgent.
     *
     * @param name Used for the key to find the SocketAgent.
     * @param portal The address of the portal this agent is connected to.
     */
    public SocketAgent(String name, Portal portal) {
        super(name, portal);
    }

    /**
     * Constructs a SocketAgent.
     *
     * @param name Used for the key to find the SocketAgent.
     * @param portal The address of the portal this agent is connected to.
     * @param socket A socket class.
     */
    public SocketAgent(String name, Portal portal, Socket socket) {
        super(name, portal);
        this.socket = socket;
        updateList();
    }

    /**
     * Gets the name of SocketAgent.
     *
     * @return The name of the SocketAgent class.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the socket class in SocketAgent class.
     *
     * @return The socket class.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Creates a thread for messages to go through in real time.
     *
     */
    public void makeReadLoop() {
        Thread thread = new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String input;
                while ((input = dataInputStream.readUTF()) != null) {
                    portal.msgQueue.add(textToMsg(input));
                }
            } catch (IOException ex) {
                System.out.println("Error");
            }
        });
        thread.start();
    }

    /**
     * Puts a message into a Message class.
     *
     * @param input Text to put in a Message class.
     * @return The string contained in a Message class.
     */
    private Message textToMsg(String input) {
        return new Message("socket", "portal", input);
    }

    @Override
    /**
     * Watches the thread and catches errors.
     *
     * @param msg A system message.
     */
    public void msgHandler(Message msg) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msgToText(msg));
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }

    /**
     * Gets the text from within the message class..
     *
     * @param msg A system message.
     * @return The string in the message.
     */
    private String msgToText(Message msg) {
        return msg.getContent();
    }

    /**
     * Add the socket passed through to this routing table .
     *
     * @param partner The socket to be added to the routing table.
     */
    public void addSocket(SocketAgent partner) {
        if (!socketList.containsKey(partner.getName())) {
            socketList.put(partner.getName(), partner);
        } else {
            System.out.println("Socket already in list");
        }
    }

    /**
     * Checks the socket passed through is in the routing table by finding the
     * key.
     *
     * @param partner The socket to be added to the routing table.
     */
    public void checkSocket(SocketAgent partner) {
        System.out.println(socketList.containsKey(partner.getName()));
    }

    /**
     * Removes the socket passed through is in the routing table by finding the
     * key and clearing it.
     *
     * @param partner The socket to be added to the routing table.
     */
    public void removeSocket(SocketAgent partner) {
        boolean socketDeleted = false;
        if (socketList.containsKey(partner.getName())) {
            socketDeleted = socketList.remove(partner.getName(), partner);
            socketDeleted = true;
        }
        if (socketDeleted == false) {
            System.out.println("Could not be deleted");
        }
    }

    public void updateList() {
        Router tempRouter = new Router(null, null);
        this.socketList.put(this.name, this);
        tempRouter.socketSync(this, name);
    }

    public void addRouterSync(Router router) {
        this.routerRouting.put(router, this);
    }

    public void removeRouterSync(Router router) {
        this.routerRouting.remove(router, this);
    }

    public HashMap<Router, SocketAgent> getRouting() {

        return this.routerRouting;
    }
}
