/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author psych
 */
public class PortalTest {
    
    /**
     *
     */
    public PortalTest() {
    }

    /**
     * Test of getRoutingTable method, of class Portal.
     */
    @Test
    public void testGetRoutingTable() {
        System.out.println("getRoutingTable");
        Portal instance = new Portal("P1", null);
        HashMap<String, UserAgent> expResult = new HashMap<>();
        HashMap<String, UserAgent> result = instance.getRoutingTable();
        assertEquals(expResult, result);
    }

    /**
     * Test of routingTableToString method, of class Portal.
     */
    @Test
    public void testRoutingTableToString() {
        System.out.println("routingTableToString");
        Portal instance = new Portal("P1", null);
        UserAgent agent = new UserAgent("Jason", instance);
        String expResult = "[Jason]";
        String result = instance.routingTableToString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRoutingTable method, of class Portal.
     */
    @Test
    public void testSetRoutingTable() {
        System.out.println("setRoutingTable");
        HashMap<String, UserAgent> routing = new HashMap<>();
        Portal portal = new Portal("P1", null);
        UserAgent agent = new UserAgent("Jason", portal);
        routing.put(agent.getName(), agent);
        Portal instance = new Portal("P2", portal);
        instance.getRoutingTable();
    }

    /**
     * Test of getKeyAgent method, of class Portal.
     */
    @Test
    public void testGetKeyAgent() {
        System.out.println("getKeyAgent");
        String key = "Jason";
        Portal instance = new Portal("P1", null);
        UserAgent expResult = new UserAgent("Jason", instance);
        UserAgent result = instance.getKeyAgent(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of sync method, of class Portal.
     */
    @Test
    public void testSync() {
        System.out.println("sync");
        Portal portal0 = new Portal("P1", null);
        UserAgent agent0 = new UserAgent("Jason", portal0);
        Portal instance = new Portal("P2", null);
        UserAgent agent1 = new UserAgent("Cristian", instance);
        portal0.addPortal(instance);
        System.out.println(portal0.routingTableToString());
    }

    /**
     * Test of addPortal method, of class Portal.
     */
    @Test
    public void testAddPortal() {
        System.out.println("addPortal");
        Portal portal = new Portal("P1", null);
        UserAgent agent = new UserAgent("Testing", portal);
        Portal instance = new Portal("P2", null);
        instance.addPortal(portal);
        System.out.println(instance.routingTableToString());
    }

    /**
     * Test of msgHandler method, of class Portal.
     */
    @Ignore
    @Test
    public void testMsgHandler() {
        System.out.println("msgHandler");
        Message msg = null;
        Portal instance = null;
        instance.msgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAgent method, of class Portal.
     * NOTE: Because of how addAgent is designed. This method can not be properly tested.
     * This method is called in UserAgent's constructor to add itself to the Portal's routing table.
     * The fact that the test fails however is a success in itself because it proves the UserAgent already exists in the routing table.
     */
    @Test
    public void testAddAgent() {
        System.out.println("addAgent");
        Portal instance = new Portal("P1", null);
        boolean expResult = false;
        boolean result = instance.addAgent(new UserAgent("Jason", instance));
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAgent method, of class Portal.
     * NOTE: The agent is removed from the Portal's routing table, but the UserAgent is never fully removed.
     * It still contains a pointer to the Portal even after it's removed as we aren't allowed to null the agent.
     * We can't null the pointer either because the portal instance variable is final.
     */
    @Test
    public void testRemoveAgent() {
        System.out.println("removeAgent");
        Portal instance = new Portal("P1", null);
        UserAgent agent = new UserAgent("Jason", instance);
        UserAgent expResult = null;
        UserAgent result = instance.removeAgent(agent);
        assertEquals(expResult, result);
    }
    
}
