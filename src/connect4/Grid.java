package connect4;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {
	
	private List<T> cells;
	private int width;
	private int height;

	// Creates a new game board with the given dimensions.
	public Grid(int width, int height) {
		super(); 
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.height = height;
		this.width = width;
		cells = new ArrayList<T>();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public List<T> getCells() {
		return cells;
	}
	
	public T getElement(int column, int row) {
		return cells.get(column + (row * width));
	}
	
	public int returnInt() {
		return 1;
	}
	
	public void setElement(int column, int row, T element) {
		 cells.set((column + (row * width)), element);
	}

}
