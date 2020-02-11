import java.util.Random;

public class ForceUser extends EnemyDecorator implements Force
{
	/**
	 * this decorates the enemy created in EnemyGenerator as many times as the level of the hero.
	 * super() is called to create the new type of specific enemy.
	 * @param e - the enemy that will be decorated is passed in as a parameter.
	 */
	public ForceUser(Enemy e)
	{
		super(e.getName().contains("Force User") ? e.getName() : e.getName() + " Force User", e.getLevel(), e.getMaxHP(), e);
	}
	
	@Override
	/**
	 * attack() is used when the specific enemy needs to attack the hero.
	 * Because this decorator is of type force, extra if statements are added to determine the random
	 * force attack the enemy will do.
	 * @param e - the hero entity is passed in and takes the enemies hits.
	 */
    public void attack(Entity e)
    {
		for(int i = 0; i < e.getLevel(); i++)
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
    }
	
	@Override
	/**
	 * forcePush() is chosen randomly and is used to deal a random amount of damage to the hero.
	 * @return the damage done to the hero
	 */
	public int forcePush() 
	{
		Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        return damage;
	}

	@Override
	/**
	 * forceChoke() is chosen randomly and is used to deal a random amount of damage to the hero.
	 * @return the damage done to the hero
	 */
	public int forceChoke() 
	{
		Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        return damage;
	}

	@Override
	/**
	 * forceSlam() is chosen randomly and is used to deal a random amount of damage to the hero.
	 * @return the damage done to the hero
	 */
	public int forceSlam() 
	{
		Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        return damage;
	}
}
