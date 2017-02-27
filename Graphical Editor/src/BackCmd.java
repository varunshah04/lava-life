import java.awt.*;

/**
 * Command class to perform a back command.
 * 
 * @author Varun Shah
 * @see Command
 */
public class BackCmd extends Command {
	private Shape shape; //the shape clicked
	
	/**
	 * When the mouse is clicked, find the frontmost shape in the drawing
	 * that contains the mouse position and push it to the back of the canvas.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executeClick(Point p, Drawing dwg)	{
		shape = dwg.getFrontmostContainer(p);
		dwg.pushToBack(shape);
	}
}