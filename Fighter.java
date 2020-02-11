import java.util.Random;

public class Fighter extends EnemyDecorator
{
	/**
	 * this decorates the enemy created in EnemyGenerator as many times as the level of the hero.
	 * super() is called to create the new type of specific enemy.
	 * @param e - the enemy that will be decorated is passed in as a parameter.
	 */
	public Fighter(Enemy e)
	{
		super(e.getName().contains("Fighter") ? e.getName() : e.getName() + " Fighter", e.getLevel(), e.getMaxHP(), e);
	}
	
	@Override
	/**
	 * attack() is used when the specific enemy needs to attack the hero.
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