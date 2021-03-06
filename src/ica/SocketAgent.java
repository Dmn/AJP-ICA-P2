package ica;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 * this is class method that extends to the metaAgent
 * @author t7082305
 */

public class SocketAgent extends MetaAgent {

    private Socket socket;
    HashMap<String, SocketAgent> socketList = new HashMap<String, SocketAgent>();
    HashMap<Router,SocketAgent> routerRouting = new HashMap<>();

    /**
     *
     * @param name socketAgent name.
     * @param portal Actual portal.
     * @param socket Actual socket.
     */
    public SocketAgent(String name, Portal portal, Socket socket) {
        super(name, portal);
        this.socket = socket;
        updateList();
    }

    public String getName() {
        return name;
    }

    /**
     * gets the socket.
     * @return
     */
    public Socket getSocket() {
        return socket;
    }
 

    /**
     * this is while loop that reads the data input and thread
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

    private Message textToMsg(String input) {
        return new Message("socket", "portal", input);
    }

    /**
     * this is method  msgHandler if message has error or not
     * @param msg
     */
    @Override
    public void msgHandler(Message msg) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msgToText(msg));
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
// this is the msgtotext that has content

    private String msgToText(Message msg) {
        return msg.getContent();
    }
/*
    void routerConnect(Router aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/

    /**
     *
     * @param partner
     */

    public void addSocket(SocketAgent partner) {
        if (!socketList.containsKey(partner.getName())) {
            socketList.put(partner.getName(), partner);
        }
    }

    /**
     *
     * @param partner
     */
    public void removeSocket(SocketAgent partner) {
        boolean socketDeleted=false;
        if (socketList.containsKey(partner.getName())) {
            socketDeleted = socketList.remove(partner.getName(), partner);
            socketDeleted=true;
        }
        if (socketDeleted == false) {
            System.out.println("Could not be deleted");
        }
    }

    /**
     * Author v8117091: Creates an instance of tempRouter, and adds it to the 
     * router's static HashMap of sockets lists.
     * 
     */
    public void updateList()
    {
        Router tempRouter = new Router(null,null);
        this.socketList.put(this.name, this);
        tempRouter.socketSync(this, name);
    }
    
    /**
     * Author v8117091: Called by the router, once addSocket() is called in the router
     * it calls this socket and adding it from the socket side.
     * @param router
     */
    public void addRouterSync(Router router)
    {
        this.routerRouting.put(router, this);   
    }
    
    /**
     * Author v8117091: Called by a router, once removeSocket() is called in the
     * router, it calls this socket method thus removing it from the socket side.
     * 
     * @param router - the router you wish to remove
     */
    public void removeRouterSync(Router router)
    {
        this.routerRouting.remove(router,this);
    }
    
    /**
     * Author v8117091: returns the router - socket connection of this specfic
     * socket.
     * @return
     */
    public HashMap<Router,SocketAgent> getRouting()
    {
        
        return this.routerRouting;
    }
}
