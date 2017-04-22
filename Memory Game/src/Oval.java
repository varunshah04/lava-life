import java.awt.*;

/**
 * Class that represents an Oval shape
 * 
 * @author Lam Ngo
 * @version 03/06/17
 */
public class Oval extends Shape {

    /**
     * Creates an Oval shape
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param color The color of the shape
     * @param filling The filling of the shape
     * @param shapeType The type of shape
     */
    public Oval(int x, int y, int color, int filling, int shapeType) {
        super(x,y,color,filling, shapeType);
    }

    /**
     * Have the Oval draw itself
     *
     * @param page The page you wish to draw on
     */
    public void display(Graphics page) {
        myPath = "/images/" + getColor() + "_" + getShape() + "_" + getFilling() + ".png";
        img = loadImage(img, myPath);
        page.drawImage(img,x,y,null);
    }
}