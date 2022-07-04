package tictactoe;

public class EasyAi {

	//Die AI besetzt zufaellig ein freies Feld
	protected static int[][] aiTurn(int bot) {

		int x = (int) (Math.random() * 3);
		int y = (int) (Math.random() * 3);
		int[][] field = Spielablauf.getField();

		if (field[x][y] == 0) { 
			GUI.buttonClicked(x, y, bot);

			field[x][y] = bot;
			return field;
		}

		else {

			return aiTurn(bot);
		}
	}
}