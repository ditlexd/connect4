package inputOutput;

import java.util.Scanner;

import Players.IPlayer;
import Players.Player;
import connect4.Board;
import connect4.Token;
import connect4.TokenColor;

public class IO {
	
	
	/*
	 * Gets players input.
	 * Only legal input is an int between 1 and board width. 
	 */
	public static int getPlayerInput(IPlayer player, Board board) throws IllegalArgumentException {
	//	playersTurn(player);
		System.out.println("What column would you like to drop the token into?");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		if (input > board.getWidth() || input < 1) {
			throw new IllegalArgumentException();
		}
		System.out.println();
		return input;
	}
	
	public static void playersTurn(IPlayer player) {
		System.out.println(player.getColor() + "'s turn!");
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
	
	public static void printBoard(Board board) {
		int index = 0;
		for (int j = 0; j < board.getHeight(); j++) {
			for (int i = 0; i < board.getWidth(); i++) {
				if (((Token) board.getCells().get(index)) != null) {
					System.out.print("| " + ((Token) (board.getCells()).get(index)).getSymbol() + " ");
				} else {
					System.out.print("|   ");
				}
				index++;
			}
			System.out.print("|\n");
		}
		
		for (int i = 0; i < board.getWidth(); i++) {
			System.out.print("  " + (i+1) + " ");
		}
		System.out.println();
	}
	
	public static void illegalMove(Board board) {
		System.out.println("Not a legal move! Please enter a number between 1 - " + board.getWidth());
	}

}
