import java.awt.*;

/**
 * Command class to execute commands on an ellipse
 * 
 * @author Varun Shah
 * @see Command
 */
public class EllipseCmd extends Command	{
	private double x1;
	private double y1;
	private double height;
	private double width;
	private Color color;
	
	/**
	 * When mouse is pressed down, the dimensions of the ellipse are set
	 * 
	 * @param p Point where the mouse is pressed
	 * @param dwg Drawing being clicked
	 */
	public void executePress(Point p, Drawing dwg)	{
		color = dwg.getColor();
		x1 = p.x;
		y1 = p.y;
		height = 0;
		width = 0;
		dwg.makeShape(new Ellipse((int) x1, (int) y1, (int) width, (int) height, color));
	}
	
	/**
	 * When mouse is being dragged, an ellipse is drawn over the canvas
	 * and when mouse is released, it is added to the canvas.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executeDrag(Point p, Drawing dwg)	{
		width = Math.abs(p.x - x1);
		height = Math.abs(p.y - y1);
		Point origin = new Point((int) Math.min(p.x, x1), (int) Math.min(p.y, y1));
		Ellipse Ellip = new Ellipse(origin.x, origin.y, (int) width, (int) height, color);
		dwg.createWhileDrag(Ellip);
	}
}