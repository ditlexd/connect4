package connect4;

import java.util.ArrayList;
import java.util.List;

import Players.IPlayer;
import Players.Player;
import inputOutput.IO;
import rules.Rules;

public class Game {
	
	private Board board; 
	private ArrayList<IPlayer> players = new ArrayList<IPlayer>();
	
	public Game() {
		board = new Board(5,5);
		players.add(new Player(TokenColor.RED, "Red"));
		players.add(new Player(TokenColor.YELLOW, "Yellow"));
	}
	
	public void startGame() {
		boolean finished = false;
		Token lastPlayedToken = null;
		
		for (int i = 0; i < board.getHeight() * board.getWidth(); ++i) {
			board.getCells().add(new Token(TokenColor.BLANK));
		}

		while (!finished) {
			for (int i = 0; i < players.size(); i++) {
				lastPlayedToken = players.get(i).doTurn(this, board);
				hasWon(lastPlayedToken);
			}
			
		}
		IO.printBoard(board);
	}
	
	private <T> void hasWon(Token lastPlayedToken) {
		List<T> horizontal = board.getHorizontalNeighbours(lastPlayedToken.getColumnPos(),
				lastPlayedToken.getRowPos());
		
		List<T> vertical = board.getVerticalNeighbours(lastPlayedToken.getColumnPos(),
				lastPlayedToken.getRowPos());
		
		
		if (Rules.straightWinCondition(horizontal) || 
			Rules.straightWinCondition(vertical)) {

			if (lastPlayedToken.getSymbol().equals("R")) {
				System.out.println("Red won!");
				System.exit(0);
			} else if (lastPlayedToken.getSymbol().equals("Y")) {
				System.out.println("Yellow won!");
				System.exit(0);
			}
		}
	}
	
	public void insertToken(int x, int y, TokenColor tokenColor) {
		if (x < 0 || x > board.getWidth())
			throw new IndexOutOfBoundsException();
		if (y < 0 || y > board.getHeight())
			throw new IndexOutOfBoundsException();

		((Token) board.getCells().get(x + (y * board.getWidth()))).setColor(( tokenColor));
	}
	
	public Token dropToken(int column, TokenColor color) throws IllegalArgumentException {
		if (column >= board.getWidth()) {
			throw new IllegalArgumentException("Not that many columns on the board!");
			
		}

		boolean dropped = false;

		while (!false) {
			for (int i = board.getHeight() - 1; i >= 0; i--) {
				String tk = ((Token) board.getElement(column, i)).getSymbol();
				if (tk.equals(" ")) {
					insertToken(column, i, color);
					((Token) board.getElement(column, i)).setPos(column, i);
					return ((Token) board.getElement(column, i));
				}
			}
		} 
		
	}
	
	
}
