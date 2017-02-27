import java.awt.*;

/**
 * Command class to perform a move command.
 * 
 * @author Varun Shah
 * @see Command
 */
public class MoveCmd extends Command	{
	private Point origin; //Mouse position
	private Shape shape; //The shape clicked
	
	/**
	 * When the mouse is pressed, find the frontmost shape in the drawing
	 * that contains the mouse position and save both the shape and the
	 * position in the instance variables.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executePress(Point p, Drawing dwg)	{
		shape = dwg.getFrontmostContainer(p);
		origin = p;
	}
	
	/**
	 * When mouse is being dragged, the selected shape is dragged over the canvas
	 * and when mouse is released, it is added to the canvas in the new position.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executeDrag(Point p, Drawing dwg)	{
		int deltaX = p.x - origin.x;
		int deltaY = p.y - origin.y;
		shape.move(deltaX, deltaY);
		origin = p;
	}
}