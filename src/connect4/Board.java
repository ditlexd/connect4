package connect4;

import java.util.ArrayList;
import java.util.List;

public class Board<T> extends Grid<T> {

	

	// Creates a new game board with the given dimensions.
	public Board(int width, int height) {
		super(width,height);
		initializeBoard();
	}
	
	public void initializeBoard() {
		for (int i = 0; i < getHeight() * getWidth(); ++i) {
			cells.add(null);
		}
	}
	
	
	public boolean dropToken(int column, T token) {
		
		//User is promted to type column number 1 - boardWidth, even though available 
		//x positions is 0 - boardWidth-1. Therefore we substract 1 from the number given by the user. 
		column--;

		boolean dropped = false;

		while (!false) {
			for (int i = super.getHeight() - 1; i >= 0; i--) {
				if (getElement(column, i) != null) {
					continue;
				}
					setElement(column, i, token); // Puts the token in the correct x,y position.
					return true;
			}
			return false;
		}

	} 
	
	public void clearBoard() {
		for (int i = 0; i < cells.size(); i++) {
			cells.set(i, null);
		}
	}
	

}
