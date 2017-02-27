import java.util.Scanner;

/**
 * Runs the game of Crazy Eights
 * @author Varun Shah
 * @version 1/29/16
 */
public class CrazyEights {
	private int numPlayers;
	private Deck deck1;
	private Deck deck2;
	private Hand[] hand;
	private Card top;
	private Scanner input;
	private String[] record;
	private final int INITIAL_DEAL = 7;
	
	/**
	 * Non-default constructor that initializes 
	 * all the instance variables
	 */
	public CrazyEights(int numPlayers)	{
		this.numPlayers = numPlayers;
		record = new String[2];
		hand = new Hand[2];
		initialDeck();
		initialGame();
	}
	
	/**
	 * Initializes and combines the two deck of cards
	 */
	public void initialDeck()	{
		deck1 = new Deck();
		deck2 = new Deck();
		deck1.shuffle();
		deck2.shuffle();
		deck1 = deck1.combine(deck2);
		deck1.shuffle();
	}
	
	/**
	 * Initializes the game for each player
	 */
	public void initialGame()	{
		input = new Scanner(System.in);
		top = deck1.deal();
		for(int j=0;j<numPlayers;j++)	{
			System.out.println("Enter player" + (j+1) + "'s name:");
			hand[j] = new Hand(input.nextLine());
			for(int i=0;i<INITIAL_DEAL;i++)	{
				hand[j].addCard(deck1.deal());
			}
			record[j] = "";
		}
		System.out.println("\n------------------\n");	
	}
	
	/**
	 * Plays the game according to the rules of Crazy Eights
	 */
	public void playGame()	{
		String topName = "none";
		boolean isOver = false;
		int winner = -1;
		String finalMessage = "";
		int turn;
		while(isOver==false)	{
			for(turn=0;turn<numPlayers;turn++)	{
				if(isOver==false)	{
					if(deck1.isEmpty())	{
						System.out.println("\n+++++Deck is empty!" + 
					"Pass or Play Card.+++++\n");
					}
					if(top!=null)	{
						topName = top.toString();
						if(top.getValue()==8)	{
							topName += "(Nominated as:" + top.getChosenSuit() + ")";
						}
						System.out.println(hand[turn].getHandOwner() + "'s Hand");
						System.out.println("Top Card is " + topName);
						playMove(turn);
						if(Tie())	{
							isOver = true;
							winner = -1;
						}
						else	{
							switch(hand[turn].countCards())	{
								case 0: isOver = true;
									winner = turn;
									break;
								case 1: System.out.println("1 card left!");
							}
						}
						System.out.println("\n--------------\n");
					}
				}
			}
		}
		if(isOver)	{
			if(winner>-1)	{
				finalMessage = hand[winner].getHandOwner() + " wins!";
			}
			else	{
				finalMessage = "It's a tie!";
			}
			System.out.println(finalMessage);
		}
	}
	
	/**
	 * checks if the game is a tie
	 * @return isTie : True if the game is a tie and false if it is not
	 */
	public boolean Tie() {
		boolean isTie = false;
		for(int i=1;i<numPlayers;i++)	{
			if(!isTie)	{
				if(!record[i].equalsIgnoreCase("p") && record[i-1].equalsIgnoreCase("p"))	{
					isTie = true;
				}
			}
		}
		return isTie;
	}
	
	/**
	 * Plays one move for a player
	 * @param turn
	 */
	public void playMove(int turn)	{
		String command;
		int index = -1;
		Card dealt;
		Card drawn;
		boolean illegal = false;
		System.out.println("\n" + hand[turn].toString() + "\n");
		System.out.println("Choose a number, or 'd' for draw, or 'p' for pass.");
		command = input.nextLine();
		record[turn] = command;
		if(command.equalsIgnoreCase("d"))	{
			drawn = deck1.deal();
			if(drawn!=null)	{
				hand[turn].addCard(drawn);
			}
			else	{
				System.out.print("\n+++++No cards in deck!");
			}
			playMove(turn);
		}
		else if(command.equalsIgnoreCase("p"))	{
			if(deck1.isEmpty() || !hand[turn].playable(top))	{
				return;
			}
			else	{
				System.out.print("\n+++++Cannot pass!+++++\n");
				playMove(turn);
			}
		}
		else	{
			if(isNumber(command))	{
				index = Integer.parseInt(command);
			}
			else	{
				System.out.println("Invalid Command!");
				playMove(turn);
			}
			if(index>=0 && index<hand[turn].countCards())	{
				dealt = hand[turn].seekCard(index);
				if(top.getValue()!=8)	{
					if(top.getSuit().equals(dealt.getSuit()) || 
							top.getValue()==dealt.getValue() || 
							dealt.getValue()==8)	{
						if(dealt.getValue()==8)	{
							dealt.setSuit(chooseSuit());
						}
					}
					else	{
						illegal = true;
					}
				}
				else	{
					if(dealt.getValue()==8 || 
							top.getChosenSuit().equalsIgnoreCase(dealt.getSuit()))	{
						illegal = false;
					}
					else	{
						illegal = true;
					}
				}
				if(illegal)	{
					System.out.println("Illegal Move!");
					playMove(turn);
				}
				else	{
					top = dealt;
					hand[turn].removeCard(index);
				}
			}
			else	{
				System.out.println("Card does not exist!");
				playMove(turn);
			}
		}
		return;
	}
	
	/**
	 * Allows the user to choose a suit (when 8 is played)
	 * @return choose : chosen suit
	 */
	public String chooseSuit()	{
		String choose = "";
		System.out.println("Choose a suit: h) hearts, d) diamonds, s) spades, c) clubs");
		choose = input.nextLine();
		switch(choose.toLowerCase())	{
		case "h":
			choose = "Hearts";
			break;
		case "d":
			choose = "Diamonds";
			break;
		case "s":
			choose = "Spades";
			break;
		case "c":
			choose = "Clubs";
			break;
		default:
			System.out.println("Try again!");
			chooseSuit();
			break;
		}
		return choose;
	}
	
	/**
	 * Checks if the entered string is a number 
	 * @param num
	 * @return True if user input is a number and false if it is not
	 */
	public boolean isNumber(String num)	{
		for(int i=0;i<13;i++)	{
			if(Integer.parseInt(num)==i)	{
				return true;
			}
		}
		return false;
	}
}