import javax.swing.*;
/**
 * Main function, constructs all necessary classes and calls other methods in class Main.
 */
@SuppressWarnings("serial")
public class Window extends JFrame
{
	/**
	 * runs the Window() statements and creates the window where the panel will be made.
	 * @param args
	 */
	public static void main(String[] args)
    {
        Window w = new Window();
    }
	
	public Window()
	{
		setBounds(100, 100, 720, 570); //size of the window
		setTitle("Star Wars Game"); //Title of the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //If X is pressed, window closes
		Panel p = new Panel(); //new panel is created
		setContentPane(p); //content pane is set as p
		Thread t = new Thread(p); //Thread is passed for the Panel
		t.start(); //starts repaint
		setVisible(true); //makes window visible.
	}
}
    
    