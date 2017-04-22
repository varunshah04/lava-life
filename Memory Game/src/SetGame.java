import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * Plays the game of SET
 *
 * @author: Lam Ngo and James Murphy
 * @version: 02/24/2017
 */
public class SetGame
{
	private final int PLAYINGNUM = 12; //the default number of cards on deck.
	private final int MAXIMUMNUM = 15; // the maximum number of cards on deck.
	private final int SETNUM = 3;      // 3 is the number of cards needed to make a set.

	private PlayingDeck playingDeck;
	private ArrayList<Set> validSets; //List of valid sets
	private boolean showset;
	private int counter; //number of times we click "show set" button
	private boolean done; // a variable to indicate when we are done showing all sets in tutorial mode
	private int setCount;

	/**
	 * Constuctor of the Game that initalizes the variables. Only going to be called once, as to main
	 * tain the singleton design pattern.
	 */
	public SetGame(){
		playingDeck = new PlayingDeck();
		showset = false;
		counter = -1;
		done = false;
		setCount = 0;
	}

	/**
	 * Replaces all the cards from the playing field with new cards
	 */
	public void reDeal(){
		setCount = 0;
		playingDeck.reset();
		playingDeck.deal(PLAYINGNUM);
		counter = -1;
		done = false;
		showset = false;
	}

	/**
	 * Getter method that gets the number of valid sets formed
	 * 
	 * @return setCount The number of valid sets formed
	 */
	public int getSetCount(){
		return setCount;
	}

	/**
	 * Getter method that gets the cards at particular points
	 * and check if they form a set
	 *
	 * @param p Point that is clicked by the mouse
	 * @return 1 if the selected cards form a valid set
	 * 		   0 if the selected cards do not form a valid set
	 * 		   2 if three cards have not been selected yet 
	 */
	public int getCards(Point p){
		playingDeck.selectCards(p,3);
		if(playingDeck.enoughSelected(3)){
			if (isSet(playingDeck.getSelected(0), playingDeck.getSelected(1),playingDeck.getSelected(2))){
				setCount++;
				return 1;
			}else{
				return 0;
			}
		}
		return 2;
	}

	/**
	 * setter method. Called when MainGame is done showing set.
	 */
	public void setDone(){
		done = false;
	}

	/**
	 * Removes the selected cards from the playing field
	 */
	public void removeSelected(){
		playingDeck.removeSelected(SETNUM);
	}

	/**
	 * Unselects all the selected cards
	 */
	public void deSelectCards(){
		playingDeck.deSelect();
	}


	/**
	 * Getter method that gets number of cards that remain on the playing field
	 *
	 * @return The number of cards that are left on the playing field
	 */
	public int getCardsLeft(){
		return playingDeck.getCardLeft();
	}

	/**
	 * Adds three cards to the playing field
	 * Only add if there are less than 15 cards on the deck.
	 */
	public int addThreeCards(){
		if (playingDeck.getSize() == MAXIMUMNUM){
			return 0;
		}else if (playingDeck.getSize() < MAXIMUMNUM && !playingDeck.isDeckEmpty()){
			playingDeck.addCard(SETNUM);
			return 1;
		}
		return 2;
	}

	/**
	 * Make sure there are always 12 cards on the playing field.
	 */
	public void ensureFullField(){
		if (playingDeck.getSize() < PLAYINGNUM && !playingDeck.isDeckEmpty()){
			playingDeck.addCard(SETNUM);
		}
	}

	/**
	 * Check to see if there are still sets in the playing field.
	 * return true if there are
	 * 			no if there are not
	 */
	public boolean stillSet(){
		validSets = showAllSets();
		showset = false;
		return (validSets.size() > 0);
	}

	/**
	 * Shows a valid set formed from the cards on the playing field 
	 */
	public void showASet(){
		validSets = showAllSets();
		showset = false;
		playingDeck.deSelect();
		if (!validSets.isEmpty()){
			Set aSet = validSets.get(0);
			aSet.partialSelect(playingDeck);
		}
	}

	/**
	 * Get all the valid sets formed from the cards on the playing field
	 *
	 * @return validSets List of all the valid sets on the playing field 
	 */
	public ArrayList<Set> showAllSets(){
		int cardOne = 0;
		int cardTwo = 1;
		int cardThree = 2;
		validSets = new ArrayList<Set>();
		//Loop through until there are no more sets
		//which will happen when the second card being analyzed
		//is the final card
		while(cardOne < (playingDeck.getSize() - 2)){
			while(cardTwo < (playingDeck.getSize()-1)){
				while(cardThree <= (playingDeck.getSize()-1)){
					//if the set is valid
					if(isSet(playingDeck.getCard(cardOne),playingDeck.getCard(cardTwo),playingDeck.getCard(cardThree))){
						validSets.add(new Set(playingDeck.getCard(cardOne),playingDeck.getCard(cardTwo),playingDeck.getCard(cardThree)));
					}
					cardThree++;
				}
				cardTwo++;
				cardThree = cardTwo + 1;
			}
			cardOne++;
			cardTwo = cardOne + 1;
			cardThree = cardTwo + 1;
		}
		showset = true;
		if (counter < validSets.size()-1){
			counter++;
		}else{
			done = true;
			counter = -1;
		}
		return validSets;
	}

