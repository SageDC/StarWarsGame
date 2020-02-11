import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener, MouseListener, ActionListener, Runnable
{
	/**
	 * The JLabel lblinv is used to create the "Inventory" label which is located directly
	 * above the item boxes.
	 */
	private JLabel lblInv;
	/**
	 * The JLabel lblChInfo is used to create the "Character Information" label which is located
	 * at the top of the right most rectangle.
	 */
	private JLabel lblChInfo;
	/**
	 * The JLabel lblNamePlate is used to create the "Name:" label which is located directly
	 * below the "Character Information" label.
	 */
	private JLabel lblNamePlate;
	/**
	 * The JLabel lblLevelPlate is used to create the "Level:" label which is located directly
	 * below the "Name:" label.
	 */
	private JLabel lblLevelPlate;
	/**
	 * The JLabel lblHpPlate is used to create the "HP:" label which is located directly
	 * below the "Level:" label.
	 */
	private JLabel lblHpPlate;
	/**
	 * Map map is used to initialize the map that will be read in from the file, the characters are
	 * then set in the proper position according to the 5 x 5 grid created.
	 */
	private Map map;
	/**
	 * ItemGenerator ig is used to generate new and random items from the itemList.txt file.
	 */
	private ItemGenerator ig;
	/**
	 * EnemyGenerator eg is used to randomly generate an enemy. Them being Dathomiri, Rodian, 
	 * Twi'lek, Geonosian, and after they are level 2, they can randomly be either a Fighter
	 * or a Force User.
	 */
	private EnemyGenerator eg;
	/**
	 * Hero hero is used to generate a Hero with a given name and uses the map generated to find the
	 * hero's position.
	 */
	private Hero hero;
	/**
	 * The EnemyGenerator created above, generates a random enemy and stores it into the Enemy enemy variable
	 * created below.
	 */
	private Enemy enemy;
	/**
	 * The ItemGenerator created above, generates a random item and stores it into the Item item variable
	 * created below.
	 */
	private Item item;
	/**
	 * Random is used to generate a random number between 0 and the number given inside the parenthesis.
	 */
	private Random rand;
	/**
	 * Percent is used to store a random number that decides whether or not the exit door will open with a 
	 * Holocron.
	 */
	private int percent;
	/**
	 * Clicking the JButton Fight will either attack or add to more buttons. This depends on if
	 * a Holocron is in your inventory.
	 */
	private JButton btnFight;
	/**
	 * Clicking Run will move your character to a random position that is adjacent to the room you were previously
	 * in.
	 */
	private JButton btnRun;
	/**
	 * If a Med-Kit is in your inventory, this JButton will present itself in order to allow the hero to heal up
	 * by +25.
	 */
	private JButton btnMed;
	/**
	 * If 5 items are already in your inventory, then the following 5 ItemSlot buttons will make themselves visible which
	 * will allow you to remove the specific item clicked.
	 */
	private JButton btnItemSlot1;
	private JButton btnItemSlot2;
	private JButton btnItemSlot3;
	private JButton btnItemSlot4;
	private JButton btnItemSlot5;
	/**
	 * If a Holocron is present in your inventory, the Blaster button will present itself which will simply attack the user.
	 */
	private JButton btnBlaster;
	/**
	 * If a Holocron is present in your inventory, the Blaster button will present itself which will add 3 more buttons to
	 * choose from.
	 */
	private JButton btnForce;
	/**
	 * The following three buttons will allow the user to choose from three force type attacks
	 */
	private JButton btnChoke;
	private JButton btnSlam;
	private JButton btnPush;
	/**
	 * This boolean is used to check if the 5 itemSlot buttons are being shown.
	 */
	private boolean isInstantiated;
	private char charCheck;
	
	public Panel()
	{
		setLayout(null); //The layout of the panel is set to null to allow for the bounds to be set for each type of J-Object
		rand = new Random(); //Initializes the Random variable to accept random number generation
		map = Map.getInstance(); //Generates new instance of Map
		ig = ItemGenerator.getInstance(); //Generates new instance of ItemGenerator
		eg = EnemyGenerator.getInstance(); //Generates new instance of EnemyGenerator
		map.loadMap(1); //Loads first map from files.
		
		String name = JOptionPane.showInputDialog(this, "Enter player name: "); //Prompts the user to enter a player name
		hero = new Hero(name, map); //Generates a new Hero with the name and map created above
		hero.pickUpItem(new Item("Key"));
		hero.pickUpItem(new Item("Key"));
		hero.pickUpItem(new Item("Key"));
		hero.pickUpItem(new Item("Key"));
		hero.pickUpItem(new Item("Key"));
		map.reveal(hero.getLocation()); //Reveals the character of the location of the hero. 
		enemy = eg.generateEnemy(hero.getLevel()); //new enemy is generated.
		item = ig.generateItem();
		charCheck = '0';
		
		lblInv = new JLabel("Inventory:"); //lblInv is set to Inventory
		lblChInfo = new JLabel("Character Information"); //lblChInfo is set to Character Information
		lblNamePlate = new JLabel("Name: " + name); // lblNamePlate is set to print Name: and the player name
		lblLevelPlate = new JLabel("Level: "); //lblLevelPlate is set to Level:
		lblHpPlate = new JLabel("HP: "); //lblHpPlate is set to HP:
		btnFight = new JButton("Fight"); //btnFight is set to show Fight
		btnRun = new JButton("Run Away"); //btnRun is set to show Run Away
		btnMed = new JButton("Use Med Kit"); //btnMed is set to show Use Med Kit
		btnBlaster = new JButton("Blaster"); //btnBlaster is set to show Blaster
		btnForce = new JButton("Force"); //btnForce is set to show Force
		btnChoke = new JButton("Force Choke"); //btnChoke is set to show Force Choke
		btnSlam = new JButton("Force Slam"); //btnSlam is set to show Force Slam
		btnPush = new JButton("Force Push"); //btnPush is set to show Force Push
		
		//The bound for each JLabel and JButton are set below
		lblInv.setBounds(530, 175, 100, 15);
		lblChInfo.setBounds(545, 22, 200, 10);
		lblNamePlate.setBounds(530, 55, 100, 10);
		lblLevelPlate.setBounds(530, 75, 100, 10);
		lblHpPlate.setBounds(530, 95, 100, 10);
		btnFight.setBounds(540, 435, 125, 20);
		btnRun.setBounds(540, 460, 125, 20);
		btnMed.setBounds(540, 485, 125, 20);
		btnBlaster.setBounds(540, 435, 125, 20);
		btnForce.setBounds(540, 460, 125, 20);
		btnChoke.setBounds(540, 435, 125, 20);
		btnSlam.setBounds(540, 460, 125, 20);
		btnPush.setBounds(540, 485, 125, 20);
		
		//Every JLabel is added to the Panel for the user to view
		add(lblInv);
		add(lblChInfo);
		add(lblNamePlate);
		add(lblLevelPlate);
		add(lblHpPlate);
		
		//By adding .addActionListener to each button, the JButtons are now clickable
		btnFight.addActionListener(this);
		btnRun.addActionListener(this);
		btnMed.addActionListener(this);
		btnBlaster.addActionListener(this);
		btnForce.addActionListener(this);
		btnChoke.addActionListener(this);
		btnSlam.addActionListener(this);
		btnPush.addActionListener(this);
		
		addKeyListener(this); //Allows for WASD and the arrow keys to be clicked for movement
		setFocusable(true); //Gives focus to the panel, otherwise, keys will not work
	}
	
	/**
	 * paintComponent draws all of the necessary information to the screen. This includes the rectangle
	 * fields where map and character information will be placed, as information becomes available.
	 * @param g - The Graphics interface is needed to be used by super. 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); //super must be called when using paintComponent
		//The following for loop and nested for loop are used to create a 5 x 5 map in the panel
		//The revealed characters are also shown this way.
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				g.drawRect(10 + 100 * i, 10 + 100 * j, 100, 100); //Draws rectangles
				g.drawString(map.getCharacter(i, j), 55 + 100 * j, 65 + 100 * i); //writes characters to the screen if they're revealed
			}
		}
		g.drawString("Hero", 45 + (100 * (int) hero.getLocation().getY()), 50 + (100 * (int) hero.getLocation().getX())); // "Hero" is drawn to the screen
		
		//The following drawRects draw the GUI on the right side of the screen where all of the player and map information will be shown
		g.drawRect(520, 10, 170, 35);
		g.drawRect(520, 45, 170, 260);
		g.drawRect(520, 315, 170, 35);
		g.drawRect(520, 350, 170, 160);
		g.drawRect(530, 195, 50, 50);
		g.drawRect(580, 195, 50, 50);
		g.drawRect(630, 195, 50, 50);
		g.drawRect(530, 245, 50, 50);
		g.drawRect(580, 245, 50, 50);
		
		//Displays items as they are received and are updated as they are removed.
		for(int i = 0; i < hero.getNumItems(); i++)
		{
			if(i == 0)
			{
				//The first character of each item is shown to fit on the boxes, same for i = 1 through 5
				g.drawString(hero.displayItems(i).charAt(0) + "", (i > 2) ? 550 + 50 * (i-3):550 + 50*i, (i > 2) ? 275 : 225);
			}
			else if(i == 1)
			{
				g.drawString(hero.displayItems(i).charAt(0) + "", (i > 2) ? 550 + 50 * (i-3):550 + 50*i, (i > 2) ? 275 : 225);
			}
			else if(i == 2)
			{
				g.drawString(hero.displayItems(i).charAt(0) + "", (i > 2) ? 550 + 50 * (i-3):550 + 50*i, (i > 2) ? 275 : 225);
			}
			else if(i == 3)
			{
				g.drawString(hero.displayItems(i).charAt(0) + "", (i > 2) ? 550 + 50 * (i-3):550 + 50*i, (i > 2) ? 275 : 225);
			}
			else if(i == 4)
			{
				g.drawString(hero.displayItems(i).charAt(0) + "", (i > 2) ? 550 + 50 * (i-3):550 + 50*i, (i > 2) ? 275 : 225);
			}

		}
		
		//Draws updated level to the screen, next to the "Level:" label
		g.drawString(hero.getLevel() + "", 570, 85);
		//Draws the updated hero HP to the screen next to the "HP:" label
		g.drawString(hero.getHP() + "/" + hero.getMaxHP(), 560, 105);
		
		if(map.getCharAtLoc(hero.getLocation()) == 's')
		{ 
			//If hero is on 's' then the following strings are drawn to the screen
			g.drawString("Entry", 585, 335);
			g.drawString("You enter the area.", 545, 375);
		}
		else if(map.getCharAtLoc(hero.getLocation()) == 'e')
		{
			//If hero is on 'e' then the following strings are drawn to the screen
			g.drawString("An enemy!", 575, 335);
			g.drawString("You've encountered a", 540, 375);
			g.drawString(enemy.getName(), 540, 390);
			g.drawString("Level " + enemy.getLevel(), 540, 405);
			g.drawString("HP: " + enemy.getHP() + " / " + enemy.getMaxHP(), 540, 420);
		}
		else if(map.getCharAtLoc(hero.getLocation()) == 'n')
		{
			//If hero is on 'n' then the following strings are drawn to the screen
			g.drawString("Empty Room", 570, 335);
			g.drawString("There is nothing here...", 535, 375);
			//The following checks if the enemy HP is 0 and if it is, the following is draw to the screen
			if(charCheck == 'e')
			{
				g.drawString("You received a", 535, 390);
				g.drawString(enemy.getItem().getName(), 535, 405);
			}
			else if(charCheck == 'i')
			{
				g.drawString("You picked up a", 535, 390);
				g.drawString(item.getName() + "", 535, 405);
			}
		}
		else if(map.getCharAtLoc(hero.getLocation()) == 'i')
		{
			//If hero is on 'i' then the following strings are drawn to the screen
			g.drawString("An item!", 580, 335);
			g.drawString("You found a", 535, 375);
			g.drawString(item.getName(), 535, 390);
		}
		else if(map.getCharAtLoc(hero.getLocation()) == 'f')
		{
			//If hero is on 'f' then the following strings are drawn to the screen
			g.drawString("Exit Door!", 575, 335);
			g.drawString("You found the exit door!", 530, 375);
			//If a key is in the inventory, the following is drawn to the screen
			if(hero.hasKey() == true)
			{
				g.drawString("Key used on door", 530, 390);
				g.drawString("You passed the level!", 530, 405);
				g.drawString("Next level revealed", 530, 420);
			}
			else if(hero.hasKey() == false && hero.hasHolocron() == true) // if there is no key but there is a Holocron, the following is written to the screen.
			{
				if(percent > 50)
				{
					g.drawString("Key used force on door", 530, 390);
					g.drawString("You passed the level!", 530, 405);
					g.drawString("Next level revealed", 530, 420);
				}
			}
			else
			{
				//Otherwise, the following is drawn to the screen
				g.drawString("Come back with a key", 530, 390);
				g.drawString("or a Holocron", 530, 405);
			}
		}
	}
	
	/**
	 * run() constantly repaints to the screen using threads with updated information.
	 * It is called from main and called in window using start().
	 */
	public void run()
	{
		while(true)
		{
			repaint(); //Repaint is repeatedly called which is constantly changing the GUI with the updated position and text
			try
			{
				Thread.sleep(16); //Time between frames
			}
			catch(InterruptedException e) //Catches interruption
			{
				System.out.println("Interruption Caught");
			}
		}
	}

	@Override
	/**
	 * actionPerformed allows for defining information and logic behind a click-able button. In
	 * this method several buttons are defined including attack buttons and item buttons.
	 */
	public void actionPerformed(ActionEvent a) 
	{
		if(a.getSource() == btnFight) //Program checks to see if "Fight" is clicked and runs through the information inside if it is.
		{
			if(hero.hasHolocron() == true) //If hasHolocron is true, the following buttons are added
			{
				remove(btnFight); //method to remove the "Fight" button
				remove(btnRun); //method to remove the "Run" button
				remove(btnMed); //method to remove the "Use Med Kit" button
				add(btnBlaster); //method to add a "Blaster" button
				add(btnForce); //method to add a "Force" button
				btnBlaster.setFocusable(true); //focus is given to the blaster and force buttons
				btnForce.setFocusable(true);
			}
			else
			{
				hero.attack(enemy); //hero attacks
	            if(enemy.getHP() == 0)
	            {
	                map.removeCharAtLoc(hero.getLocation()); //places n where e was if enemy is defeated
	                item = enemy.getItem(); //gets the item that the enemy had to be picked up by hero
	                if(hero.getNumItems() < 5)//checks item list size
	                {
	                    hero.pickUpItem(item); //if enough space, pick up item
	                    charCheck = 'e';
	                }
	                else
	                {
	                	int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
		                
		                if(reply == JOptionPane.YES_OPTION)
		                {
		                	setFocusable(true);
		                	for(int i = 0; i < hero.getNumItems(); i++)
		                	{
		                		if(i == 0)
		            			{
		                			//First item in ArrayList information
		            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
		            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
		            				add(btnItemSlot1); //first button item added
		            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
		            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
		            			}
		            			else if(i == 1)
		            			{
		            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
		            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
		            				add(btnItemSlot2);
		            				btnItemSlot2.addActionListener(this);
		            				btnItemSlot2.setFocusable(true);
		            			}
		            			else if(i == 2)
		            			{
		            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
		            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
		            				add(btnItemSlot3);
		            				btnItemSlot3.addActionListener(this);
		            				btnItemSlot3.setFocusable(true);
		            			}
		            			else if(i == 3)
		            			{
		            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
		            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
		            				add(btnItemSlot4);
		            				btnItemSlot4.addActionListener(this);
		            				btnItemSlot4.setFocusable(true);
		            			}
		            			else if(i == 4)
		            			{
		            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
		            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
		            				add(btnItemSlot5);
		            				btnItemSlot5.addActionListener(this);
		            				btnItemSlot5.setFocusable(true);
		            				isInstantiated = true;
		            				charCheck = 'e';
		            			}
		                	}
		                }
		                else
		                {
		                	JOptionPane.showMessageDialog(this, "Item has been lost");
		                }
	                }
	                remove(btnFight);
	                remove(btnRun);
	                remove(btnMed);
	                if(isInstantiated != true) //checks if all 5 buttons are on the screen
		            {
		            	setFocusable(true); //gives panel focus if all buttons are not on the screen
		                requestFocusInWindow(); //method requests focus for the panel
		            }
	            }
	            else
	            {
	            	fight(); //function for enemy to attack hero
	            }
			}
            
            if(hero.getHP() == 0)
            {
            	JOptionPane.showMessageDialog(this, "You have died!"); //If hero is out of health, a message is show to the screen and the games exits.
            	System.exit(0); //quits the window.
            }
            btnFight.setFocusable(false); //takes focus away from the "Fight" button
		}
		else if(a.getSource() == btnChoke) ////Program checks to see if "Force Choke" is clicked and runs through the information inside if it is.
		{ //Does not need to check if a Holocron is present because this button is only available through the program first checking if there is one present.
			hero.chokeAttack(enemy); //It otherwise has the same code as the "Fight" button with the same functions.
			if(enemy.getHP() == 0)
            {
                map.removeCharAtLoc(hero.getLocation()); //places n where e was if enemy is defeated
                item = enemy.getItem(); //gets the item that the enemy had to be picked up by hero
                if(hero.getNumItems() < 5)//checks item list size
                {
                    hero.pickUpItem(item); //if enough space, pick up item
                    charCheck = 'e';
                }
                else
                {
                	int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
	                
	                if(reply == JOptionPane.YES_OPTION)
	                {
	                	setFocusable(true);
	                	for(int i = 0; i < hero.getNumItems(); i++)
	                	{
	                		if(i == 0)
	            			{
	                			//First item in ArrayList information
	            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
	            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
	            				add(btnItemSlot1); //first button item added
	            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
	            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
	            			}
	            			else if(i == 1)
	            			{
	            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot2);
	            				btnItemSlot2.addActionListener(this);
	            				btnItemSlot2.setFocusable(true);
	            			}
	            			else if(i == 2)
	            			{
	            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot3);
	            				btnItemSlot3.addActionListener(this);
	            				btnItemSlot3.setFocusable(true);
	            			}
	            			else if(i == 3)
	            			{
	            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot4);
	            				btnItemSlot4.addActionListener(this);
	            				btnItemSlot4.setFocusable(true);
	            			}
	            			else if(i == 4)
	            			{
	            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot5);
	            				btnItemSlot5.addActionListener(this);
	            				btnItemSlot5.setFocusable(true);
	            				isInstantiated = true;
	            				charCheck = 'e';
	            			}
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(this, "Item has been lost");
	                }
                }
                remove(btnFight);
                remove(btnRun);
                remove(btnMed);
                if(isInstantiated != true)
	            {
	            	setFocusable(true);
	                requestFocusInWindow();
	            }
            }
            else
            {
            	fight();
            	add(btnFight); //Fight button is added if the enemy is not at 0 health.
    			add(btnRun); //Run button is added if the enemy is not at 0  health.
    			if(hero.hasMedKit() == true) 
    			{
    				add(btnMed); //Use med Kit button is added if both enemy health is not 0 and a Med Kit is present in inventory.
    				btnMed.setFocusable(true); //gives focus to "Use Med kIT" JButton
    			}
    			btnFight.setFocusable(true); //gives focus to "Fight" button
    			btnRun.setFocusable(true); //gives focus to "Run" button
            }
			remove(btnChoke); // method to remove "Force Choke" button
			remove(btnSlam); //method to remove "Force Slam" button
			remove(btnPush); //method to remove "Force Push"
			hero.removeItem("Holocron"); //"Holocron is searched for and removed from the ArrayList
			btnChoke.setFocusable(false); //focus is removed from the button
		}
		else if(a.getSource() == btnSlam) //btnSlam works the exact same way as Choke but is just a different type of attack
		{
			hero.slamAttack(enemy);
			if(enemy.getHP() == 0)
            {
                map.removeCharAtLoc(hero.getLocation()); //places n where e was if enemy is defeated
                item = enemy.getItem(); //gets the item that the enemy had to be picked up by hero
                if(hero.getNumItems() < 5)//checks item list size
                {
                    hero.pickUpItem(item); //if enough space, pick up item
                    charCheck = 'e';
                }
                else
                {
                	int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
	                
	                if(reply == JOptionPane.YES_OPTION)
	                {
	                	setFocusable(true);
	                	for(int i = 0; i < hero.getNumItems(); i++)
	                	{
	                		if(i == 0)
	            			{
	                			//First item in ArrayList information
	            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
	            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
	            				add(btnItemSlot1); //first button item added
	            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
	            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
	            			}
	            			else if(i == 1)
	            			{
	            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot2);
	            				btnItemSlot2.addActionListener(this);
	            				btnItemSlot2.setFocusable(true);
	            			}
	            			else if(i == 2)
	            			{
	            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot3);
	            				btnItemSlot3.addActionListener(this);
	            				btnItemSlot3.setFocusable(true);
	            			}
	            			else if(i == 3)
	            			{
	            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot4);
	            				btnItemSlot4.addActionListener(this);
	            				btnItemSlot4.setFocusable(true);
	            			}
	            			else if(i == 4)
	            			{
	            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot5);
	            				btnItemSlot5.addActionListener(this);
	            				btnItemSlot5.setFocusable(true);
	            				isInstantiated = true;
	            				charCheck = 'e';
	            			}
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(this, "Item has been lost");
	                }
                }
                remove(btnFight);
                remove(btnRun);
                remove(btnMed);
                if(isInstantiated != true)
	            {
	            	setFocusable(true);
	                requestFocusInWindow();
	            }
            }
            else
            {
            	fight();
            	add(btnFight);
    			add(btnRun);
    			if(hero.hasMedKit() == true)
    			{
    				add(btnMed);
    				btnMed.setFocusable(true);
    			}
    			btnFight.setFocusable(true);
    			btnRun.setFocusable(true);
            }
			remove(btnChoke);
			remove(btnSlam);
			remove(btnPush);
			hero.removeItem("Holocron");
			btnSlam.setFocusable(false);
		}
		else if(a.getSource() == btnPush) //btnSlam works the exact same way as Choke but is just a different type of attack
		{
			hero.pushAttack(enemy);
			if(enemy.getHP() == 0)
            {
                map.removeCharAtLoc(hero.getLocation()); //places n where e was if enemy is defeated
                item = enemy.getItem(); //gets the item that the enemy had to be picked up by hero
                if(hero.getNumItems() < 5)//checks item list size
                {
                    hero.pickUpItem(item); //if enough space, pick up item
                    charCheck = 'e';
                }
                else
                {
                	int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
	                
	                if(reply == JOptionPane.YES_OPTION)
	                {
	                	setFocusable(true);
	                	for(int i = 0; i < hero.getNumItems(); i++)
	                	{
	                		if(i == 0)
	            			{
	                			//First item in ArrayList information
	            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
	            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
	            				add(btnItemSlot1); //first button item added
	            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
	            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
	            			}
	            			else if(i == 1)
	            			{
	            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot2);
	            				btnItemSlot2.addActionListener(this);
	            				btnItemSlot2.setFocusable(true);
	            			}
	            			else if(i == 2)
	            			{
	            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot3);
	            				btnItemSlot3.addActionListener(this);
	            				btnItemSlot3.setFocusable(true);
	            			}
	            			else if(i == 3)
	            			{
	            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot4);
	            				btnItemSlot4.addActionListener(this);
	            				btnItemSlot4.setFocusable(true);
	            			}
	            			else if(i == 4)
	            			{
	            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot5);
	            				btnItemSlot5.addActionListener(this);
	            				btnItemSlot5.setFocusable(true);
	            				isInstantiated = true;
	            				charCheck = 'e';
	            			}
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(this, "Item has been lost");
	                }
                }
                remove(btnFight);
                remove(btnRun);
                remove(btnMed);
                if(isInstantiated != true)
	            {
	            	setFocusable(true);
	                requestFocusInWindow();
	            }
            }
            else
            {
            	fight();
            	add(btnFight);
    			add(btnRun);
    			if(hero.hasMedKit() == true)
    			{
    				add(btnMed);
    				btnMed.setFocusable(true);
    			}
    			btnFight.setFocusable(true);
    			btnRun.setFocusable(true);
            }
			remove(btnChoke);
			remove(btnSlam);
			remove(btnPush);
			hero.removeItem("Holocron");
			btnPush.setFocusable(false);
		}
		else if(a.getSource() == btnForce) //This statement check if the "Force" button was clicked.
		{
			remove(btnBlaster); //"Blaster" button is removed
			remove(btnForce); //"Force" button is removed
			add(btnChoke); //Choke button is added
			add(btnSlam); //"Force Slam" button is added
			add(btnPush); //"Force Push" button is added
			btnForce.setFocusable(false); //focus is taken away from Force
			btnChoke.setFocusable(true); //focus is given to Choke
			btnSlam.setFocusable(true); //Focus is fiven to Slam
			btnPush.setFocusable(true);//focus is given to Push
		}
		else if(a.getSource() == btnBlaster) //btnBlaster works the exact same way as Choke but is just a different type of attack
		{
			hero.attack(enemy); //hero attacks
            if(enemy.getHP() == 0)
            {
                map.removeCharAtLoc(hero.getLocation()); //places n where e was if enemy is defeated
                item = enemy.getItem(); //gets the item that the enemy had to be picked up by hero
                if(hero.getNumItems() < 5)//checks item list size
                {
                    hero.pickUpItem(item); //if enough space, pick up item
                    charCheck = 'e';
                }
                else
                {
                	int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
	                
	                if(reply == JOptionPane.YES_OPTION)
	                {
	                	setFocusable(true);
	                	for(int i = 0; i < hero.getNumItems(); i++)
	                	{
	                		if(i == 0)
	            			{
	                			//First item in ArrayList information
	            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
	            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
	            				add(btnItemSlot1); //first button item added
	            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
	            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
	            			}
	            			else if(i == 1)
	            			{
	            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot2);
	            				btnItemSlot2.addActionListener(this);
	            				btnItemSlot2.setFocusable(true);
	            			}
	            			else if(i == 2)
	            			{
	            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot3);
	            				btnItemSlot3.addActionListener(this);
	            				btnItemSlot3.setFocusable(true);
	            			}
	            			else if(i == 3)
	            			{
	            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot4);
	            				btnItemSlot4.addActionListener(this);
	            				btnItemSlot4.setFocusable(true);
	            			}
	            			else if(i == 4)
	            			{
	            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
	            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
	            				add(btnItemSlot5);
	            				btnItemSlot5.addActionListener(this);
	            				btnItemSlot5.setFocusable(true);
	            				isInstantiated = true;
	            				charCheck = 'e';
	            			}
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(this, "Item has been lost");
	                }
                }
                remove(btnFight);
                remove(btnRun);
                remove(btnMed);
                if(isInstantiated != true)
	            {
	            	setFocusable(true);
	                requestFocusInWindow();
	            }
            }
            else
            {
            	fight();
            	add(btnFight);
                add(btnRun);
                if(hero.hasMedKit() == true)
                {
                	add(btnMed);
                	btnMed.setFocusable(true);
                }
                btnFight.setFocusable(true);
    			btnRun.setFocusable(true);
            }
            remove(btnBlaster);
            remove(btnForce);
            btnBlaster.setFocusable(false);
		}
		else if(a.getSource() == btnItemSlot1) //The following 5 buttons all do the same thing
		{ //These buttons check if they are pressed and if they are, that specific number in the list is removed.
			remove(btnItemSlot1); //removes btnItemSlot1
        	remove(btnItemSlot2); //removes btnItemSlot2
        	remove(btnItemSlot3); //removes btnItemSlot3
        	remove(btnItemSlot4); //removes btnItemSlot4
        	remove(btnItemSlot5); //removes btnItemSlot5
        	hero.removeItem(1); //removes item in first position
        	hero.pickUpItem(item); //picks up item after button is pressed
        	btnItemSlot1.setFocusable(false); //focus is taken from the item button
        	setFocusable(true); //panel is given focus again
        	requestFocusInWindow(); //focus is requested for the window
        	isInstantiated = false; //the boolean is made false so an if statement above is not taken
		}
		else if(a.getSource() == btnItemSlot2)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
        	hero.removeItem(2);
        	hero.pickUpItem(item);
        	btnItemSlot2.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(a.getSource() == btnItemSlot3)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
        	hero.removeItem(3);
        	hero.pickUpItem(item);
        	btnItemSlot3.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(a.getSource() == btnItemSlot4)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
        	hero.removeItem(4);
        	hero.pickUpItem(item);
        	btnItemSlot4.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(a.getSource() == btnItemSlot5)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
        	hero.removeItem(5);
        	hero.pickUpItem(item);
        	btnItemSlot5.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(a.getSource() == btnMed) //Checks if btnMed is clicked
		{
			hero.heal(25); //heals +25 for use of a Med Kit
            hero.removeItem("Med Kit"); //removes Med Kit by string name if used
            if(hero.hasMedKit() == false)
            {
            	remove(btnMed); //removes Med Kit button if it is pressed
            }
            btnMed.setFocusable(false); //focus is taken from btnMed
            setFocusable(true); //panel is given focus
            requestFocusInWindow(); //focus is requested for the window
		}
		else if(a.getSource() == btnRun) //Checks if btnRun is clicked and does the following.
		{
			int randomPosition = rand.nextInt(4) + 1;
            int x = (int) hero.getLocation().getX();
            int y = (int) hero.getLocation().getY();
            //check if the adjacent direction a hero may run is a valid direction.
            if(randomPosition == 1)
            {
                if(x - 1 > 0)
                {
                    hero.goNorth();
                }
                else if(x - 1 < 0)
                {
                    randomPosition = rand.nextInt(3) + 1;
                    if(randomPosition == 1 && y - 1 > 0)
                    {
                        if(y - 1 > 0)
                        {
                            hero.goWest();
                        }
                        else if(y - 1 < 0)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && y + 1 < 4)
                            {
                                hero.goEast();
                            }
                            else
                            {
                                hero.goSouth();
                            }
                        }
                    }
                    else if(randomPosition == 2 && y + 1 < 4)
                    {
                        if(y + 1 < 4)
                        {
                            hero.goEast();
                        }
                        else if(y + 1 > 4)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 2 && y - 1 > 0)
                            {
                                hero.goWest();
                            }
                            else
                            {
                                hero.goSouth();
                            }
                        }
                    }
                    else
                    {
                        if(x + 1 < 4)
                        {
                            hero.goSouth();
                        }
                        else if(x + 1 > 4)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && y - 1 > 0)
                            {
                                hero.goWest();
                            }
                            else
                            {
                                hero.goEast();
                            }
                        }
                    }
                }
                if(map.getCharAtLoc(hero.getLocation()) == 'e') //check if the hero ran into another enemy
				{
					enemy = eg.generateEnemy(hero.getLevel());
					add(btnFight);
					add(btnRun);
					if(hero.hasMedKit() == true)
					{
						add(btnMed);
						btnMed.setFocusable(true);
					}
					setFocusable(false);
					btnFight.setFocusable(true);
					btnRun.setFocusable(true);
				}
                else
                {
                	remove(btnFight);
                    remove(btnRun);
                    remove(btnMed);
                    btnRun.setFocusable(false);
                    setFocusable(true);
                    requestFocusInWindow();
                }
            }
            else if(randomPosition == 2)
            {
                if(x + 1 < 4)
                {
                    hero.goSouth();
                }
                else if(x + 1 > 4)
                {
                    randomPosition = rand.nextInt(3) + 1;
                    if(randomPosition == 1 && y + 1 < 4)
                    {
                        if(y + 1 < 4)
                        {
                            hero.goEast();
                        }
                        else if(y + 1 > 4)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && y - 1 > 0)
                            {
                                hero.goWest();
                            }
                            else
                            {
                                hero.goNorth();
                            }
                        }
                    }
                    else if(randomPosition == 2 && y - 1 > 0)
                    {
                        if(y - 1 > 0)
                        {
                            hero.goWest();
                        }
                        else if(y - 1 < 0)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 2 && y + 1 < 4)
                            {
                                hero.goEast();
                            }
                            else
                            {
                                hero.goNorth();
                            }
                        }
                    }
                    else
                    {
                        if(x - 1 > 0)
                        {
                            hero.goNorth();
                        }
                        else if(x - 1 < 0)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && y - 1 > 0)
                            {
                                hero.goWest();
                            }
                            else
                            {
                                hero.goEast();
                            }
                        }
                    }
                }
                if(map.getCharAtLoc(hero.getLocation()) == 'e')
				{
					enemy = eg.generateEnemy(hero.getLevel());
					add(btnFight);
					add(btnRun);
					if(hero.hasMedKit() == true)
					{
						add(btnMed);
						btnMed.setFocusable(true);
					}
					setFocusable(false);
					btnFight.setFocusable(true);
					btnRun.setFocusable(true);
				}
                else
                {
                	remove(btnFight);
                    remove(btnRun);
                    remove(btnMed);
                    btnRun.setFocusable(false);
                    setFocusable(true);
                    requestFocusInWindow();
                }
            }
            else if(randomPosition == 3)
            {
                if(y + 1 < 4)
                {
                    hero.goEast();
                }
                else if(y + 1 > 4)
                {
                    randomPosition = rand.nextInt(3) + 1;
                    if(randomPosition == 1 && x - 1 > 0)
                    {
                        if(x - 1 > 0)
                        {
                            hero.goNorth();
                        }
                        else if(x - 1 < 0)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && x + 1 < 4)
                            {
                                hero.goSouth();
                            }
                            else
                            {
                                hero.goWest();
                            }
                        }
                    }
                    else if(randomPosition == 2 && x + 1 < 4)
                    {
                        if(x + 1 < 4)
                        {
                            hero.goSouth();
                        }
                        else if(x + 1 > 4)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 2 && x - 1 > 0)
                            {
                                hero.goNorth();
                            }
                            else
                            {
                                hero.goWest();
                            }
                        }
                    }
                    else
                    {
                        if(y - 1 > 0)
                        {
                            hero.goWest();
                        }
                        else if(y - 1 < 0)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && x - 1 > 0)
                            {
                                hero.goNorth();
                            }
                            else
                            {
                                hero.goSouth();
                            }
                        }
                    }
                }
                if(map.getCharAtLoc(hero.getLocation()) == 'e')
				{
					enemy = eg.generateEnemy(hero.getLevel());
					add(btnFight);
					add(btnRun);
					if(hero.hasMedKit() == true)
					{
						add(btnMed);
						btnMed.setFocusable(true);
					}
					setFocusable(false);
					btnFight.setFocusable(true);
					btnRun.setFocusable(true);
				}
                else
                {
                	remove(btnFight);
                    remove(btnRun);
                    remove(btnMed);
                    btnRun.setFocusable(false);
                    setFocusable(true);
                    requestFocusInWindow();
                }
            }
            else if(randomPosition == 4)
            {
                if(y - 1 > 0)
                {
                    hero.goWest();
                }
                else if(y - 1 < 0)
                {
                    randomPosition = rand.nextInt(3) + 1;
                    if(randomPosition == 1 && x - 1 > 0)
                    {
                        if(x - 1 > 0)
                        {
                            hero.goNorth();
                        }
                        else if(x - 1 < 0)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && x + 1 < 4)
                            {
                                hero.goSouth();
                            }
                            else
                            {
                                hero.goEast();
                            }
                        }
                    }
                    else if(randomPosition == 2 && x + 1 < 4)
                    {
                        if(x + 1 < 4)
                        {
                            hero.goSouth();
                        }
                        else if(x + 1 > 4)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && x - 1 > 0)
                            {
                                hero.goNorth();
                            }
                            else
                            {
                                hero.goEast();
                            }
                        }
                    }
                    else
                    {
                        if(y + 1 < 4)
                        {
                            hero.goEast();
                        }
                        else if(y + 1 > 4)
                        {
                            randomPosition = rand.nextInt(2) + 1;
                            if(randomPosition == 1 && x - 1 > 0)
                            {
                                hero.goNorth();
                            }
                            else
                            {
                                hero.goSouth();
                            }
                        }
                    }
                }
            }
            
            if(map.getCharAtLoc(hero.getLocation()) == 'e')
			{
				enemy = eg.generateEnemy(hero.getLevel());
				add(btnFight);
				add(btnRun);
				if(hero.hasMedKit() == true)
				{
					add(btnMed);
					btnMed.setFocusable(true);
				}
				setFocusable(false);
				btnFight.setFocusable(true);
				btnRun.setFocusable(true);
			}
            else
            {
            	remove(btnFight);
                remove(btnRun);
                remove(btnMed);
                btnRun.setFocusable(false);
                setFocusable(true);
                requestFocusInWindow();
            }
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
	}

	@Override
	/**
	 * keyPressed is a method used to account for the WASD and arrow keys being pressed in order to move the hero.
	 * @param k - is a parameter used from the interface KeyListener in order to check for key presses.
	 */
	public void keyPressed(KeyEvent k)
	{
		//key press is received and if it is W or the up arrow key, the following code runs
		if(k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_UP)
		{
			charCheck = '0';
			if(isInstantiated == true) //used to check if the item buttons are on the screen, if they are, warning message.
			{
				JOptionPane.showMessageDialog(this, "You can't move while dropping an item.");
			}
			else //if the item buttons are not on the screen then the hero is allowed to move
			{
				if(hero.getLocation().getX() - 1 < 0) //Checks for 5 x 5 bounds
				{
					JOptionPane.showMessageDialog(this, "Out of Bounds");
				}
				else
				{
					map.reveal(hero.getLocation()); //location the hero is on is revealed
					hero.goNorth(); //y coordinate changes by +1.
					
					if(map.getCharAtLoc(hero.getLocation()) == 'f')
	                {
	                    //if key is in inventory move to next level
	                    if(hero.hasKey() == true)
	                    {
	                        hero.removeItem("Key");
	                        hero.increaseLevel(); //hero gains level
	                        hero.increaseMaxHP(10); // increases max health
	                        hero.heal(10);
	                        map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                        map.reveal(hero.getLocation());
	                    }
	                    else if(hero.hasKey() == false && hero.hasHolocron() == true)
	                    {
	                        percent = rand.nextInt(100) + 1;
	                        if(percent > 50)//passed level if hero rolls a 51 or higher
	                        {
	                            hero.increaseLevel(); //hero gains level
	                            hero.increaseMaxHP(10); // increases max health
	                            hero.heal(10);
	                            map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                            map.reveal(hero.getLocation());
	                        }
	                        hero.removeItem("Holocron");
	                    }
	                }
					else if(map.getCharAtLoc(hero.getLocation()) == 'e')
					{
						enemy = eg.generateEnemy(hero.getLevel());
						add(btnFight);
						add(btnRun);
						if(hero.hasMedKit() == true)
						{
							add(btnMed);
							btnMed.setFocusable(true);
						}
						setFocusable(false);
						btnFight.setFocusable(true);
						btnRun.setFocusable(true);
					}
					else if(map.getCharAtLoc(hero.getLocation()) == 'i')
					{
						item = ig.generateItem();
						if(hero.getNumItems() < 5)//checks item list size
		                {
		                    hero.pickUpItem(item); //if enough space, pick up item
		                    charCheck = 'i';
		                }
						else
						{
							int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
			                
			                if(reply == JOptionPane.YES_OPTION)
			                {
			                	for(int i = 0; i < hero.getNumItems(); i++)
			                	{
			                		if(i == 0)
			            			{
			                			//First item in ArrayList information
			            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
			            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
			            				add(btnItemSlot1); //first button item added
			            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
			            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
			            			}
			            			else if(i == 1)
			            			{
			            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot2);
			            				btnItemSlot2.addActionListener(this);
			            				btnItemSlot2.setFocusable(true);
			            			}
			            			else if(i == 2)
			            			{
			            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot3);
			            				btnItemSlot3.addActionListener(this);
			            				btnItemSlot3.setFocusable(true);
			            			}
			            			else if(i == 3)
			            			{
			            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot4);
			            				btnItemSlot4.addActionListener(this);
			            				btnItemSlot4.setFocusable(true);
			            			}
			            			else if(i == 4)
			            			{
			            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot5);
			            				btnItemSlot5.addActionListener(this);
			            				btnItemSlot5.setFocusable(true);
			            				isInstantiated = true;
			            				charCheck = 'i';
			            			}
			                	}
			                }
			                else
			                {
			                	JOptionPane.showMessageDialog(this, "Item has been lost");
			                }
						}
						map.removeCharAtLoc(hero.getLocation());
					}
				}
				if(hero.getHP() == 0)
				{
					JOptionPane.showMessageDialog(this, "You have died!");
					System.exit(0);
				}
			}
		}
		else if(k.getKeyCode() == KeyEvent.VK_A || k.getKeyCode() == KeyEvent.VK_LEFT)
		{
			charCheck = '0';
			if(isInstantiated == true)
			{
				JOptionPane.showMessageDialog(this, "You can't move while dropping an item.");
			}
			else
			{
				if(hero.getLocation().getY() - 1 < 0)
				{
					JOptionPane.showMessageDialog(this, "Out of Bounds");
				}
				else
				{
					map.reveal(hero.getLocation());
					hero.goWest();
					if(map.getCharAtLoc(hero.getLocation()) == 'f')
	                {
	                    //if key is in inventory move to next level
	                    if(hero.hasKey() == true)
	                    {
	                        hero.removeItem("Key");
	                        hero.increaseLevel(); //hero gains level
	                        hero.increaseMaxHP(10); // increases max health
	                        hero.heal(10);
	                        map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                        map.reveal(hero.getLocation());
	                    }
	                    else if(hero.hasKey() == false && hero.hasHolocron() == true)
	                    {
	                        percent = rand.nextInt(100) + 1;
	                        if(percent > 50)//passed level if hero rolls a 51 or higher
	                        {
	                            hero.increaseLevel(); //hero gains level
	                            hero.increaseMaxHP(10); // increases max health
	                            hero.heal(10);
	                            map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                            map.reveal(hero.getLocation());
	                        }
	                        hero.removeItem("Holocron");
	                    }
	                }
					else if(map.getCharAtLoc(hero.getLocation()) == 'e')
					{
						enemy = eg.generateEnemy(hero.getLevel());
						add(btnFight);
						add(btnRun);
						if(hero.hasMedKit() == true)
						{
							add(btnMed);
							btnMed.setFocusable(true);
						}
						setFocusable(false);
						btnFight.setFocusable(true);
						btnRun.setFocusable(true);
					}
					else if(map.getCharAtLoc(hero.getLocation()) == 'i')
					{
						item = ig.generateItem();
						if(hero.getNumItems() < 5)//checks item list size
		                {
		                    hero.pickUpItem(item); //if enough space, pick up item
		                    charCheck = 'i';
		                }
						else
						{
							int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
			                
			                if(reply == JOptionPane.YES_OPTION)
			                {
			                	for(int i = 0; i < hero.getNumItems(); i++)
			                	{
			                		if(i == 0)
			            			{
			                			//First item in ArrayList information
			            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
			            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
			            				add(btnItemSlot1); //first button item added
			            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
			            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
			            			}
			            			else if(i == 1)
			            			{
			            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot2);
			            				btnItemSlot2.addActionListener(this);
			            				btnItemSlot2.setFocusable(true);
			            			}
			            			else if(i == 2)
			            			{
			            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot3);
			            				btnItemSlot3.addActionListener(this);
			            				btnItemSlot3.setFocusable(true);
			            			}
			            			else if(i == 3)
			            			{
			            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot4);
			            				btnItemSlot4.addActionListener(this);
			            				btnItemSlot4.setFocusable(true);
			            			}
			            			else if(i == 4)
			            			{
			            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot5);
			            				btnItemSlot5.addActionListener(this);
			            				btnItemSlot5.setFocusable(true);
			            				isInstantiated = true;
			            				charCheck = 'i';
			            			}
			                	}
			                }
			                else
			                {
			                	JOptionPane.showMessageDialog(this, "Item has been lost");
			                }
						}
						map.removeCharAtLoc(hero.getLocation());
					}
				}
				if(hero.getHP() == 0)
				{
					JOptionPane.showMessageDialog(this, "You have died!");
					System.exit(0);
				}
			}
		}
		else if(k.getKeyCode() == KeyEvent.VK_S || k.getKeyCode() == KeyEvent.VK_DOWN)
		{
			charCheck = '0';
			if(isInstantiated == true)
			{
				JOptionPane.showMessageDialog(this, "You can't move while dropping an item.");
			}
			else
			{
				if(hero.getLocation().getX() + 1 > 4)
				{
					JOptionPane.showMessageDialog(this, "Out of Bounds");
				}
				else
				{
					map.reveal(hero.getLocation());
					hero.goSouth();
					if(map.getCharAtLoc(hero.getLocation()) == 'f')
	                {
	                    //if key is in inventory move to next level
	                    if(hero.hasKey() == true)
	                    {
	                        hero.removeItem("Key");
	                        hero.increaseLevel(); //hero gains level
	                        hero.increaseMaxHP(10); // increases max health
	                        hero.heal(10);
	                        map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                        map.reveal(hero.getLocation());
	                    }
	                    else if(hero.hasKey() == false && hero.hasHolocron() == true)
	                    {
	                        percent = rand.nextInt(100) + 1;
	                        if(percent > 50)//passed level if hero rolls a 51 or higher
	                        {
	                            hero.increaseLevel(); //hero gains level
	                            hero.increaseMaxHP(10); // increases max health
	                            hero.heal(10);
	                            map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                            map.reveal(hero.getLocation());
	                        }
	                        hero.removeItem("Holocron");
	                    }
	                }
					else if(map.getCharAtLoc(hero.getLocation()) == 'e')
					{
						enemy = eg.generateEnemy(hero.getLevel());
						add(btnFight);
						add(btnRun);
						if(hero.hasMedKit() == true)
						{
							add(btnMed);
							btnMed.setFocusable(true);
						}
						setFocusable(false);
						btnFight.setFocusable(true);
						btnRun.setFocusable(true);
					}
					else if(map.getCharAtLoc(hero.getLocation()) == 'i')
					{
						item = ig.generateItem();
						if(hero.getNumItems() < 5)//checks item list size
		                {
		                    hero.pickUpItem(item); //if enough space, pick up item
		                    charCheck = 'i';
		                }
						else
						{
							int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
			                
			                if(reply == JOptionPane.YES_OPTION)
			                {
			                	for(int i = 0; i < hero.getNumItems(); i++)
			                	{
			                		if(i == 0)
			            			{
			                			//First item in ArrayList information
			            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
			            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
			            				add(btnItemSlot1); //first button item added
			            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
			            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
			            			}
			            			else if(i == 1)
			            			{
			            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot2);
			            				btnItemSlot2.addActionListener(this);
			            				btnItemSlot2.setFocusable(true);
			            			}
			            			else if(i == 2)
			            			{
			            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot3);
			            				btnItemSlot3.addActionListener(this);
			            				btnItemSlot3.setFocusable(true);
			            			}
			            			else if(i == 3)
			            			{
			            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot4);
			            				btnItemSlot4.addActionListener(this);
			            				btnItemSlot4.setFocusable(true);
			            			}
			            			else if(i == 4)
			            			{
			            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot5);
			            				btnItemSlot5.addActionListener(this);
			            				btnItemSlot5.setFocusable(true);
			            				isInstantiated = true;
			            				charCheck = 'i';
			            			}
			                	}
			                }
			                else
			                {
			                	JOptionPane.showMessageDialog(this, "Item has been lost");
			                }
						}
						map.removeCharAtLoc(hero.getLocation());
					}
				}
				if(hero.getHP() == 0)
				{
					JOptionPane.showMessageDialog(this, "You have died!");
					System.exit(0);
				}
			}
		}
		else if(k.getKeyCode() == KeyEvent.VK_D || k.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			charCheck = '0';
			if(isInstantiated == true)
			{
				JOptionPane.showMessageDialog(this, "You can't move while dropping an item.");
			}
			else
			{
				if(hero.getLocation().getY() + 1 > 4)
				{
					JOptionPane.showMessageDialog(this, "Out of Bounds");
				}
				else
				{
					map.reveal(hero.getLocation());
					hero.goEast();
					if(map.getCharAtLoc(hero.getLocation()) == 'f')
	                {
	                    //if key is in inventory move to next level
	                    if(hero.hasKey() == true)
	                    {
	                        hero.removeItem("Key");
	                        hero.increaseLevel(); //hero gains level
	                        hero.increaseMaxHP(10); // increases max health
	                        hero.heal(10);
	                        map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                        map.reveal(hero.getLocation());
	                    }
	                    else if(hero.hasKey() == false && hero.hasHolocron() == true)
	                    {
	                        percent = rand.nextInt(100) + 1;
	                        if(percent > 50)//passed level if hero rolls a 51 or higher
	                        {
	                            hero.increaseLevel(); //hero gains level
	                            hero.increaseMaxHP(10); // increases max health
	                            hero.heal(10);
	                            map.loadMap(hero.getLevel()%4);//loads maps in consecutive order, if level 4 loads 1, if level 5 loads 2, etc.
	                            map.reveal(hero.getLocation());
	                        }
	                        hero.removeItem("Holocron");
	                    }
	                }
					else if(map.getCharAtLoc(hero.getLocation()) == 'e')
					{
						enemy = eg.generateEnemy(hero.getLevel());
						add(btnFight);
						add(btnRun);
						if(hero.hasMedKit() == true)
						{
							add(btnMed);
							btnMed.setFocusable(true);
						}
						setFocusable(false);
						btnFight.setFocusable(true);
						btnRun.setFocusable(true);
					}
					else if(map.getCharAtLoc(hero.getLocation()) == 'i')
					{
						item = ig.generateItem();
						if(hero.getNumItems() < 5)//checks item list size
		                {
		                    hero.pickUpItem(item); //if enough space, pick up item
		                    charCheck = 'i';
		                }
						else
						{
							int reply = JOptionPane.showConfirmDialog(this, "Would you like to remove an item?", "Item Drop", JOptionPane.YES_NO_OPTION);
			                
			                if(reply == JOptionPane.YES_OPTION)
			                {
			                	for(int i = 0; i < hero.getNumItems(); i++)
			                	{
			                		if(i == 0)
			            			{
			                			//First item in ArrayList information
			            				btnItemSlot1 = new JButton(hero.displayItems(i).charAt(0) + ""); //First character of item written
			            				btnItemSlot1.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50); //Bounds are set depending on what item number it is
			            				add(btnItemSlot1); //first button item added
			            				btnItemSlot1.addActionListener(this); //allows for the button to be clicked
			            				btnItemSlot1.setFocusable(true); //focus is given to the item buttons
			            			}
			            			else if(i == 1)
			            			{
			            				btnItemSlot2 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot2.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot2);
			            				btnItemSlot2.addActionListener(this);
			            				btnItemSlot2.setFocusable(true);
			            			}
			            			else if(i == 2)
			            			{
			            				btnItemSlot3 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot3.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot3);
			            				btnItemSlot3.addActionListener(this);
			            				btnItemSlot3.setFocusable(true);
			            			}
			            			else if(i == 3)
			            			{
			            				btnItemSlot4 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot4.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot4);
			            				btnItemSlot4.addActionListener(this);
			            				btnItemSlot4.setFocusable(true);
			            			}
			            			else if(i == 4)
			            			{
			            				btnItemSlot5 = new JButton(hero.displayItems(i).charAt(0) + "");
			            				btnItemSlot5.setBounds( (i > 2) ? 530 + 50 * (i-3):530 + 50*i, (i > 2) ? 245 : 195, 50, 50);
			            				add(btnItemSlot5);
			            				btnItemSlot5.addActionListener(this);
			            				btnItemSlot5.setFocusable(true);
			            				isInstantiated = true;
			            				charCheck = 'i';
			            			}
			                	}
			                }
			                else
			                {
			                	JOptionPane.showMessageDialog(this, "Item has been lost");
			                }
						}
						map.removeCharAtLoc(hero.getLocation());
					}
				}
				if(hero.getHP() == 0)
				{
					JOptionPane.showMessageDialog(this, "You have died!");
					System.exit(0);
				}
			}
		}
		else if(k.getKeyCode() == KeyEvent.VK_1)
		{
			remove(btnItemSlot1); //All of the item buttons are removed once an item is removed
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
			if(isInstantiated == true) //checks if all of the button are appearing on the GUI
			{
				hero.removeItem(1); //removes specific index of item
			}
        	hero.pickUpItem(item); //picks up item
        	btnItemSlot1.setFocusable(false); //removes focus from button
        	setFocusable(true); //panel receives focus
        	requestFocusInWindow(); //focus is requested by the window
        	isInstantiated = false; //sets isInstantiated to false to signify that the buttons are not visible.
		}
		else if(k.getKeyCode() == KeyEvent.VK_2)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
			if(isInstantiated == true)
			{
				hero.removeItem(2);
			}
        	hero.pickUpItem(item);
        	btnItemSlot2.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(k.getKeyCode() == KeyEvent.VK_3)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
			if(isInstantiated == true)
			{
				hero.removeItem(3);
			}
        	hero.pickUpItem(item);
        	btnItemSlot3.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(k.getKeyCode() == KeyEvent.VK_4)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
			if(isInstantiated == true)
			{
				hero.removeItem(4);
			}
        	hero.pickUpItem(item);
        	btnItemSlot4.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
		else if(k.getKeyCode() == KeyEvent.VK_5)
		{
			remove(btnItemSlot1);
        	remove(btnItemSlot2);
        	remove(btnItemSlot3);
        	remove(btnItemSlot4);
        	remove(btnItemSlot5);
			if(isInstantiated == true)
			{
				hero.removeItem(5);
			}
        	hero.pickUpItem(item);
        	btnItemSlot5.setFocusable(false);
        	setFocusable(true);
        	requestFocusInWindow();
        	isInstantiated = false;
		}
	}
	
	/**
	 * method used for the enemy attacks, checks if armor is in the inventory and if it is then hero takes one less damage.
	 * @return boolean if the enemy health is greater than 0. else returns false
	 */
	public boolean fight()
    {
        if(enemy.getHP() > 0) //enemy could attack if still has a health greater then 0
        {
            if(hero.hasArmor() == true) //if a piece of armor is in the inventory, returns true
            {
                hero.heal(1); //armor takes a hit of damage
                enemy.attack(hero); //enemy attack method
                hero.removeFirstArmorItem();
            }
            else if(hero.hasArmor() == false)//if no armor, all damage taken by hero
            {
                enemy.attack(hero);
            }
            return true;
        }
        return false;
    }
	
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}