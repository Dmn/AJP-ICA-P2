package ajp.ica.p2;

public class UserAgent implements MetaAgent {

    private final String name;

    public UserAgent(String name)
    {
        this.name = name;
    }
    
    public String getName() 
    {
        return name;
    }
    
    @Override
    public void msgHandler(Message msg) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}