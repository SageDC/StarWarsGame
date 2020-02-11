import java.awt.Point;
import java.util.*;
public class Hero extends Entity implements Force
{
	/**
	 * a variable of type ArrayList is created to store Item types.
	 */
    private ArrayList<Item> items;
    /**
     * map is used to be saved to the m that is passed in with the hero constructor and can then be used by any other methods in this
     * class
     */
    private Map map;
    /**
     * location stores the position of the hero at the beginning of the game.
     */
    private Point location;
    
    /**
     * calls Entity superclass by using keyword super and passing in the correct values.
     * @param n - passed in for constructing a hero with a Name that the user inputs.
     * @param m - used to find the start position of the hero.
     */
    public Hero(String n, Map m) //constructs the hero
    {
        super(n, 1, 1); //creates entity
        items = new ArrayList<Item>(); //allows for use of the items on the map and in the heros inventory
        map = m; //initialized map from main is used
        location = map.findStart(); //finds the start position for the hero
    }
    
    /**
     * overrides Entity attack method, used to determine and type of attack the hero will use.
     * @param e - allows us to use methods made in the Entity class, such as takeDamage().
     */
    @Override
    public void attack(Entity e)
    {
        Random rand = new Random(); 
        int damage = (rand.nextInt(15)+1); //random damage
        e.takeDamage(damage);//enemy takes damage
        System.out.println(getName() + " attacks " + e.getName() + " for " + damage + " damage.");
    }
    
    /**
     * specific type of attack that needs to be used by the hero if btnChoke is clicked
     * @param e - allows us to use methods made in the Entity class, such as takeDamage().
     */
    public void chokeAttack(Entity e)
    {
    	int harm = forceChoke();
    	e.takeDamage(harm);
    }
    
    /**
     * specific type of attack that needs to be used by the hero if btnSlam is clicked
     * @param e - allows us to use methods made in the Entity class, such as takeDamage().
     */
    public void slamAttack(Entity e)
    {
    	int harm = forceSlam();
    	e.takeDamage(harm);
    }
    
    /**
     * specific type of attack that needs to be used by the hero if btnPush is clicked
     * @param e - allows us to use methods made in the Entity class, such as takeDamage().
     */
    public void pushAttack(Entity e)
    {
    	int harm = forcePush();
    	e.takeDamage(harm);
    }
    
    /**
     * displays the hero's entire itemList.
     */
    public String displayItems(int i)
    {
        return items.get(i).getName();
    }
    
    /**
     * used to determine the amount of items in the heros inventory
     * @return an integer which will be the size of the itemList.
     */
    public int getNumItems()
    {
        return items.size(); //returns the size of the hero's inventory
    }
    
    /**
     * checks if the hero has enough space to pick up an item and picks it up if there is enough space.
     * @param i - passes in the item dropped by the hero or found on the ground.
     * @return true if the hero has enough space, otherwise returns false.
     */
    public boolean pickUpItem(Item i)
    {
        if(getNumItems() < 5)
        {
            //picks up item if the hero has enough space
            items.add(i);
            return true;
        }
        else
        {
            //returns false if not enough space
            System.out.println("Your inventory is full.");
            return false;
        }
    }
    
    /**
     * removes an item from the heros inventory and cycles through the list using a for each loop.
     * @param n - passes in the name of the item to remove.
     * removes items by String name.
     */
    public void removeItem(String n)
    {
        //loops through heros items to look for a specific item to remove
        for(Item i : items)
        {
            if(i.getName().equals(n))
            {
                items.remove(i);
                break;
            }
        }
    }
    
    /**
     * removes an item from the heros inventory.
     * @param index - passes in the position of the item to remove.
     * removes items by index position.
     */
    public void removeItem(int index)
    {
        //removes specific item index from inventory
        items.remove(index-1);
    }
    
    /**
     * removes the first armor piece when the hero takes any sort of damage. Cycles through the heros inventory using a for each loop.
     */
    public String removeFirstArmorItem()
    {
        //loops through hero's inventory to remove the first piece of armor it finds if this method is called
        String armor = "";
        for(Item i : items)
        {
            if(i.getName().equals("Chestplate"))
            {
                armor = "chestplate";
                items.remove(i);
                break;
            }
            else if(i.getName().equals("Helmet"))
            {
                armor = "helmet";
                items.remove(i);
                break;
            }
            else if(i.getName().equals("Shield"))
            {
                armor = "Shield";
                items.remove(i);
                break;
            }
        }
        return armor + " has been removed from inventory.";
    }
    
