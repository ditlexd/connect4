package connect4;

import java.util.ArrayList;
import java.util.List;

public class Board<T> {

	private List<T> cells;
	private int width;
	private int height;

	// Creates a new game board with the given dimensions.
	public Board(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException();
		}

		this.height = height;
		this.width = width;
		cells = new ArrayList<T>();
		for (int i = 0; i < height * width; ++i) {
			T token = (T) new Token(TokenColor.BLANK);
			cells.add(token);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void insertToken(int x, int y, T tokenColor) {	
		if (x < 0 || x > width)
			throw new IndexOutOfBoundsException();
		if (y < 0 || y > height)
			throw new IndexOutOfBoundsException();

		((Token) cells.get(x + (y * width))).setColor(((TokenColor) tokenColor));;
	}

	//Prints the board. Currently prints it upside down, aka 0,0 is top left corner. 
	public <T> void printBoard() {
		int index = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				System.out.print("| "+((Token) cells.get(index)).getSymbol()+" ");
				index++;
			}
			System.out.print("|\n");
		}
	}
	
	private boolean isBlank(int column, int row) {
		if (((Token) cells.get(column + (row * width))).getSymbol() != " ") {
			return false;
		}
		return true;
	}
	
	public void dropToken(int column, T color) {
		if (column > height) {
			throw new IllegalArgumentException("Not that many columns on the board!");
		}
		
		for (int i = height-1; i >= 0; i--) {
			if (isBlank(column, i)) {
				insertToken(column, i, color);
				break;
			}
		}
	}

}
