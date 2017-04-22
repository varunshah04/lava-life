import java.awt.Graphics;

/**
 * A Rectangle shape
 * @author Lam Ngo
 */
public class Rectangle extends Shape {

	public Rectangle(int x, int y, int color, int filling, int shapeType) {
		super(x,y,color,filling, shapeType);
	}

    /**
     * Display the rectangle
     * @param page a graphics object
     */
	public void display(Graphics page) {
        if (selected){
            img = loadImage(img,"/images/chosen.PNG");
        } else {
            img = loadImage(img, "/images/notchosen.PNG");
        }
        page.drawImage(img,x,y,null);
    }

}
