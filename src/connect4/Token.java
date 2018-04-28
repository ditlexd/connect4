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
		}
		return null;
	}

	public TokenColor getColor() {
		return color;
	}

	
}
