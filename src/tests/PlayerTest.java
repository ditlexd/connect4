package tests;

import org.junit.Test;

import Players.Player;
import connect4.Board;
import connect4.Game;
import connect4.TokenColor;

public class PlayerTest {
	
	@Test
	public void doTurnTest() {
		Board board = new Board(5, 5);
		Game game = new Game();
		Player player = new Player(TokenColor.RED, "Ditlef");
		player.doTurn(game, board);
		player.doTurn(game, board);
		player.doTurn(game, board);
		
		board.printBoard();
	}

}
