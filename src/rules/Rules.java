package rules;

import java.util.ArrayList;
import java.util.List;

import Players.IPlayer;
import connect4.Board;
import connect4.Token;
import connect4.TokenColor;

public class Rules {
	private static int WIN_NUMBER = 4;

	public static <T> boolean straightWinCondition(List<T> tokens) {
		int redCounter = 0;
		int yellowCounter = 0;
		for (int i = 0; i < tokens.size(); i++) {
			System.out.print(((Token) tokens.get(i)).getSymbol());
		}

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

}
