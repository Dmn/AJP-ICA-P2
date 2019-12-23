package ica;
public class UserAgent extends MetaAgent {

    private final Portal portal; /** Portal should never change */

    public UserAgent(String name, Portal portal)
    {
        super(name, portal);
        this.portal = portal;
    }

    public String getName()
    {
        return name;
    }

    public Portal getPortal() {
        return portal;
    }

    @Override
    public void msgHandler(Message msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}