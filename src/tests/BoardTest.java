package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import connect4.Board;
import connect4.Token;
import connect4.TokenColor;

public class BoardTest {
	
	@Test
	public <T> void boardSize() {
		for (int i = 1; i < 10; i++) {
			Board board = new Board(i, i); 
			assertEquals(i, board.getWidth());
		}
			
	}
	
	@Test
	public void printBoardTest() {
		Board board = new Board(5, 5);
		board.insertToken(3, 3, TokenColor.RED);
		board.insertToken(0, 4, TokenColor.RED);
		board.insertToken(4, 4, TokenColor.RED);
	//	board.printBoard();
		System.out.println("Printed");
	}
	
	@Test
	public void dropTest() {
		Board board = new Board(5, 5);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				board.dropToken(j, TokenColor.YELLOW);
			}
		}
		board.printBoard();
	}
	
	

}
