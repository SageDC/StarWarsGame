import java.util.*;
public class Twilek extends Enemy
{
	/**
	 * Twilek() calls super to create a new type of enemy. 
	 * @param l - level is passed in to follow the heros level as he levels up, enemy level is matched.
	 */
	public Twilek(int l)
	{
		super("Twi'lek", l, 15);
	}
	
	@Override
	/**
	 * attack() is used when the Twilek() enemy needs to attack the hero.
	 * @param e - the hero entity is passed in and takes the enemies hits.
	 */
	public void attack(Entity e)
    {
		for(int i = 0; i < e.getLevel(); i++)
		{
	        Random rand = new Random();
	        int damage = rand.nextInt(5) + 1;
	        e.takeDamage(damage);
	        System.out.println(getName() + " hits " + e.getName() + " for " + damage + ".");
		}
    }
}
