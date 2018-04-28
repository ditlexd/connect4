package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;
import inputOutput.IO;

public class GameTest {
	private Game game = new Game(2);
	Board board = new Board(5, 5);

	// @Test
	public void hasWonTest() {
		game.startGame();

		assertEquals(game.isFinished(), true);
	}

	@Test
	public void insertTokenTest() {

		for (int i = 0; i < board.getHeight(); i++) {
			board.setElement(0, i, new Token(TokenColor.RED));

		}

		for (int i = 0; i < board.getHeight(); i++) {
			TokenColor tc = ((Token) board.getElement(0, i)).getColor();
			assertEquals(tc, TokenColor.RED);
		}

	}

	@Test
	public void horizontalWin() {
		board.initializeBoard();

		// board2.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 4, new Token(TokenColor.RED));
		board.setElement(2, 4, new Token(TokenColor.RED));
		board.setElement(1, 4, new Token(TokenColor.RED));
		board.setElement(0, 4, new Token(TokenColor.RED));

		assertEquals(winCondition(board), true);
		assertEquals(checkHorizontalAlt(board, 4), true);
	}

	@Test
	public void verticalTest() {
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(4, 3, new Token(TokenColor.RED));
		board.setElement(4, 2, new Token(TokenColor.RED));
		board.setElement(4, 1, new Token(TokenColor.RED));

		assertEquals(winCondition(board), true);
	}
	
	@Test
	public void diagonalTest() {
	
		board.setElement(0, 4, new Token(TokenColor.RED));
		board.setElement(1, 3, new Token(TokenColor.RED));
		board.setElement(2, 2, new Token(TokenColor.RED));
		board.setElement(3, 1, new Token(TokenColor.RED));

		assertEquals(winCondition(board), true);	
		board.clearBoard();
		
		board.setElement(4, 4, new Token(TokenColor.RED));
		board.setElement(3, 3, new Token(TokenColor.RED));
		board.setElement(2, 2, new Token(TokenColor.RED));
		board.setElement(1, 1, new Token(TokenColor.RED));

		IO.printNull(board);
		assertEquals(winCondition(board), true);
		board.clearBoard();
		
		board.setElement(4, 3, new Token(TokenColor.RED));
		board.setElement(3, 2, new Token(TokenColor.RED));
		board.setElement(2, 1, new Token(TokenColor.RED));
		board.setElement(1, 0, new Token(TokenColor.RED));
		
		assertEquals(winCondition(board), true);

	}

	private boolean winCondition(Board board) {
		final int WIN_CONDITION = 4;
		if (checkHorizontal(board, WIN_CONDITION) ||
			checkVertical(board, WIN_CONDITION) ||
			checkDiagonal(board, WIN_CONDITION))
			return true;
		
		return false;
	}
	
	private boolean checkHorizontal(Board board, int winCondition) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();
		

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				// Token player = ((Token) board.getElement(column, row));
				if (board.getElement(column, row) != null) {
					Token player = ((Token) board.getElement(column, row));

					if (column + 3 < WIDTH) {
						int counter = 1;
						for (int i = column + 1; i < WIDTH; i++) {
							if (board.getElement(i, row) == null)
								break;
							if (player.getColor().equals(((Token) board.getElement(i, row)).getColor()))
								counter++;
							if (counter == winCondition)
								return true;
						}
					}
							
				} else 
					continue;
			}
		}
		
		return false;
	}
	
	private boolean checkVertical(Board board, int winCondition) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				// Token player = ((Token) board.getElement(column, row));
				if (board.getElement(column, row) != null) {
					Token player = ((Token) board.getElement(column, row));

					if (row + 3 < HEIGHT) {
						int counter = 1;
						for (int i = row + 1; i < HEIGHT; i++) {
							if (board.getElement(column, i) == null)
								break;
							if (player.getColor().equals(((Token) board.getElement(column, row)).getColor())) {
								counter++;
							}
							if (counter == 4)
								return true;
						}
					}
				} else {
					continue;
				}
			}
		}
		return false;
	}
	
	private <T> boolean checkDiagonal(Board board, int winCondition) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				// Token player = ((Token) board.getElement(column, row));
				if (board.getElement(column, row) != null) {
					T token = ((T) board.getElement(column, row));

					if (column + 3 < WIDTH && row -3 >= 0) {
						int counter = 1;
						for (int i = 0; i < HEIGHT; i++) {
							if (board.getElement(column+i, row-i) == null) {
								break;
							}
							if (game.compareTokens(token, board.getElement(column+i, row-i))) {
								counter++;
							}
							if (counter == winCondition) {
								return true;
							}
						}
					}
					
					if (column - 3 >= 0 && row - 3 >= 0) {
						int counter = 1;
						for (int i = 0; i < WIDTH; i++) {
							if (board.getElement(column-i, row-i) == null)
								break;
							if (game.compareTokens(token, board.getElement(column-i, row-i)))
								counter++;
							if (counter == winCondition) 
								return true;
						}
					} 
				} else {
					continue;
				}
			}
		}
		return false;
	}
	
	private <T> boolean checkHorizontalAlt(Board board, int winCondition) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();
		

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				// Token player = ((Token) board.getElement(column, row));
				if (board.getElement(column, row) != null) {
					T token = (T) board.getElement(column, row);

					if (column + 3 < WIDTH) {
						int counter = 1;
						for (int i = column + 1; i < WIDTH; i++) {
							if (board.getElement(i, row) == null)
								break;
							
							if (game.compareTokens(token, board.getElement(i, row)))
								counter++;
							
				//			if (player.getColor().equals(((Token) board.getElement(i, row)).getColor()))
					//			counter++;
							if (counter == winCondition)
								return true;
						}
					}
							
				} else 
					continue;
			}
		}
		
		return false;
	}
	
}
