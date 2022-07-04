package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

	//Deklarierung aller Elemente der GUI
	private JFrame frame;
	private JFrame gameover;

	private JPanel mainmenu;
	private JPanel playerVsAimenu;
	private JPanel symbolchoice;
	private JPanel game;
	private JPanel endmenu;

	private JLabel empty;
	private JLabel headline;

	private JButton pvp;
	private JButton pvc;
	private JButton easy;
	private JButton medium;
	private JButton x;
	private JButton o;
	private JButton playagain;

	private static JButton[][] buttonfield;

	private Font font = new Font("Arial", Font.BOLD, 75);
	private static Font font2 = new Font("Arial", Font.BOLD, 25);

	//Erstellt eine neue GUI
	public GUI() {
		frameLayout();
		mainmenuLayout();

	}

	//Erstellt das Fenster Layout
	private void frameLayout() {

		frame = new JFrame("TIC TAC TOE");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	//Erstellt das Hauptmenu Layout
	private void mainmenuLayout() {
		pvp = new JButton("Player vs. Player");
		pvp.setBackground(Color.LIGHT_GRAY);
		pvp.setFont(font2);

		pvc = new JButton("Player vs. AI");
		pvc.setBackground(Color.LIGHT_GRAY);
		pvc.setFont(font2);

		empty = new JLabel();

		mainmenu = new JPanel();
		mainmenu.setBorder(BorderFactory.createEmptyBorder(200, 100, 200, 100));
		mainmenu.setLayout(new GridLayout(0, 1));
		mainmenu.setBackground(Color.DARK_GRAY);
		mainmenu.add(pvp);
		mainmenu.add(empty);
		mainmenu.add(pvc);

		mainmenu.setVisible(true);
		frame.add(mainmenu, BorderLayout.CENTER);

		//Startet das Spiel im PvP Modus
		pvp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu.setVisible(false);
				Spielablauf.setMode(1);
				gameLayout();
			}
		});
		
		//Ruft Das PvC Menu auf
		pvc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logic for Player Vs Computer
				mainmenu.setVisible(false);
				playerVsComputer();
			}
		});
	}
	
	//Erstellt das PvC Menu Layout
	private void playerVsComputer() {

		easy = new JButton("Easy Bot");
		easy.setBackground(Color.GREEN);
		easy.setFont(font2);

		medium = new JButton("Medium Bot");
		medium.setBackground(Color.ORANGE);
		medium.setFont(font2);

		empty = new JLabel();

		playerVsAimenu = new JPanel();
		playerVsAimenu.setBorder(BorderFactory.createEmptyBorder(225, 200, 225, 200)); 
		playerVsAimenu.setLayout(new GridLayout(0, 1));
		playerVsAimenu.setBackground(Color.DARK_GRAY);
		playerVsAimenu.add(easy);
		playerVsAimenu.add(empty);
		playerVsAimenu.add(medium);

		playerVsAimenu.setVisible(true);
		frame.add(playerVsAimenu, BorderLayout.CENTER);

		//Aussuchen ob gegen easy oder medium spielen 
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerVsAimenu.setVisible(false);
				Spielablauf.setMode(2);
				symbolChoiceLayout();
			}
		});
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logic for Player vs Hard Ai
				playerVsAimenu.setVisible(false);
				Spielablauf.setMode(3);
				symbolChoiceLayout();
			}
		});
	}

	//Erstellt das Symbol Menu Layout
	private void symbolChoiceLayout() {
		x = new JButton("X");
		x.setFont(font);

		o = new JButton("O");
		o.setFont(font);
		
		symbolchoice = new JPanel();
		symbolchoice.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
		symbolchoice.setLayout(new GridLayout(0, 1));
		symbolchoice.setBackground(Color.DARK_GRAY);
		symbolchoice.add(x);
		symbolchoice.add(o);

		symbolchoice.setVisible(true);
		frame.add(symbolchoice, BorderLayout.CENTER);

		//Spiel nimmt x als sein Symbol
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				symbolchoice.setVisible(false);
				Spielablauf.setOrder(1);
				gameLayout();
			}
		});
		
		//Spiel nimmt o als sein Symbol
		o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				symbolchoice.setVisible(false);
				Spielablauf.setOrder(2);
				gameLayout();
			}
		});
	}
	
	//Erstellst das Layout des Spielfelds
	private void gameLayout() {

		game = new JPanel(); 
		game.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		game.setLayout(new GridLayout(0, 3));
		game.setBackground(Color.DARK_GRAY);

		buttonfield = new JButton[3][3]; 
		//Erstellt ein 3x3 Button Array als Spielfeld
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int x = i;
				int y = j;

				buttonfield[x][y] = new JButton();
				buttonfield[x][y].setFont(font);
				game.add(buttonfield[x][y]);

				//Durch Buttonclick wird eine Gameturn eingeleitet
				//Button gibt seine Position im Array weiter und kriegt den Status des Spielstandes wieder
				buttonfield[x][y].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int gamestate;
						gamestate = Spielablauf.gameTurn(x, y);

						buttonClicked(x, y);
						
						//Wenn das Spiel gewonnen oder Unentschieden ausgegangen ist wird das Gameover Fenster geoeffnet
						if (gamestate == 1) 
							gameOverWon();
						else if(gamestate == 2)
							gameOverDraw();
					}
				});
			}
		}

		game.setVisible(true);
		frame.add(game);
	}

	//Stellt ein welches Symbol der Knopf den einer der Spieler gedrueckt hat darstellen soll und schaltet ihn aus
	private static void buttonClicked(int x, int y) {
		String symbol;
		symbol = Spielablauf.chooseSymbol(x, y);
		buttonfield[x][y].setText(symbol);
		buttonfield[x][y].setEnabled(false);
	}

	//Stellt ein welches Symbol der Knopf den die AI ausgewaehlt hat darstellen soll und schaltet ihn aus
	protected static void buttonClicked(int x, int y, int value) {
		String symbol;
		symbol = Spielablauf.chooseSymbol(value);
		buttonfield[x][y].setText(symbol);
		buttonfield[x][y].setEnabled(false);
	}

	//Gameover Layout bei einem Sieg
	private void gameOverWon() {
		gameOverLayout();

		int mode = Spielablauf.getMode();
		int turn = Spielablauf.getTurn();
		int winner = Spielablauf.getWinner();
		String symbol = Spielablauf.chooseSymbol(winner);

		if (mode == 1)
			headline.setText(symbol + " hat gewonnen");
		else if ((mode == 2 || mode == 3) && turn % 2 == 1)
			headline.setText("Du hast gewonnen");
		else if ((mode == 2 || mode == 3) && turn % 2 == 0)
			headline.setText("Die AI gewonnen");
	}

	//Gameover Layout bei einem Unentschieden
	private void gameOverDraw() {
		gameOverLayout();
		headline.setText("Unentschieden");
	}
	
	//Erstellt das Gameover Layout
	private void gameOverLayout() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				buttonfield[i][j].setEnabled(false);
		}

		headline = new JLabel();
		headline.setFont(font2);

		playagain = new JButton("Nochmal spielen");
		playagain.setFont(font2);

		endmenu = new JPanel();
		endmenu.setBorder(BorderFactory.createEmptyBorder(5, 25, 25, 25));
		endmenu.setLayout(new FlowLayout());
		endmenu.add(headline);
		endmenu.add(playagain);
		endmenu.setVisible(true);

		gameover = new JFrame("gameover");
		gameover.setSize(250, 150);
		gameover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameover.setResizable(false);
		gameover.setLocationRelativeTo(null);
		gameover.setVisible(true);
		gameover.toFront();
		gameover.add(endmenu);

		//Startet das Programm von vorne
		playagain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				gameover.dispose();
				TictactoeMain.start();
			}
		});
	}
}