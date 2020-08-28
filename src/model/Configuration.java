package model;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class of Configuration
 * The class set up and start a game
 * @author DELAUNAY Gurwan 1C1
 */

public class Configuration {

	private Game gamePlay;
	private Mode mode;
	private int turns;
	private int difficulty;
	private String playerName1;
	private String playerName2;
	private static int WIDTH = 11;
	private static int HEIGHT = 11;
	private Scanner scanner;
	private int version;

	/**
	 * Constructor for the text version
	 * @param fileName the file with game parameters
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 */
	public Configuration(String fileName, String playerName1, String playerName2,int version,Scanner in) {
		if(fileName != null && playerName1 != null && playerName2 != null) {
			this.playerName1 = playerName1;
			this.playerName2 = playerName2;
			this.version = version;
			this.configure(fileName);
			this.printConfiguration();
			if(version == 0) {
				this.scanner = in;
			}
			gamePlay = new Game(difficulty,turns,playerName1,playerName2,mode,WIDTH,HEIGHT,version,this.scanner,this);
			gamePlay.start(); /* lance la boucle du jeu */
		} else {
			System.err.println("Configuration : parameter error");
		}
	}

	/**
	 * Constructor for the graphic version
	 * @param fileName the file with game parameters
	 * @param playerName1 the name of the first player
	 * @param playerName2 the name of the second player
	 */
	public Configuration(String fileName, String playerName1, String playerName2, int version) {
		if(fileName != null && playerName1 != null && playerName2 != null) {
			this.playerName1 = playerName1;
			this.playerName2 = playerName2;
			this.version = version;
			this.configure(fileName);
			this.printConfiguration();
			gamePlay = new Game(difficulty,turns,playerName1,playerName2,mode,WIDTH,HEIGHT,version,this);
		} else {
			System.err.println("Configuration : parameter error");
		}
	}

	/**
	 * Constructor to load a part for the text version
	 * @param in the user input scanner
	 */
	public Configuration(Scanner in) {
		this.scanner = in;
	}

	/**
	 * Constructor to load a part for the graphic version
	 */
	public Configuration() {}

	/**
	 * Method allows to read the config file and to initialize attributes
	 * @param fileName the config file
	 */
	private void configure(String fileName) {
		try {
			Scanner scanner = new Scanner(new FileReader(fileName)).useDelimiter("\\s*:\\s*");

			try {
				scanner.next();
				int modeConfig = Integer.valueOf(scanner.next());
				if(modeConfig == 1){
					this.mode = Mode.HA;
				} else {
					this.mode = Mode.HH;
				}
			} catch(NoSuchElementException e) {
				System.err.println("configure : mode " + e.getMessage());
			}

			if(version == 0) {
				if (this.mode == Mode.HA) {
					try {
						scanner.next();
						this.difficulty = Integer.valueOf(scanner.next());
					} catch (NoSuchElementException e) {
						System.err.println("configure : difficulty " + e.getMessage());
					}
				}
			} else {
				try {
					scanner.next();
					this.difficulty = Integer.valueOf(scanner.next());
				} catch (NoSuchElementException e) {
					System.err.println("configure : difficulty " + e.getMessage());
				}
			}

			try {
				scanner.next();
				this.turns = Integer.valueOf(scanner.next());
			} catch (NoSuchElementException e) {
				System.err.println("configure : turns " + e.getMessage());
			}

			scanner.close();
		} catch(FileNotFoundException e) {
			System.err.println("configure - Fichier non trouve : " + fileName);
		}
	}

	/**
	 * Method presenting the configuration of the game
	 */
	public void printConfiguration() {
		if(this.version == 0) {
			System.out.println("La partie se déroulera sur " + this.turns + " tour(s)");
			System.out.println("Elle opposera " + this.playerName1 + " à " + this.playerName2);
			if (this.mode == Mode.HA) {
				String diff;
				if (difficulty == 1) {
					diff = "débutant";
				} else if (difficulty == 2) {
					diff = "confirmé";
				} else {
					diff = "expert";
				}
				System.out.println(this.playerName2 + " ayant un niveau " + diff);
			}
		}
	}

	/**
	 * Method allowing to load a game
	 * @param s the name of the save
	 * @return ret : true if the game is load else false
	 */
	public boolean loadGame(String s) {
		boolean ret = true;
		try {
			FileInputStream fileInputStream = new FileInputStream("./ZenGame/save/" + s);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			this.gamePlay = (Game) objectInputStream.readObject();

			objectInputStream.close();

			if(version == 0) {
				this.gamePlay.gameIsLoad(scanner,this);
				this.gamePlay.start();
			}

		} catch (FileNotFoundException e) {
			if(version == 0) {
				System.err.println("Partie non trouvée ! Entre le nom de la partie que tu veux charger : ");
			}
			ret = false;
		} catch (ClassNotFoundException | IOException e){
			System.err.println(e.getMessage());
		}

		return ret;
	}

	/**
	 * Method allowing to save a game
	 * @param s the name of the save
	 */
	public void saveGame(String s) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("./ZenGame/save/" + s);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(gamePlay);

			objectOutputStream.close();
		} catch (FileNotFoundException e){
			System.err.println("Impossible de sauvegarder le fichier : FileNotFoundException");
		} catch (IOException e){
			System.err.println("Impossible de sauvegarder le fichier");
		}

	}

	/**
	 * Gameplay access method
	 * @return gamePlay : the game
	 */
	public Game getGamePlay() {
		return this.gamePlay;
	}
}