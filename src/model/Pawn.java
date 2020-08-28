package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class of Pawn
 * The class allows to create a pawn and initialize his location on the board
 * @author DELAUNAY Gurwan 1C1
 * */
public class Pawn implements Serializable {

	private int type;
	private int x;
	private int y;
	private boolean inGame;
	private int[] oldCoordZen;
	private boolean visite;

	/**
	 * First constructor of Pawn
	 * @param type the type of the pawn (white,black,zen)
	 * @param x the position of the pawn on the board in x-coordinate
	 * @param y the position of the pawn on the board in y-coordinate
	 */
	public Pawn(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.inGame = true;
		this.oldCoordZen = null;
	}

	/**
	 * Second constructor of Pawn
	 * @param type the type of the pawn (white,black,zen)
	 */
	public Pawn(int type) {
		this.type = type;
		this.inGame = true;
		this.oldCoordZen = null;
		this.x = 1000;
		this.y = 1000;
	}

	/**
	 * Third constructor of Pawn
	 * This constructor allows to create a test pawn that is not in play and has no type
	 */
	public Pawn(){
		inGame = false;
		type = 3;
	}

	/**
	 * type access method
	 * @return type : the type of the pawn
	 **/
	public int getType() {
		// white -> 0 black -> 1 zen -> 2 test -> 3
		return this.type;
	}

	/**
	 * Method of modification of the type of the pawn
	 * @param t the new type of the pawn
	 */
	public void setType(int t) {
		this.type = t;
	}

	/**
	 * position access method
	 * @return ret : the position of the pawn on the board with x and y coordinates
	 **/
	public int[] getPosition() {
		int[] ret = new int[2];
		ret[0] = this.x;
		ret[1] = this.y;
		return ret;
	}

	/**
	 * Method of modification of the position of the pawn
	 * @param x the new x-coordinate of the pawn
	 * @param y the new y-coordinate of the pawn
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * inGame access method
	 * @return inGame : true if the pawn is not remove of the game else false
	 **/
	public boolean getInGame() {
		return this.inGame;
	}

	/**
	 * Method of modification of the inGame status of the Pawn
	 * @param inGame : the new state of the pawn
	 */
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	/**
	 * Method checking that the pawn is moving in the right direction
	 * @return ret : true if the rule is respected else false
	 */
	public boolean rules1(int[] coord) {
		boolean ret = false;
		int newX = coord[0];
		int newY = coord[1];

		if(newX != x && newY != y){
			for(int i =0; i<11; i++){
				if(newX == x+i && newY == y+i){
					ret = true;
				} else if(newX == x-i && newY == y+i){
					ret = true;
				} else if(newX == x-i && newY == y-i){
					ret = true;
				} else if(newX == x+i && newY == y-i){
					ret = true;
				}
			}
		} else {
			ret = true;
		}
		return ret;
	}

	/**
	 * Method checking that the pawn moves as many squares as pawns on the moving line
	 * @return ret : true if the rule is respected else false
	 */
	public boolean rules2(int[] coord,ArrayList<Pawn> myPawn,ArrayList<Pawn> pawnOpponent,Pawn zen) {
		boolean ret = false;
		int newX = coord[0];
		int newY = coord[1];
		String direction = "";
		int compteurPawn = 0;

		if(newX != this.x && newY != this.y){
			for(int i =0; i<11; i++){
				if(newX == this.x+i && newY == this.y+i){
					direction = "BD";
				} else if(newX == this.x-i && newY == this.y+i){
					direction = "HD";
				} else if(newX == this.x-i && newY == this.y-i){
					direction = "HG";
				} else if(newX == this.x+i && newY == this.y-i){
					direction = "BG";
				}
			}
		} else {
			if(newX == this.x){
				direction = "H";
			} else {
				direction = "V";
			}
		}

		if(direction.equals("H")){
			if(zen.getX() == newX){
				compteurPawn++;
			}

			for(Pawn p : myPawn){
				if(p.getX() == newX){
					compteurPawn++;
				}
			}

			for(Pawn p : pawnOpponent){
				if(p.getX() == newX){
					compteurPawn++;
				}
			}

			if(newY == y+compteurPawn || newY == y-compteurPawn){
				ret = true;
			}
		} else if(direction.equals("V")){
			if(zen.getY() == newY){
				compteurPawn++;
			}

			for(Pawn p : myPawn){
				if(p.getY() == newY){
					compteurPawn++;
				}
			}

			for(Pawn p : pawnOpponent){
				if(p.getY() == newY){
					compteurPawn++;
				}
			}

			if(newX == x+compteurPawn || newX == x-compteurPawn){
				ret = true;
			}

		} else if(direction.equals("HD") || direction.equals("BG")){

			for(int i =0; i<11; i++) {
				if(zen.getX() == this.x+i && zen.getY() == this.y-i){
					compteurPawn++;
				} else if(zen.getX() == this.x-i && zen.getY() == this.y+i){
					compteurPawn++;
				}
			}

			for(Pawn p : myPawn){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y-i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y+i){
						compteurPawn++;
					}
				}
			}

