package ajp.ica.p2;

public class MetaAgent {
    private String name;
    private Portal p;

    public MetaAgent(String name, Portal p) {
        this.name = name;
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Portal getPortal() {
        return p;
    }

    public void setPortal(Portal p) {
        this.p = p;
    }
}
