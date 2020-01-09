/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica;


import ica.Portal;
import ica.Portal;
import ica.Router;
import ica.Router;
import ica.UserAgent;
import ica.UserAgent;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;

/**
 *
 * @author zidlegit
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RouterTest {
    
    Portal dummyPortal = new Portal("DP",null);
    Router dummyRouter = new Router("DR",null);
    
    Router R1 = new Router("R1",null);
    Portal P1 = new Portal("P1",null);
    Portal P2 = new Portal("P2",null);
    Portal P3 = new Portal("P3",null);
    UserAgent A1 = new UserAgent("A1",P1);
    UserAgent A2 = new UserAgent("A2",P2);
    UserAgent A3 = new UserAgent("A3",P1);
    UserAgent A4 = new UserAgent("A4",P3);
    UserAgent A5 = new UserAgent("A4",P3);
    
    
    /**
     * Test of addPortal method, of class Router.
     */
    @Test
    public void TestA() {

        
        boolean testPass1 = false;
        
        System.out.println("\nTest for Portal Adding & if Portals sync");
        System.out.println("\nAdding Portals [P1,P2] to Router [R1]");
        R1.addPortal("P1");
        R1.addPortal("P2");
        
        System.out.println("Current R1 Routing: " + R1.getPortalRouting());
        System.out.println("Current R1 UserAgent Routing: " + R1.getUserAgent());
        System.out.println("\nCurrent P1 Routing: " + P1.getPortalRouting());
        System.out.println("Current P1 UserAgent Routing: " + P1.getUserAgentRoutings());
        System.out.println("\nCurrent P2 Routing: " + P2.getPortalRouting());
        System.out.println("Current P2 UserAgent Routing: " + P2.getUserAgentRoutings());
        
        if(P1.getPortalRouting().isEmpty() == false && P2.getPortalRouting().isEmpty() == false && R1.getPortalRouting().isEmpty() == false)
        {
            if(P1.getUserAgentRoutings().isEmpty() == false && P2.getUserAgentRoutings().isEmpty() == false && R1.getUserAgent().isEmpty()==false)
                    {
                        testPass1 = true;
                    }
        }
        
        System.out.println("\n Testing for Portal Remove & if Portals sync");
        System.out.println("\n\n Router Table before removal:");
        System.out.println("\nCurrent R1 Routing: " + R1.getPortalRouting());
        System.out.println("Current R1 UserAgent Routing: " + R1.getUserAgent());
        System.out.println("Removing P1");
        R1.removePortal("P1");
        System.out.println("\nSending Message to A2 from A1");
        A1.sendMessage("A2", "Hello");

        System.out.println("\nAfter Removal:");
        System.out.println("Current R1 Routing: " + R1.getPortalRouting());
        System.out.println("Current R1 UserAgent Routing: " + R1.getUserAgent());
        System.out.println("\nCurrent P1 Routing: " + P1.getPortalRouting());
        System.out.println("Current P1 UserAgent Routing: " + P1.getUserAgentRoutings());
        System.out.println("\nCurrent P2 Routing: " + P2.getPortalRouting());
        System.out.println("Current P2 UserAgent Routing: " + P2.getUserAgentRoutings());
    
        System.out.println("\nAdding Portal 1 back.");
        R1.addPortal("P1");
        System.out.println("Current R1 Routing: " + R1.getPortalRouting());
        System.out.println("Current R1 UserAgent Routing: " + R1.getUserAgent());
        System.out.println("\nCurrent P1 Routing: " + P1.getPortalRouting());
        System.out.println("Current P1 UserAgent Routing: " + P1.getUserAgentRoutings());
        System.out.println("\n\n Sending Message from A1 to A2");
       
        A1.sendMessage("A2", "Hello");
        
        System.out.println("\nRemoving Agent [A2] from [P2]");
        
        P2.removeAgent(A1);
        System.out.println("Current R1 UserAgent Routing: " + R1.getUserAgent());
        assertEquals(testPass1,true);
    }
  
}
