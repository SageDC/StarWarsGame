import java.util.*;
public class EnemyGenerator
{
	/**
	 * The Enemy creator is used to store a new enemy randomly from the enemy classes
	 */
	private Enemy e;
	/**
	 * instance set to null to verify that a new Enemy Generator has not been created.
	 */
    private static EnemyGenerator instance = null;
    
    /**
     * Constructor is private so that a new EnemyGenerator cannot be created.
     */
    private EnemyGenerator()
    {
    	
    }
    /**
     * randomly generates a random enemy.
     * @param level - passes in the level of the hero to calculate its max health.
     * @return a random enemy from the enemyList.
     */
    public Enemy generateEnemy(int l)
    {
    	Random rand = new Random();
    	int randomEnemy = rand.nextInt(4) + 1;
        if(randomEnemy == 1)
        {
        	e = new Rodian(l);
        }
        else if(randomEnemy == 2)
        {
        	e = new Dathomiri(l);
        }
        else if(randomEnemy == 3)
        {
        	e = new Twilek(l);
        }
        else if(randomEnemy == 4)
        {
        	e = new Geonosian(l);
        }
        
    	int enemyType = rand.nextInt(2) + 1;
        if(enemyType == 1)
        {
        	for(int i = 1; i < e.getLevel(); i++)
        	{
        		e = new Fighter(e);
        		e.increaseMaxHP(1);
        		e.heal(1);
        	}
        }
        else if(enemyType == 2)
        {
        	for(int i = 1; i < e.getLevel(); i++)
        	{
        		e = new ForceUser(e);
        		e.increaseMaxHP(2);
        		e.heal(2);
        	}
        }
    	return e;
    }
    
    /**
     * allows for a new EnemyGenerator to be generated without having to call new in another class.
     * @return - a new instance of EnemyGenerator
     */
    public static EnemyGenerator getInstance()
    {
    	if(instance == null)
    	{
    		instance = new EnemyGenerator();
    	}
    	return instance;
    }
}
