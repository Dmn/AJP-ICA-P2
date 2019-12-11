package ajp.ica.p2;

public class UserAgent extends MetaAgent {

    public UserAgent(String name)
    {
        super(name, null);
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

    @Override
    public void msgHandler(Message msg) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}