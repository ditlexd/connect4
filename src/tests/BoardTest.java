package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import connect4.Board;
import connect4.Token;
import connect4.TokenColor;
import rules.Rules;

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
		// board.printBoard();
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

	@Test
	public void getHorizontalNeighbours() {
		Board board = new Board(6, 5);
		for (int i = 0; i < board.getWidth(); i++) {
			board.dropToken(i, TokenColor.RED);
		}
	//	board.printBoard();

		List<Token> row = board.getHorizontalNeighbours(board.getToken(2, 4));
		
		for (int i = 0; i < row.size(); i++) {
	//		System.out.println(row.get(i).getSymbol());
		}

		if (Rules.straightWinCondition(row).getSymbol().equals("R")) {
	//		System.out.println("Red won!");
		} else if (Rules.straightWinCondition(row).getSymbol().equals("Y")) {
	//		System.out.println("Yellow has won!");
		}
	}
	
	@Test
	public void getVerticallNeighbours() {
		Board board = new Board(6, 5);
		for (int i = 0; i < board.getHeight(); i++) {
			board.dropToken(0, TokenColor.YELLOW);
		}
		board.printBoard();

		List<Token> row = board.getVerticalNeighbours(board.getToken(0, 0));
		
		for (int i = 0; i < row.size(); i++) {
			System.out.println(row.get(i).getSymbol());
		}

		if (Rules.straightWinCondition(row).getSymbol().equals("R")) {
			System.out.println("Red won!");
		} else if (Rules.straightWinCondition(row).getSymbol().equals("Y")) {
			System.out.println("Yellow has won!");
		}
	}

}
