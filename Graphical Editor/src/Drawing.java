import java.awt.*;
import java.util.ArrayList;

/**
 * Canvas on which to draw.
 * 
 * @author Varun Shah
 */
public class Drawing {
	//create a list of shapes drawn on the canvas
	private ArrayList<Shape> shapes = new ArrayList<>();
	private Color color; //color of the shape
	
	/**
	 * Constructor just saves the selected color in the instance variable.
	 * 
	 * @param color The color chosen by the user
	 */
	public Drawing(Color color)	{
		this.color = color;
	}
	
	/**
	 * Get the shape's color.
	 * 
	 * @return Color of the shape
	 */
	public Color getColor()	{
		return this.color;
	}
	
	/**
	 * Set the shape's color.
	 * 
	 * @param color Color of the shape
	 */
	public void setColor(Color color)	{
		this.color = color;
	}
	
	/**
	 * Gets each shape from the array of shapes on the canvas and draws them.
	 * 
	 * @param page The page on which the shape is drawn
	 */
	public void draw(Graphics page)	{
		int i;
		for(i=shapes.size()-1; i>=0;i--)	{
			Shape current = shapes.get(i);
			current.draw(page);
		}
	}
	
	/**
	 * Adds a shape to the front of array of shapes.
	 * 
	 * @param shape Shape to be drawn
	 */
	public void makeShape(Shape shape)	{
		if(shape!=null)	{
			shapes.add(0, shape);
		}
	}
	
	/**
	 * While mouse is being dragged, a shape is constantly removed
	 * and re-added to the array of shapes so that its mouse position
	 * is constantly updated.
	 * 
	 * @param shape The shape to be drawn
	 */
	public void createWhileDrag(Shape shape)	{
		if(shape!=null)	{
			shapes.remove(0);
			makeShape(shape);
		}
	}
	
	/**
	 * Returns the frontmost shape in the clicked mouse position,
	 * and null if no such shape exists.
	 * 
	 * @return frontmost shape
	 */
	public Shape getFrontmostContainer(Point p)	{
		int i;
		for(i=0;i<shapes.size();i++)	{
			Shape shape = shapes.get(i);
			if(shape.containsPoint(p))	{
				return shape;
			}
		}
		return null;
	}
	
	/**
	 * Removes a shape from the array of shapes.
	 * 
	 * @param shape Shape to be removed
	 */
	public void removeShape(Shape shape)	{
		shapes.remove(shape);
	}
	
	/**
	 * Brings a shape to the front of the array of shapes.
	 * 
	 * @param shape Shape to bring to front
	 */
	public void bringToFront(Shape shape)	{
		if(shape!=null)	{
			removeShape(shape);
			makeShape(shape);
		}
	}
	
	/**
	 * Pushes a shape to the back of the array of shapes.
	 * 
	 * @param shape Shape to push to back
	 */
	public void pushToBack(Shape shape)	{
		if(shape!=null)	{
			removeShape(shape);
			shapes.add(shapes.size(), shape);
		}
	}
}