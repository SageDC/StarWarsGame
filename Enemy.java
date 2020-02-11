public abstract class Enemy extends Entity
{
	/**
	 * item is created to store a random item which will be generated by ItemGenerator
	 */
    private Item item;
    /**
     * A new instance of ItemGenerator is created and stored in ig in order to generate new unique random
     * items.
     */
    private ItemGenerator ig;
    
    /**
     * This is the constructor of the enemy. Super s called because Entity is being extended. In addition, a 
     * new instance of ItemGenerated is created in order to generate a random item for the enemy to hold.
     * @param n - this is the name created in the classes that extend Enemy
     * @param l - this is the level created in the classes that extend Enemy
     * @param m - this is the MaxHP created in the classes that extend Enemy
     */
    public Enemy(String n, int l, int m)
    {
        super(n, l, m);
        ig = ItemGenerator.getInstance();
        item = ig.generateItem();
    }
    
    /**
     * getItem is needed to receive the unique item generated for the enemy.
     * @return - the item that the enemy holds when it is at 0 hp.
     */
    public Item getItem()
    {
        return item;
    }
}
