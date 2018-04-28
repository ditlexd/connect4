package rules;

import java.util.ArrayList;
import java.util.List;

import Players.IPlayer;
import connect4.Board;
import connect4.Game;
import connect4.Token;
import connect4.TokenColor;

	/*
	 * The class that decides if a board contains a winning combination. 
	 * As of now only one winning combination is possible. It's should be doable to modify the winning algorith to be more dynamic,
	 * but the focus has been on making a connect 4 game, not on potentially expanding the rules. 
	 * 
	 * The list is for debugging purposes, it is benificial to know what tokens are responsible for triggering the win-mechanism.
	 * 
	 * The commented out print statements are also for debugging purposes. 
	 */
public class Rules<T> {
	private List<Token> list = new ArrayList<Token>();
	final int winCondition;
	Game game;
	
	public Rules (Game game, int winCondition) {
		this.game = game;
		this.winCondition = winCondition;
	}

	
	public List<Token> getList() {
		return this.list;
	}
	
	public <T> boolean winCondition(Board<T> board) {
		int HEIGHT = board.getHeight();
		int WIDTH = board.getWidth();
		
		
		 for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
		        for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
		       // 	Token player = ((Token) board.getElement(column, row));
		            if (board.getElement(column, row) == null) {     
		                continue; // don't check empty slots
		            }
		            
		            Token player = ((Token) board.getElement(column, row));
		            
		            if (column + 3 < WIDTH &&
		                player.getColor().equals(((Token) board.getElement(column + 1, row)).getColor()) && // look right
		                player.getColor().equals(((Token) board.getElement(column + 2, row)).getColor()) &&
		                player.getColor().equals(((Token) board.getElement(column + 3, row)).getColor())) {
		            	list.add((Token) board.getElement(column, row));
		            	list.add((Token)board.getElement(column +1, row));
		            	list.add((Token)board.getElement(column +2, row));
		            	list.add((Token)board.getElement(column +3, row));
		          //  	System.out.println("Look right rule applied"); // These print statements can be used when it's difficult
		                return true;									//to see where the winning combination is.
		            }
		            if (row + 3 < HEIGHT) {
		                if (player.getColor().equals(((Token) board.getElement(column, row +1)).getColor()) && // look up
		                	player.getColor().equals(((Token) board.getElement(column, row +2)).getColor()) &&
		                	player.getColor().equals(((Token) board.getElement(column, row +3)).getColor())) {
		                	list.add((Token) board.getElement(column, row));
		                	list.add((Token) board.getElement(column, row +1));
		                	list.add((Token) board.getElement(column, row +2));
		                	list.add((Token) board.getElement(column, row +3));
		              //  	System.out.println("Look up rule applied");
		                    return true;
		                }
		                }
		                if ((column + 3 < WIDTH && row -3 >= 0) &&
		                		player.getColor().equals(((Token) board.getElement(column +1, row -1)).getColor()) && // look up & right
		                		player.getColor().equals(((Token) board.getElement(column +2, row -2)).getColor()) &&
		                		player.getColor().equals(((Token) board.getElement(column +3, row -3)).getColor())) {
		                	list.add((Token) board.getElement(column, row));
		                	list.add((Token) board.getElement(column +1, row -1));
		                	list.add((Token) board.getElement(column +2, row -2));
		                	list.add((Token) board.getElement(column +3, row -3));
		              //  	System.out.println("Look UP to the RIGHT apllied");
		                    return true;
		                }
		                if ((column - 3 >= 0 && row - 3 >= 0) &&
		                		player.getColor().equals(((Token) board.getElement(column -1, row -1)).getColor()) && // look up & left
		                		player.getColor().equals(((Token) board.getElement(column -2, row -2)).getColor())  &&
		                		player.getColor().equals(((Token) board.getElement(column -3, row -3)).getColor())) {
		                	list.add((Token) board.getElement(column, row));
		                	list.add((Token) board.getElement(column -1, row -1));
		                	list.add((Token) board.getElement(column -2, row -2));
		                	list.add((Token) board.getElement(column -3, row -3));
		          //      	System.out.println("Look UP to the LEFT applied");
		                    return true;
		                }
		            }
		        }
		 return false;
	}
	
	public boolean hasWon(Board board, Game game) {
		
		if (checkHorizontal(board, winCondition, game) ||
			checkVertical(board, winCondition, game) ||
			checkDiagonal(board, winCondition, game))
			return true;
		
		return false;
	}
	
	private <T> boolean checkHorizontal(Board board, int winCondition, Game game) {
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
	
	private <T> boolean checkVertical(Board board, int winCondition, Game game) {
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
	
	private <T> boolean checkDiagonal(Board board, int winCondition, Game game) {
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

}
