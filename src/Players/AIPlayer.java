package Players;

import java.util.Random;

import connect4.Board;
import connect4.Game;
import connect4.TokenColor;

public class AIPlayer implements IPlayer {
	private TokenColor color;
	private String name;
	
	public AIPlayer(TokenColor color) {
		this.color = color;
		this.name = "AIPlayer";
	}

	@Override
	public void doTurn(Game game, Board board) {
		Random random = new Random();
		System.out.println(name + "'s turn");
		game.dropToken(random.nextInt(5), color);
		
	}

	@Override
	public TokenColor getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}

}
