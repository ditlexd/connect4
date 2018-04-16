package connect4;

public class Location {
	private int widthIndex;
	private int heigthIndex; 
	private Token token;
	
	public Location(int widthIndex, int heightIndex) {
		this.widthIndex = widthIndex;
		this.heigthIndex = heightIndex;
	}
	
	public void addToken(Token token) {
		if (this.token != null) {
			System.out.println("There's already a token here! Try another place!");
			return;
		}
		this.token = token;
	}
	
	public Token getTokenInLoc() {
		return token;
	}
	
	
	

}
