import java.awt.*;

/**
 * An Oval shape
 * Created by Lam Ngo on 2/18/2017.
 * @author Varun Shah
 */
public class Oval extends Shape {

    public Oval(int x, int y, int color, int filling, int shapeType) {
        super(x,y,color,filling, shapeType);
    }

    /**
     * Have the Oval draw itself.
     *
     * @param page the page you wish to draw on
     */
    public void display(Graphics page) {
        myPath = "/images/" + getColor() + "_" + getShape() + "_" + getFilling() + ".png";
        img = loadImage(img, myPath);
        page.drawImage(img,x,y,null);
    }
}
