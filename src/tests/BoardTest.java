package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import connect4.Board;
import connect4.Token;
import connect4.TokenColor;

public class BoardTest {
	
	@Test
	public <T> void boardSize() {
		for (int i = 1; i < 1000; i++) {
			Board board = new Board(i, i, new Token(TokenColor.BLANK)); 
			assertEquals(i, board.getWidth());
		}
			
	}
	
	@Test
	public void printBoardTest() {
		Board board = new Board(6, 7, new Token(TokenColor.BLANK));
		board.insertToken(1, 1, TokenColor.RED);
		board.printBoard();
		System.out.println("Printed");
	}
	
	

}
