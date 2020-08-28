package model;

import controller.ConsoleControl;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Game class where the heart of the game will be located
 * The class implements Serializable to save the game
 * @author DELAUNAY Gurwan 1C1
 **/
public class Game implements Serializable {

	private static final long serialVersionUID = 25542541450L;
	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	private Player perdant;
	private int turns;
	private int width;
	private int height;
	private int version;
	private int difficulty;
	private int victoryPlayerOne;
	private int victoryPlayerTwo;
	private boolean matchNul;
	private Square[][] boardGame;
	private Pawn zen;
	private int nbToursJoues;
	private Mode mode;
	private String indicationGUI;
	private boolean iaBegin;
	private transient ConsoleControl consoleControl;

	/**
	 * First constructor of Game
	 * @param difficulty the potential difficulty of the AI player
	 * @param turns the number of turns of the game
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 * @param mode the game mode
	 * @param width the width of board
	 * @param height the height of board
	 * @param version the version of the game
	 * @param in the input user scanner
	 * @param configuration the configuration of the game
	 */
	public Game(int difficulty, int turns, String playerName1, String playerName2, Mode mode, int width, int height,int version,Scanner in,Configuration configuration) {
		this.turns = turns;
		this.width = width;
		this.height = height;
		this.version = version;
		this.zen = new Pawn(2,5,5);
		this.difficulty = difficulty;
		this.initializeBoard();
		this.nbToursJoues = 0;
		this.mode = mode;
		this.matchNul = false;
		this.victoryPlayerOne = 0;
		this.victoryPlayerTwo = 0;

		this.consoleControl = new ConsoleControl(in, configuration);

		if(mode == Mode.HH){
			this.playerOne = new PlayerHuman(playerName1,Color.WHITE,version,this.boardGame,this.consoleControl);
			this.playerTwo = new PlayerHuman(playerName2,Color.BLACK,version,this.boardGame,this.consoleControl);
		} else {
			this.playerOne = new PlayerHuman(playerName1,Color.WHITE,version,this.boardGame,this.consoleControl);
			this.playerTwo = new PlayerAuto(playerName2,Color.BLACK,difficulty,version,this.boardGame);
		}

		this.newTurn();

	}

	/**
	 * Second constructor of Game
	 * @param difficulty the potential difficulty of the AI player
	 * @param turns the number of turns of the game
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 * @param mode the game mode
	 * @param width the width of board
	 * @param height the height of board
	 * @param version the version of the game
	 * @param configuration the configuration of the game
	 */
	public Game(int difficulty, int turns, String playerName1, String playerName2, Mode mode, int width, int height, int version, Configuration configuration) {
		this.turns = turns;
		this.width = width;
		this.height = height;
		this.version = version;
		this.zen = new Pawn(2,5,5);
		this.difficulty = difficulty;
		this.initializeBoard();
		this.nbToursJoues = 0;
		this.mode = mode;
		this.matchNul = false;
		this.victoryPlayerOne = 0;
		this.victoryPlayerTwo = 0;

		if(mode == Mode.HH){
			this.playerOne = new PlayerHuman(playerName1,Color.WHITE,version,this.boardGame,this.consoleControl);
			this.playerTwo = new PlayerHuman(playerName2,Color.BLACK,version,this.boardGame,this.consoleControl);
		} else {
			this.playerOne = new PlayerHuman(playerName1,Color.WHITE,version,this.boardGame,this.consoleControl);
			this.playerTwo = new PlayerAuto(playerName2,Color.BLACK,difficulty,version,this.boardGame);
		}

		this.newTurn();
	}

	/**
	 * Method for recreating a ConsoleControl after loading the game
	 * @param scanner the user input scanner
	 * @param configuration the configuration of the game
	 */
	public void gameIsLoad(Scanner scanner,Configuration configuration){
		this.consoleControl = new ConsoleControl(scanner,configuration);
		this.playerOne.setConsoleControl(this.consoleControl);
		this.playerTwo.setConsoleControl(this.consoleControl);
		this.currentPlayer.setConsoleControl(this.consoleControl);
	}

	/**
	 * Method to change the player who plays
	 */
	public void changeCurrentPlayer() {
		if(this.currentPlayer == this.playerOne){
			this.currentPlayer = this.playerTwo;
		} else {
			this.currentPlayer = this.playerOne;
		}

		if(version == 1) {
			if (this.currentPlayer.equals(this.playerOne)) {
				this.indicationGUI = "Au tour de " + this.currentPlayer.getName() + " (BLANC) ";
			} else {
				this.indicationGUI = "Au tour de " + this.currentPlayer.getName() + " (NOIR) ";
			}
		}
	}

