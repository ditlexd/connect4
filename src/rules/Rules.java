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
 * 
 * Class is diveded into methods that check up, down and diagonally. They are similar in nature. 
 */
public class Rules<T> {
	private int winCondition = 4;
	int winLength = winCondition - 1; // How much space is needed to check to find a winning combination.

	public Rules(Game game, int winCondition) {
		if (winCondition > 1)
			this.winCondition = winCondition;
		else
			this.winCondition = 2;  	//We need at least two tokens in a row to win...

		this.winLength = winCondition - 1;
	}

	public boolean hasWon(Board board, Game game) {
		if (checkHorizontal(board, winCondition, game) || checkVertical(board, winCondition, game)
				|| checkDiagonal(board, winCondition, game))
			return true;

		return false;
	}

	public <T> boolean checkHorizontal(Board board, int winCondition, Game game) {
		int WIDTH = board.getWidth();
		int HEIGHT = board.getHeight();

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, top to bottom
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				if (board.getElement(column, row) != null) { // Checks if current place on grid is null
					T token = (T) board.getElement(column, row);

					if (column + winLength < WIDTH) { // If there is no room for a winning combination, continue.
						int counter = 1; // We already have the first token, we need to find three more.
						for (int i = column + 1; i < WIDTH; i++) {
							if (board.getElement(i, row) == null) {
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

		for (int row = 0; row < HEIGHT; row++) { // iterate rows, top to bottom.
			for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
				if (board.getElement(column, row) != null) {
					T token = (T) board.getElement(column, row);

					if (row + winLength < HEIGHT) {
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
					if (column + winLength < WIDTH && row - winLength >= 0) { // Check up and to the right
						int counter = 1;
						for (int i = 1; i < HEIGHT; i++) {
							try {
								if (board.getElement(column + i, row - i) == null) { //Not a matching token. Break.
									counter = 1;
									break;
								}
								if (game.compareTokens(token, board.getElement(column + i, row - i)))
									counter++; //Counts the number of matching tokens in a row
								else
									break;
								if (counter == winCondition)
									return true;
							} catch (ArrayIndexOutOfBoundsException e) {
							}
						}
					}

					if (column - winLength >= 0 && row - winLength >= 0) { // Check up and to the left
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
							} catch (ArrayIndexOutOfBoundsException e) {
							}
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
