public class Item implements Cloneable
{
	/**
	 * name is set to n in the Items constructor and is used by getNme to return an actual string instead of
	 * an address wherever a name is needed
	 */
    private String name;
    
    /**
     * passes in name of an item to be picked uo by the hero.
     * @param n - name of the item to be picked up.
     */
    public Item(String n)
    {
        name = n; //passes in item n to be used by name
    }
    
    /**
     * gives the name of the item passed into the constructor
     * @return the string name of the item.
     */
    public String getName()
    {
        return name; //returns name of the item
    }
    
    /**
     * clone() is called to make a copy of the item.
     * @return the clone that is created in the method.
     */
    public Item clone()
    {
    	Item clone = null;
    	try
    	{
    		clone = (Item) super.clone();
    	}
    	catch(CloneNotSupportedException c)
    	{
    		System.out.println("Not Cloneable!");
    	}
    	return clone;
    }
}