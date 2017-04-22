import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class that represents the deck of cards
 * @author : Lam Ngo and Varun Shah
 * @version: 02/24/2017
 */
public class PlayingDeck {
	private static final int DEFAULTNUM = 81; //number of cards in the deck

	private ArrayList<Card> cardsInDeck; //cards in the deck
	private ArrayList<Card> cardsInPlayed; //cards on the playing field
	private ArrayList<Card> selectedCards; //cards selected by the user

 	private int numOfCards;			//number of cards in the deck at the start
	private int currentCards;		// number of cards in the playing field
	private int dealt;				// number of card that has been dealt
	private int cardSelected;		// number of card that has been selected
	private boolean inMemory;
	
	/**
	 * Constructor that initializes the instance variables
	 */
	public PlayingDeck(){
		inMemory = false;
		cardsInDeck = new ArrayList<Card>();
		selectedCards = new ArrayList<Card>();
		cardsInPlayed = new ArrayList<Card>();
		numOfCards = DEFAULTNUM;
		createDeck();
	}
	
	/**
	 * Constructor that initializes the instance variables
	 *
	 * @param: numofCard Number of cards in the deck
	 */
	public PlayingDeck(int numOfCard){
		inMemory = false;
		cardsInDeck = new ArrayList<Card>();
		selectedCards = new ArrayList<Card>();
		cardsInPlayed = new ArrayList<Card>();
		this.numOfCards = numOfCard;
		createDeck();
	}
	
	/**
	 * Getter method that gets the size of the deck
	 *
	 * @return currentCards The number of cards currently in the deck
	 */
	public int getSize(){
		return currentCards;
	}

	/**
	 * Getter method that gets a card on the playing field
	 *
	 * @return Card in playing field with that number
	 */
	public Card getCard(int num){
		return cardsInPlayed.get(num);
	}

	/**
	 * Selects the cards in the given point clicked my the mouse
	 *
	 * @param point The point clicked by the mouse
	 */
	public void selectCards(Point point, int num){
		for (int i = 0; i < cardsInPlayed.size(); i++){
			if (cardsInPlayed.get(i).containsPoint(point)){
				if (cardsInPlayed.get(i).isSelected() && !inMemory){
					cardsInPlayed.get(i).deSelect();
					selectedCards.remove(cardsInPlayed.get(i));
					cardSelected--;
					break;
				}
				else if (!cardsInPlayed.get(i).isSelected() && cardSelected < num){
					cardSelected++;
					cardsInPlayed.get(i).setSelected();
					selectedCards.add(cardsInPlayed.get(i));
					break;
				}
			}
		}
	}

	/**
	 * Adds cards to the playing field from the deck
	 *
	 * @param num Number of cards to be dealt
	 */
	public void deal(int num){
        dealt = 0;
        currentCards = 0;
		for (int i = 0; i < num && i < numOfCards; i++){
			cardsInPlayed.add(i,cardsInDeck.get(i));
			dealt++;
			currentCards++;
		}
	}
	
	/**
	 * Checks if the playing field is empty
	 * 
	 * @return True if there are no cards on the playing field
	 * 		   False otherwise
	 */
	public boolean isFieldEmpty(){
		return (currentCards == 0);
	}

	/**
	 * Checks if the deck has any cards
	 * 
	 * @return True if all the cards have been dealt and deck is empty
	 * 		   False otherwise
	 */
	public boolean isDeckEmpty(){
		return (dealt == numOfCards);
	}

	/**
	 * Getter method that gets the cards selected by the user
	 *
	 * @return The cards selected by the user and null if no cards are selected
	 */
	public Card getSelected(int num){
		if (!selectedCards.isEmpty()){
			return selectedCards.get(num);
		}
		return null;
	}

	/**
	 * Adds cards from the deck to the playing field
	 *
	 * @param num Number of cards to be added
	 */
	public void addCard(int num){
		int temp = currentCards;
		for (int i = currentCards; i < temp + num && dealt < cardsInDeck.size(); i++) {
			cardsInPlayed.add(i, cardsInDeck.get(dealt));
			currentCards++;
			dealt++;
		}
	}