    /**
     * checks if the hero has a Med Kit in the inventory, cycles through itemList using a for each loop
     * @return true if the item is in the inventory, otherwise, it returns false.
     */
    public boolean hasMedKit()
    {
        //loops through hero's inventory to check if a Med Kit is present
        for(Item i : items)
        {
            if(i.getName().equals("Med Kit"))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks if the hero has a Holocron in the inventory, cycles through itemList using a for each loop
     * @return true if the item is in the inventory, otherwise, it returns false.
     */
    public boolean hasHolocron()
    {
        //loops through hero's inventory to check if a Holocron is present
        for(Item i : items)
        {
            if(i.getName().equals("Holocron"))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks if the hero has a Key in the inventory, cycles through itemList using a for each loop
     * @return true if the item is in the inventory, otherwise, it returns false.
     */
    public boolean hasKey()
    {
        //loops through hero's inventory to check if a Key is present
        for(Item i : items)
        {
            if(i.getName().equals("Key"))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks if the hero has Armor in the inventory, cycles through itemList using a for each loop
     * @return true if the item is in the inventory, otherwise, it returns false.
     */
    public boolean hasArmor()
    {
        //loops through hero's inventory to check if any armor is present
        for(Item i : items)
        {
            if(i.getName().equals("Helmet") || i.getName().equals("Shield") || i.getName().equals("Chestplate"))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * finds the location of the hero.
     * @return Point; location of the hero.
     */
    public Point getLocation()
    {
        return location; //returns the current location of the hero
    }
    
    /**
     * allows the hero to move north if the user chooses that direction, and reveals the new position.
     * @return the character of the new position that the hero is in.
     */
    public char goNorth()
    {
        //updates map with hero now in north position
        int x = (int) location.getX();
        int y = (int) location.getY();
        Point position = new Point(x-1, y);
        location = position;
        map.reveal(location);
        return map.getCharAtLoc(location); //gets char in new hero location
    }
    
    /**
     * allows the hero to move south if the user chooses that direction, and reveals the new position.
     * @return the character of the new position that the hero is in.
     */
    public char goSouth()
    {
        //updates map with hero now in south position
        int x = (int) location.getX();
        int y = (int) location.getY();
        Point position = new Point(x+1, y);
        location = position;
        map.reveal(location);
        return map.getCharAtLoc(location);
    }
    
    /**
     * allows the hero to move east if the user chooses that direction, and reveals the new position.
     * @return the character of the new position that the hero is in.
     */
    public char goEast()
    {
        //updates map with hero now in east position
        int x = (int) location.getX();
        int y = (int) location.getY();
        Point position = new Point(x, y+1);
        location = position;
        map.reveal(location);
        return map.getCharAtLoc(location);
    }
    
    /**
     * allows the hero to move west if the user chooses that direction, and reveals the new position.
     * @return the character of the new position that the hero is in.
     */
    public char goWest()
    {
        //updates map with hero now in west position
        int x = (int) location.getX();
        int y = (int) location.getY();
        Point position = new Point(x, y-1);
        location = position;
        map.reveal(location);
        return map.getCharAtLoc(location);
    }
    
    /**
     * if the hero has a holocron this method is called and deals a random amount of damage to the enemy.
     * @return int, that being amount of damage to be taken.
     */
    
    //hero allowed to used the following three methods if the Holocron is present and the hero chooses to attack by force.
    public int forcePush()
    {
        Random rand = new Random();
        int damage = rand.nextInt(getHP()) + 1;
        return damage;
    }
    
    /**
     * if the hero has a holocron this method is called and deals a random amount of damage to the enemy.
     * @return int, that being amount of damage to be taken.
     */
    public int forceChoke()
    {
        Random rand = new Random();
        int damage = rand.nextInt(getHP()) + 1;
        return damage;
    }
    
    /**
     * if the hero has a holocron this method is called and deals a random amount of damage to the enemy.
     * @return int, that being amount of damage to be taken.
     */
    public int forceSlam()
    {
        Random rand = new Random();
        int damage = rand.nextInt(getHP()) + 1;
        return damage;
    }
}
