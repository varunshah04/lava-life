import java.util.ArrayList;

/**
 * SET game representing a valid SET
 *
 * @author Lam Ngo and James Murphy
 */
public class Set {
	private ArrayList<Card> cardsInSet = new ArrayList<Card>(3);

	/**
	 * Default constructor
	 *
	 * @param cardOne First card in the set
	 * @param cardTwo Second card in the set
	 * @param cardThree Third card in the set
	 */
	public Set(Card cardOne,Card cardTwo,Card cardThree){
		cardsInSet.add(cardOne);
		cardsInSet.add(cardTwo);
		cardsInSet.add(cardThree);
	}

	/**
	 * Select all three cards in set OBJECT
	 */
	public void select(){
		for (int i = 0; i < cardsInSet.size();i ++){
			cardsInSet.get(i).setSelected();
		}
	}

	/**
	 * Select first two cards in set object
	 *
	 * @param myDeck PlayingDeck object
	 */
	public void partialSelect(PlayingDeck myDeck){
		for (int i = 0; i < cardsInSet.size()-1;i ++){
			myDeck.hint(cardsInSet.get(i));
		}
	}

	/**
	 * Deslect every card in set object.
	 */
	public void deselect(){
		for (int i = 0; i < cardsInSet.size();i ++){
			cardsInSet.get(i).deSelect();
		}
	}
}