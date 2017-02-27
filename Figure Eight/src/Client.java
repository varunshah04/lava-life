import java.util.Scanner;

/**
 * Plays the game
 * @author Varun Shah
 * @version 1/29/16
 *
 */
public class Client {
	public static void main(String[] args)	{
		Scanner numOfPlayers = new Scanner(System.in);
		System.out.println("\n=====Start Game!======\n");
		System.out.println("Instructions:\n"
				+ "1) Enter the serial number of the card to play it.\n"
				+ "2) Enter 'd' to draw a card from the deck.\n");
		System.out.print("Enter the number of players playing: ");
		int numPlayers = Integer.parseInt(numOfPlayers.nextLine());
		System.out.println("");
		CrazyEights figureEight = new CrazyEights(numPlayers);
		figureEight.playGame();
		numOfPlayers.close();
	}
}