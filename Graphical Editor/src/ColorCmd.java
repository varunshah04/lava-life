import java.awt.*;

/**
 * Command class to perform a color command.
 * 
 * @author Varun Shah
 * @see Command
 */
public class ColorCmd extends Command	{
	private Color color; //the color clicked
	
	/**
	 * When the mouse is clicked, find the frontmost shape in the drawing
	 * that contains the mouse position and change the color to the one
	 * selected by the user.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executeClick(Point p, Drawing dwg)	{
		color = dwg.getColor();
		Shape shape = dwg.getFrontmostContainer(p);
		shape.setColor(color);
	}
}