/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author psych
 */
public class UserAgentTest {
    
    /**
     * UserAgent test.
     */
    public UserAgentTest() {
    }

    /**
     * Test of msgHandler method, of class UserAgent.
     */
    @Ignore
    @Test
    public void testMsgHandler() {
        System.out.println("msgHandler");
        Message msg = null;
        UserAgent instance = null;
        instance.msgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class UserAgent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Portal portal = new Portal("P1", null);
        UserAgent instance = new UserAgent("Jason", portal);
        String expResult = "Name: Jason | Portal: P1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
