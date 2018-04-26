package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;

public class GameTest {
	private Game game = new Game(2);
	Board board = game.getBoard();
	
	@Test
	public void hasWonTest() {
		game.startGame();

		assertEquals(game.isFinished(), true);
	}
	
	@Test
	public void insertTokenTest() {
		for (int i = 0; i < board.getHeight(); i++) {
			((Token) board.getElement(0, i)).setColor(TokenColor.RED);
		}
		
		for (int i = 0; i < board.getHeight(); i++) {
			TokenColor tc = ((Token) board.getElement(0, i)).getColor();
			assertEquals(tc, TokenColor.RED);
		}
	
	}
	
	@Test
	public void dropTokenTest() {
		
		for (int i = 0; i < board.getWidth(); i++) {
			((Token) board.getElement(i, i)).setColor(TokenColor.RED);
		}
		for (int i = 0; i < board.getHeight(); i++) {
			TokenColor tc = ((Token) board.getElement(i, i)).getColor();
			assertEquals(tc, TokenColor.RED);
		}
		
		
	}

}
