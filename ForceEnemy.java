import java.util.Random;
public class ForceEnemy extends Enemy implements Force
{
    
    /**
     * Constructs a ForceEnemy used in the enemy list.
     * @param n - name of the force enemy.
     * @param l - level of the force enemy.
     * @param m - max health of the force enemy.
     * @param i - item to be dropped by forceEnemy when it is defeated.
     */
    public ForceEnemy(String n, int l, int m, Item i)
    {
        super(n, l, m); //creates an entity 
    }

    /**
     * calculates random amount of damage based on the health of the hero,
     * uses forcePush, forceChoke, or forceSlam randomly. Only used by forceEnemy.
     * @param e, parameter used to get the name of the hero and for the hero to take damage.
     */
    @Override
    public void attack(Entity e)
    {
        Random rand = new Random(); //constructs Random
        int value = rand.nextInt(3) + 1; //generates random number
        //uses random attack from interface class.
        if(value == 1)
        {
            int harm = forcePush();
            System.out.println(getName() + " hits " + e.getName() + " with a Force Push for " + harm + " damage.");
            e.takeDamage(harm); //random damage
        }
        else if(value == 2)
        {
            int harm = forceChoke();
            System.out.println(getName() + " hits " + e.getName() + " with a Force Choke for " + harm + " damage.");
            e.takeDamage(harm);
        }
        else
        {
            int harm = forceSlam();
            System.out.println(getName() + " hits " + e.getName() + " with a Force Slam for " + harm + " damage.");
            e.takeDamage(harm);
        }
    }
    
    /**
     * method from the Force interface, calculates random amount of damage for the hero to take.
     */
    //methods from Force interface, generate random damage to be taken.
    public int forcePush()
    {
        Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        return damage;
    }
    
    /**
     * method from the Force interface, calculates random amount of damage for the hero to take.
     */
    public int forceChoke()
    {
        Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        return damage;
    }
    
    /**
     * method from the Force interface, calculates random amount of damage for the hero to take.
     */
    public int forceSlam()
    {
        Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        return damage;
    }
}
