package Model;

public class User
{
    
    final static private User   instance = new User();
    final private String name     = "K2000";
    final private String group    = "Groupe 6";
    
    private static User getInstance()
    {
        return instance;
    }
    
    private String getName()
    {
        return name;
    }
    private String getGroup()
    {
        return group;
    }
    
}
