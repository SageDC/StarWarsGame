import java.awt.Point;
import java.io.*;
import java.util.*;
public class Map
{
    private char[][] map;
    private boolean [][] revealed;
    private static Map instance = null;
    
    /**
     * Creates the map from a read in file, and creates another map above to hide the characters in a room.
     */
    private Map()
    {
        map = new char[5][5]; //creates new 5x5 array
        revealed = new boolean[5][5]; //creates new 5x5 array
    }
    
    /**
     * Actually reads in the characters that go on the map from a file, stores in the correct position.
     * 
     * @param mapNum - used to determine which of the three maps to display to the user.
     */
    public void loadMap(int mapNum) //loads map based on hero's level
    {
        try
        {
            Scanner read = new Scanner(new File("Map" + mapNum + ".txt"));
            while(read.hasNext()) //reads next char from a file
            {
                for(int i = 0; i < 5; i++)
                {
                    for(int j = 0; j < 5; j++)
                    {
                        String line = read.next();
                        map[i][j] =line.charAt(0); //two dimensional loop to store in 2D array
                    }
                }
            }
            read.close(); //closes file
        }
        catch(FileNotFoundException fnf) //throws an exception, checks if file exists
        {
            System.out.println("File was not found.");
        }
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                revealed[i][j] = false; //initializes the revealed array to false so the user does not know what is on the map
            }
        }
    }
    
    /**
     * returns the character of the position that the hero is in, used to check for enemies, items, and the exit door.
     * 
     * @param p - passes in the location of the hero.
     * @return character at hero location.
     */
    public char getCharAtLoc(Point p) //gets char from the map(floor hero is on).
    {
        try
        {
            return map[(int)p.getX()][(int)p.getY()]; //returns char
        }
        catch(ArrayIndexOutOfBoundsException bound) //throws an exception, checks if hero is in a valid array index
        {
            System.out.println("Invalid Index");
            return '0'; //places char 0 if area not valid
        }
    }
    
    /**
     * Used to display the map and the revealed contents of the game.
     * @param p - passes in the location of the hero.
     */
    public void displayMap(Point p) //displays map to the user
    {
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                if(i == (int) p.getX() && j == (int) p.getY())
                {
                    //prints location of hero
                    System.out.print("* ");
                }
                else if(revealed[i][j] == true)
                {
                    //prints map array's char if revealed is true
                    System.out.print(map[i][j] + " ");
                }
                else if(revealed[i][j] == false)
                {
                    //prints "x" if location is not yet revealed
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * used to receive the character if it is revealed and prints into onto the map on the panel.
     * @param x - this is the heros x-location, needed to check the character in the heros position
     * @param y - this is the heros y-location, needed to check the character in the heros position
     * @return a character where the hero is located.
     */
    public String getCharacter(int x, int y)
    {
    	if(revealed[x][y] == false)
    	{
    		return "";
    	}
    	return "" + map[x][y];
    }
    
    /**
     * Used to find the start position of the initial map.
     * @return a Point to the position of the character 's'.
     */
    public Point findStart() //finds starting position for the hero
    {
        Point startPosition = new Point();
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                if(map[i][j] == 's')
                {
                    startPosition.setLocation(i, j); //sets position of the hero to the startPosition
                }
            }
        }
        return startPosition;
    }
    
    /**
     * reveals the location where the hero is, called when an enemy or item is found.
     * 
     * @param p - passes in the location of the hero.
     */
    public void reveal(Point p) //reveals map if the hero is on specific floor
    {
        try
        {
            int row = (int)p.getX();
            int col = (int)p.getY();
            revealed[row][col] = true;
        }
        catch(ArrayIndexOutOfBoundsException o) //throws exception if hero is out of bounds
        {
            System.out.println("Out of Bounds");
        }
    }
    
    /**
     * removes the character found at the hero's position and replaces it with the character 'n'
     * to denote that nothing is there.
     * @param p - passes in the location of the hero.
     */
    public void removeCharAtLoc(Point p) //replaces character where hero is with 'n' if successfully called
    {
        int row = (int)p.getX();
        int col = (int)p.getY();
        map[row][col] = 'n';
    }
    
    /**
     * allows for a new map to be generated without having to call new in another class.
     * @return - a new instance of map
     */
    public static Map getInstance()
    {
    	if(instance == null)
    	{
    		instance = new Map();
    	}
    	return instance;
    }
}