	/**
	 * This method is executed at each new turn
	 */
	public void newTurn(){
		if(version == 0) {
			if (nbToursJoues > 0) {
				String playerName1 = playerOne.getName();
				String playerName2 = playerTwo.getName();

				this.resetValues();

				currentPlayer = perdant;

				this.zen = new Pawn(2, 5, 5);
				this.initializeBoard();

				if (mode == Mode.HH) {
					this.playerOne = new PlayerHuman(playerName1, Color.WHITE, version, this.boardGame, this.consoleControl);
					this.playerTwo = new PlayerHuman(playerName2, Color.BLACK, version, this.boardGame, this.consoleControl);
				} else {
					this.playerOne = new PlayerHuman(playerName1, Color.WHITE, version, this.boardGame, this.consoleControl);
					this.playerTwo = new PlayerAuto(playerName2, Color.BLACK, difficulty, version, this.boardGame);
				}

				this.start();
			} else {
				if ((int) (Math.random() * 2 + 1) == 1) {
					this.currentPlayer = this.playerOne;

				} else {
					this.currentPlayer = this.playerTwo;
				}

				System.out.println("Le joueur 1 sera donc " + this.playerOne.getName() + " et jouera en blanc");

				System.out.println("Et le joueur 2 sera " + this.playerTwo.getName() + " et jouera en noir");
			}
		} else {
			if(nbToursJoues > 0){
				String playerName1 = playerOne.getName();
				String playerName2 = playerTwo.getName();
				String perdantIs = null;

				if(perdant.getName().equals(playerName1)){
					perdantIs = "player1";
				} else {
					perdantIs = "player2";
				}

				this.resetValues();

				this.zen = new Pawn(2, 5, 5);
				this.initializeBoard();

				if (mode == Mode.HH) {
					this.playerOne = new PlayerHuman(playerName1, Color.WHITE, version, this.boardGame, this.consoleControl);
					this.playerTwo = new PlayerHuman(playerName2, Color.BLACK, version, this.boardGame, this.consoleControl);
				} else {
					this.playerOne = new PlayerHuman(playerName1, Color.WHITE, version, this.boardGame, this.consoleControl);
					this.playerTwo = new PlayerAuto(playerName2, Color.BLACK, difficulty, version, this.boardGame);
				}

				if(perdantIs.equals("player1")){
					currentPlayer = playerOne;
				} else {
					currentPlayer = playerTwo;
				}

			} else {
				if ((int) (Math.random() * 2 + 1) == 1) {
					this.currentPlayer = this.playerOne;

				} else {
					this.currentPlayer = this.playerTwo;
				}

			}

			if(this.currentPlayer.equals(this.playerOne)) {
				this.indicationGUI = "Au tour de " + this.currentPlayer.getName() + " (BLANC) ";
			} else {
				this.indicationGUI = "Au tour de " + this.currentPlayer.getName() + " (NOIR) ";
				if(this.mode == Mode.HA){
					this.iaBegin = true;
				}
			}

		}

	}

	/**
	 * Method for selecting the desired pawn
	 * @return ret : the pawn selected
	 */
	public Pawn selectPawn() {
		Pawn ret = null;
		int[] coord = consoleControl.askCoordPawn();

		for(Pawn p : currentPlayer.getPawns()){
			if(Arrays.equals(p.getPosition(), coord)){
				ret = p;
			}
		}

		if(ret == null){
			if(zen != null) {
				if (Arrays.equals(this.zen.getPosition(), coord)) {
					ret = this.zen;
				}
			}
		}

		return ret;
	}

	/**
	 * Method for selecting the desired pawn
	 * @param x the x coordinate of the desired pawn
	 * @param y the y coordinate of the desired pawn
	 * @return ret : the desired pawn
	 */
	public Pawn selectPawn(int x, int y){
		Pawn ret = null;
		int[] coord = new int[2];
		coord[0] = x;
		coord[1] = y;

		for(Pawn p : currentPlayer.getPawns()){
			if(Arrays.equals(p.getPosition(), coord)){
				ret = p;
			}
		}

		if(ret == null){
			if(zen != null) {
				if (Arrays.equals(this.zen.getPosition(), coord)) {
					ret = this.zen;
				}
			}
		}

		return ret;
	}

