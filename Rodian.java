import java.util.Random;

public class Rodian extends Enemy
{
	/**
	 * Rodian() calls super to create a new type of enemy. 
	 * @param l - level is passed in to follow the heros level as he levels up, enemy level is matched.
	 */
	public Rodian(int l)
	{
		super("Rodian", l, 15);
	}
	
	@Override
	/**
	 * attack() is used when the Rodian() enemy needs to attack the hero.
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
