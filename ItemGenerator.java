import java.util.*;
import java.util.Scanner;
import java.io.*;
public class ItemGenerator
{
    private ArrayList<Item> itemList;
    private static ItemGenerator instance = null;
    
    /**
     * Reads in the items from the ItemList.txt file, these items can then be generated to be picked up
     * or found.
     * 
     */
    private ItemGenerator()
    {
        itemList = new ArrayList<Item>();
        //reads in items from the ItemList.txt file, line by line
        try
        {
            Scanner read = new Scanner(new File("ItemList.txt"));
            do
            {
                String line = read.nextLine();
                Item item = new Item(line);
                itemList.add(item);
            }
            while(read.hasNextLine());
            read.close();
        }
        catch(FileNotFoundException fnf) //throws exception if the file is not found
        {
            System.out.println("File not found.");
        }
    }

    /**
     * randomly generates a random item
     * @return a random item from the itemList.
     */
    public Item generateItem()
    {
        //generates random item
        Random spawn = new Random();
        int item = spawn.nextInt(itemList.size());
        return itemList.get(item).clone();
    }
    
    /**
     * allows for a new ItemGenerator to be generated without having to call new in another class.
     * @return - a new instance of ItemGenerator
     */
    public static ItemGenerator getInstance()
    {
    	if(instance == null)
    	{
    		instance = new ItemGenerator();
    	}
    	return instance;
    }
}