	/**
	 * Keeps track of whether we are done showing the valid sets
	 *
	 * @return done Stores whether we are done displaying valid sets 
	 */
	public boolean doneShowing(){
		return done;
	}

	/**
	 * Checks if the game is over
	 * 
	 * @return True if the deck is empty and there are no more valid sets
	 *		   False otherwise
	 */
	public boolean checkEndCondition(){
		if (playingDeck.isDeckEmpty() && showAllSets().isEmpty()) {
			return true;
		}
		showset = false;
		return false;
	}

	/**
	 * Displays the playing field
	 *
	 * @param page Graphics object
	 */
	public void display(Graphics page){
		if (showset && counter != -1){
			validSets.get(counter).select();
		}
		playingDeck.display(page);

		if(showset){
			for (int i = 0; i < validSets.size(); i++){
				validSets.get(i).deselect();
			}
			showset = false;
		}
	}

	/**
	 * Calls four functions to test if the shape, color, filling, and number
	 * for the three selected cards are all equal or not making it a set
	 *
	 * @param cardOne First card selected
	 * @param cardTwo Second card selected
	 * @param cardThree Third card selected
	 * @return True if it is a set and False otherwise
	 */
	public boolean isSet(Card cardOne, Card cardTwo, Card cardThree){
		if(validNum(cardOne.getNumber(),cardTwo.getNumber(),cardThree.getNumber())
			&&validShape(cardOne.getShape(),cardTwo.getShape(),cardThree.getShape())
		    &&validFilling(cardOne.getFilling(),cardTwo.getFilling(),cardThree.getFilling())
			&&validColor(cardOne.getColor(),cardTwo.getColor(),cardThree.getColor()))
		{
			return true;
		}
		return false;
	}


	 /**
	  * Check to see if the set has 3 cards that have either all the same or
	  * all different number of shapes
	  *
	  * @param cardValue1 Number of shapes on the first card
	  * @param cardValue2 Number of shapes on the second card
	  * @param cardValue3 Number of shapes on the third card
	  * @return True if the number of shapes are all the same or all different
	  *		 False otherwise
	  */
	 public boolean validNum(int cardValue1,int cardValue2,int cardValue3){
		if(cardValue1==cardValue2 && cardValue2==cardValue3){
			return true;
		}
		else if (cardValue1!=cardValue2 && cardValue2!=cardValue3 && cardValue1 != cardValue3){
			return true;
		}
		else{
			return false;
		}
	}
	  
	/**
	 * Check to see if the set has 3 cards that have either all the same
     * or all different shapes
	 * 
	 * @param cardShape1 Shape of the first card
	 * @param cardShape2 Shape of the second card
	 * @param cardShape3 Shape of the third card
	 * @return True if all shapes are the same or all are different
	 *		   False otherwise
	 */
	public boolean validShape(String cardShape1,String cardShape2,String cardShape3){
		if(cardShape1==cardShape2 && cardShape2==cardShape3){
			return true;
		}
		else if (cardShape1!=cardShape2 && cardShape2!=cardShape3 && cardShape1 != cardShape3){
			return true;
		}
		else{
			return false;
		}
	}
	  
	/**
	 * Check to see if the set has 3 cards that have either all the same or
	 * all different colors
	 * 
	 * @param cardColor1 Color of the first card
	 * @param cardColor2 Color of the second card
	 * @param cardColor3 Color of the third card
	 * @return True if all colors are the same or all different color
	 *		   False otherwise
	 */
	public boolean validColor(String cardColor1,String cardColor2,String cardColor3){
		if(cardColor1==cardColor2 && cardColor2==cardColor3){
			return true;
		}
		else if (cardColor1!=cardColor2 && cardColor2!=cardColor3 && cardColor1!=cardColor3){
			return true;
		}
		else{
			return false;
		}
	}
	  
	/**
	 * Check to see if the set has 3 cards that have either all the same
	 * or all different filling
	 * 
	 * @param cardFilling1 Filling of the first card
	 * @param cardFilling2 Filling of the second card
	 * @param cardFilling3 Filling of the third card
	 * @return True if all the fillings are all the same or all different
	 *		   False otherwise
	 */
	public boolean validFilling(String cardFilling1,String cardFilling2,String cardFilling3){
		if(cardFilling1==cardFilling2 && cardFilling2==cardFilling3){
			return true;
		}
		else if (cardFilling1!=cardFilling2 && cardFilling2!=cardFilling3 && cardFilling1!=cardFilling3){
			return true;
		}
		else{
			return false;
		}
	}
}