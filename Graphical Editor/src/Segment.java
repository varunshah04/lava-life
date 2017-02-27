import java.awt.*;

/**
 * Segment.java
 * Class for a line segment.
 * 
 * Written by THC for CS 5 Lab Assignment 3.
 *
 * @author Thomas H. Cormen
 * @see Shape
 */
public class Segment extends Shape {
	private static final double tolerance = 3;
	private double x1; //starting x-coordinate
	private double y1; //starting y-coordinate
	private double x2; //ending x-coordinate
	private double y2; //ending y-coordinate
	
	/**
	 * Constructor just saves the parameters in the instance variables.
	 * 
	 * @param p1 Starting point of the line segment
	 * @param p2 Ending point of the line segment
	 * @param color Color of the line segment
	 */
	public Segment(Point p1, Point p2, Color color)	{
		super(color);
		//Points p1 and p2 represent the start and end points, respectively
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
	}
	
	/**
	 * Return true if the line segment contains Point p, false otherwise.
	 *  
	 * @param p Point tested for containment
	 */
	public boolean containsPoint(Point p)	{
		double distance = distanceToPoint(p, (int) x1, (int) y1, (int) x2, (int) y2);
		return almostContainsPoint(p, (int) x1, (int) y1, (int) x2, (int) y2, tolerance) 
				|| distance <= tolerance;
	}

  // Helper method that returns true if Point p is within a tolerance of a
  // given bounding box. Here, the bounding box is given by the coordinates of
  // its left, top, right, and bottom.
  private static boolean almostContainsPoint(Point p, int left, int top,
      int right, int bottom, double tolerance) {
    return p.x >= left - tolerance && p.y >= top - tolerance
        && p.x <= right + tolerance && p.y <= bottom + tolerance;
  }

  // Helper method that returns the distance from Point p to the line
  // containing a line segment whose endpoints are given.
  private static double distanceToPoint(Point p, int x1, int y1, int x2,
      int y2) {
    if (x1 == x2) // vertical segment?
      return (double) (Math.abs(p.x - x1)); // yes, use horizontal distance
    else if (y1 == y2) // horizontal segment?
      return (double) (Math.abs(p.y - y1)); // yes, use vertical distance
    else {
      // Here, we know that the segment is neither vertical nor
      // horizontal.
      // Compute m, the slope of the line containing the segment.
      double m = ((double) (y1 - y2)) / ((double) (x1 - x2));

      // Compute mperp, the slope of the line perpendicular to the
      // segment.
      double mperp = -1.0 / m;

      // Compute the (x, y) intersection of the line containing the
      // segment and the line that is perpendicular to the segment and that
      // contains Point p.
      double x = (((double) y1) - ((double) p.y) - (m * x1) + (mperp * p.x))
          / (mperp - m);
      double y = m * (x - x1) + y1;

      // Return the distance between Point p and (x, y).
      return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }
  }
  
  /**
   * Have the line segment draw itself.
   *
   * @param page The page you wish to draw on
   */
  public void drawShape(Graphics page) {
	  page.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
  }
  
  /**
   * Have the line segment move itself.
   * 
   * @param deltaX new x coordinate
   * @param deltaY new y coordinate
   */
  public void move(int deltaX, int deltaY) {
	  //We shift the coordinates by the change in X and Y
	  x1 += deltaX;
	  y1 += deltaY;
	  x2 += deltaX;
	  y1 += deltaY;
  }
  
  /**
   * Return the line segment's center.
   * 
   * @return The center of the line segment
   */
  public Point getCenter() {
	  double cx; //center point x-coordinate
	  double cy; //center point y-coordinate
	  Point center = new Point();
	  cx = (x1+x2)/2;
	  cy = (y1+y2)/2;
	  center.x = (int) cx;
	  center.y = (int) cy;
	  return center;
  }
}