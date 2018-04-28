package tests;

import static org.junit.Assert.assertEquals;

import java.util.InputMismatchException;

import org.junit.Test;

import Players.IPlayer;
import Players.Player;
import connect4.Board;
import connect4.Game;
import connect4.TokenColor;

public class PlayerTest {
	
	//Must give input here!
	@Test
	public void doTurnTestPlayer() {
		IPlayer player = new Player(TokenColor.RED);
		Board board = new Board(5,5);
		
		//Give input from 1 - 5 to see that returns true for the right input
		System.out.println("Enter the right input! A number from 1 - 5!");
		assertEquals(player.doTurn(board), true);
		
		try {
			System.out.println("Enter wrong input! Either the wrong number or a String!");
			player.doTurn(board);
		} catch (IllegalArgumentException | InputMismatchException e) {
			assertEquals(true, true);			
		}
	}

}
