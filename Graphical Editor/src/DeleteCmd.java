import java.awt.*;

/**
 * Command class to perform a delete command.
 * 
 * @author Varun Shah
 * @see Command
 */
public class DeleteCmd extends Command	{
	private Shape shape; //the shape clicked
	
	/**
	 * When mouse is clicked, find the frontmost shape in the drawing
	 * that contains the mouse position and remove it from the canvas.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked 
	 */
	public void executeClick(Point p, Drawing dwg)	{
		shape = dwg.getFrontmostContainer(p);
		dwg.removeShape(shape);
	}
}