	/**
	 * Removes the selected cards
	 * 
	 * @param num Number of cards to be removed 
	 */
	public void removeSelected(int num){
		for (int i = 0; i < num; i++){
			selectedCards.get(i).setNotDisplay();
		}
		deSelect();
		currentCards = currentCards - num;
	}

	/**
	 * Unselects the selected cards
	 */
	public void deSelect(){
		for (int i = 0; i < selectedCards.size(); i++){
			selectedCards.get(i).deSelect();
		}
		cardSelected = 0;
		selectedCards.removeAll(selectedCards);
	}

	/**
	 * Selects a card and add it to selectedCards
	 *
	 * @param aCard The first card used to form a valid set
	 */
	public void hint(Card aCard){
		aCard.setSelected();
		selectedCards.add(aCard);
		cardSelected++;
	}

	/**
	 * Resets the playing field and the deck.
	 */
	public void reset(){
		dealt = 0;
		deSelect();
		currentCards = 0;
		cardSelected = 0;
		for (int i = 0; i < cardsInDeck.size(); i++){
			cardsInDeck.get(i).setDisplay();
		}
		shuffle();
		cardsInPlayed.removeAll(cardsInDeck);
	}


	/**
	 * Checks if three cards have been selected
	 *
	 * @return True if three cards have been selected and false otherwise
	 */
	public boolean enoughSelected(int num){
		if (cardSelected == num){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Displays the cards in the deck
	 *
	 * @param p Graphics object
	 */
	public void display(Graphics p){
		int canvasWidth = MainGame.getCanvasWidth();
		int canvasHeight = MainGame.getCanvasHeight();
		int cardWidth = canvasWidth/9;
		int cardHeight = canvasHeight/8;
		int counter = 0;
		for (int j = 0; j < canvasHeight && counter < cardsInPlayed.size() ; j = j + cardHeight){
			for (int k = 0; k < (canvasWidth - cardWidth) && counter < cardsInPlayed.size(); k = k + cardWidth){
				cardsInPlayed.get(counter).display(p,k,j);
                counter++;
			}
		}
	}

	/**
	 * Creates the deck of cards 
	 */
	private void createDeck(){
		for (int shape = 0; shape < 3; shape ++){
			for (int filling = 0; filling < 3; filling++){
				for (int color = 0; color < 3; color++){
					for (int num = 0; num < 2; num++){
						cardsInDeck.add(new Card(shape,color,filling,num));
					}
				}
			}
		}
	}

	public void createMemoryDeck(){
		cardsInDeck = new ArrayList<Card>(cardsInDeck.subList(0, 33));
		cardsInDeck.add(new Card(3,0,0,0));
		cardsInDeck.add(new Card(4,0,0,0));
		cardsInDeck.add(new Card(5,0,0,0));
		for (int i = 0; i < 36; i++){
			Card newCard = cardsInDeck.get(i).clone();
			cardsInDeck.add(newCard);
		}
		inMemory = true;
	}

	/**
	 * Getter method that gets the number of cards left in the deck
	 *
	 * @return Number of cards left in the deck
	 */
	public int getCardLeft(){
		return numOfCards - dealt;
	}

	/**
	 * Makes the cards in the playing field into String form
	 *
	 * @return returnSTR String form of all the cards on the playing field
	 */
	public String toString(){
	    String returnSTR = "";
	    for (int i = 0; i < currentCards; i++){
            returnSTR += " " + cardsInPlayed.get(i).toString() + "\n";
        }
        return returnSTR;
    }

    /**
     * Shuffles the deck of cards
	 * From the Collections Oracle Java docs. Researched on how to shuffle an array list without having
	 * to go through all the hassles like in an arrays.
     */
	public void shuffle(){
		long seed = System.nanoTime();
		Collections.shuffle(cardsInDeck,new Random(seed));
	}
}