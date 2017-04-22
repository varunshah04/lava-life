import java.awt.Graphics;

/**
 * Class that represents the Bean shape
 * @author Lam Ngo
 */
public class Bean extends Shape {

	/**
	 * Constructor that creates the bean shape
	 *
	 * @param x x-coordinate of the bean
	 * @param y y-coordinate of the bean
	 * @param color Color of the bean
	 * @param filling Filling of the bean
	 * @param shapeType Stores the type of shape we are creating
	 */
	public Bean(int x, int y, int color, int filling, int shapeType) {
		super(x,y,color,filling, shapeType);
	}

	/**
	 * Have the Bean draw itself.
	 *
	 * @param page The page you wish to draw on
	 */
	public void display(Graphics page) {
		myPath = "/images/" + getColor() + "_" + getShape() + "_" + getFilling() + ".png";
		img = loadImage(img, myPath);
		page.drawImage(img,x,y,null);
	}
}