package Players;

import java.util.Random;

import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;
import inputOutput.IO;

public class AIPlayer implements IPlayer {
	private TokenColor color;
	private String name;

	public AIPlayer(TokenColor color) {
		this.color = color;
		this.name = name;
	}

	/* 
	 * Very stupid AI. Picks a random number between 1 and board width. 
	 * In the future I can hopefully uprgade this to return a smart move.
	 */
	@Override
	public boolean doTurn(Board board) {
		Random random = new Random();
		boolean dropped = false;

		while (!dropped) {
			int target= random.nextInt(board.getWidth() +1);
			
			if (target > 0 && target <= board.getWidth()) {
				 dropped = board.dropToken(target, new Token(color));
			}
		}
		return dropped;
	}

	@Override
	public TokenColor getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

}