	/**
	 * Method for remove a pawn of the game when he was captured
	 * @param p the pawn that must be removed
	 */
	public void removePawn(Pawn p) {
		// Le pion n'est pas vraiment supprimé mais déplacer loin du plateau
		if(p.getX() != 1000 && p.getY() != 1000) {
			int[] positionPion;
			int i;
			positionPion = p.getPosition();

			if (p.getType() == 0) {
				i = playerOne.getPawns().indexOf(p);
				playerOne.getPawns().get(i).setPosition(1000, 1000);
				playerOne.getPawns().get(i).setVisited(true);
			} else if (p.getType() == 1) {
				i = playerTwo.getPawns().indexOf(p);
				playerTwo.getPawns().get(i).setPosition(1000, 1000);
				playerTwo.getPawns().get(i).setVisited(true);
			} else if (p.getType() == 2) {
				this.zen = null;
			}

			p.setInGame(false);
			this.boardGame[positionPion[0]][positionPion[1]].setOccupy(false);
		} else {
			p.setVisited(true);
		}
	}

	/**
	 * Method to check if a player is in position to win
	 * @param player the player who we want to know if he won
	 * @return ret : true if the player has win else false
	 */
	public boolean win(Player player) {
		boolean ret = true;
		int i = 0;
		Pawn premierPawn = null;
		do {
			premierPawn = player.getPawns().get(i);
			if (premierPawn.getX() == 1000) {
				i++;
				premierPawn = player.getPawns().get(i);
			}
		} while(premierPawn == null && premierPawn.getX() == 1000);

		parcoursDFS(premierPawn,player.getPawns());

		for(Pawn p : player.getPawns()){
			if (!p.isVisite()) {
				ret = false;
				break;
			}
		}

		if(zen!=null){
			if(!(zen.isVisite())){
				ret = false;
			}
		}

		if(!ret){
			for(Pawn p : player.getPawns()){
				if(p.getX() != 1000 && p.getY() != 1000) {
					p.setVisited(false);
				}
			}
		}

		return ret;
	}


	/**
	 * This method is a depth first search allowing to know if all the pawns are aligned.
	 * It's a recursive method.
	 * @param p the pawn whose neighbors we want to know
	 * @param currentPawns all the pawns we want to know if they are aligned
	 */
	public void parcoursDFS(Pawn p,ArrayList<Pawn> currentPawns){
		ArrayList<Pawn> voisins = p.getNeighbors(currentPawns,zen);
		p.setVisited(true);
		for (Pawn pawn : voisins) {
			if (pawn != null && !pawn.isVisite()) {
				parcoursDFS(pawn, currentPawns);
			}
		}
	}

	/**
	 * Method to check if the game is draw
	 * @return ret true if the game is draw else false
	 */
	public boolean isDraw() {
		boolean ret = false;
		if(this.matchNul){
			ret = true;
		} else {
			if (this.win(playerOne) && this.win(playerTwo)) {
				if (this.zen != null) {
					ret = true;
				}
			}
		}
		return ret;
	}

	/**
	 * Method that initializes the game board
	 */
	public void initializeBoard() {
		int a = this.width;
		int b = this.height;
		int c = 0;
		this.boardGame = new Square[a][b];
		for(int i = 0; i<a ; i++){
			for(int j = 0; j<b ; j++){

				if(c==0) {
					this.boardGame[i][j] = new Square(i, j, new Color(102, 51, 0));
					c = 1;
				} else {
					this.boardGame[i][j] = new Square(i, j, new Color(255, 204, 0));
					c = 0;
				}

				if((j == 0 && (i ==0 || i == 5)) || ((j == 2 || j == 8) && (i ==3 || i==7)) || ((j==4 || j==6) && (i==1 || i==9)) || (j==10 && (i == 5 || i==10))){
					this.boardGame[i][j].setSign("geometric");
					this.boardGame[i][j].setOccupy(true);
				}

				if((j == 0 && i == 10) || ((j==1 || j==9) && (i==4 || i==6)) || ((j==3 || j== 7) && (i== 2 || i == 8)) || (j==5 && (i==0 || i==10)) || (j==10 && i==0)){
					this.boardGame[i][j].setSign("chinese");
					this.boardGame[i][j].setOccupy(true);
				}

				if(i == 5 && j == 5){
					// placement de Zen en début de partie
					this.boardGame[i][j].setOccupy(true);
				}
			}
		}
		this.zen.setPosition(5,5);
	}

