import java.awt.*;

/**
 * Class for a Rectangle.
 * 
 * @author Varun Shah
 * @see Shape
 */
public class Rectangle extends Shape	{
	private double x1; //x coordinate of starting point
	private double y1; //y coordinate of starting point
	private double width; //width of rectangle
	private double height; //height of rectangle
	
	/**
	 * Constructor just saves parameters in the instance variables.
	 * 
	 * @param p The point at which the rectangle starts
	 * @param width Width of the rectangle
	 * @param height Height of the rectangle
	 * @param color Color of the rectangle
	 */
	public Rectangle(Point p, int width, int height, Color color)	{
		super(color);
		x1 = p.x;
		y1 = p.y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Have the rectangle draw itself.
	 * 
	 * @param page The page you wish to draw on
	 */
	public void drawShape(Graphics page)	{
		page.fillRect((int) x1, (int) y1, (int) width, (int) height);
	}
	
	/**
	 * Return true if the rectangle contains point p, false otherwise.
	 * 
	 * @param p Point tested for containment
	 */
	public boolean containsPoint(Point p)	{
		return (x1<p.x && y1<p.y && (x1+width)>p.x && (y1+height)> p.y);
	}
	
	/**
	 * Have the rectangle move itself.
	 * 
	 * @param deltaX Change in the x coordinate
	 * @param deltaY Change in the y coordinate
	 */
	public void move(int deltaX, int deltaY)	{
		x1 += deltaX;
		y1 += deltaY;
	}
	
	/**
	 * Return the rectangle's center.
	 * 
	 * @return Center of the rectangle
	 */
	public Point getCenter()	{
		double cx;
		double cy;
		Point center = new Point();
		cx = (width/2) + x1;
		cy = (height/2) + y1;
		center.x = (int) cx;
		center.y = (int) cy;
		return center;
	}
}