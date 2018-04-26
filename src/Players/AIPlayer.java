package Players;

import java.util.Random;

import connect4.Board;
import connect4.Game;
import connect4.TokenColor;

public class AIPlayer implements IPlayer {
	private TokenColor color;
	private String name;

	public AIPlayer(TokenColor color, String name) {
		this.color = color;
		this.name = name;
	}

	@Override
	public boolean doTurn(Game game, Board board) {
		Random random = new Random();
		System.out.println(name + "'s turn");
		boolean dropped = false;

		while (!dropped) {
			int target= random.nextInt(board.getWidth() +1);
			
			if (target > 0 && target <= board.getWidth()) {
				 dropped = game.dropToken(target, color);
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
