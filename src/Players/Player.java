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

	public Player(TokenColor color, String name) {
		this.color = color;
		this.name = name;
	}

	@Override
	public Token doTurn(Game game, Board board) {
		int targetColumn = IO.getPlayerInput(this, board);
		boolean dropped = false;
		Token token = null;

		while (!dropped) {
			try {
				token = game.dropToken(targetColumn, color);
				dropped = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Can't drop there, not enough room.");
			}
		}
		return token;
	}

	@Override
	public TokenColor getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

}
