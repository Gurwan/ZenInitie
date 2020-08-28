package model;

import controller.ConsoleControl;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Abstract class of Player
 * @author DELAUNAY Gurwan 1C1
 **/
public abstract class Player implements Serializable {
	
	protected String name;
	protected Color color;
	protected ArrayList<Pawn> pawnListPlayer;
	protected int version;
	protected transient ConsoleControl consoleControl;

	/**
	 * Constructor of the Player
	 * @param name the name of the Player
	 * @param color the color of the pawns of the Player
	 * @param version the version of the game
	 * @param board the board game
	 * @param consoleControl the console control for the text version
	 */
	public Player(String name, Color color, int version, Square[][] board, ConsoleControl consoleControl) {
		this.name = name;
		this.color = color;
		this.version = version;
		this.createPawnList();
		this.initializePawnList(board);
		this.consoleControl = consoleControl;
	}

	/**
	 * name access method
	 * @return name : the name of the player
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Method of modification of the name
	 * @param s the new name of the Player
	 */
	public void setName(String s){
		this.name = s;
	}

	/**
	 * color access method
	 * @return name : the color of pawns of the player
	 */
	public Color getColor(){
		return this.color;
	}

	/**
	 * Method of modification of the color
	 * @param c the new color of pawns of the Player
	 */
	public void setColor(Color c){
		this.color = c;
	}

	/**
	 * pawnList access method
	 * @return pawnListPlayer : the pawns of the Player
	 */
	public ArrayList<Pawn> getPawns(){
		return this.pawnListPlayer;
	}

	/**
	 * Method of modification of the pawnList
	 * @param pawnL the new list of pawns of the Player
	 */
	public void setPawns(ArrayList<Pawn> pawnL){
		this.pawnListPlayer = pawnL;
	}

	/**
	 * Abstract method allowing the player to play if the zen pawn is not null for the text version
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @param zen the zen pawn
	 * @return true if the player has played
	 */
	public abstract boolean play(Pawn p, ArrayList<Pawn> opponentPawnList, Pawn zen);

	/**
	 * Abstract method allowing the player to play if the zen pawn is null for the text version
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @return true if the player has played
	 */
	public abstract boolean play(Pawn p, ArrayList<Pawn> opponentPawnList);

	/**
	 * Abstract method allowing the player to play if the zen pawn is not null for the graphic version
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @param zen the zen pawn
	 * @param coord the coordinate of the selected pawn
	 * @return true if the player has played
	 */
	public abstract boolean play(Pawn p, ArrayList<Pawn> opponentPawnList, Pawn zen, int[] coord);

	/**
	 * Abstract method allowing the player to play if the zen pawn is null for the graphic version
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @param coord the coordinate of the selected pawn
	 * @return true if the player has played
	 */
	public abstract boolean play(Pawn p, ArrayList<Pawn> opponentPawnList, int[] coord);

	/**
	 * Abstract method allowing the player to play if the zen pawn is not null for the AI player
	 * @param opponentPawnList the list of the opponent pawn
	 * @param zen the zen pawn
	 * @return the pawn who moved
	 */
	public abstract Pawn play(ArrayList<Pawn> opponentPawnList, Pawn zen);

	/**
	 * Abstract method allowing the player to play if the zen pawn is null for the AI player
	 * @param opponentPawnList the list of the opponent pawn
	 * @return the pawn who moved
	 */
	public abstract Pawn play(ArrayList<Pawn> opponentPawnList);

	/**
	 * This method is a depth first search allowing to know if all the pawns are aligned.
	 * It's a recursive method.
	 * @param p the pawn whose neighbors we want to know
	 * @param currentPawns all the pawns we want to know if they are aligned
	 * @param zen the zen pawn
	 */
	public void parcoursDFS(Pawn p,ArrayList<Pawn> currentPawns,Pawn zen){
		ArrayList<Pawn> voisins = p.getNeighbors(currentPawns,zen);
		p.setVisited(true);
		for (Pawn pawn : voisins) {
			if (pawn != null && !pawn.isVisite()) {
				parcoursDFS(pawn, currentPawns,zen);
			}
		}
	}

	/**
	 * This method is a depth first search allowing to know if all the pawns are aligned.
	 * It's a recursive method.
	 * @param p the pawn whose neighbors we want to know
	 * @param currentPawns all the pawns we want to know if they are aligned
	 */
	public void parcoursDFS(Pawn p,ArrayList<Pawn> currentPawns){
		ArrayList<Pawn> voisins = p.getNeighbors(currentPawns,null);
		p.setVisited(true);
		for (Pawn pawn : voisins) {
			if (pawn != null && !pawn.isVisite()) {
				parcoursDFS(pawn, currentPawns);
			}
		}
	}

	/**
	 * Method for creating a list of pawns
	 */
	protected void createPawnList(){
		ArrayList<Pawn> pawnArrayList = new ArrayList<Pawn>();

		if(this.color == Color.BLACK) {
			for (int a = 0; a < 13; a++) {
				pawnArrayList.add(new Pawn(1));
			}
		} else {
			for (int a = 0; a < 13; a++) {
				pawnArrayList.add(new Pawn(0));
			}
		}

		this.pawnListPlayer = pawnArrayList;
	}

	/**
	 * Method initializing the player's pawn list
	 * @param board the board game
	 */
	protected void initializePawnList(Square[][] board){
		int k = 0;

		if(this.color == Color.BLACK){
			for(int i = 0;i<board.length; i++){
				for(int j =0 ; j<board[i].length; j++){
					if(board[i][j].getSign() != null) {
						if (board[i][j].getSign().equals("chinese")) {
							if (k < 12) {
								this.pawnListPlayer.get(k).setPosition(i, j);
							}
							k++;
						}
					}
				}
			}
		} else {
			for(int i = 0;i<board.length; i++){
				for(int j =0 ; j<board[i].length; j++){
					if(board[i][j].getSign() != null) {
						if (board[i][j].getSign().equals("geometric")) {
							if (k < 12) {
								this.pawnListPlayer.get(k).setPosition(i, j);
							}
							k++;
						}
					}
				}
			}
		}
	}

	/**
	 * Method of modification of the ConsoleControl
	 * @param cc the new console control
	 */
	public void setConsoleControl(ConsoleControl cc){
		this.consoleControl = cc;
	}


}