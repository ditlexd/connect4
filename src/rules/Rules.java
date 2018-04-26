package rules;

import java.util.ArrayList;
import java.util.List;

import Players.IPlayer;
import connect4.Board;
import connect4.Token;
import connect4.TokenColor;

public class Rules<T> {
	private static int WIN_NUMBER = 4;
	private List<Token> list = new ArrayList<Token>();

	public <T> boolean straightWinCondition(List<T> tokens) {
		int redCounter = 0;
		int yellowCounter = 0;

		for (int i = 0; i < tokens.size(); i++) {
			if (((Token)tokens.get(i)).getSymbol().equals("R")) {
				redCounter++;
				yellowCounter = 0;
			} else if (((Token)tokens.get(i)).getSymbol().equals("Y")) {
				yellowCounter++;
				redCounter = 0;
			} else {
				yellowCounter = 0;
				redCounter = 0;
			}

			if (redCounter == WIN_NUMBER || yellowCounter == WIN_NUMBER) {
				return true;
			}

		}
		return false;
	}
	
	public List<Token> getList() {
		return this.list;
	}
	
	public <T> boolean winCondition(Board<T> board) {
		int HEIGHT = board.getHeight();
		int WIDTH = board.getWidth();
		
		
		 for (int row = 0; row < HEIGHT; row++) { // iterate rows, bottom to top
		        for (int column = 0; column < WIDTH; column++) { // iterate columns, left to right
		        	Token player = ((Token) board.getElement(column, row));
		            if (player.getColor().equals(TokenColor.BLANK))
		                continue; // don't check empty slots

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

}
