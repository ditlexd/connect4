package inputOutput;

import java.util.Scanner;

import Players.IPlayer;
import Players.Player;
import connect4.Board;

public class IO {
	
	public static int getPlayerInput(IPlayer player, Board board) {
		System.out.println(((Player) player).getName() + "'s turn!");
		System.out.println("What column would you like to drop the token into?");
		board.printBoard();
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		in.nextLine();
		return input;
	}

}
