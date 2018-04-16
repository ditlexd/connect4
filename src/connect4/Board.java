package connect4;

import java.util.ArrayList;
import java.util.List;

public class Board<T> {

	private List<T> cells;
	private int width;
	private int height;

	// Creates a new game board with the given dimensions.
	public Board(int width, int height, T element) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}

		this.height = height;
		this.width = width;
		cells = new ArrayList<T>(height * width);
		for (int i = 0; i < height * width; ++i) {
			Token<T> token = new Token<T>(element);
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

	
	public void printBoard() {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				System.out.print("| "+((Token) cells.get(i)).getSymbol()+" ");
			}
			System.out.print("|\n");
		}

	}

}
