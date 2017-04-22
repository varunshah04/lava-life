import java.awt.*;

/**
 * Class represents a card from the deck
 * @author: Lam Ngo
 * @version: 02/23/2017
 */
public class Card {
	private int shapeWith = 45;
	private int shapeHeight = 36;
	private ShapeFactory myFactory;
	private Shape myShape;
	private Shape myOutline;
	private int numOfShapes;
	
	/**
	 * Constructor to initialize the instance variables
	 * 
	 * @param shapeType Stores the shape of the card
	 * @param color Stores the color of the card
	 * @param filling Stores the type of filling of the card
	 * @param num Stores the number of shapes on the card 
	 */
	public Card(int shapeType, int color, int filling, int num){
		myFactory = new ShapeFactory();
		myOutline = myFactory.createShape("Outline", 4, 0, 0);
		myShape = myFactory.createShape("SetShape", shapeType, filling, color);
		numOfShapes = num + 1;
	}
	
	/**
	 * Getter mathod that returns the shape of the card
	 * 
	 * @return The shape of the card
	 */
	public String getShape(){
		return myShape.getShape();
	}
	
	/**
	 * Getter method for the number of shapes on the card
	 * 
	 * @return numOfShapes Stores the number of shapes on the card
	 */
	public int getNumber(){
		return numOfShapes;
	}
	
	/**
	 * Getter method that gets the filling of a card
	 * 
	 * @return The type of filling in the card
	 */
	public String getFilling(){
		return myShape.getFilling();
	}
	
	/**
	 * Getter method that gets the color of the card
	 * 
	 * @return The color of the shape(s) on the card
	 */
	public String getColor(){
		return myShape.getColor();
	}
	
	/**
	 * Determines whether the card contains the point clicked by the mouse
	 * 
	 * @param point Stores the point clicked by the mouse
	 * @return True if the shape contains point and False otherwise
	 */
	public boolean containsPoint(Point point){
		if (myOutline.containsPoint(point)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Checks if the card is selected by the user
	 * 
	 * @return True if the card is selected and False otherwise
	 */
	public boolean isSelected(){
		return myOutline.isSelected();
	}
	
	/**
	 * Setter method that sets whether the card is selected
	 */
	public void setSelected(){
		myOutline.setSelected();
	}
	
	/**
	 * Cancels the selection of a selected card
	 */
	public void deSelect(){
		myOutline.deSelect();
	}
	
	/**
	 * Displays the card
	 * 
	 * @param page Graphics object
	 * @param outlineX x-coordinate of the top left corner of card outline
	 * @param outlineY y-coordinate of the top left corner of card outline
	 */
	public void display(Graphics page, int outlineX, int outlineY){
		int spaceX = 0;
		int spaceY = 0;
		myOutline.setPosition(outlineX,outlineY);
		myOutline.display(page);
		if (getNumber() == 1){
			spaceX = (151 - shapeWith)/2;
			spaceY =  (116 - shapeHeight)/2;
			myShape.setPosition(outlineX + spaceX, outlineY + spaceY);
			myShape.display(page);
		}else if (getNumber() == 2){
			spaceX = (151 - shapeWith)/3;
			spaceY =  (116 - shapeHeight)/2;
			myShape.setPosition(outlineX + spaceX/2, outlineY + spaceY);
			myShape.display(page);
			myShape.setPosition(outlineX + spaceX/2 + shapeWith + spaceX/2, outlineY + spaceY);
			myShape.display(page);
		}else if (getNumber() == 3){
			spaceX = (151 - shapeWith)/4;
			spaceY =  (116 - shapeHeight)/2;
			myShape.setPosition(outlineX + 2, outlineY + spaceY);
			myShape.display(page);
			myShape.setPosition(outlineX + shapeWith + spaceX/4, outlineY + spaceY);
			myShape.display(page);
			myShape.setPosition(outlineX + shapeWith + spaceX/4 + shapeWith + spaceX/4, outlineY + spaceY);
			myShape.display(page);
		}
	}
	
	/**
	 * Generates the string version of the card for testing
	 *
	 * @return The card in String form
	 */
	public String toString(){
		return "\n" + numOfShapes + " " + myShape.getColor() + " " + myShape.getFilling() + " " + myShape.getShape();
	}
}
