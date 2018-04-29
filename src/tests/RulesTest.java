package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;
import inputOutput.IO;
import rules.Rules;

public class RulesTest {
	Game game = new Game(1);
	Board board = new Board(5, 5);
	Rules rules = new Rules(game, 4);
	
	
	@Test
	public void horizontalWin() {

		board.setElement(3, 4, new Token(TokenColor.RED));
		board.setElement(2, 4, new Token(TokenColor.RED));
		board.setElement(1, 4, new Token(TokenColor.RED));
		board.setElement(0, 4, new Token(TokenColor.RED));

		assertEquals(rules.hasWon(board, game), true);
	}

	@Test
	public void verticalTest() {
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(4, 3, new Token(TokenColor.RED));
		board.setElement(4, 2, new Token(TokenColor.RED));
		board.setElement(4, 1, new Token(TokenColor.RED));

		assertEquals(rules.hasWon(board, game), true);
	}
	
	@Test
	public void diagonalTest() {
	
		board.setElement(0, 4, new Token(TokenColor.RED));
		board.setElement(1, 3, new Token(TokenColor.RED));
		board.setElement(2, 2, new Token(TokenColor.RED));
		board.setElement(3, 1, new Token(TokenColor.RED));

	
		assertEquals(rules.hasWon(board, game), true);	
		board.clearBoard();
		
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 3, new Token(TokenColor.RED));
		board.setElement(2, 2, new Token(TokenColor.RED));
		board.setElement(1, 1, new Token(TokenColor.RED));

		assertEquals(rules.hasWon(board, game), true);
		board.clearBoard();
		
		board.setElement(4, 3, new Token(TokenColor.RED));
		board.setElement(3, 2, new Token(TokenColor.RED));
		board.setElement(2, 1, new Token(TokenColor.RED));
		board.setElement(1, 0, new Token(TokenColor.RED));
		
		assertEquals(rules.hasWon(board, game), true);
		board.clearBoard();
		
		
		//Three in a row, sohuld return false.
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 3, new Token(TokenColor.RED));
		board.setElement(2, 2, new Token(TokenColor.RED));
	
		assertEquals(rules.hasWon(board, game), false);
		board.clearBoard();
		
		
		//Three in a row, should return false
		board.setElement(0, 4, new Token(TokenColor.RED));
		board.setElement(1, 3, new Token(TokenColor.RED));
		board.setElement(2, 2, new Token(TokenColor.RED));

		assertEquals(rules.hasWon(board, game), false);	
		board.clearBoard();

	}
	
	@Test
	public void falsePositives() {
		
		// Three in a row but four on the same line. Should return false.
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(4, 3, new Token(TokenColor.RED));
		board.setElement(4, 2, new Token(TokenColor.RED));
		board.setElement(4, 1, new Token(TokenColor.YELLOW));
		board.setElement(4, 0, new Token(TokenColor.RED));
		
		assertEquals(rules.hasWon(board, game), false);
		board.clearBoard();
		
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 4, new Token(TokenColor.RED));
		board.setElement(2, 4, new Token(TokenColor.RED));
		board.setElement(1, 4, new Token(TokenColor.YELLOW));
		board.setElement(0, 4, new Token(TokenColor.RED));

		assertEquals(rules.hasWon(board, game), false);
		
	}
	
	@Test
	public void differentRules() {
		Rules rules = new Rules(game, 5);
		
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 4, new Token(TokenColor.RED));
		board.setElement(2, 4, new Token(TokenColor.RED));
		board.setElement(1, 4, new Token(TokenColor.RED));
		
		assertEquals(rules.hasWon(board, game), false);
		
		board.clearBoard();
		
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 4, new Token(TokenColor.RED));
		board.setElement(2, 4, new Token(TokenColor.RED));
		board.setElement(1, 4, new Token(TokenColor.RED));
		board.setElement(0, 4, new Token(TokenColor.RED));
		
		assertEquals(rules.hasWon(board, game), true);
		
	}

}
