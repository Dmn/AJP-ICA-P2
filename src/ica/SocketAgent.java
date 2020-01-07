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
 */
// this is class method that extends to the metaagent
public class SocketAgent extends MetaAgent {

    private Socket socket;
    HashMap<String, SocketAgent> socketList = new HashMap<String, SocketAgent>();

    public SocketAgent(String name, Portal portal, Socket socket) {
        super(name, portal);
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    // this is while loop that reads the data input and thread
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

    @Override
    // this is method  msgHandler if message has error or not
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
    private void addSocket(SocketAgent partner) {
        if (!socketList.containsKey(partner.getName())) {
            socketList.put(partner.getName(), partner);
        }
    }

    private void removeSocket(SocketAgent partner) {
        boolean socketDeleted=false;
        if (socketList.containsKey(partner.getName())) {
            socketDeleted = socketList.remove(partner.getName(), partner);
            socketDeleted=true;
        }
        if (socketDeleted == false) {
            System.out.println("Could not be deleted");
        }
    }
}