			for(Pawn p : pawnOpponent){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y-i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y+i){
						compteurPawn++;
					}
				}
			}

			if(newX == x+compteurPawn && newY == y-compteurPawn){
				ret = true;
			} else if(newX == x-compteurPawn && newY == y+compteurPawn){
				ret = true;
			}

		} else if(direction.equals("BD") || direction.equals("HG")){
			for(int i =0; i<11; i++) {
				if(zen.getX() == this.x+i && zen.getY() == this.y+i){
					compteurPawn++;
				} else if(zen.getX() == this.x-i && zen.getY() == this.y-i){
					compteurPawn++;
				}
			}

			for(Pawn p : myPawn){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y+i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y-i){
						compteurPawn++;
					}
				}
			}

			for(Pawn p : pawnOpponent){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y+i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y-i){
						compteurPawn++;
					}
				}
			}

			if(newX == x+compteurPawn && newY == y+compteurPawn){
				ret = true;
			} else if(newX == x-compteurPawn && newY == y-compteurPawn){
				ret = true;
			}
		}

		return ret;
	}

	public boolean rules2(int[] coord,ArrayList<Pawn> myPawn,ArrayList<Pawn> pawnOpponent) {
		boolean ret = false;
		int newX = coord[0];
		int newY = coord[1];
		String direction = "";
		int compteurPawn = 0;

		if(newX != this.x && newY != this.y){
			for(int i =0; i<11; i++){
				if(newX == this.x+i && newY == this.y+i){
					direction = "BD";
				} else if(newX == this.x-i && newY == this.y+i){
					direction = "HD";
				} else if(newX == this.x-i && newY == this.y-i){
					direction = "HG";
				} else if(newX == this.x+i && newY == this.y-i){
					direction = "BG";
				}
			}
		} else {
			if(newX == this.x){
				direction = "H";
			} else {
				direction = "V";
			}
		}

		if(direction.equals("H")){
			for(Pawn p : myPawn){
				if(p.getX() == newX){
					compteurPawn++;
				}
			}

			for(Pawn p : pawnOpponent){
				if(p.getX() == newX){
					compteurPawn++;
				}
			}

			if(newY == y+compteurPawn || newY == y-compteurPawn){
				ret = true;
			}
		} else if(direction.equals("V")){
			for(Pawn p : myPawn){
				if(p.getY() == newY){
					compteurPawn++;
				}
			}

			for(Pawn p : pawnOpponent){
				if(p.getY() == newY){
					compteurPawn++;
				}
			}

			if(newX == x+compteurPawn || newX == x-compteurPawn){
				ret = true;
			}

		} else if(direction.equals("HD") || direction.equals("BG")){

			for(Pawn p : myPawn){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y-i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y+i){
						compteurPawn++;
					}
				}
			}

			for(Pawn p : pawnOpponent){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y-i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y+i){
						compteurPawn++;
					}
				}
			}

			if(newX == x+compteurPawn && newY == y-compteurPawn){
				ret = true;
			} else if(newX == x-compteurPawn && newY == y+compteurPawn){
				ret = true;
			}

		} else if(direction.equals("BD") || direction.equals("HG")){
			for(Pawn p : myPawn){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y+i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y-i){
						compteurPawn++;
					}
				}
			}

			for(Pawn p : pawnOpponent){
				for(int i =0; i<11; i++) {
					if(p.getX() == this.x+i && p.getY() == this.y+i){
						compteurPawn++;
					} else if(p.getX() == this.x-i && p.getY() == this.y-i){
						compteurPawn++;
					}
				}
			}

			if(newX == x+compteurPawn && newY == y+compteurPawn){
				ret = true;
			} else if(newX == x-compteurPawn && newY == y-compteurPawn){
				ret = true;
			}
		}

		return ret;
	}

	/**
	 * Method checking that the pawn goes over the pawn of its color only
	 * @return ret : true if the rule is respected else false
	 */
	public boolean rules3(int[] coord,ArrayList<Pawn> pawnOpponent) {
		boolean ret = true;
		int newX = coord[0];
		int newY = coord[1];
		String direction = "";

		if(newX != this.x && newY != this.y){
			for(int i =0; i<11; i++){
				if(newX == this.x+i && newY == this.y+i){
					direction = "BD";
				} else if(newX == this.x-i && newY == this.y+i){
					direction = "HD";
				} else if(newX == this.x-i && newY == this.y-i){
					direction = "HG";
				} else if(newX == this.x+i && newY == this.y-i){
					direction = "BG";
				}
			}
		} else {
			if(newX == this.x){
				direction = "H";
			} else {
				direction = "V";
			}
		}

		if(direction.equals("H")){
			int distanceNewAndOld = newY - y;
			for(Pawn p : pawnOpponent){
				if(p.getX() == x) {
					if (distanceNewAndOld > 0) {
						if (p.getY() < newY && p.getY() > y) {
							ret = false;
						}
					} else {
						if (p.getY() > newY && p.getY() < y) {
							ret = false;
						}
					}
				}
			}
		} else if(direction.equals("V")){
			int distanceNewAndOld = newX - x;
			for(Pawn p : pawnOpponent){
				if(p.getY() == y) {
					if (distanceNewAndOld > 0) {
						if (p.getX() < newX && p.getX() > x) {
							ret = false;
						}
					} else {
						if (p.getX() > newX && p.getX() < x) {
							ret = false;
						}
					}
				}
			}
		} else if(direction.equals("HD") || direction.equals("BG")){
			int distanceNewAndOldX = newX - x;
			distanceNewAndOldX = Math.abs(distanceNewAndOldX);

			for(Pawn p : pawnOpponent){
				if(direction.equals("HD")) {
					for (int i = 1; i < distanceNewAndOldX; i++) {
						if (p.getX() == x - i && p.getY() == y + i){
							ret = false;
						}
					}
				} else {
					for (int i = 1; i < distanceNewAndOldX; i++) {
						if (p.getX() == x + i && p.getY() == y - i){
							ret = false;
						}
					}
				}
			}
		} else if(direction.equals("BD") || direction.equals("HG")){
			int distanceNewAndOldX = newX - x;
			distanceNewAndOldX = Math.abs(distanceNewAndOldX);

			for(Pawn p : pawnOpponent){
				if(direction.equals("BD")){
					for (int i = 1; i < distanceNewAndOldX; i++) {
						if (p.getX() == x + i && p.getY() == y + i){
							ret = false;
						}
					}
				} else {
					for (int i = 1; i < distanceNewAndOldX; i++) {
						if (p.getX() == x - i && p.getY() == y - i){
							ret = false;
						}
					}
				}
			}
		}
		return ret;
	}

	/**
	 * if the pawn is zen he cannot redo the same movement and he can be moved only if he is in contact with other pawn
	 * @return ret : true if the rule is respected else false
	 */
	public boolean rulesZen(int[] coord,Pawn zen,ArrayList<Pawn> opponentPawn, ArrayList<Pawn> myPawn) {
		boolean ret = false;

		if(zen.getX() == 0 && zen.getY() == 0){
			// pour opponent et my si pion sur case (1,0) ou (1,1) ou (0,1) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == 1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 1){
					ret = true;
				} else if(p.getX() == 0 && p.getY() == 1){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == 1 && p.getY() == 0){
						ret = true;
					} else if(p.getX() == 1 && p.getY() == 1){
						ret = true;
					} else if(p.getX() == 0 && p.getY() == 1){
						ret = true;
					}
				}
			}
		} else if(zen.getX() == 0 && zen.getY() > 0 && zen.getY() < 10){
			// pour opponent et my si pion sur case (0,y-1) ou (0,y+1) ou (x+1,y-1) ou (x+1,y) ou (x+1,y+1) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == 0 && p.getY() == zen.getY()-1){
					ret = true;
				} else if(p.getX() == 0 && p.getY() == zen.getY()+1){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()-1){
					ret = true;
				}  else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()+1){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == 0 && p.getY() == zen.getY()-1){
						ret = true;
					} else if(p.getX() == 0 && p.getY() == zen.getY()+1){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()-1){
						ret = true;
					}  else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()+1){
						ret = true;
					}
				}
			}
		} else if(zen.getX() == 0 && zen.getY() == 10){
			// pour opponent et my si pion sur case (0,9) ou (1,9) ou (1,10) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == 0 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 10){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == 0 && p.getY() == 9){
						ret = true;
					} else if(p.getX() == 1 && p.getY() == 9){
						ret = true;
					} else if(p.getX() == 1 && p.getY() == 10){
						ret = true;
					}
				}
			}
		} else if(zen.getX() > 0 && zen.getX() < 10 && zen.getY() == 0) {
			// pour opponent et my si pion sur case (x-1,0) ou (x+1,0) ou (x+1,y+1) ou (x,y+1) ou (x-1,y+1) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == zen.getX()-1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()+1){
					ret = true;
				}  else if(p.getX() == zen.getX() && p.getY() == zen.getY()+1){
					ret = true;
				} else if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()+1){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == zen.getX()-1 && p.getY() == 0){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == 0){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()+1){
						ret = true;
					}  else if(p.getX() == zen.getX() && p.getY() == zen.getY()+1){
						ret = true;
					} else if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()+1){
						ret = true;
					}
				}
			}
		} else if(zen.getX() == 10 && zen.getY() == 0) {
			// pour opponent et my si pion sur case (9,0) ou (9,1) ou (10,1) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == 9 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == 1){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == 1){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == 9 && p.getY() == 0){
						ret = true;
					} else if(p.getX() == 9 && p.getY() == 1){
						ret = true;
					} else if(p.getX() == 10 && p.getY() == 1){
						ret = true;
					}
				}
			}
		} else if(zen.getX() == 10 && zen.getY() > 0 && zen.getY() < 10) {
			// pour opponent et my si pion sur case (10,y-1) ou (10,y+1) ou (9,y+1) ou (9,y) ou (9,y-1) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == 10 && p.getY() == zen.getY()-1){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == zen.getY()+1){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == zen.getY()+1){
					ret = true;
				}  else if(p.getX() == 9 && p.getY() == zen.getY()){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == zen.getY()-1){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == 10 && p.getY() == zen.getY()-1){
						ret = true;
					} else if(p.getX() == 10 && p.getY() == zen.getY()+1){
						ret = true;
					} else if(p.getX() == 9 && p.getY() == zen.getY()+1){
						ret = true;
					}  else if(p.getX() == 9 && p.getY() == zen.getY()){
						ret = true;
					} else if(p.getX() == 9 && p.getY() == zen.getY()-1){
						ret = true;
					}
				}
			}
		} else if(zen.getX() == 10 && zen.getY() == 10){
			// pour opponent et my si pion sur case (9,9) ou (9,10) ou (10,9) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == 9 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == 9){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == 9 && p.getY() == 9){
						ret = true;
					} else if(p.getX() == 9 && p.getY() == 10){
						ret = true;
					} else if(p.getX() == 10 && p.getY() == 9){
						ret = true;
					}
				}
			}
		} else if(zen.getX() > 0 && zen.getX() < 10 && zen.getY() == 10) {
			// pour opponent et my si pion sur case (x-1,10) ou (x+1,10) ou (x+1,9) ou (x,9) ou (x-1,9) alors peut bouger
			for(Pawn p : opponentPawn){
				if(p.getX() == zen.getX()-1 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == 9){
					ret = true;
				}  else if(p.getX() == zen.getX() && p.getY() == 9){
					ret = true;
				} else if(p.getX() == zen.getX()-1 && p.getY() == 9){
					ret = true;
				}
			}

			if(!ret){
				for(Pawn p : myPawn){
					if(p.getX() == zen.getX()-1 && p.getY() == 10){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == 10){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == 9){
						ret = true;
					}  else if(p.getX() == zen.getX() && p.getY() == 9){
						ret = true;
					} else if(p.getX() == zen.getX()-1 && p.getY() == 9){
						ret = true;
					}
				}
			}
		} else {
			//cas général si Zen se trouve sur une case au 'milieu' du plateau
			for(Pawn p : opponentPawn){
				if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()){
					ret = true;
				} else if(p.getX() == zen.getX() && p.getY() == zen.getY()+1){
					ret = true;
				} else if(p.getX() == zen.getX() && p.getY() == zen.getY()-1){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()-1){
					ret = true;
				} else if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()-1){
					ret = true;
				} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()+1){
					ret = true;
				} else if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()+1){
					ret = true;
				}
			}

			if(!ret){
				//si l'algo n'a pas trouvé de pion de l'autre joueur à proximité de Zen
				for (Pawn p : myPawn){
					if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()){
						ret = true;
					} else if(p.getX() == zen.getX() && p.getY() == zen.getY()+1){
						ret = true;
					} else if(p.getX() == zen.getX() && p.getY() == zen.getY()-1){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()-1){
						ret = true;
					} else if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()-1){
						ret = true;
					} else if(p.getX() == zen.getX()+1 && p.getY() == zen.getY()+1){
						ret = true;
					} else if(p.getX() == zen.getX()-1 && p.getY() == zen.getY()+1){
						ret = true;
					}
				}
			}
		}

		//vérifie que Zen ne retourne pas à la même place
		if(Arrays.equals(coord,oldCoordZen)){
			ret = false;
		}

		oldCoordZen = new int[2];
		oldCoordZen[0] = zen.getX();
		oldCoordZen[1] = zen.getY();

		return ret;
	}

	/**
	 * Method for comparing two coordinates with that of a pawn
	 * @param newX the x coordinate
	 * @param newY the y coordinate
	 * @return ret : true if the coordinates are equal else false
	 */
	public boolean sameCoord(int newX,int newY){
		boolean ret;
		if(newX == x && newY == y){
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}

	/**
	 * Access method of the x-coordinate of the pawn
	 * @return x : the x-coordinate of the pawn
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Access method of the y-coordinate of the pawn
	 * @return y : the y-coordinate of the pawn
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Method allows to know the neighbors of the pawn
	 * @param currentPawns the list of pawns belonging to the same group as the pawn
	 * @param zen the zen pawn
	 * @return ret : the list of the neighbors of the pawn
	 */
	public ArrayList<Pawn> getNeighbors(ArrayList<Pawn> currentPawns,Pawn zen){
		ArrayList<Pawn> ret = new ArrayList<Pawn>();

		for(Pawn p : currentPawns){
			if(this.x == 0 && this.y == 0){
				// pour opponent et my si pion sur case (1,0) ou (1,1) ou (0,1) alors peut bouger
				if(p.getX() == 1 && p.getY() == 0){
					ret.add(p);
				} else if(p.getX() == 1 && p.getY() == 1){
					ret.add(p);
				} else if(p.getX() == 0 && p.getY() == 1){
					ret.add(p);
				}
			} else if(this.x == 0 && this.y > 0 && this.y < 10){
				// pour opponent et my si pion sur case (0,y-1) ou (0,y+1) ou (x+1,y-1) ou (x+1,y) ou (x+1,y+1) alors peut bouger
				if(p.getX() == 0 && p.getY() == this.y-1){
					ret.add(p);
				} else if(p.getX() == 0 && p.getY() == this.y+1){
					ret.add(p);
				} else if(p.getX() == this.x+1 && p.getY() == this.y-1){
					ret.add(p);
				}  else if(p.getX() == this.x+1 && p.getY() == this.y){
					ret.add(p);
				} else if(p.getX() == this.x+1 && p.getY() == this.y+1){
					ret.add(p);
				}
			} else if(this.x == 0 && this.y == 10){
				// pour opponent et my si pion sur case (0,9) ou (1,9) ou (1,10) alors peut bouger

				if(p.getX() == 0 && p.getY() == 9){
					ret.add(p);
				} else if(p.getX() == 1 && p.getY() == 9){
					ret.add(p);
				} else if(p.getX() == 1 && p.getY() == 10){
					ret.add(p);
				}

			} else if(this.x > 0 && this.x < 10 && this.y == 0) {
				// pour opponent et my si pion sur case (x-1,0) ou (x+1,0) ou (x+1,y+1) ou (x,y+1) ou (x-1,y+1) alors peut bouger
				if(p.getX() == this.x-1 && p.getY() == 0){
					ret.add(p);
				} else if(p.getX() == this.x+1 && p.getY() == 0){
					ret.add(p);
				} else if(p.getX() == this.x+1 && p.getY() == this.y+1){
					ret.add(p);
				}  else if(p.getX() == this.x && p.getY() == this.y+1){
					ret.add(p);
				} else if(p.getX() == this.x-1 && p.getY() == this.y+1){
					ret.add(p);
				}

			} else if(this.x == 10 && this.y == 0) {
				// pour opponent et my si pion sur case (9,0) ou (9,1) ou (10,1) alors peut bouger
				if(p.getX() == 9 && p.getY() == 0){
					ret.add(p);
				} else if(p.getX() == 9 && p.getY() == 1){
					ret.add(p);
				} else if(p.getX() == 10 && p.getY() == 1){
					ret.add(p);
				}

			} else if(this.x == 10 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (10,y-1) ou (10,y+1) ou (9,y+1) ou (9,y) ou (9,y-1) alors peut bouger
				if(p.getX() == 10 && p.getY() == this.y-1){
					ret.add(p);
				} else if(p.getX() == 10 && p.getY() == this.y+1){
					ret.add(p);
				} else if(p.getX() == 9 && p.getY() == this.y+1){
					ret.add(p);
				}  else if(p.getX() == 9 && p.getY() == this.y){
					ret.add(p);
				} else if(p.getX() == 9 && p.getY() == this.y-1){
					ret.add(p);
				}

			} else if(this.x == 10 && this.y == 10){
				// pour opponent et my si pion sur case (9,9) ou (9,10) ou (10,9) alors peut bouger
				if(p.getX() == 9 && p.getY() == 9){
					ret.add(p);
				} else if(p.getX() == 9 && p.getY() == 10){
					ret.add(p);
				} else if(p.getX() == 10 && p.getY() == 9){
					ret.add(p);
				}

			} else if(this.x > 0 && this.x < 10 && this.y == 10) {
				// pour opponent et my si pion sur case (x-1,10) ou (x+1,10) ou (x+1,9) ou (x,9) ou (x-1,9) alors peut bouger
				if(p.getX() == this.x-1 && p.getY() == 10){
					ret.add(p);
				} else if(p.getX() == this.x+1 && p.getY() == 10){
					ret.add(p);
				} else if(p.getX() == this.x+1 && p.getY() == 9){
					ret.add(p);
				}  else if(p.getX() == this.x && p.getY() == 9){
					ret.add(p);
				} else if(p.getX() == this.x-1 && p.getY() == 9){
					ret.add(p);
				}

			} else {
				//cas général si Zen se trouve sur une case au 'milieu' du plateau
				if (p.getX() == this.x-1 && p.getY() == this.y) {
					ret.add(p);
				} else if (p.getX() == this.x+1 && p.getY() == this.y) {
					ret.add(p);
				} else if (p.getX() == this.x && p.getY() == this.y+1) {
					ret.add(p);
				} else if (p.getX() == this.x && p.getY() == this.y-1) {
					ret.add(p);
				} else if (p.getX() == this.x+1 && p.getY() == this.y-1) {
					ret.add(p);
				} else if (p.getX() == this.x-1 && p.getY() == this.y-1) {
					ret.add(p);
				} else if (p.getX() == this.x+1 && p.getY() == this.y+1) {
					ret.add(p);
				} else if (p.getX() == this.x-1 && p.getY() == this.y+1) {
					ret.add(p);
				}
			}
		}

		if(zen != null) {
			// Zen
			if (this.x == 0 && this.y == 0) {
				// pour opponent et my si pion sur case (1,0) ou (1,1) ou (0,1) alors peut bouger
				if (zen.getX() == 1 && zen.getY() == 0) {
					ret.add(zen);
				} else if (zen.getX() == 1 && zen.getY() == 1) {
					ret.add(zen);
				} else if (zen.getX() == 0 && zen.getY() == 1) {
					ret.add(zen);
				}
			} else if (this.x == 0 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (0,y-1) ou (0,y+1) ou (x+1,y-1) ou (x+1,y) ou (x+1,y+1) alors peut bouger
				if (zen.getX() == 0 && zen.getY() == this.y - 1) {
					ret.add(zen);
				} else if (zen.getX() == 0 && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y - 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y + 1) {
					ret.add(zen);
				}
			} else if (this.x == 0 && this.y == 10) {
				// pour opponent et my si pion sur case (0,9) ou (1,9) ou (1,10) alors peut bouger

				if (zen.getX() == 0 && zen.getY() == 9) {
					ret.add(zen);
				} else if (zen.getX() == 1 && zen.getY() == 9) {
					ret.add(zen);
				} else if (zen.getX() == 1 && zen.getY() == 10) {
					ret.add(zen);
				}

			} else if (this.x > 0 && this.x < 10 && this.y == 0) {
				// pour opponent et my si pion sur case (x-1,0) ou (x+1,0) ou (x+1,y+1) ou (x,y+1) ou (x-1,y+1) alors peut bouger
				if (zen.getX() == this.x - 1 && zen.getY() == 0) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == 0) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x - 1 && zen.getY() == this.y + 1) {
					ret.add(zen);
				}

			} else if (this.x == 10 && this.y == 0) {
				// pour opponent et my si pion sur case (9,0) ou (9,1) ou (10,1) alors peut bouger
				if (zen.getX() == 9 && zen.getY() == 0) {
					ret.add(zen);
				} else if (zen.getX() == 9 && zen.getY() == 1) {
					ret.add(zen);
				} else if (zen.getX() == 10 && zen.getY() == 1) {
					ret.add(zen);
				}

			} else if (this.x == 10 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (10,y-1) ou (10,y+1) ou (9,y+1) ou (9,y) ou (9,y-1) alors peut bouger
				if (zen.getX() == 10 && zen.getY() == this.y - 1) {
					ret.add(zen);
				} else if (zen.getX() == 10 && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == 9 && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == 9 && zen.getY() == this.y) {
					ret.add(zen);
				} else if (zen.getX() == 9 && zen.getY() == this.y - 1) {
					ret.add(zen);
				}

			} else if (this.x == 10 && this.y == 10) {
				// pour opponent et my si pion sur case (9,9) ou (9,10) ou (10,9) alors peut bouger
				if (zen.getX() == 9 && zen.getY() == 9) {
					ret.add(zen);
				} else if (zen.getX() == 9 && zen.getY() == 10) {
					ret.add(zen);
				} else if (zen.getX() == 10 && zen.getY() == 9) {
					ret.add(zen);
				}

			} else if (this.x > 0 && this.x < 10 && this.y == 10) {
				// pour opponent et my si pion sur case (x-1,10) ou (x+1,10) ou (x+1,9) ou (x,9) ou (x-1,9) alors peut bouger
				if (zen.getX() == this.x - 1 && zen.getY() == 10) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == 10) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == 9) {
					ret.add(zen);
				} else if (zen.getX() == this.x && zen.getY() == 9) {
					ret.add(zen);
				} else if (zen.getX() == this.x - 1 && zen.getY() == 9) {
					ret.add(zen);
				}

			} else {
				//cas général si Zen se trouve sur une case au 'milieu' du plateau
				if (zen.getX() == this.x - 1 && zen.getY() == this.y) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y) {
					ret.add(zen);
				} else if (zen.getX() == this.x && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x && zen.getY() == this.y - 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y - 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x - 1 && zen.getY() == this.y - 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y + 1) {
					ret.add(zen);
				} else if (zen.getX() == this.x - 1 && zen.getY() == this.y + 1) {
					ret.add(zen);
				}
			}
		}

		return ret;
	}

	/**
	 * Method to check if the pawn has neighbors
	 * @param arrayList the list of pawns belonging to the same group as the pawn
	 * @return ret : true if the pawn has at least a neighbor
	 */
	public boolean hasNeighbor(ArrayList<Pawn> arrayList){
		boolean ret = false;
		for(Pawn p : arrayList){
			if(this.x == 0 && this.y == 0){
				// pour opponent et my si pion sur case (1,0) ou (1,1) ou (0,1) alors peut bouger
				if(p.getX() == 1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 1){
					ret = true;
				} else if(p.getX() == 0 && p.getY() == 1){
					ret = true;
				}
			} else if(this.x == 0 && this.y > 0 && this.y < 10){
				// pour opponent et my si pion sur case (0,y-1) ou (0,y+1) ou (x+1,y-1) ou (x+1,y) ou (x+1,y+1) alors peut bouger
				if(p.getX() == 0 && p.getY() == this.y-1){
					ret = true;
				} else if(p.getX() == 0 && p.getY() == this.y+1){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == this.y-1){
					ret = true;
				}  else if(p.getX() == this.x+1 && p.getY() == this.y){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == this.y+1){
					ret = true;
				}
			} else if(this.x == 0 && this.y == 10){
				// pour opponent et my si pion sur case (0,9) ou (1,9) ou (1,10) alors peut bouger

				if(p.getX() == 0 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 10){
					ret = true;
				}

			} else if(this.x > 0 && this.x < 10 && this.y == 0) {
				// pour opponent et my si pion sur case (x-1,0) ou (x+1,0) ou (x+1,y+1) ou (x,y+1) ou (x-1,y+1) alors peut bouger
				if(p.getX() == this.x-1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == this.y+1){
					ret = true;
				}  else if(p.getX() == this.x && p.getY() == this.y+1){
					ret = true;
				} else if(p.getX() == this.x-1 && p.getY() == this.y+1){
					ret = true;
				}

			} else if(this.x == 10 && this.y == 0) {
				// pour opponent et my si pion sur case (9,0) ou (9,1) ou (10,1) alors peut bouger
				if(p.getX() == 9 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == 1){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == 1){
					ret = true;
				}

			} else if(this.x == 10 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (10,y-1) ou (10,y+1) ou (9,y+1) ou (9,y) ou (9,y-1) alors peut bouger
				if(p.getX() == 10 && p.getY() == this.y-1){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == this.y+1){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == this.y+1){
					ret = true;
				}  else if(p.getX() == 9 && p.getY() == this.y){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == this.y-1){
					ret = true;
				}

			} else if(this.x == 10 && this.y == 10){
				// pour opponent et my si pion sur case (9,9) ou (9,10) ou (10,9) alors peut bouger
				if(p.getX() == 9 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == 9){
					ret = true;
				}

			} else if(this.x > 0 && this.x < 10 && this.y == 10) {
				// pour opponent et my si pion sur case (x-1,10) ou (x+1,10) ou (x+1,9) ou (x,9) ou (x-1,9) alors peut bouger
				if(p.getX() == this.x-1 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == 9){
					ret = true;
				}  else if(p.getX() == this.x && p.getY() == 9){
					ret = true;
				} else if(p.getX() == this.x-1 && p.getY() == 9){
					ret = true;
				}

			} else {
				//cas général si Zen se trouve sur une case au 'milieu' du plateau
				if (p.getX() == this.x - 1 && p.getY() == this.y) {
					ret = true;
				} else if (p.getX() == this.x + 1 && p.getY() == this.y) {
					ret = true;
				} else if (p.getX() == this.x && p.getY() == this.y + 1) {
					ret = true;
				} else if (p.getX() == this.x && p.getY() == this.y - 1) {
					ret = true;
				} else if (p.getX() == this.x + 1 && p.getY() == this.y - 1) {
					ret = true;
				} else if (p.getX() == this.x - 1 && p.getY() == this.y - 1) {
					ret = true;
				} else if (p.getX() == this.x + 1 && p.getY() == this.y + 1) {
					ret = true;
				} else if (p.getX() == this.x - 1 && p.getY() == this.y + 1) {
					ret = true;
				}
			}
		}
		return ret;
	}

	/**
	 * Method to check if the pawn has neighbors
	 * @param arrayList the list of pawns belonging to the same group as the pawn
	 * @param zen the zen pawn
	 * @return ret : true if the pawn has at least a neighbor
	 */
	public boolean hasNeighbor(ArrayList<Pawn> arrayList,Pawn zen){
		boolean ret = false;
		for(Pawn p : arrayList){
			if(this.x == 0 && this.y == 0){
				// pour opponent et my si pion sur case (1,0) ou (1,1) ou (0,1) alors peut bouger
				if(p.getX() == 1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 1){
					ret = true;
				} else if(p.getX() == 0 && p.getY() == 1){
					ret = true;
				}
			} else if(this.x == 0 && this.y > 0 && this.y < 10){
				// pour opponent et my si pion sur case (0,y-1) ou (0,y+1) ou (x+1,y-1) ou (x+1,y) ou (x+1,y+1) alors peut bouger
				if(p.getX() == 0 && p.getY() == this.y-1){
					ret = true;
				} else if(p.getX() == 0 && p.getY() == this.y+1){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == this.y-1){
					ret = true;
				}  else if(p.getX() == this.x+1 && p.getY() == this.y){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == this.y+1){
					ret = true;
				}
			} else if(this.x == 0 && this.y == 10){
				// pour opponent et my si pion sur case (0,9) ou (1,9) ou (1,10) alors peut bouger

				if(p.getX() == 0 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 1 && p.getY() == 10){
					ret = true;
				}

			} else if(this.x > 0 && this.x < 10 && this.y == 0) {
				// pour opponent et my si pion sur case (x-1,0) ou (x+1,0) ou (x+1,y+1) ou (x,y+1) ou (x-1,y+1) alors peut bouger
				if(p.getX() == this.x-1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == this.y+1){
					ret = true;
				}  else if(p.getX() == this.x && p.getY() == this.y+1){
					ret = true;
				} else if(p.getX() == this.x-1 && p.getY() == this.y+1){
					ret = true;
				}

			} else if(this.x == 10 && this.y == 0) {
				// pour opponent et my si pion sur case (9,0) ou (9,1) ou (10,1) alors peut bouger
				if(p.getX() == 9 && p.getY() == 0){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == 1){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == 1){
					ret = true;
				}

			} else if(this.x == 10 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (10,y-1) ou (10,y+1) ou (9,y+1) ou (9,y) ou (9,y-1) alors peut bouger
				if(p.getX() == 10 && p.getY() == this.y-1){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == this.y+1){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == this.y+1){
					ret = true;
				}  else if(p.getX() == 9 && p.getY() == this.y){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == this.y-1){
					ret = true;
				}

			} else if(this.x == 10 && this.y == 10){
				// pour opponent et my si pion sur case (9,9) ou (9,10) ou (10,9) alors peut bouger
				if(p.getX() == 9 && p.getY() == 9){
					ret = true;
				} else if(p.getX() == 9 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == 10 && p.getY() == 9){
					ret = true;
				}

			} else if(this.x > 0 && this.x < 10 && this.y == 10) {
				// pour opponent et my si pion sur case (x-1,10) ou (x+1,10) ou (x+1,9) ou (x,9) ou (x-1,9) alors peut bouger
				if(p.getX() == this.x-1 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == 10){
					ret = true;
				} else if(p.getX() == this.x+1 && p.getY() == 9){
					ret = true;
				}  else if(p.getX() == this.x && p.getY() == 9){
					ret = true;
				} else if(p.getX() == this.x-1 && p.getY() == 9){
					ret = true;
				}

			} else {
				//cas général si Zen se trouve sur une case au 'milieu' du plateau
				if (p.getX() == this.x - 1 && p.getY() == this.y) {
					ret = true;
				} else if (p.getX() == this.x + 1 && p.getY() == this.y) {
					ret = true;
				} else if (p.getX() == this.x && p.getY() == this.y + 1) {
					ret = true;
				} else if (p.getX() == this.x && p.getY() == this.y - 1) {
					ret = true;
				} else if (p.getX() == this.x + 1 && p.getY() == this.y - 1) {
					ret = true;
				} else if (p.getX() == this.x - 1 && p.getY() == this.y - 1) {
					ret = true;
				} else if (p.getX() == this.x + 1 && p.getY() == this.y + 1) {
					ret = true;
				} else if (p.getX() == this.x - 1 && p.getY() == this.y + 1) {
					ret = true;
				}
			}
		}

		if(zen != null) {
			// Zen
			if (this.x == 0 && this.y == 0) {
				// pour opponent et my si pion sur case (1,0) ou (1,1) ou (0,1) alors peut bouger
				if (zen.getX() == 1 && zen.getY() == 0) {
					ret = true;
				} else if (zen.getX() == 1 && zen.getY() == 1) {
					ret = true;
				} else if (zen.getX() == 0 && zen.getY() == 1) {
					ret = true;
				}
			} else if (this.x == 0 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (0,y-1) ou (0,y+1) ou (x+1,y-1) ou (x+1,y) ou (x+1,y+1) alors peut bouger
				if (zen.getX() == 0 && zen.getY() == this.y - 1) {
					ret = true;
				} else if (zen.getX() == 0 && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y - 1) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y + 1) {
					ret = true;
				}
			} else if (this.x == 0 && this.y == 10) {
				// pour opponent et my si pion sur case (0,9) ou (1,9) ou (1,10) alors peut bouger

				if (zen.getX() == 0 && zen.getY() == 9) {
					ret = true;
				} else if (zen.getX() == 1 && zen.getY() == 9) {
					ret = true;
				} else if (zen.getX() == 1 && zen.getY() == 10) {
					ret = true;
				}

			} else if (this.x > 0 && this.x < 10 && this.y == 0) {
				// pour opponent et my si pion sur case (x-1,0) ou (x+1,0) ou (x+1,y+1) ou (x,y+1) ou (x-1,y+1) alors peut bouger
				if (zen.getX() == this.x - 1 && zen.getY() == 0) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == 0) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == this.x && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == this.x - 1 && zen.getY() == this.y + 1) {
					ret = true;
				}

			} else if (this.x == 10 && this.y == 0) {
				// pour opponent et my si pion sur case (9,0) ou (9,1) ou (10,1) alors peut bouger
				if (zen.getX() == 9 && zen.getY() == 0) {
					ret = true;
				} else if (zen.getX() == 9 && zen.getY() == 1) {
					ret = true;
				} else if (zen.getX() == 10 && zen.getY() == 1) {
					ret = true;
				}

			} else if (this.x == 10 && this.y > 0 && this.y < 10) {
				// pour opponent et my si pion sur case (10,y-1) ou (10,y+1) ou (9,y+1) ou (9,y) ou (9,y-1) alors peut bouger
				if (zen.getX() == 10 && zen.getY() == this.y - 1) {
					ret = true;
				} else if (zen.getX() == 10 && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == 9 && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == 9 && zen.getY() == this.y) {
					ret = true;
				} else if (zen.getX() == 9 && zen.getY() == this.y - 1) {
					ret = true;
				}

			} else if (this.x == 10 && this.y == 10) {
				// pour opponent et my si pion sur case (9,9) ou (9,10) ou (10,9) alors peut bouger
				if (zen.getX() == 9 && zen.getY() == 9) {
					ret = true;
				} else if (zen.getX() == 9 && zen.getY() == 10) {
					ret = true;
				} else if (zen.getX() == 10 && zen.getY() == 9) {
					ret = true;
				}

			} else if (this.x > 0 && this.x < 10 && this.y == 10) {
				// pour opponent et my si pion sur case (x-1,10) ou (x+1,10) ou (x+1,9) ou (x,9) ou (x-1,9) alors peut bouger
				if (zen.getX() == this.x - 1 && zen.getY() == 10) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == 10) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == 9) {
					ret = true;
				} else if (zen.getX() == this.x && zen.getY() == 9) {
					ret = true;
				} else if (zen.getX() == this.x - 1 && zen.getY() == 9) {
					ret = true;
				}

			} else {
				//cas général si Zen se trouve sur une case au 'milieu' du plateau
				if (zen.getX() == this.x - 1 && zen.getY() == this.y) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y) {
					ret = true;
				} else if (zen.getX() == this.x && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == this.x && zen.getY() == this.y - 1) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y - 1) {
					ret = true;
				} else if (zen.getX() == this.x - 1 && zen.getY() == this.y - 1) {
					ret = true;
				} else if (zen.getX() == this.x + 1 && zen.getY() == this.y + 1) {
					ret = true;
				} else if (zen.getX() == this.x - 1 && zen.getY() == this.y + 1) {
					ret = true;
				}
			}
		}
		return ret;
	}

	/**
	 * Access method of the visite boolean
	 * @return visite : true if the pawn is visited during the depth first search
	 */
	public boolean isVisite() {
		return this.visite;
	}

	/**
	 * Method of modification of the visite boolean
	 * @param b : the new visite boolean
	 */
	public void setVisited(boolean b){
		this.visite = b;
	}
}