	/**
	 * Method that display the game board for the text version
	 */
	public void displayBoard() {
		if(version == 0){

			System.out.println();
			System.out.println("       A    B    C    D    E    F    G    H    I    J   K");
			System.out.println("    ┌————┬————┬————┬————┬————┬————┬————┬————┬————┬————┬————┐");

			for(int i=0; i<this.boardGame.length; i++){
				int k = 0;

				String ligne = "   │";

				if(i>1) {
					ligne = "   │";
				}

				if(i>8){
					ligne = "  │";
				}

				if(i != 0) {
					System.out.println("    │————│————│————│————│————│————│————│————│————│————│————│");
				}

				for(int j=0; j<this.boardGame[i].length; j++){
					boolean found = false;

					if(!this.boardGame[i][j].isOccupy()){
						ligne = ligne + "  " + "  │";
					} else if(this.boardGame[i][j].isOccupy()){
						int[] pos = {i,j};

						for(Pawn p : playerOne.getPawns()){
							if(Arrays.equals(p.getPosition(), pos)){
								ligne = ligne + "" + " B" + "  │" ;
								found = true;
								break;
							}
						}

						if(!found) {
							for (Pawn p : playerTwo.getPawns()) {
								if (Arrays.equals(p.getPosition(), pos)) {
									ligne = ligne + "" + " N" + "  │";
									break;
								}
							}
						}

						if(zen != null) {
							if (Arrays.equals(this.zen.getPosition(), pos)) {
								ligne = ligne + "" + " Z" + "  │";
							}
						}
					}
				}
				k = i+1;
				System.out.println(k + ligne);

			}
			System.out.println("    └————┴————┴————┴————┴————┴————┴————┴————┴————┴————┴————┘");
			System.out.println("\n");
		}
	}

