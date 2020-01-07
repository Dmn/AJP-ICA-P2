package ica;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        /**
         * TODO:
         * Insert Unit tests for sending messages and then receiving messages;
         * Implement Socket;
         * Insert Unit tests for sending/receiving messages across Sockets.
         */

        /**
         * In the future this will be where we demonstrate
         * our user agent system functioning.
         *
         * Example below:
         */

        System.out.println("Testing adding Agents to one portal.");

        Portal portal = new Portal("P1", null);
        UserAgent one = new UserAgent("Jason", portal);
        UserAgent two = new UserAgent("Cristian", portal);
        
        System.out.printf("Testing UserAgent: %s\n", one.toString());
        System.out.printf("Testing if Agent added to Routing table: %s\n", portal.getKeyAgent("Jason").getName());
        System.out.println("_______\n");

        System.out.println("Testing adding Portals to new portals.");

        Portal portalTwo = new Portal("P2", null);
        portalTwo.addPortal(portal);

        System.out.printf("Portal one is connect to: %s\n", portal.getPortal().getName());

        System.out.printf("Testing if Routing tables get merged: %s\n", portalTwo.routingTableToString());

        UserAgent three = new UserAgent("Joshua", portalTwo);
        UserAgent four = new UserAgent("Jake", portalTwo);

        Portal portalThree = new Portal("P3", portalTwo);

        UserAgent five = new UserAgent("Drake", portalThree);

        System.out.printf("Testing if Routing tables synced when new Agent: %s\n", portal.routingTableToString());

        /**
        Portal one = new Portal("P1", null);
        UserAgent a1 = new UserAgent("A1", one);
        UserAgent a2 = new UserAgent ("A2", one);


        a1.sendMessage(new Message(a1.getName(),a2.getName(),"Hello"));
         */

        System.out.println(portal.routingTableToString());

        /** Removing an agent requires you to make method removeAgent return null if it succeeds or return agent. */
        //System.out.println("Testing if UserAgent gets removed from routing tables.");
        //three = portalTwo.removeAgent(three);

        /** Code below should produce a NullPointerException because we null the UserAgent "three". */
        //try {
        //   System.out.printf("%s", three.toString());
        //}
        //catch (NullPointerException ex) {
        //    System.out.println("This UserAgent does not exist.");
        //}

        Vector<UserAgent> agents = new Vector<>();
        agents.add(one);
        agents.add(two);
        agents.add(three);
        agents.add(four);
        agents.add(five);

        //one.sendMessage(two.getName(),"Hello A2.");
        //two.sendMessage(three.getName(),"Hey there Joshua.");

        for (UserAgent i: agents) {
            one.sendMessage(i.getName(), ("Spamming. Hello mr. " + i.getName()));
        }
        five.sendMessage(one.getName(), "What the hell.");

        //System.out.printf("New Routing table: %s\n", portalTwo.routingTableToString());



        //one.sendMessage(two, "Hello World");

        //Agent three = new Agent("Josh");
        //Portal portalTwo = new Portal();
        //portalTwo.addAgent(three.getName(), three);

        //Socket router = new Socket();
        //router.addPortal(portalOne);
        //router.addPortal(portalTwo);

        //three.sendMessage(one, "Communicating through socket");
        //two.sendMessage(three, "Communicating through socket Part 2");

        /**
         * Disclaimer: The method addAgent's parameters may not be correct,
         * This was my interpretation of the BORIS implementations of that method.
         */

        /**

         MessageQueue msgQ;
         msgQ = new MessageQueue("User1", 100);

         msgQ.add(new Message("Hello Jason!"));

         for(int i = 0; i < 40; i++)
         {
            msgQ.add(new Message("Hello " + i));
            Thread.sleep(100);
         }

         msgQ.stop();
         System.out.println("Messages stopped.");
         Test
         */
        
        /* Socket to Socket test:
        1st test creating two sockets and connecting them.
        2nd test removing socket from hashmap.
        */
        Portal S2SPortal1= new Portal("S2SPortal1", portal);
        Portal S2SPortal2= new Portal("S2SPortal2", portal);        
        SocketAgent S2Sagent1 = new SocketAgent("S2Sagent1", S2SPortal1);
        SocketAgent S2Sagent2 = new SocketAgent("S2Sagent2", S2SPortal1);

        System.out.println("\n--Test 1 connecting socketAgents---\n");
        S2Sagent1.addSocket(S2Sagent2);
        System.out.println("The socket Agent should print 'true' if the 2nd socket agent is in the hash map");
        S2Sagent1.checkSocket(S2Sagent2);
        
        System.out.println("\n---Test 2 deleting socketAgents---\n");
        S2Sagent1.removeSocket(S2Sagent2);
        System.out.println("If the 2nd agent is deleted from the hash map, running again should print a error 'Could not be deleted'");
        S2Sagent1.removeSocket(S2Sagent2);
