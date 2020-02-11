public abstract class EnemyDecorator extends Enemy
{
	/**
	 * enemy is used and set as e which is the enemy created in Fighter and Force User.
	 * It is used to attack the hero in the attack method.
	 */
	private Enemy enemy;
	
	/**
	 * EnemyDecorator creates new types of enemies from the Fighter and Force User classes
	 * @param n - this parameter is the name of the enemy and is passed in to super to create the new enemy.
	 * @param l - this parameter is the level of the enemy and is passed in to super to create the new enemy.
	 * @param m - this parameter is the MaxHP of the enemy and is passed in to super to create the new enemy.
	 * @param e - enemy is passed in to read attacks which will be overridden in Fighter and Force User
	 */
	public EnemyDecorator(String n, int l, int m, Enemy e)
	{
		super(n, l, m);
		enemy = e;
	}
	
	/**
	 * attack() is used when the specific enemy needs to attack the hero.
	 * @param e - the hero entity is passed in and takes the enemies hits.
	 */
	public void attack(Entity e)
	{
		enemy.attack(e);
	}
}
