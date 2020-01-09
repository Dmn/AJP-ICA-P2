/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zidlegit
 */
public class Router extends MetaAgent {

    //For User Input as user input is string
    private static HashMap<String, UserAgent> userAgentList = new HashMap<>();
    private static HashMap<String, Portal> portalList = new HashMap<>();
    private static HashMap<String, Router> routerList = new HashMap<>();
    private static HashMap<String, SocketAgent> socketList = new HashMap<>();

    private static HashMap<UserAgent, Portal> userAgentRouting = new HashMap<>();
    private static HashMap<Portal, Router> portalRouting = new HashMap<>();
    private static HashMap<Router, SocketAgent> routerRouting = new HashMap<>();

    /**
     *
     * @param routerName
     * @param portal
     */
    public Router(String routerName, Portal portal) {
        super(routerName, null);
    }

    /**
     * Authour v8117091: Adds portal to the portalList & calls portal to sync
     * all UserAgents of it's userAgent's that are connected to the portal to
     * sync with this router so the routing's are the same.
     * @param portalName
     */
    public void addPortal(String portalName) {
        if (portalList.containsKey(portalName) == true && portalRouting.get(portalList.get(portalName)) != this && portalRouting.containsKey(portalList.get(portalName)) == false) {
            portalList.get(portalName).syncPortalRouting(this, true);
            portalRouting.put(portalList.get(portalName), this);
        } else {
            System.out.println("Either portal doesn't exist or it has already conencted to this/another router.");
        }
    }

    /**
     * Author v8117091: Removes the portal & UserAgents connected to that
     * portal from the routing tables HashMap.
     * @param portalName
     */
    public void removePortal(String portalName) {
        System.out.println("Entered");
        if (portalRouting.get(portalList.get(portalName)) == this) {
            portalRouting.remove(portalList.get(portalName), this);
            portalList.get(portalName).syncPortalRouting(this, false);

            while (userAgentRouting.containsValue(portalList.get(portalName)) == true) {
                userAgentRouting.remove(getUserAgentPortal(portalList.get(portalName)), portalList.get(portalName));
            }
        }
    }

    /**
     * Author v8117091: Checks which user is connected to this portal since the
     * only usage is in a while loop it will call it until it the while loop
     * condition is met.
     * @param portal
     * @return
     */
    public UserAgent getUserAgentPortal(Portal portal) {
        UserAgent UA = null;
        for (Map.Entry<UserAgent, Portal> userAgentPortal : userAgentRouting.entrySet()) {
            UserAgent tempUserAgent = userAgentPortal.getKey();
            Portal tempPortal = userAgentPortal.getValue();

            if (tempPortal == portal) {
                UA = tempUserAgent;
            }
        }
        return UA;
    }

    /**
     * Author v8117091: Adds Router to Socket connection and to enable router
     * to socket connection.
     * @param socketName String to accept a user input.
     */
    public void addSocket(String socketName) {
        if (socketList.containsKey(socketName) == true && routerRouting.isEmpty() == true) {
            routerRouting.put(this, socketList.get(socketName));
            socketList.get(socketName).addRouterSync(this);
        }
    }

    /**
     * Author v8117091: Removes socket from router & calls the socket and remove
     * it's connection from socket to router.
     * 
     * @param socketName
     */
    public void removeSocket(String socketName) {
        if (socketList.containsKey(socketName) == true && routerRouting.get(this) == socketList.get(socketName)) {
            routerRouting.remove(this, socketList.get(socketName));
            socketList.get(socketName).removeRouterSync(this);
        }
    }

    /**
     * Auhtor v8117091: When a socket is created, the socket will be added to the
     * socket list on the router side. Only called by SocketAgent.
     * 
     * @param socket
     * @param socketName
     */
    public void socketSync(SocketAgent socket, String socketName) {
        this.socketList.put(socketName, socket);
    }

    /**
     * Author v8117091: Once a portal is created it calls this method, and
     * add's itself to the portalList but not the the portalRouting HashMap.
     * @param portal - the portal that just registered.
     * @param portalName - the portal's name.
     */
    public void syncPortalList(Portal portal, String portalName) {
        portalList.put(portalName, portal);
    }

    /**
     * Author v8117091: Returns the portalRouting HashMap.
     * @return
     */
    public HashMap<Portal, Router> getPortalRouting() {
        return portalRouting;
    }

    /**
     * Author v8117091: syncs the userAgentList & userAgentPortals 
     * @param userAgentConnection
     * @param userAgentLists
     */
    public void userAgentSync(HashMap<UserAgent, Portal> userAgentConnection, HashMap<String, UserAgent> userAgentLists) {
        userAgentList.putAll(userAgentLists);
        userAgentRouting.putAll(userAgentConnection);
    }

    /**
     * Author v8117091: checks userAgent is on the list and if get and if it is
     * it will re-route message to appropriate portal.
     * @param msg
     */
    @Override
    public void msgHandler(Message msg) {
//        if(userAgentList.containsKey(msg.getReceiver()))
        if (userAgentRouting.containsKey(userAgentList.get(msg.getReceiver())) == true && userAgentRouting.containsKey(userAgentList.get(msg.getSender()))) {
            userAgentRouting.get(userAgentList.get(msg.getReceiver())).msgHandler(msg);
        } else if (userAgentList.containsKey(msg.getReceiver()) && userAgentList.containsKey(msg.getSender())) {
            System.out.println("Agents are not connected to this router.");
        }
    }

    /**
     * Author v8117091: When called, this method will return the Router - SocketAgent
     * routing.
     * @return
     */
    public HashMap<Router, SocketAgent> getRouting() {

        return this.routerRouting;
    }

    /**
     *Author v8117091: When called will return the user-agent routing.
     * @return
     */
    public HashMap<UserAgent, Portal> getUserAgent() {

        return this.userAgentRouting;
    }

    /**
     *Author v8117091: Not completed because the socket agent doesn't work.
     * @param msg
     */
    public void receiveMessageFromRouter(Message msg) {
        if (userAgentList.containsKey(msg.getReceiver()) == true) {
            userAgentRouting.get(userAgentList.get(msg.getReceiver())).msgHandler(msg);
        }
    }

    /**
     * Author v8117091: If a userAgent is removed from a portal it will call this
     * class and will remove the apropriate useragent from the userAgent - portal routing.
     * @param userAgent
     * @param portal
     * @param add
     */
    public void userAgentSync(UserAgent userAgent, Portal portal, boolean add) {
        if (userAgentRouting.containsKey(userAgent) == false && add == true) {
            userAgentRouting.put(userAgent, portal);
        }
        if (userAgentRouting.containsKey(userAgent) == true && add == false) {
            userAgentRouting.remove(userAgent, portal);
        }

    }

}
