package Players;

import java.util.Scanner;

import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;
import inputOutput.IO;

public class Player implements IPlayer {
	private TokenColor color;
	private String name;

	public Player(TokenColor color) {
		this.color = color;
	}

	//Returns true when a token has been dropped in taret column in Board. 
	public boolean doTurn(Board board) throws IllegalArgumentException {
		boolean dropped = false;

		while (!dropped) {
			int targetColumn = IO.getPlayerInput(this, board);
			if (board.dropToken(targetColumn, new Token(color))) {
				dropped = true;
			}
		}
		return dropped;
	}

	public TokenColor getColor() {
		return color;
	}


}
