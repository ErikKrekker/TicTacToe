package tictactoe;

public class TictactoeMain {
	public static void main(String[] args) {
		start();
	}

	protected static void start() {
		Spielablauf.newGame();
		new GUI();
	}
}