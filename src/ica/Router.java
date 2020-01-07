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
    private static HashMap<String,UserAgent> userAgentList = new HashMap<>();
    private static HashMap<String,Portal> portalList = new HashMap<>();
    private static HashMap<String,Router> routerList = new HashMap<>();
    private static HashMap<String,SocketAgent> socketList = new HashMap<>();
    
    private static HashMap<UserAgent,Portal> userAgentRouting = new HashMap<>();
    private static HashMap<Portal,Router> portalRouting = new HashMap<>();
    private static HashMap<Router,SocketAgent> routerRouting = new HashMap<>();
    
    /**
     *
     * @param routerName
     * @param portal
     */
    public Router(String routerName, Portal portal)
    {
        super(routerName,null);
    }

    /**
     *
     * @param portalName
     */
    public void addPortal(String portalName)
    {
        if(portalList.containsKey(portalName) == true && portalRouting.get(portalList.get(portalName)) != this && portalRouting.containsKey(portalList.get(portalName)) == false)
        {
            portalList.get(portalName).syncPortalRouting(this,true);
            portalRouting.put(portalList.get(portalName), this);
        }
        else
        {
            System.out.println("Either portal doesn't exist or it has already conencted to this/another router.");
        }
    }
        
    /**
     *
     * @param portalName
     */
    public void removePortal(String portalName)
    {
        
        if(portalList.containsKey(portalName) == true && portalRouting.get(portalList.get(portalName))==this)
        {
            portalRouting.remove(portalList.get(portalName), this);
            for(int i = 0 ; findUserAgentPortal(portalList.get(portalName)).size() < i ; i++)
            {
                userAgentRouting.remove(findUserAgentPortal(portalList.get(portalName)).get(i),portalList.get(portalName));
            }
            portalList.get(portalName).syncPortalRouting(this,false);
        }
    }
    
    /**
     * Gets an ArrayList of all UserAgents that are connected to this router
     * 
     * @param portal
     * @return returns an ArrayList of all users inside the portal a specfic portal.
     */
    public ArrayList<UserAgent> findUserAgentPortal(Portal portal)
    {
        ArrayList<UserAgent> userAgentListPortal = new ArrayList<>();
        
        for(Map.Entry<UserAgent,Portal> userAgentPortal: userAgentRouting.entrySet())
        {
            UserAgent tempUserAgent = userAgentPortal.getKey();
            Portal tempPortal = userAgentPortal.getValue();
            
            if(tempPortal == portal)
            {
                userAgentListPortal.add(tempUserAgent);
            }
        }
        return userAgentListPortal;
    }
    
    /**
     * Ignore for now
     * @param socketName String to accept a user input.
     */
    public void addSocket(String socketName)
    {
        if(socketList.containsKey(socketName)==true)
        {
            routerRouting.put(this, socketList.get(socketName));
            socketList.get(socketName).routerConnect(this);
        } 
    }
    
    /**
     *
     * @param socketName
     */
    public void removeSocket(String socketName)
    {
        
    }
    
    public void socketSync(SocketAgent socket, String socketName)
    {
        this.socketList.put(socketName,socket);
    }

    
    /**
     *
     * @param portal
     * @param portalName
     */
    public void syncPortalList(Portal portal,String portalName)
    {
        portalList.put(portalName,portal);
    }
    
    /**
     *
     * @return
     */
    public HashMap<Portal,Router> getPortalRouting()
    {
        return portalRouting;
    }
      
    /**
     * Returns an ArrayList of Portal 
     * @param portal
     * @return
     */
    public ArrayList<Portal> getRouterLists(Portal portal)
    {
        ArrayList<Portal> portalLists = new ArrayList<>();
        for(Map.Entry<Portal,Router> portalrouters: portalRouting.entrySet())
        {
            Portal tempP = portalrouters.getKey();
            Router tempR = portalrouters.getValue();
            
            if(tempR == this && tempP == portal)
            {
                portalLists.add(tempP);
            }
        }
        return portalLists;
    }
    
    
    //want to return portals connected to this router

    /**
     *
     * @return
     */
    public ArrayList<Portal> getRouterPortalList()
    {
        ArrayList<Portal> portalListSpecficRouter = new ArrayList<>();
        
        for(Map.Entry<Portal,Router> portalrouters: portalRouting.entrySet())
        {
            Portal tempPortal = portalrouters.getKey();
            Router tempRouter = portalrouters.getValue();
            System.out.println(tempPortal);
            if(tempRouter == this)
            {
                portalListSpecficRouter.add(tempPortal);
            }
        }

        return portalListSpecficRouter;
        
    }

    /**
     *
     * @param userAgentConnection
     * @param userAgentLists
     */
    public void userAgentSync(HashMap<UserAgent,Portal> userAgentConnection,HashMap<String,UserAgent> userAgentLists)
    {
        userAgentList.putAll(userAgentLists);
        userAgentRouting.putAll(userAgentConnection);   
    }
    
    /**
     *
     * @param msg
     */
    @Override
    public void msgHandler(Message msg) {
        if(userAgentList.containsKey(msg.getReceiver()))
        {
            if(getRouterLists(userAgentRouting.get(userAgentList.get(msg.getReceiver()))).isEmpty() == false)
            {
                userAgentRouting.get(userAgentList.get(msg.getReceiver())).msgHandler(msg);
            }
            }
 
        }
    }