<<<<<<< Upstream, based on origin/t7199384
=======
//        System.out.println("Testing adding Agents to one portal.");
//
//        Portal portal = new Portal("P1", null);
//        UserAgent one = new UserAgent("Jason", portal);
//        UserAgent two = new UserAgent("Cristian", portal);
//        
//        System.out.printf("Testing UserAgent: %s\n", one.toString());
//        System.out.printf("Testing if Agent added to Routing table: %s\n", portal.getKeyAgent("Jason").getName());
//        System.out.println("_______\n");
//
//        System.out.println("Testing adding Portals to new portals.");
//
//        Portal portalTwo = new Portal("P2", null);
//        portalTwo.addPortal(portal);
//
//        System.out.printf("Portal one is connect to: %s\n", portal.getPortal().getName());
//
//        System.out.printf("Testing if Routing tables get merged: %s\n", portalTwo.routingTableToString());
//
//        UserAgent three = new UserAgent("Joshua", portalTwo);
//        UserAgent four = new UserAgent("Jake", portalTwo);
//
//        Portal portalThree = new Portal("P3", portalTwo);
//
//        UserAgent five = new UserAgent("Drake", portalThree);
//
//        System.out.printf("Testing if Routing tables synced when new Agent: %s\n", portal.routingTableToString());
//
//        /**
//        Portal one = new Portal("P1", null);
//        UserAgent a1 = new UserAgent("A1", one);
//        UserAgent a2 = new UserAgent ("A2", one);
//
//
//        a1.sendMessage(new Message(a1.getName(),a2.getName(),"Hello"));
//         */
//
//        System.out.println(portal.routingTableToString());
//
//        /** Removing an agent requires you to make method removeAgent return null if it succeeds or return agent. */
//        //System.out.println("Testing if UserAgent gets removed from routing tables.");
//        //three = portalTwo.removeAgent(three);
//
//        /** Code below should produce a NullPointerException because we null the UserAgent "three". */
//        //try {
//        //   System.out.printf("%s", three.toString());
//        //}
//        //catch (NullPointerException ex) {
//        //    System.out.println("This UserAgent does not exist.");
//        //}
//
//        Vector<UserAgent> agents = new Vector<>();
//        agents.add(one);
//        agents.add(two);
//        agents.add(three);
//        agents.add(four);
//        agents.add(five);
//
//        //one.sendMessage(two.getName(),"Hello A2.");
//        //two.sendMessage(three.getName(),"Hey there Joshua.");
//
//        for (UserAgent i: agents) {
//            one.sendMessage(i.getName(), ("Spamming. Hello mr. " + i.getName()));
//        }
//        five.sendMessage(one.getName(), "What the hell.");
//
//        //System.out.printf("New Routing table: %s\n", portalTwo.routingTableToString());
//
//
//
//        //one.sendMessage(two, "Hello World");
//
//        //Agent three = new Agent("Josh");
//        //Portal portalTwo = new Portal();
//        //portalTwo.addAgent(three.getName(), three);
//
//        //Socket router = new Socket();
//        //router.addPortal(portalOne);
//        //router.addPortal(portalTwo);
//
//        //three.sendMessage(one, "Communicating through socket");
//        //two.sendMessage(three, "Communicating through socket Part 2");
//
//        /**
//         * Disclaimer: The method addAgent's parameters may not be correct,
//         * This was my interpretation of the BORIS implementations of that method.
//         */
//
//        /**
//
//         MessageQueue msgQ;
//         msgQ = new MessageQueue("User1", 100);
//
//         msgQ.add(new Message("Hello Jason!"));
//
//         for(int i = 0; i < 40; i++)
//         {
//            msgQ.add(new Message("Hello " + i));
//            Thread.sleep(100);
//         }
//
//         msgQ.stop();
//         System.out.println("Messages stopped.");
//         Test
//         */


     Portal p1 = new Portal("p1",null);
     Portal p2 = new Portal("p2",null);
     Portal p3 = new Portal("p3",null);
     Portal p4 = new Portal("p4",null);
     UserAgent A1 = new UserAgent("A1",p1);
     UserAgent A2 = new UserAgent("A2",p2);
     UserAgent A3 = new UserAgent("A3",p3);
     UserAgent A4 = new UserAgent("A4",p4);
     Router router = new Router("r1",null);
     router.addPortal("p1");
     router.addPortal("p2");
     router.addPortal("p3");
     router.addPortal("p4");
     A1.sendMessage("A4", "YP");
     A4.sendMessage("A2", "YP");
     A3.sendMessage("A1", "YP");
     A2.sendMessage("A3", "YP");
=======
>>>>>>> 93a0352 >Added hashmap for socket connections >Added addSocket method >Added removeSocket method >gave socket a getName to use for former methods >Added socket adding and removing to main method for testing
    }
}
