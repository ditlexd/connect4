package inputOutput;

import java.util.Scanner;

import Players.IPlayer;
import Players.Player;
import connect4.Board;
import connect4.Token;

public class IO {
	
	public static int getPlayerInput(IPlayer player, Board board) throws IllegalArgumentException {
		System.out.println(((Player) player).getName() + "'s turn!");
		System.out.println("What column would you like to drop the token into?");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		System.out.println();
		return input;
	}
	
	public static void printBoard(Board board) {
		int index = 0;
		for (int j = 0; j < board.getHeight(); j++) {
			for (int i = 0; i < board.getWidth(); i++) {
				System.out.print("| " + ((Token) (board.getCells()).get(index)).getSymbol() + " ");
				index++;
			}
			System.out.print("|\n");
		}
		
		for (int i = 0; i < board.getWidth(); i++) {
			System.out.print("  " + (i+1) + " ");
		}
	}

}
