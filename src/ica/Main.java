package ica;
public class Main {
    public static void main(String[] args) {
        /**
         * TODO:
         *
         * Implement MetaAgent, Agent, Portal and Message;
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

        Portal portalOne = new Portal("p1", null);
        UserAgent one = new UserAgent("Jason", portalOne);
        UserAgent two = new UserAgent("Cristian", portalOne);

        portalOne.addAgent(one);
        portalOne.addAgent(two);

        System.out.println("hello");

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
         */
    }
}
