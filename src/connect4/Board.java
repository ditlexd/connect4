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
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public List<T> getCells() {
		return cells;
	}


	public T getElement(int column, int row) {
		return cells.get(column + (row * width));
	}

	// Prints the board. Currently prints it upside down, aka 0,0 is top left
	// corner.
	public <T> void printBoard() {
		int index = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				System.out.print("| " + ((Token) cells.get(index)).getSymbol() + " ");
				index++;
			}
			System.out.print("|\n");
		}
	}


	public List<T> getHorizontalNeighbours(int tokenColumn, int tokenRow) {
		ArrayList<T> neighbours = new ArrayList<T>();
		int column = tokenColumn;
		int row = tokenRow;

		for (int i = 0; i < width; i++) {
			neighbours.add(getElement(i, row));
		}

		return neighbours;
	}

	public List<T> getVerticalNeighbours(int tokenColumn, int tokenRow) {
		ArrayList<T> neighbours = new ArrayList<T>();
		int column = tokenColumn;
		int row = tokenRow;

		for (int i = 0; i < height; i++) {
			neighbours.add(getElement(column, i));
		}

		return neighbours;
	}

}
