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

/*
 * Tests for Board class. 
 * Rules class is also tested here.
 */
public class BoardTest {
	Game game = new Game(2);
	Board board = new Board(5,5);
	Rules rules = new Rules(game, 4);

	@Test
	public <T> void boardSize() {
		for (int i = 1; i < 10; i++) {
			Board board = new Board(i, i);
			assertEquals(i, board.getWidth());
		}

	}


	@Test
	public void initializeBoard() {
		int width = 5;
		int height = 5;
		
		Board board2 = new Board(width, height);
		
		board2.initializeBoard();
		
		for (int i = 0; i < (width * height); i++) {
			assertEquals(board2.getCells().get(i), null);
		}
	}
	
	@Test
	public void printBoardTest() {
		
		board.setElement(4, 3, new Token(TokenColor.RED));
		board.setElement(3, 2, new Token(TokenColor.RED));
		board.setElement(2, 1, new Token(TokenColor.RED));
		board.setElement(1, 0, new Token(TokenColor.RED));
		
		IO.printBoard(board);
		
		board.dropToken(1, new Token(TokenColor.YELLOW));
		board.dropToken(3, new Token(TokenColor.YELLOW));
		board.dropToken(1, new Token(TokenColor.YELLOW));
		board.dropToken(5, new Token(TokenColor.YELLOW));
		
		IO.printBoard(board);	
	}
	
	@Test
	public void dropToken() {
		board.dropToken(1, new Token(TokenColor.YELLOW));
		board.dropToken(1, new Token(TokenColor.YELLOW));
		board.dropToken(1, new Token(TokenColor.YELLOW));
		board.dropToken(1, new Token(TokenColor.YELLOW));
		
		for (int i = 4; i < 0; i--) {
			Token tk = ((Token) board.getElement(0, i));
			assertEquals(tk.getColor(), TokenColor.YELLOW);
		}
		
		board.dropToken(2, new Token(TokenColor.YELLOW));
		board.dropToken(3, new Token(TokenColor.YELLOW));
		board.dropToken(4, new Token(TokenColor.YELLOW));
		board.dropToken(5, new Token(TokenColor.YELLOW));
		
		for (int i = 0; i < 4; i++) {
			Token tk = ((Token) board.getElement(i, 4));
			assertEquals(tk.getColor(), TokenColor.YELLOW);
		}
	}
	
	

}
