package ica;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author t7082305
 */

// this is class method that extends to the metaagent
public class SocketAgent extends MetaAgent{
    
    private Socket socket;

    public SocketAgent(String name, Portal portal,Socket socket) {
        super(name, portal);
        this.socket = socket;
    }
    // this is while loop that reads the data input and thread
    public void makeReadLoop(){
        Thread thread = new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String input;
                while((input=dataInputStream.readUTF())!=null){
                    portal.msgQueue.add(textToMsg(input));
                }
            } catch (IOException ex) {
                System.out.println("Error");
            }
        });
        thread.start();
    }

    private Message textToMsg(String input) {
        return new Message("socket","portal",input);
    }

    @Override
    // this is method  msgHandler if message has error or not
    public void msgHandler(Message msg) {
        try{
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msgToText(msg));
        }catch(IOException ex){
            System.out.println("Error");
        }
    }
// this is the msgtotext that has content
    private String msgToText(Message msg) {
        return msg.getContent();
    }
}
