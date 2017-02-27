import java.util.Vector;

/**
 * Represents the group of cards that the player is holding
 * @author Varun Shah
 * @version 1/29/16
 */
public class Hand	{
	private Vector<Card> handCards;
	private String handOwner;
	
	/**
	 * A non-default constructor that takes a String identifying 
	 * the player who owns this hand as a parameter.
	 * @param handOwner
	 */
	public Hand(String handOwner)	{
		this.handOwner = handOwner;
		handCards = new Vector<Card>();
	}
	
	/**
	 * adds a card from the deck to a hand and returns false if there
	 * are no cards to add
	 * @param cardToAdd
	 * @return True if a card has been added and false if the deck is
	 * 			out of cards
	 */
	public boolean addCard(Card cardToAdd)	{
		if(cardToAdd!=null)	{
			handCards.add(cardToAdd);
			return true;
		}
		return false;
	}
	
	/**
	 * removes a card from a hand and returns it
	 * @param cardToRemove
	 * @return remove: the card that was removed
	 */
	public Card removeCard(int cardToRemove)	{
		if(cardToRemove<0 || cardToRemove>=handCards.size())	{
			return null;
		}
		Card remove = handCards.get(cardToRemove);
		handCards.remove(cardToRemove);
		return remove;
	}
	
	/**
	 * Returns the card specified in the parameter but does not
	 * remove it from the player's hand.
	 * @param cardToSeek
	 * @return seekPosition: the position of the card to seek
	 */
	public Card seekCard(int cardToSeek)	{
		Card seekPosition = handCards.get(cardToSeek);
		return seekPosition;
	}
	
	/**
	 * returns the hand as a printable string
	 * @return output: the hand 
	 */
	public String toString()	{
		String output = "";
		for(int i=0;i<handCards.size();i++)	{
			output += i + ")" + handCards.get(i).toString() + "\n";
		}
		return output;
	}
	
	/**
	 * Gets the name of the player who owns the hand
	 * @return handOwner
	 */
	public String getHandOwner() {
		return handOwner;
	}
	
	/**
	 * Sets the name of the player who owns the hand
	 * @param handOwner
	 */
	public void setHandOwner(String handOwner)	{
		this.handOwner = handOwner;
	}
	
	/**
	 * Returns the number of cards in the hand
	 * @return handCards.size() : the number of cards in the hand
	 */
	public int countCards()	{
		return handCards.size();
	}
	
	/**
	 * Checks if the hand has a playable card
	 * @param card
	 * @return True if the card is of the same suit and false if it is not
	 */
	public boolean playable(Card card)	{
		boolean sameSuit = false;
		for(int i=0;i<countCards();i++)	{
			if(sameSuit==false)	{
				if(handCards.get(i).getChosenSuit().equals(card.getChosenSuit()) ||
						handCards.get(i).getValue()==card.getValue() ||
						handCards.get(i).getValue()==8)	{
					sameSuit = true;
				}
			}
		}
		return sameSuit;
	}
}