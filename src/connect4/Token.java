package connect4;

import java.awt.Color;

public class Token<T> {
	private TokenColor color;

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
		System.out.println(color);
		if (newColor == null) {
			throw new IllegalArgumentException();
		}

		this.color = newColor;
		System.out.println("Changed color! To " + this.color);

	}

}