	/**
	 * The game loop method for the text version
	 */
	public void start() {
		if(version == 0){
			Player opponentPlayer;
			Pawn p;
			boolean enCours = true;
			int[] oldCoor;

			while (enCours) {
				displayBoard();
				boolean isZen = false;

				System.out.println("Au tour de " + currentPlayer.getName());

				if (currentPlayer.equals(playerOne)) {
					opponentPlayer = playerTwo;
				} else {
					opponentPlayer = playerOne;
				}

				if (this.mode == Mode.HH) {
					if (this.zen != null) {
						do {
							p = null;
							do {
								p = this.selectPawn();
								if (p == null) {
									System.out.println("Il faut choisir un de ses pions ou le pion Zen");
								}
							} while (p == null);
							oldCoor = p.getPosition();

							isZen = p.equals(this.zen);

						} while (!currentPlayer.play(p, opponentPlayer.getPawns(), this.zen));

						if (isZen) {
							this.zen = p;
						}

						this.boardGame[oldCoor[0]][oldCoor[1]].setOccupy(false);

						for (Pawn pawn : opponentPlayer.getPawns()) {
							if (pawn.getX() == p.getX() && pawn.getY() == p.getY()) {
								for (Pawn pawn1 : opponentPlayer.pawnListPlayer) {
									if (!pawn.isVisite() && pawn1.isVisite()) {
										this.removePawn(pawn);
										this.matchNul = true;
									} else {
										this.removePawn(pawn);
									}
								}
							}
						}

						if (!isZen) {
							if (this.zen.getX() == p.getX() && this.zen.getY() == p.getY()) {
								this.removePawn(this.zen);
							}
						}

					} else {
						do {
							do {
								p = this.selectPawn();
								if (p == null) {
									System.out.println("Il faut choisir un de ses pions ou le pion Zen");
								}
							} while (p == null);

							oldCoor = p.getPosition();

						} while (!currentPlayer.play(p, opponentPlayer.getPawns()));

						this.boardGame[oldCoor[0]][oldCoor[1]].setOccupy(false);

						for (Pawn pawn : opponentPlayer.pawnListPlayer) {
							if (pawn.getX() == p.getX() && pawn.getY() == p.getY()) {
								for (Pawn pawn1 : opponentPlayer.pawnListPlayer) {
									if (!pawn.isVisite() && pawn1.isVisite()) {
										this.removePawn(pawn);
										this.matchNul = true;
									} else {
										this.removePawn(pawn);
									}
								}


							}
						}

					}
				} else {
					if (currentPlayer.equals(playerOne)) {
						if (this.zen != null) {
							do {
								p = null;
								do {
									p = this.selectPawn();
									if (p == null) {
										System.out.println("Il faut choisir un de ses pions ou le pion Zen");
									}
								} while (p == null);

								oldCoor = p.getPosition();

								isZen = p.equals(this.zen);

							} while (!currentPlayer.play(p, opponentPlayer.getPawns(), this.zen));

							if (isZen) {
								this.zen = p;
							}

							this.boardGame[oldCoor[0]][oldCoor[1]].setOccupy(false);

							for (Pawn pawn : opponentPlayer.getPawns()) {
								if (pawn.getX() == p.getX() && pawn.getY() == p.getY()) {
									for (Pawn pawn1 : opponentPlayer.pawnListPlayer) {
										if (!pawn.isVisite() && pawn1.isVisite()) {
											this.removePawn(pawn);
											this.matchNul = true;
										} else {
											this.removePawn(pawn);
										}
									}
								}
							}

							if (!isZen) {
								if (this.zen.getX() == p.getX() && this.zen.getY() == p.getY()) {
									this.removePawn(this.zen);
								}
							}

						} else {
							do {
								do {
									p = this.selectPawn();
									if (p == null) {
										System.out.println("Il faut choisir un de ses pions ou le pion Zen");
									}
								} while (p == null);

								oldCoor = p.getPosition();

							} while (!currentPlayer.play(p, opponentPlayer.getPawns()));

							this.boardGame[oldCoor[0]][oldCoor[1]].setOccupy(false);

							for (Pawn pawn : opponentPlayer.pawnListPlayer) {
								if (pawn.getX() == p.getX() && pawn.getY() == p.getY()) {
									for (Pawn pawn1 : opponentPlayer.pawnListPlayer) {
										if (!pawn.isVisite() && pawn1.isVisite()) {
											this.removePawn(pawn);
											this.matchNul = true;
										} else {
											this.removePawn(pawn);
										}
									}


								}
							}

						}
					} else {
						if (zen != null) {
							p = currentPlayer.play(opponentPlayer.getPawns(), zen);

							if (p.equals(this.zen)) {
								isZen = true;
							}

							if (isZen) {
								this.zen = p;
							}

							for (Pawn pawn : opponentPlayer.getPawns()) {
								if (pawn.getX() == p.getX() && pawn.getY() == p.getY()) {
									for (Pawn pawn1 : opponentPlayer.pawnListPlayer) {
										if (!pawn.isVisite() && pawn1.isVisite()) {
											this.removePawn(pawn);
											this.matchNul = true;
										} else {
											this.removePawn(pawn);
										}
									}
								}
							}

							if (!isZen) {
								if (this.zen.getX() == p.getX() && this.zen.getY() == p.getY()) {
									this.removePawn(this.zen);
								}
							}

						} else {
							p = currentPlayer.play(opponentPlayer.getPawns());

							for (Pawn pawn : opponentPlayer.getPawns()) {
								if (pawn.getX() == p.getX() && pawn.getY() == p.getY()) {
									for (Pawn pawn1 : opponentPlayer.pawnListPlayer) {
										if (!pawn.isVisite() && pawn1.isVisite()) {
											this.removePawn(pawn);
											this.matchNul = true;
										} else {
											this.removePawn(pawn);
										}
									}
								}
							}
						}
					}
				}

				this.boardGame[p.getX()][p.getY()].setOccupy(true);

				if (this.isDraw()) {
					enCours = false;
				} else if (this.win(opponentPlayer)) {
					enCours = false;
				} else if (this.win(currentPlayer)) {
					enCours = false;
				}

				if (enCours) {

					for (Pawn p1 : playerOne.getPawns()) {
						if (p1.getX() == 1000 && p1.getY() == 1000) {
							p1.setVisited(true);
						}
					}

					for (Pawn p2 : playerTwo.getPawns()) {
						if (p2.getX() == 1000 && p2.getY() == 1000) {
							p2.setVisited(true);
						}
					}

					if (this.zen != null) {
						this.zen.setVisited(false);
					}

					changeCurrentPlayer();
				}
			}

			displayBoard();
			try {
				// Permet de faire une pause de 3 secondes afin de voir les résultats
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}

			nbToursJoues++;

			if (turns == nbToursJoues) {
				this.endOfGame();
			} else {
				if (this.win(currentPlayer)) {
					System.out.println("Bravo à " + currentPlayer.getName());
					if (currentPlayer.equals(playerOne)) {
						victoryPlayerOne++;
						perdant = playerTwo;
					} else {
						victoryPlayerTwo++;
						perdant = playerOne;
					}
				} else {
					System.out.println("Match nul !");
				}
				System.out.println("Score : ");
				System.out.println(playerOne.getName() + " : " + victoryPlayerOne + " point(s)");
				System.out.println(playerTwo.getName() + " : " + victoryPlayerTwo + " point(s)");
				System.out.println("On passe au tour numéro " + (nbToursJoues + 1));
				this.newTurn();
			}
		}
	}

