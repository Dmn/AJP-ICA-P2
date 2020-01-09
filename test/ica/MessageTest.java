package ica;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author v8039087
 */
public class MessageTest {
    
    /**
     * test for message.
     */
    public MessageTest() {
    }

    /**
     * Test of getSender method, of class Message.
     */
    @Test
    public void testGetSender() {
        System.out.println("getSender");
        Message instance = new Message("Jason","Cristian","Hello World");
        String expResult = "Jason";
        String result = instance.getSender();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReceiver method, of class Message.
     */
    @Test
    public void testGetReceiver() {
        System.out.println("getReceiver");
        Message instance = new Message("Jason","Cristian","Hello World");
        String expResult = "Cristian";
        String result = instance.getReceiver();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContent method, of class Message.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        Message instance = new Message("Jason","Cristian","Hello World");
        String expResult = "Hello World";
        String result = instance.getContent();
        assertEquals(expResult, result);
    }

    /**
     * Test of wrap method, of class Message.
     */
    @Test
    public void testWrap() {
        System.out.println("wrap");
        Message instance = new Message("Jason","Cristian","Hello World");
        String expResult = "Jason:Cristian:Hello World";
        String result = instance.wrap();
        assertEquals(expResult, result);
    }
}
