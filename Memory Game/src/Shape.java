import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Abstract class that represents a shape
 * @author Varun Shah
 * @version
 */
public abstract class Shape {
    private static final int CARDWIDTH = 80;
    private static final int CARDHEIGHT = 80;

    protected String myPath;
    protected BufferedImage img;
    protected int x,y; // shape topleft xy
    protected int color; // Shape's color
    protected int filling;  //shape's filling
    protected int shapeType; //shape's type
    protected boolean selected; // if outline is selected

    public abstract void display(Graphics page); // draw the Shape
  
   /**
    * Create a Shape, setting its color, x, y, filling and shape type.
    *
    * @param x The x-coordinate
    * @param y The y-coordinate
    * @param filling Stores the filling of the shape
    * @param shapeType Stores the type of shape to create
    * @param c The color you wish the shape to initially have
    */
    public Shape(int x, int y, int c, int filling, int shapeType) {
        this.x = x;
        this.y = y;
        color = c;
        this.filling = filling;
        this.shapeType = shapeType;
        selected = false;
    }

    /**
     * Checks if the shape is selected
     *
     * @return selected True if shape is selected and False otherwise
     */
    public boolean isSelected(){
        return selected;
    }

    /**
     * Setter method that makes a shape's selection true
     */
    public void setSelected(){
        selected = true;
    }

    /**
     * Unselects a selected shape
     */
    public void deSelect(){
        selected = false;
    }

    /**
     * Getter method that gets the color of the shape
     * 
     * @return The color of the shape
     */
    public String getColor(){
	  switch (color){
        case 0: return "red";
        case 1: return "purple";
        default: return "green";
      }
    }

    /**
     * Getter method that gets the filling of the shape
     * 
     * @return The filling of the shape 
     */
    public String getFilling(){
        switch (filling){
            case 0: return "empty";
            case 1: return "lined";
            default: return "filled";
        }
    }

    /**
     * Getter method that gets the shape
     *
     * @return The shape on the card
     */
    public String getShape(){
        switch (shapeType){
            case 0: return "squiggly";
            case 1: return "diamond";
            case 2: return "oval";
            case 3: return "james";
            case 4: return "varun";
            case 5: return "diego";
            default: return "Outline";
        }
    }

    /**
     * Setter method that sets the position of the shape
     * 
     * @param newX x-coordinate of the top left corner of the screen
     * @param newY y-coordinate of the top left corner of the screen
     */
    public void setPosition(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    /**
     * Checks if there is a shape on the point where the mouse is clicked
     * 
     * @param p Point where the mouse is clicked
     * @return True if there is a shape in the clicked point and False otherwise
     */
    public boolean containsPoint(Point p){
        return ((x <= p.x) && (p.x <= (x + CARDWIDTH)) && (y <= p.y) && (p.y <= y + CARDHEIGHT));
    }

    /**
     * Loads the image of the shape
     *
     * @param img a Bufferedimage object
     * @param path Path of the image in String form
     * @return img Image object containing the shape
     */
    protected BufferedImage loadImage(BufferedImage img, String path) {
        try {
          img = ImageIO.read(MainGame.class.getResourceAsStream(path));
        }
        catch (IOException ex) {
          // handle exception
        }
        return img;
    }
}