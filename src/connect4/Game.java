package connect4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import Players.AIPlayer;
import Players.IPlayer;
import Players.Player;
import inputOutput.IO;
import rules.Rules;

public class Game {
	
	private final int WIN_CONDITION = 4;
	private Board board;
	private ArrayList<IPlayer> players = new ArrayList<IPlayer>();
	private Token lastPlayedToken;
	private boolean finished = false;
	private Rules rules;

	public Game(int mode) {
		board = new Board(5, 5);
		rules = new Rules(this, WIN_CONDITION);
		
		switch (mode) {
		case 0: { // Two human players
			players.add(new Player(TokenColor.RED));
			players.add(new Player(TokenColor.YELLOW));
			break;
		}
		case 1: { // One human, one AI
			players.add(new Player(TokenColor.RED));
			players.add(new AIPlayer(TokenColor.YELLOW));
			break;
		}
		case 2: { // Two AIs
			players.add(new AIPlayer(TokenColor.RED));
			players.add(new AIPlayer(TokenColor.YELLOW));
		}
		}

		board.initializeBoard();

	}

	public <T> Board<T> getBoard() {
		return board;
	}

	public void startGame() {

		while (!finished) {
			boolean dropped = false;
			for (int i = 0; i < players.size(); i++) {
				IO.printBoard(board);
				do {
					IO.playersTurn(players.get(i));
				try {
					 dropped = players.get(i).doTurn(board);
				} catch (IllegalArgumentException | IndexOutOfBoundsException | InputMismatchException e) {
					IO.illegalMove(board);
				} 
				} while (!dropped);
				if (rules.hasWon(board, this)) {
					IO.winMessage(players.get(i), board);
					finished = true;
					break;
				}
			}
		}
	}

	// For debugging.
	public boolean isFinished() {
		return this.finished;
	}
	//For debugging
	public Rules getRules() {
		return rules;
	}
	
	public <T> boolean compareTokens(T token1, T token2) {
		if (token1 == null || token2 == null) 
			return false; 
		if (!(token1 instanceof Token) || !(token2 instanceof Token))
			return false;
		if (token1.equals(token2))
			return true;
		Token tk1 = ((Token) token1);
		Token tk2 = ((Token) token2);
		if (tk1.getColor().equals(tk2.getColor()))
			return true;
		
		return false;
	}

}
