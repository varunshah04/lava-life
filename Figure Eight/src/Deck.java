import java.util.Random;

/**
 * Represents a collection of cards
 * @author Varun Shah
 * @version 1/29/16
 */
public class Deck	{
	private Card[] cardDeck = new Card[52];
	private int alreadyDealt;
	private String[] Suits = {"SPADES","HEARTS","DIAMONDS","CLUBS"};
	private int lenDeck = 52;
	private int lenSuit = 13;
	
	/**
	 * Default constructor
	 */
	public Deck()	{
		alreadyDealt = 0;
		cardDeck = new Card[lenDeck];
		initializeArray();
	}
	
	/**
	 * Non-default constructor
	 * @param cardDeck
	 */
	public Deck(Card[] cardDeck)	{
		this.cardDeck = cardDeck;
		lenDeck = cardDeck.length;
		initializeArray();
	}
	
	/**
	 * Initializes array and sends it to the constructor
	 */
	public void initializeArray()	{
		int count = 0;
		for(int i=0;i<Suits.length;i++)	{
			for(int j=0;j<lenSuit;j++)	{
				Card presentCard = new Card(Suits[i],j+1);
				cardDeck[count] = presentCard;
				count++;
			}
		}
	}
	
	/**
	 * returns the entire deck as a printable string
	 * @return deckOutput
	 */
	public String toString() 	{
		String deckOutput = "";
		for(Card card:cardDeck)	{
			deckOutput += card.toString() + "\n";
		}
		return deckOutput;
	}
	
	/**
	 * Checks if deck is empty
	 * @return alreadyDealt>=lenDeck: True if empty and false if not
	 */
	public boolean isEmpty() {
		return alreadyDealt >= lenDeck;
	}
	
	/**
	 * returns the next card to dealt
	 * @return nextCardDealt
	 */
	public Card deal()	{
		alreadyDealt++;
		if(alreadyDealt!=lenDeck)	{
			Card nextCardDealt = cardDeck[alreadyDealt];
			return nextCardDealt;
		}
		return null;
	}
	
	/**
	 * randomizes the deck
	 */
	public void shuffle()	{
		Random rand = new Random();
		for(int i=0;i<lenDeck;i++)	{
			int randNum = rand.nextInt(lenDeck-i)+i;
			Card swap = cardDeck[i];
			cardDeck[i] = cardDeck[randNum];
			cardDeck[randNum] = swap;
		}
	}
	
	/**
	 * Returns the number of cards in the deck
	 * @return lenDeck-alreadyDealt
	 */
	public int numCards()	{
		return lenDeck - alreadyDealt;
	}
	
	/**
	 * Takes two decks and combines them into a new one
	 * @param secDeck
	 * @return the combined deck
	 */
	public Deck combine(Deck secDeck)	{
		Card[] anotherDeck = new Card[lenDeck+secDeck.numCards()];
		int i;
		for(i=alreadyDealt;i<lenDeck;i++)	{
			anotherDeck[i] = cardDeck[i];
		}
		Card card = secDeck.deal();
		while(card!=null)	{
			anotherDeck[i] = card;
			card = secDeck.deal();
			i++;
		}
		return new Deck(anotherDeck);
	}
}