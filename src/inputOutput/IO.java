package inputOutput;

import java.util.Scanner;

import Players.IPlayer;
import Players.Player;
import connect4.Board;
import connect4.Token;
import connect4.TokenColor;

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
		System.out.println("Hello");
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
		System.out.println();
	}
	
	public static void winMessage(IPlayer winner, Board board) {
		if (winner.getColor().equals(TokenColor.RED)) {
			System.out.println("Red won!");
			printBoard(board);
		} else if (winner.getColor().equals(TokenColor.YELLOW)) {
			System.out.println("Yellow won!");
			printBoard(board);
		}
	}

}
