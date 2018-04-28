package rules;

import java.util.ArrayList;
import java.util.List;

import Players.IPlayer;
import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;

/*
 * The class that decides if a Board has a winning combination on it. 
 * It recieves an int that decides how many tokens in a row is needed to win. 
 * It has a variable for Game, because it uses Game to check if two tokens are the same color. 
 * Rules is generic so that it's easier to use in other peoples games as well. 
 */
public class Rules<T> {
	final int winCondition;
	Game game;

	public Rules(Game game, int winCondition) {
		this.game = game;
		this.winCondition = winCondition;
	}

	public boolean hasWon(Board board, Game game) {
		if (checkHorizontal(board, winCondition, game) || 
			checkVertical(board, winCondition, game)||
			checkDiagonal(board, winCondition, game))
			return true;

		return false;
	}

	
	
	public <T> boolean checkHorizontal(Board board, int winCondition, Game game) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				if (board.getElement(column, row) != null) {
					T token = (T) board.getElement(column, row);

					if (column + 3 < WIDTH) {
						int counter = 1;
						for (int i = column + 1; i < WIDTH; i++) {
							if (board.getElement(i, row) == null) {
								counter = 1;
								break;
							}
							if (game.compareTokens(token, board.getElement(i, row)))
								counter++;
							else 
								break;

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

	public <T> boolean checkVertical(Board board, int winCondition, Game game) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				if (board.getElement(column, row) != null) {
					T token = (T) board.getElement(column, row);

					if (row + 3 < HEIGHT) {
						int counter = 1;
						for (int i = row + 1; i < HEIGHT; i++) {
							if (board.getElement(column, i) == null) {
								counter = 1;
								break;
							}
							if (game.compareTokens(token, board.getElement(column, i)))
								counter++;
							else 
								break;
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

	public <T> boolean checkDiagonal(Board board, int winCondition, Game game) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, top to bottom
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				if (board.getElement(column, row) != null) {
					T token = ((T) board.getElement(column, row));

					if (column + 3 < WIDTH && row - 3 >= 0) { // Check up and to the right
						int counter = 1;
						for (int i = 1; i < HEIGHT; i++) {
							try {
								if (board.getElement(column + i, row - i) == null) {
									counter = 1;
									break;
								}
								if (game.compareTokens(token, board.getElement(column + i, row - i)))
									counter++;
								else 
									break;
								if (counter == winCondition) 
									return true;
							} catch (ArrayIndexOutOfBoundsException e) {}
						}
					}

					if (column - 3 >= 0 && row - 3 >= 0) { // Check up and to the left
						int counter = 1;
						for (int i = 1; i < WIDTH; i++) {
							try {
								if (board.getElement(column - i, row - i) == null) {
									counter = 1;
									break;
								}
								if (game.compareTokens(token, board.getElement(column - i, row - i)))
									counter++;
								else 
									break;
								if (counter == winCondition) 
									return true;
							} catch (ArrayIndexOutOfBoundsException e) {} 
						}
					}
				} else {
					continue;
				}
			}
		}
		return false;
	}

}
