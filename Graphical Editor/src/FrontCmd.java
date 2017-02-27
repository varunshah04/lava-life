import java.awt.*;

/**
 * Command class to perform a front command.
 * 
 * @author Varun Shah
 * @see Command
 */
public class FrontCmd extends Command	{
	private Shape shape; //the shape clicked
	
	/**
	 * When the mouse is clicked, find the frontmost shape in the drawing
	 * that contains the mouse position and bring it to the front of the canvas.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executeClick(Point p, Drawing dwg)	{
		shape = dwg.getFrontmostContainer(p);
		dwg.bringToFront(shape);
	}
}