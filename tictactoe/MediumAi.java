package tictactoe;

public class MediumAi {

	//In Anlehnung an den Folgenden Code: https://stackoverflow.com/questions/10645381/tictactoe-ai-java (10.01.2021)
	public static int[][] mediumAiTurn(int player2) {
		boolean didMove = false;
		int bot = player2;
		int player = Spielablauf.getPlayer1();
		int x = 0;
		int y = 0;
		int[][] field = Spielablauf.getField();

		// BotWin schaut ob in Zeile 3 und 1 die gleichen Zeichen sind, wenn ja
		// platziert er in Zeile 3 unter den
		for (int c = 0; c < 3; c++) {
			if (field[2][c] == field[1][c] && field[2][c] == bot && didMove == false) {
				if (field[0][c] == 0) {
					
					x = 0;
					y = c;
					didMove = true;
				}
			}
		}
		// BotWin schaut ob in Zeile 1 und 2 die gleichen Zeichen sind, wenn ja
		// platziert er in Zeile 3
		for (int d = 0; d < 3; d++) {
			if (field[0][d] == field[1][d] && field[0][d] == bot && didMove == false) {
				if (field[2][d] == 0) {

					x = 2;
					y = d;
					didMove = true;
				}
			}
		}
		// BotWin schaut ob in Zeile 1 und 3 die gleichen Zeichen sind, wenn ja
		// platziert er in Zeile 2
		for (int d = 0; d < 3; d++) {
			if (field[0][d] == field[2][d] && field[0][d] == bot && didMove == false) {
				if (field[1][d] == 0) {

					x = 1;
					y = d;
					didMove = true;
				}
			}
		}
		// BotWin schaut,ob in Reihe 1 und 2 bereits das Zeichen vom Bot ist, platziert
		// in Reihe 3
		for (int e = 0; e < 3; e++) {
			if (field[e][0] == field[e][1] && field[e][0] == bot && didMove == false) {
				if (field[e][2] == 0) {
					
					x = e;
					y = 2;
					didMove = true;
				}
			}
		}
		// BotWin schaut, ob Reihe 3 und 2 bereits das Zeichen vom Bot ist, platziert in
		// Reihe 1
		for (int f = 0; f < 3; f++) {
			if (field[f][2] == field[f][1] && field[f][2] == bot && didMove == false) {
				if (field[f][0] == 0) {
					
					x = f;
					y = 0;
					didMove = true;
				}
			}
		}
		// BotWin 1. schaut, ob Reihe 1 und 3 bereits das Zeichen vom Bot ist, platziert
		// in Reihe 2
		for (int f = 0; f < 3; f++) {
			if (field[f][0] == field[f][2] && field[f][0] == bot && didMove == false) {
				if (field[f][1] == 0) {
					
					x = f;
					y = 1;
					didMove = true;
				}
			}
		}
		// BotWin Diagonal von unten links nach oben rechts, platziert oben rechts
		if (field[2][0] == field[1][1] && field[2][0] == bot && didMove == false) {
			if (field[0][2] == 0) {
				
				x = 0;
				y = 2;
				didMove = true;
			}
		}
		// BotWin Diagonal von oben rechts nach unten links, platziert unten links
		if (field[0][2] == field[1][1] && field[0][2] == bot && didMove == false) {
			if (field[2][0] == 0) {
				
				x = 2;
				y = 0;
				didMove = true;
			}
		}
		// BotWin Diagonal schauen die enden (oben rechts und unten links), platziert in
		// der Mitte
		if (field[0][2] == field[2][0] && field[0][2] == bot && didMove == false) {
			if (field[1][1] == 0) {
				
				x = 1;
				y = 1;
				didMove = true;
			}
		}
		// Bot Win Diagonal von oben links nach unten rechts, platziert unten rechts
		if (field[0][0] == field[1][1] && field[0][0] == bot && didMove == false) {
			if (field[2][2] == 0) {
				
				x = 2;
				y = 2;
				didMove = true;
			}
		}
		// BotWin Diagonal von unten rechts nach oben links, platziert oben links
		if (field[2][2] == field[1][1] && field[2][2] == bot && didMove == false) {
			if (field[0][0] == 0) {
				
				x = 0;
				y = 0;
				didMove = true;
			}
		}
		// BotWin Diagonal schauen die enden (oben links und unten rechts), platziert in
		// der Mitte
		if (field[2][2] == field[0][0] && field[2][2] == bot && didMove == false) {
			if (field[1][1] == 0) {
				
				x = 1;
				y = 1;
				didMove = true;
			}
		}

		// Schauen, dass der Bot den Sieg verhindern kann

		for (int g = 0; g < 3; g++) // Schaut,ob in Reihe 1 und 2 bereits das Zeichen vom Spieler ist, platziert in
									// reihe 3
		{
			if (field[0][g] == field[1][g] && field[0][g] == player && didMove == false) {
				if (field[2][g] == 0) {
					
					x = 2;
					y = g;
					didMove = true;
				}
			}
		}
		for (int h = 0; h < 3; h++) // Schaut, ob Reihe 3 und 2 bereits das Zeichen vom Bot ist, platziert in Reihe
									// 1
		{
			if (field[2][h] == field[1][h] && field[2][h] == player && didMove == false) {
				if (field[0][h] == 0) {
					
					x = 0;
					y = h;
					didMove = true;
				}
			}
		}
		for (int h = 0; h < 3; h++) // Schaut, ob Reihe 1 und 3 bereits das Zeichen vom Bot ist, platziert in Reihe
									// 2
		{
			if (field[0][h] == field[2][h] && field[0][h] == player && didMove == false) {
				if (field[1][h] == 0) {
					
					x = 1;
					y = h;
					didMove = true;
				}
			}
		}
		for (int i = 0; i < 3; i++) // Schaut,ob in Zeile 1 und 2 bereits das Zeichen vom Spieler ist
		{
			if (field[i][0] == field[i][1] && field[i][0] == player && didMove == false) {
				if (field[i][2] == 0) {
					
					x = i;
					y = 2;
					didMove = true;
				}
			}
		}
		for (int j = 0; j < 3; j++) // Schaut, ob Reihe 3 und 2 bereits das Zeichen vom Bot ist
		{
			if (field[j][2] == field[j][1] && field[j][2] == player && didMove == false) {
				if (field[j][0] == 0) {
					
					x = j;
					y = 0;
					didMove = true;
				}
			}
		}
		for (int j = 0; j < 3; j++) // Schaut, ob Reihe 1 und 3 bereits das Zeichen vom Bot ist, platziert in der
									// Reihe 2
		{
			if (field[j][0] == field[j][2] && field[j][0] == player && didMove == false) {
				if (field[j][1] == 0) {
					
					x = j;
					y = 1;
					didMove = true;
				}
			}
		}
		// BotWin Diagonale

		// schaut die diagonale von unten links nach oben rechts, platziert oben rechts
		if (field[2][0] == field[1][1] && field[2][0] == player && didMove == false) {
			if (field[0][2] == 0) {
				
				x = 0;
				y = 2;
				didMove = true;
			}
		}
		// schaut die diagonale von oben rechts nach unten links, platziert unten links
		if (field[0][2] == field[1][1] && field[0][2] == player && didMove == false) {
			if (field[2][0] == 0) {
				
				x = 2;
				y = 0;
				didMove = true;
			}
		}
		// schaut die enden der diagonale (oben rechts und unten links), platziert dann
		// in der Mitte
		if (field[0][2] == field[2][0] && field[0][2] == player && didMove == false) {
			if (field[1][1] == 0) {
				
				x = 1;
				y = 1;
				didMove = true;
			}
		}
		// schaut die diagonale von oben links nach unten rechts, platziert unten rechts
		if (field[0][0] == field[1][1] && field[0][0] == player && didMove == false) {
			if (field[2][2] == 0) {
				
				x = 2;
				y = 2;
				didMove = true;
			}
		}
		// schaut die diagonale von unten rechts nach oben links, platziert oben links
		if (field[2][2] == field[1][1] && field[2][2] == player && didMove == false) {
			if (field[0][0] == 0) {
				
				x = 0;
				y = 0;
				didMove = true;
			}
		}
		// schaut die enden der diagonalen (unten rehchts und oben links), platziert in
		// der mitte
		if (field[2][2] == field[0][0] && field[2][2] == player && didMove == false) {
			if (field[1][1] == 0) {
				
				x = 1;
				y = 1;
				didMove = true;
			}
		}

		// Falls es noch nicht kurz vor einem Sieg ist, wird ein zufalls Feld genommen
		while (didMove == false) {
			int a = (int) (Math.random() * 3);
			int b = (int) (Math.random() * 3);

			if (field[a][b] == 0) { // Spieler darf nur setzen wenn das Feld leer ist

				x = a;
				y = b;
				didMove = true;
			}
		}
		
		GUI.buttonClicked(x, y, bot);

		field[x][y] = bot;
		return field;
	}
}