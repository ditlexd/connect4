package connect4;

public class Main {
	
	/*
	 * Three types of game modes: 
	 * 0 : Player vs Player.
	 * 1: Player vs AI
	 * 2: AI vs AI.
	 * 
	 * Switch between modes by giving game constructor different ints 0 - 2.
	 */
	public static void main(String[] args) {
		Game game = new Game(0);
		game.startGame();
	}

}
