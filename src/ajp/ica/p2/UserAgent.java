package ajp.ica.p2;

public class UserAgent extends MetaAgent {

    private final String name;
    private Portal portal;

    public UserAgent(String name)
    {
        this.name = name;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void setPortal(Portal p)
    {
        this.portal = p;
        p.addAgent(this);
    }

    public void msgHandler(Message msg) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}