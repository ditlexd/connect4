package connect4;

import java.util.ArrayList;
import java.util.List;

import Players.AIPlayer;
import Players.IPlayer;
import Players.Player;
import inputOutput.IO;
import rules.Rules;

public class Game {
	
	private Board board; 
	private ArrayList<IPlayer> players = new ArrayList<IPlayer>();
	private Token lastPlayedToken; 
	private boolean finished = false;
	private Rules rules;
	
	public Game(int mode ) {
		board = new Board(5,5);
		rules = new Rules();
		switch (mode) {
		case 0: { //Two human players
			players.add(new Player(TokenColor.RED, "Red"));
			players.add(new Player(TokenColor.YELLOW, "Yellow"));
			break;
		}
		case 1: { //One human, one AI
			players.add(new Player(TokenColor.RED, "Red"));
			players.add(new AIPlayer(TokenColor.YELLOW, "Yellow"));
			break;
		}
		case 2: { //Two AIs
			players.add(new AIPlayer(TokenColor.RED, "Red"));
			players.add(new AIPlayer(TokenColor.YELLOW, "Yellow"));
		}
		}
		
		for (int i = 0; i < board.getHeight() * board.getWidth(); ++i) {
			board.getCells().add(new Token(TokenColor.BLANK));
		}
		
	}
	
	public <T> Board<T> getBoard() {
		return board;
	}
	
	public void startGame() {

		while (!finished) {
			for (int i = 0; i < players.size(); i++) {
				IO.printBoard(board);
				boolean dropped = players.get(i).doTurn(this, board);
				if (rules.winCondition(board)) {
					IO.winMessage(players.get(i), board);
					finished = true;
					break;
				}
			}
		}
	}
	
	public boolean isFinished() {
		return this.finished;
	}
	
	public void insertToken(int x, int y, TokenColor tokenColor) {
		if (x < 0 || x > board.getWidth())
			throw new IndexOutOfBoundsException();
		if (y < 0 || y > board.getHeight())
			throw new IndexOutOfBoundsException();

		((Token) board.getCells().get(x + (y * board.getWidth()))).setColor(( tokenColor));
	}
	
	public boolean dropToken(int column, TokenColor color) throws IllegalArgumentException {
		if (column >= board.getWidth() +1) {
			throw new IllegalArgumentException("Not that many columns on the board!");
			
		}
		
		column--;

		boolean dropped = false;

		while (!false) {
			for (int i = board.getHeight() - 1; i >= 0; i--) {
				String tk = ((Token) board.getElement(column, i)).getSymbol();
				if (tk.equals(" ")) {
					insertToken(column, i, color);				//Puts the token in the correct x,y position.
					((Token) board.getElement(column, i)).setPos(column, i); //Updates the tokens position. 
					return true;
				}
			}
			return false;
		}
		
	}
	
	public Rules getRules() {
		return rules;
	}
	
	
}
