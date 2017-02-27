import java.awt.*;

/**
 * 
 * @author Varun Shah
 */
public class RectangleCmd extends Command	{
	private double x1;
	private double y1;
	private double width;
	private double height;
	private Color color;
	
	/**
	 * 
	 */
	public void executePress(Point p, Drawing dwg)	{
		x1 = p.x;
		y1 = p.y;
		width = 0;
		height = 0;
		color = dwg.getColor();
		dwg.makeShape(new Rectangle(p, (int) width, (int) height, color));
	}
	
	/**
	 * 
	 */
	public void executeDrag(Point p, Drawing dwg)	{
		width = Math.abs(p.x - x1);
		height = Math.abs(p.y - y1);
		Point origin = new Point((int) Math.min(p.x, x1), (int) Math.min(p.y, y1));
		Rectangle Rect = new Rectangle(origin, (int) width, (int) height, color);
		dwg.createWhileDrag(Rect);
	}
}