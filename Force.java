public interface Force
{
    public static final String FORCE_MENU = "1. Force Push\n2. Force Choke\n3. Force Slam";
    //Menu to print the different force attacks.
    /**
     * this method is used by any class that implements the Force interface, 
     * method is defined in those classes.
     */
    //Methods to be defined in Force Enemy and the Hero classes.
    int forcePush();
    
    /**
     * this method is used by any class that implements the Force interface, 
     * method is defined in those classes.
     */
    int forceChoke();
    
    /**
     * this method is used by any class that implements the Force interface, 
     * method is defined in those classes.
     */
    int forceSlam();
}
