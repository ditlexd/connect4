package Players;

import connect4.Board;
import connect4.Game;
import connect4.TokenColor;

/*
 * Methods that are common for both AI and human players. 
 */

public interface IPlayer {
	
	//Do a turn. Drop a token in a column on the board
	 void doTurn(Game game, Board board);
	 
	 //What color is the player? It can be red or Yellow.
	 TokenColor getColor();
	 
	 
	

}
