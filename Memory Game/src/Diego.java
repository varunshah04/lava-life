import java.awt.*;

/**
 * Class that represents a Diamond shape
 * @author Lam Ngo
 */
public class Diego extends Shape {

    public Diego(int x, int y, int color, int filling, int shapeType) {
        super(x,y,color,filling, shapeType);
    }
    /**
     * Have the Diamond draw itself
     *
     * @param page The page you wish to draw on
     */
    public void display(Graphics page) {
        myPath = "/images/" + "Diego" + ".jpeg";
        img = loadImage(img, myPath);
        page.drawImage(img,x,y,null);
    }
}