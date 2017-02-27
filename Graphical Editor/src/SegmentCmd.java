import java.awt.*;

/**
 * Command class to execute commands on a line segment.
 * 
 * @author Varun Shah
 * @see Command
 */
public class SegmentCmd extends Command	{
	private double x1; //starting x-coordinate
	private double y1; //starting y-coordinate
	private Color color;
	
	/**
	 * When the mouse is pressed down, the dimensions of the line segment are set.
	 * 
	 * @param p the coordinates of the click
	 * @param dwg the drawing being clicked
	 */
	public void executePress(Point p, Drawing dwg)	{
		color = dwg.getColor();
		x1 = p.x;
		y1 = p.y;
		dwg.makeShape(new Segment(p, p, color));
	}
	
	/**
	 * When mouse is being dragged, a line segment is drawn over the canvas
	 * and when mouse is released, it is added to the canvas.
	 * 
	 * @param p Point where the mouse is clicked
	 * @param dwg Drawing being clicked
	 */
	public void executeDrag(Point p, Drawing dwg)	{
		Point p1 = new Point((int) x1, (int) y1);
		Segment seg = new Segment(p1, p, color);
		dwg.createWhileDrag(seg);
	}
}