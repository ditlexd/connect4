package Players;

import java.util.Scanner;

import connect4.Board;
import connect4.Game;
import connect4.TokenColor;

public class Player implements IPlayer {
	private TokenColor color; 
	private String name;
	
	public Player(TokenColor color, String name) {
		this.color = color;
		this.name = name;
	}

	@Override
	public void doTurn(Game game, Board board) {
		int targetColumn = getInput(board);
		
		board.dropToken(targetColumn, color);
		
	}
	
	private int getInput(Board board) {
		System.out.println("What column would you like to drop the token into?");
		board.printBoard();
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		in.nextLine();
		return input;
	}

	@Override
	public TokenColor getColor() {
		return color;
	}

}
