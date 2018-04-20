package connect4;

import java.awt.Color;

public class Token<T> {
	private TokenColor color;
	private int columnPos;
	private int rowPos;

	public Token(T color) {
		this.color = (TokenColor) color;
	}

	public String getSymbol() {
		switch (color) {
		case YELLOW:
			return "Y";
		case RED:
			return "R";
		case BLANK:
			return " ";
		}
		return null;
	}

	public TokenColor getColor() {
		return color;
	}

	public void setColor(TokenColor newColor) {
		if (newColor == null) {
			throw new IllegalArgumentException();
		}

		this.color = newColor;
	}
	
	public void setPos(int column, int row) {
		this.columnPos = column;
		this.rowPos = row;
	}
	
	public int getColumnPos() {
		return columnPos;
	}
	
	public int getRowPos() {
		return rowPos;
	}

}
