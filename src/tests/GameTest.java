package tests;

import org.junit.Test;

import connect4.Game;

public class GameTest {
	
	@Test
	public void startGameTest() {
		Game game = new Game();
		game.startGame();
	}

}