	/**
	 * Method for resetting game elements between each round
	 */
	public void resetValues(){
		this.playerOne = null;
		this.playerTwo = null;
		this.currentPlayer = null;
		this.zen = null;
		this.boardGame = null;
		this.matchNul = false;
	}

	/**
	 * Method triggered to finish the game for the text version
	 */
	public void endOfGame() {
		if(version == 0) {
			if (turns == 1) {
				if (victoryPlayerOne > victoryPlayerTwo) {
					System.out.println("Bravo à " + playerOne.getName() + " qui remporte la partie");
				}
			} else {
				if (victoryPlayerOne > victoryPlayerTwo) {
					System.out.println("Bravo à " + playerOne.getName() + " qui remporte la partie avec " + victoryPlayerOne + " point(s)");
				} else if (victoryPlayerOne < victoryPlayerTwo) {
					System.out.println("Bravo à " + playerTwo.getName() + " qui remporte la partie avec " + victoryPlayerTwo + " point(s)");
				} else {
					System.out.println("Aucun vainqueur ! Chaque joueur aura remporté " + victoryPlayerTwo + " tours chacun");
				}
			}
			System.exit(0);
		}
	}

	/**
	 * Board game access method
	 * @return boardGame : the board of the game
	 */
	public Square[][] getBoardGame() {
		return this.boardGame;
	}

	/**
	 * Player One access method
	 * @return playerOne : the first player
	 */
	public Player getPlayerOne() {
		return this.playerOne;
	}

	/**
	 * Player Two access method
	 * @return playerTwo : the second player
	 */
	public Player getPlayerTwo() {
		return this.playerTwo;
	}

	/**
	 * Current player access method
	 * @return currentPlayer : the current player
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Zen pawn access method
	 * @return zen : the zen pawn
	 */
	public Pawn getZen() {
		return this.zen;
	}

	/**
	 * Information message access method
	 * @return indicationGUI : the information message for the graphic version
	 */
	public String getIndicationGUI(){
		return this.indicationGUI;
	}

	/**
	 * matchNul boolean modification method
	 * @param matchNul the new matchNul
	 */
	public void setMatchNul(boolean matchNul) {
		this.matchNul = matchNul;
	}

	/**
	 * iaBegin boolean access method
	 * @return iaBegin : true if the AI begin the game else false
	 */
	public boolean isIaBegin(){
		return this.iaBegin;
	}

	/**
	 * Method of modification of the boolean iaBegin
	 * @param b the new iaBegin
	 */
	public void setIaBegin(boolean b){
		this.iaBegin = b;
	}

	/**
	 * Access method of the number of turns played
	 * @return nbToursJoues : the number of turns played
	 */
	public int getNbToursJoues() {
		return this.nbToursJoues;
	}

	/**
	 * Access method of the number of turns of the game
	 * @return turns : the number of turns of the game
	 */
	public int getTurns(){
		return this.turns;
	}

	/**
	 * Method of modification of the number of turns played
	 * @param i the new number of turns played
	 */
	public void setNbToursJoues(int i){
		this.nbToursJoues = i;
	}

	/**
	 * Method of modification of the perdant player
	 * @param perdant the player who lost the turn
	 */
	public void setPerdant(Player perdant) {
		this.perdant = perdant;
	}

	/**
	 * Method of modification of the number of wins of the first player
	 * @param victoryPlayerOne the new number of wins of the first player
	 */
	public void setVictoryPlayerOne(int victoryPlayerOne) {
		this.victoryPlayerOne = victoryPlayerOne;
	}

	/**
	 * Method of modification of the number of wins of the second player
	 * @param victoryPlayerTwo the new number of wins of the second player
	 */
	public void setVictoryPlayerTwo(int victoryPlayerTwo) {
		this.victoryPlayerTwo = victoryPlayerTwo;
	}

	/**
	 * Access method of the number of wins of the first player
	 * @return victoryPlayerOne : the number of wins of the first player
	 */
	public int getVictoryPlayerOne() {
		return this.victoryPlayerOne;
	}

	/**
	 * Access method of the number of wins of the second player
	 * @return victoryPlayerOne : the number of wins of the second player
	 */
	public int getVictoryPlayerTwo() {
		return this.victoryPlayerTwo;
	}
}