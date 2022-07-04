package tictactoe;

public class Spielablauf {

	private static int[][] field; 	//3x3 int Array fuer das Tic Tac Toe Feld
	private static int player1; 	//Wert von Spieler 1
	private static int player2; 	//Wert von Spieler 2
	private static int mode; 		//Welcher Modus gewaehlt wurde. 1 = PvP, 2 = easy AI, 3 = medium AI
	private static int turn;		//Der wie vielte Zug gerade ist
	private static boolean won;		//Ob wer gewonnen hat
	private static int winner;		//Wer gewonnen hat

	static void newGame() {
		//Alle Werte werden auf den Ausgangszustand gesetzt
		field = new int[3][3];
		setOrder();
		mode = 0;
		turn = 0;
		won = false;
		winner = 0;
	}

	//Zufaellige Bestimmung welches Zeichen beginnt
	private static void setOrder() {
		player1 = (int) (Math.random() * 2) + 1;

		if (player1 == 2) //Gegenteil von dem zufaellig erstelltem Wert
			player2 = 1;
		else
			player2 = 2;
	}
	
	//Gegen AI kann man sein Zeichen aussuchen
	protected static void setOrder(int player) {
		player1 = player;
		
		if (player1 == 2) //Gegenteil von dem ausgewaehlten Wert
			player2 = 1;
		else
			player2 = 2;
	}

	//Ablauf eines Spielzuges. Wird durch Buttonclick in der GUI eingeleitet
	protected static int gameTurn(int x, int y) {
		turn++;

		//In ungeraden Zuegen setzt Spieler 1
		if (turn % 2 == 1)
			field[x][y] = player1;

		//In geraden Zuegen setzt Spieler 2
		else if (turn % 2 == 0 && mode == 1)
			field[x][y] = player2;

		checkWin(player1);

		//Wenn man gegen die AI spielt dann wird hier ihr Zug eingeleitet
		if (mode == 2 && turn != 9 && won == false) {
			turn++;

			field = EasyAi.aiTurn(player2);
		}
		else if(mode == 3 && turn != 9 && won == false) {
			turn++;

			field = MediumAi.mediumAiTurn(player2);
		}

		checkWin(player2);

		//Schaut ob wer gewonnen hat oder es unentschieden ausgegange ist und gibt den Status der GUI weiter
		if (turn == 9 && won == false) {
			return 2;
		}

		if (won == true)
			return 1;

		else
			return 0;
	}

	//Schaut ob der uebergebene Spieler gewonnen hat
	private static void checkWin(int player) {

		//Untersucht ob der Spieler nach seinem Zug horizontal oder vertikal gewonnen hat
		for (int i = 0; i < field.length; i++) {
			
			int check1 = 0;
			int check2 = 0;
			for (int j = 0; j < field.length; j++) {

				if (field[i][j] == player)
					check1++;
				if (field[j][i] == player)
					check2++;
			}
			if (check1 == 3 || check2 == 3) {
				won = true;
				winner = player;
			}

		}

		//Untersucht ob der Spieler nach seinem Zug diagonal gewonnen hat
		if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] == player) {
			won = true;
			winner = player;
		}

		if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] == player) {
			won = true;
			winner = player;
		}
	}

	//Uebersetzung der Werte in Zeichen
	protected static String chooseSymbol(int x, int y) {
		int value = field[x][y];
		String symbol;

		if (value == 1)
			symbol = "X";
		else
			symbol = "O";

		return symbol;
	}

	//Uebersetzung der Werte in Zeichen
	protected static String chooseSymbol(int value) {
		String symbol;

		if (value == 1)
			symbol = "X";
		else
			symbol = "O";

		return symbol;
	}

	protected static int[][] getField() {
		return field;
	}

	protected static void setMode(int a) {
		mode = a;
	}

	protected static int getMode() {
		return mode;
	}

	protected static int getTurn() {
		return turn;
	}

	protected static int getWinner() {
		return winner;
	}
	
	protected static int getPlayer1() {
		return player1;
	}
}
