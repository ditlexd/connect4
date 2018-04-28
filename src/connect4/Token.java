package connect4;

import java.awt.Color;

public class Token {
	private TokenColor color;
	private int columnPos;
	private int rowPos;

	public Token(TokenColor color) {
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
	
	public <T> boolean compareColor(T token) {
		if (token == null) 
			return false; 
		if (!(token instanceof Token))
			return false;
		if (token.equals(this))
			return true;
		Token tk = ((Token) token);
		if (tk.getColor().equals(color))
			return true;
		
		return false; 
	}

}
