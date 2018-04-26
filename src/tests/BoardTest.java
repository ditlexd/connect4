package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;
import inputOutput.IO;
import rules.Rules;

public class BoardTest {
	Game game = new Game(2);
	Board board = new Board(5,5);
	Rules rules = new Rules();

	@Test
	public <T> void boardSize() {
		for (int i = 1; i < 10; i++) {
			Board board = new Board(i, i);
			assertEquals(i, board.getWidth());
		}

	}

	@Test
	public void horizontalWin() {
		Board board = new Board(5, 5);

		for (int i = 0; i < board.getHeight() * board.getWidth(); ++i) {
			board.getCells().add(new Token(TokenColor.BLANK));
		}

		assertEquals(rules.winCondition(board), false);

		((Token) board.getElement(0, 2)).setColor(TokenColor.RED);
		((Token) board.getElement(0, 4)).setColor(TokenColor.RED);
		((Token) board.getElement(2, 1)).setColor(TokenColor.RED);

		 assertEquals(rules.winCondition(board), false);

		for (int i = 0; i < board.getHeight(); i++) {
			((Token) board.getElement(i, 0)).setColor(TokenColor.RED);
			((Token) board.getElement(0, i)).setColor(TokenColor.RED);
		}

		 assertEquals(rules.winCondition(board), true);
	}

	
	//Checks that the winCondition doesn't give false positives
	@Test
	public void notWin() {
		Board board = new Board(5, 5);

		for (int i = 0; i < board.getHeight() * board.getWidth(); ++i) {
			board.getCells().add(new Token(TokenColor.BLANK));
		}

		((Token) board.getElement(4, 0)).setColor(TokenColor.RED);
		assertEquals(rules.winCondition(board), false);
	}

	
	//Checks that win condition from top right to bottom left diagonally works.
	@Test
	public void rightDown() {
		Board board = new Board(5, 5);

		for (int i = 0; i < board.getHeight() * board.getWidth(); ++i) {
			board.getCells().add(new Token(TokenColor.BLANK));
		}
		
		((Token) board.getElement(0, 4)).setColor(TokenColor.RED);
		((Token) board.getElement(1, 3)).setColor(TokenColor.RED);
		((Token) board.getElement(2, 2)).setColor(TokenColor.RED);
		((Token) board.getElement(3, 1)).setColor(TokenColor.RED);
		((Token) board.getElement(4, 0)).setColor(TokenColor.RED);
		
		assertEquals(game.getRules().winCondition(board), true);
	}

	
	//Checks that win condition for diagonal left and down towards right works
	@Test
	public void leftDown() {
		Board board = new Board(5, 5);

		for (int i = 0; i < board.getHeight() * board.getWidth(); ++i) {
			board.getCells().add(new Token(TokenColor.BLANK));
		}

		((Token) board.getElement(3, 4)).setColor(TokenColor.RED);
		((Token) board.getElement(2, 3)).setColor(TokenColor.RED);
		((Token) board.getElement(1, 2)).setColor(TokenColor.RED);
		((Token) board.getElement(0, 1)).setColor(TokenColor.RED);

		((Token) board.getElement(4, 4)).setColor(TokenColor.RED);
		((Token) board.getElement(3, 3)).setColor(TokenColor.RED);
		((Token) board.getElement(2, 2)).setColor(TokenColor.RED);
		((Token) board.getElement(1, 1)).setColor(TokenColor.RED);
		((Token) board.getElement(0, 0)).setColor(TokenColor.RED);

		((Token) board.getElement(4, 3)).setColor(TokenColor.RED);
		((Token) board.getElement(3, 2)).setColor(TokenColor.RED);
		((Token) board.getElement(2, 1)).setColor(TokenColor.RED);
		((Token) board.getElement(1, 0)).setColor(TokenColor.RED);


		assertEquals(game.getRules().winCondition(board), true);
	}

}
