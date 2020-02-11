public abstract class Entity
{
    private String name;
    private int level;
    private int maxHp;
    private int hp;
    
    /**
     * Counstructs an Entity to be used by both the Hero and Enemy class.
     * @param n - name of the entity.
     * @param l - level of the entity.
     * @param m - max health of the Entity.
     */
    public Entity(String n, int l, int m) //constructs new entity
    {
        name = n; //saves name to be used 
        level = l; //saves level to be used 
        maxHp = m; //saves health to be used 
        hp = maxHp; //initiates health to max health
    }
    
    /**
     * attack method is not used because it is abstract, the attack classes in Hero and Enemy override this method.
     * @param e - either hero or enemy.
     */
    public abstract void attack(Entity e); //uses attack methods from forceEnemy and hero class
    
    /**
     * Gives the name of the entity that calls this method. Can be item, hero, or enemy.
     * @return the name of the entity.
     */
    public String getName()
    {
        return name; //returns the name of the entity that calls it
    }
    
    /**
     * Gives the level of the entity that is used to call this method, can be hero or enemy.
     * @return the level of the entity.
     */
    public int getLevel()
    {
        return level; //returns the level of the entity that calls it 
    }
    
    /**
     * Gives the current health of the entity that is used to call this method, can be a hero or an enemy.
     * @return the current health of the entity.
     */
    public int getHP()
    {
        return hp; // returns the health of the entity that calls it
    }
    
    /**
     * Gives the max health of the entity that is used to call this method, can be a hero or an enemy.
     * @return the max health of the entity.
     */
    public int getMaxHP()
    {
        return maxHp; //returns the max health of the user 
    }
    
    /**
     * used to increase the level of either the enemy or the hero.
     */
    public void increaseLevel()
    {
        level++; //increases the level of the entity that calls this method
    }
    
    /**
     * used to add health to the current health of the hero.
     * @param h  - is the integer parameter which will be the amount we increase the current health by.
     */
    public void heal(int h)
    {
        if(hp + h > maxHp) //if the health the user will heal pass the max health, inititiates the health to MaxHP
        {
            hp = maxHp;
        }
        else //else, heals the amount given
        {
            hp = hp + h;
        }
    }
    
    /**
     * used after calling the attack method, allows the hero to take the damage calculated by the attack method
     * @param h - damage passed in that either the hero or enemy will take.
     */
    public void takeDamage(int h)
    {
        if(hp - h < 0) //takes damage but sets health to 0 if the damage makes the health negative
        {
            hp = 0;
        }
        else //else, takes full amount of damage
        {
            hp = hp - h;
        }
    }
    
    /**
     * increases the total health that a enemy or hero can have.
     * @param h - will be the amount passed in that will be used to increase the max health.
     */
    public void increaseMaxHP(int h)
    {
        maxHp = maxHp + h; //increases max health of the hero when he goes to the next level
    }
    
    /**
     * decreases the total health that a enemy or hero can have.
     * @param h - will be the amount passed in that will be used to decrease the max health.
     */
    public void decreaseMaxHP(int h)
    {
        maxHp = maxHp - h; //decreases the max healht of the entity used to call it
    }
}
