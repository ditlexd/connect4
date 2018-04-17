package connect4;

import java.util.ArrayList;
import java.util.List;

import Players.IPlayer;
import Players.Player;

public class Game {
	
	private Board board; 
	private ArrayList<IPlayer> players = new ArrayList<IPlayer>();
	
	public Game() {
		board = new Board(5,5);
		players.add(new Player(TokenColor.RED, "Ditlef"));
		players.add(new Player(TokenColor.YELLOW, "Arisa"));
	}
	
	public void startGame() {
		boolean finished = false;
		int iterate = 0;
		while (!finished) {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).doTurn(this, board);
			}
			iterate++;
			if (iterate == 5) {
				finished = true;
			}
		}
		board.printBoard();
	}
	
	
}
