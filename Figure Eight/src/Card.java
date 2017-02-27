/**
 * Class representing a single playing card
 * @author Varun Shah
 * @version 1/29/16
 *
 */
public class Card	{
	private String suit;
	private int value;
	private String[] values = {"Ace","2","3","4","5","6","7",
			"8","9","10","Jack","Queen","King"};
	private String chosenSuit;
	
	/**
	 * Non-default constructor
	 * @param suit
	 * @param value
	 */
	public Card(String suit, int value)	{
		this.suit = suit;
		this.value = value;
		chosenSuit = suit;
	}
	
	/**
	 * returns the card as a printable string
	 * @return output
	 */
	public String toString()	{
		String output = values[value-1] + " of " + suit;
		return output;
	}
	
	/**	
	 * Gets the value
	 * @return value
	 */
	public int getValue()	{
		return value;
	}
	
	/**
	 * Gets the suit
	 * @return suit
	 */
	public String getSuit()	{
		return suit;
	}
	
	/**
	 * Gets the chosen suit
	 * @param chosenString
	 */
	public String getChosenSuit()	{
		return chosenSuit;
	}
	
	/**
	 * Sets the chosen suit
	 * @param chosenSuit
	 */
	public void setSuit(String chosenSuit)	{
		this.chosenSuit = chosenSuit;
	}
}