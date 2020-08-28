package model;

import controller.ConsoleControl;

import java.awt.Color;
import java.io.Serializable;
import java.util.*;

/** PlayerAuto class inherited of the Player class
 * @author DELAUNAY Gurwan 1C1
 **/
public class PlayerAuto extends Player implements Serializable {

	private int difficulty;
	private Square[][] board;

	/**
	 * Constructor
	 * @param name the name of the PlayerAuto
	 * @param difficulty the difficulty of the AI
	 */
	public PlayerAuto(String name, Color color, int difficulty, int version, Square[][] board) {
		super(name,color,version,board,null);
		this.difficulty = difficulty;
		this.board = board;
	}

	/**
	 * Method allowing the autoplayer to play
	 * @param opponentPawnList the list of the opponent pawn
	 * @param zen the zen pawn
	 * @return takePawn : the selectedPawn
	 */
	public Pawn play(ArrayList<Pawn> opponentPawnList, Pawn zen) {

		Pawn takePawn = null;
		int indTakePawn = -1;
		boolean tousUnVoisin = true;
		int[] coordNextMove;
		Random randomNextMove = new Random();
		Random randomTakePawn = new Random();
		int probaNextMove = randomNextMove.nextInt(10 + 1);

		do {
			int k = 0;
			ArrayList<Pawn> takeAlonePawn = new ArrayList<Pawn>();
			ArrayList<Integer> indTakeAlonePawn = new ArrayList<Integer>();
			for (Pawn myPawn : this.pawnListPlayer) {
				if(myPawn.getX() != 1000 && myPawn.getY() != 1000) {
					if (!(myPawn.hasNeighbor(pawnListPlayer,zen))) {
						takeAlonePawn.add(myPawn);
						indTakeAlonePawn.add(k);
						tousUnVoisin = false;
					}
				}
				k++;
			}

			if(!takeAlonePawn.isEmpty()){
				int i = randomTakePawn.nextInt(takeAlonePawn.size());
				takePawn = takeAlonePawn.get(i);
				indTakePawn = indTakeAlonePawn.get(i);
			}

			if(zen.hasNeighbor(this.pawnListPlayer) || zen.hasNeighbor(opponentPawnList)) {
				if (probaNextMove >= 0 && probaNextMove < 2) {
					takePawn = zen;
				}
			}

			if (tousUnVoisin) {

				int[] countVisited = new int[12];
				int i = 0;
				int moinsGrandeChaine = 10000;
				int indMoinsGrandeChaine = -1;

				for (Pawn myPawn : this.pawnListPlayer) {
					if(myPawn.getX() != 1000 && myPawn.getY() != 1000) {
						parcoursDFS(myPawn, this.pawnListPlayer, zen);
						for (Pawn pawn : this.pawnListPlayer) {
							if (pawn.isVisite()) {
								countVisited[i]++;
							}
							pawn.setVisited(false);
						}
					}
					i++;
				}

				for (int j = 0; j < countVisited.length; j++) {
					if (countVisited[j] < moinsGrandeChaine) {
						moinsGrandeChaine = countVisited[j];
						indMoinsGrandeChaine = j;
					}
				}

				takePawn = this.pawnListPlayer.get(indMoinsGrandeChaine);
				indTakePawn = indMoinsGrandeChaine;
			}

		} while (takePawn == null);

		int[] coordError = new int[2];
		coordError[0] = -1;
		coordError[1] = -1;

		probaNextMove = randomNextMove.nextInt(10 + 1);

		if (difficulty == 3) {
			if (probaNextMove <= 9 && probaNextMove >= 0) {
				coordNextMove = bestNextMove(takePawn,opponentPawnList,zen);
				if(Arrays.equals(coordNextMove, coordError)){
					coordNextMove = randomNextMove(takePawn,opponentPawnList,zen);
					if(Arrays.equals(coordNextMove, coordError)){
						do {
							takePawn = takeAnotherPawn(takePawn);
							coordNextMove = randomNextMove(takePawn, opponentPawnList, zen);
						} while(Arrays.equals(coordNextMove, coordError));
					}
				}
			} else {
				coordNextMove = randomNextMove(takePawn,opponentPawnList,zen);
				if(Arrays.equals(coordNextMove, coordError)){
					do {
						takePawn = takeAnotherPawn(takePawn);
						coordNextMove = randomNextMove(takePawn, opponentPawnList, zen);
					} while(Arrays.equals(coordNextMove, coordError));
				}
			}
		} else if (difficulty == 2) {
			if (probaNextMove <= 7 && probaNextMove >= 0) {
				coordNextMove = bestNextMove(takePawn,opponentPawnList,zen);
				if(Arrays.equals(coordNextMove, coordError)){
					coordNextMove = randomNextMove(takePawn,opponentPawnList,zen);
					if(Arrays.equals(coordNextMove, coordError)){
						do {
							takePawn = takeAnotherPawn(takePawn);
							coordNextMove = randomNextMove(takePawn, opponentPawnList, zen);
						} while(Arrays.equals(coordNextMove, coordError));
					}
				}
			} else {
				coordNextMove = randomNextMove(takePawn,opponentPawnList,zen);
				if(Arrays.equals(coordNextMove, coordError)){
					do {
						takePawn = takeAnotherPawn(takePawn);
						coordNextMove = randomNextMove(takePawn, opponentPawnList, zen);
					} while(Arrays.equals(coordNextMove, coordError));
				}
			}
		} else {
			if (probaNextMove <= 5 && probaNextMove >= 0) {
				coordNextMove = bestNextMove(takePawn,opponentPawnList,zen);
				if(Arrays.equals(coordNextMove, coordError)){
					coordNextMove = randomNextMove(takePawn,opponentPawnList,zen);
					if(Arrays.equals(coordNextMove, coordError)){
						do {
							takePawn = takeAnotherPawn(takePawn);
							coordNextMove = randomNextMove(takePawn, opponentPawnList, zen);
						} while(Arrays.equals(coordNextMove, coordError));
					}
				}
			} else {
				coordNextMove = randomNextMove(takePawn,opponentPawnList,zen);
				if(Arrays.equals(coordNextMove, coordError)){
					do {
						takePawn = takeAnotherPawn(takePawn);
						coordNextMove = randomNextMove(takePawn, opponentPawnList, zen);
					} while(Arrays.equals(coordNextMove, coordError));
				}
			}
		}

		this.board[takePawn.getX()][takePawn.getY()].setOccupy(false);
		takePawn.setPosition(coordNextMove[0],coordNextMove[1]);

		if(!(takePawn.equals(zen))){
			this.pawnListPlayer.set(indTakePawn,takePawn);
		}


		return takePawn;
	}


	/**
	 * Method allowing the autoplayer to play
	 * @param opponentPawnList the list of the opponent pawn
	 * @return takePawn : the selectedPawn
	 */
	public Pawn play(ArrayList<Pawn> opponentPawnList) {
		Pawn takePawn = null;
		int indTakePawn = -1;
		boolean tousUnVoisin = true;
		int[] coordNextMove;
		Random randomNextMove = new Random();
		Random randomTakePawn = new Random();
		int probaNextMove = randomNextMove.nextInt(10+1);

		do {
			int k = 0;
			ArrayList<Pawn> takeAlonePawn = new ArrayList<Pawn>();
			ArrayList<Integer> indTakeAlonePawn = new ArrayList<Integer>();
			for (Pawn myPawn : this.pawnListPlayer) {
				if(myPawn.getX() != 1000 && myPawn.getY() != 1000) {
					if (!(myPawn.hasNeighbor(pawnListPlayer))) {
						takeAlonePawn.add(myPawn);
						indTakeAlonePawn.add(k);
						tousUnVoisin = false;
					}
				}
				k++;
			}

			if(!takeAlonePawn.isEmpty()){
				int i = randomTakePawn.nextInt(takeAlonePawn.size());
				takePawn = takeAlonePawn.get(i);
				indTakePawn = indTakeAlonePawn.get(i);
			}

			if (tousUnVoisin) {
				int[] countVisited = new int[12];
				int i = 0;
				int moinsGrandeChaine = 10000;
				int indMoinsGrandeChaine = -1;

				for (Pawn myPawn : this.pawnListPlayer) {
					if(myPawn.getX() != 1000 && myPawn.getY() != 1000) {
						parcoursDFS(myPawn, this.pawnListPlayer, null);
						for (Pawn pawn : this.pawnListPlayer) {
							if (pawn.isVisite()) {
								countVisited[i]++;
							}
							pawn.setVisited(false);
						}
					}
					i++;
				}

				for (int j = 0; j < countVisited.length; j++) {
					if (countVisited[j] < moinsGrandeChaine) {
						moinsGrandeChaine = countVisited[j];
						indMoinsGrandeChaine = j;
					}
				}

				takePawn = this.pawnListPlayer.get(indMoinsGrandeChaine);
				indTakePawn = indMoinsGrandeChaine;
			}

		} while (takePawn == null);

		int[] coordError = new int[2];
		coordError[0] = -1;
		coordError[1] = -1;

		if (difficulty == 3) {
			if (probaNextMove <= 9 && probaNextMove >= 0) {
				coordNextMove = bestNextMove(takePawn,opponentPawnList,null);
				if(Arrays.equals(coordNextMove, coordError)){
					coordNextMove = randomNextMove(takePawn,opponentPawnList,null);
					if(Arrays.equals(coordNextMove, coordError)){
						do {
							takePawn = takeAnotherPawn(takePawn);
							coordNextMove = randomNextMove(takePawn, opponentPawnList, null);
						} while(Arrays.equals(coordNextMove, coordError));
					}
				}
			} else {
				coordNextMove = randomNextMove(takePawn,opponentPawnList,null);
				if(Arrays.equals(coordNextMove, coordError)){
					do {
						takePawn = takeAnotherPawn(takePawn);
						coordNextMove = randomNextMove(takePawn, opponentPawnList, null);
					} while(Arrays.equals(coordNextMove, coordError));
				}
			}
		} else if (difficulty == 2) {
			if (probaNextMove <= 7 && probaNextMove >= 0) {
				coordNextMove = bestNextMove(takePawn,opponentPawnList,null);
				if(Arrays.equals(coordNextMove, coordError)){
					coordNextMove = randomNextMove(takePawn,opponentPawnList,null);
					if(Arrays.equals(coordNextMove, coordError)){
						do {
							takePawn = takeAnotherPawn(takePawn);
							coordNextMove = randomNextMove(takePawn, opponentPawnList, null);
						} while(Arrays.equals(coordNextMove, coordError));
					}
				}
			} else {
				coordNextMove = randomNextMove(takePawn,opponentPawnList,null);
				if(Arrays.equals(coordNextMove, coordError)){
					do {
						takePawn = takeAnotherPawn(takePawn);
						coordNextMove = randomNextMove(takePawn, opponentPawnList, null);
					} while(Arrays.equals(coordNextMove, coordError));
				}
			}
		} else {
			if (probaNextMove <= 5 && probaNextMove >= 0) {
				coordNextMove = bestNextMove(takePawn,opponentPawnList,null);
				if(Arrays.equals(coordNextMove, coordError)){
					coordNextMove = randomNextMove(takePawn,opponentPawnList,null);
					if(Arrays.equals(coordNextMove, coordError)){
						do {
							takePawn = takeAnotherPawn(takePawn);
							coordNextMove = randomNextMove(takePawn, opponentPawnList, null);
						} while(Arrays.equals(coordNextMove, coordError));
					}
				}
			} else {
				coordNextMove = randomNextMove(takePawn,opponentPawnList,null);
				if(Arrays.equals(coordNextMove, coordError)){
					do {
						takePawn = takeAnotherPawn(takePawn);
						coordNextMove = randomNextMove(takePawn, opponentPawnList, null);
					} while(Arrays.equals(coordNextMove, coordError));
				}
			}
		}

		this.board[takePawn.getX()][takePawn.getY()].setOccupy(false);
		takePawn.setPosition(coordNextMove[0],coordNextMove[1]);

		this.pawnListPlayer.set(indTakePawn,takePawn);

		return takePawn;
	}


	/**
	 * Method calculating the best move to make
	 * @param takePawn the pawn that will be moving
	 * @param opponentPawn the list of opponent pawns
	 * @param zen the zen pawn
	 * @return ret : the coordinates of the new pawn square
	 */
	public int[] bestNextMove(Pawn takePawn,ArrayList<Pawn> opponentPawn,Pawn zen) {
		int[] ret = new int[2];
		Square retSquare = null;
		boolean found = false;
		boolean nonOccupeParProprePion = true;
		if(zen != null){
			for(int i = 0; i<this.board.length; i++){
				for(int j = 0; j<this.board[i].length; j++) {
					int[] coord = new int[2];
					coord[0] = i;
					coord[1] = j;

					for (Pawn pawn : this.pawnListPlayer) {
						if (Arrays.equals(pawn.getPosition(), coord)) {
							nonOccupeParProprePion = false;
						}
					}

					if (nonOccupeParProprePion) {
						if (takePawn.rules1(coord)) {
							if (takePawn.rules2(coord, this.pawnListPlayer, opponentPawn, zen)) {
								if (takePawn.rules3(coord, opponentPawn)) {
									if (takePawn.equals(zen)) {
										if (takePawn.rulesZen(coord, zen, opponentPawn, this.pawnListPlayer)) {
											Pawn testPawn = new Pawn();
											testPawn.setPosition(i, j);
											if (testPawn.hasNeighbor(this.pawnListPlayer)) {
												retSquare = this.board[i][j];
												ret[0] = retSquare.getX();
												ret[1] = retSquare.getY();
												return ret;
											}
										}
									} else {
										Pawn testPawn = new Pawn();
										if (testPawn.hasNeighbor(this.pawnListPlayer)) {
											retSquare = this.board[i][j];
											ret[0] = retSquare.getX();
											ret[1] = retSquare.getY();
											return ret;
										}
									}
								}
							}
						}
					}
				}
			}

		} else {

			for(int i = 0; i<this.board.length; i++){
				for(int j = 0; j<this.board[i].length; j++){
					int[] coord = new int[2];
					coord[0] = i;
					coord[1] = j;

					for (Pawn pawn : this.pawnListPlayer) {
						if (Arrays.equals(pawn.getPosition(), coord)) {
							nonOccupeParProprePion = false;
						}
					}

					if (nonOccupeParProprePion) {
						if (takePawn.rules1(coord)) {
							if (takePawn.rules2(coord, this.pawnListPlayer, opponentPawn)) {
								if (takePawn.rules3(coord, opponentPawn)) {
									Pawn testPawn = new Pawn();
									if (testPawn.hasNeighbor(this.pawnListPlayer)) {
										retSquare = this.board[i][j];
										found = true;
										ret[0] = retSquare.getX();
										ret[1] = retSquare.getY();
										testPawn = null;
										return ret;
									}
									testPawn = null;
								}
							}
						}
					}
				}
			}

		}

		if(!found){
			ret[0] = -1;
			ret[1] = -1;
		}

		return ret;
	}


	/**
	 * Method calculating a random move to make
	 * @param takePawn the pawn that will be moving
	 * @param opponentPawn the list of opponent pawns
	 * @param zen the zen pawn
	 * @return ret : the coordinates of the new pawn square
	 */
	public int[] randomNextMove(Pawn takePawn,ArrayList<Pawn> opponentPawn,Pawn zen) {
		int[] ret = new int[2];
		ret[0] = -1;
		ret[1] = -1;
		Square retSquare = null;
		Random randomNextMove = new Random();
		boolean nonOccupeParProprePion = true;
		ArrayList<Square> possibleMouv = new ArrayList<Square>();
		if(zen != null){
			for(int i = 0; i<this.board.length; i++){
				for(int j = 0; j<this.board[i].length; j++){
					nonOccupeParProprePion = true;
					int[] coord = new int[2];
					coord[0] = i;
					coord[1] = j;

					for (Pawn pawn : this.pawnListPlayer) {
						if (Arrays.equals(pawn.getPosition(), coord)) {
							nonOccupeParProprePion = false;
						}
					}

					if (nonOccupeParProprePion) {
						if (takePawn.rules1(coord)) {
							if (takePawn.rules2(coord, this.pawnListPlayer, opponentPawn, zen)) {
								if (takePawn.rules3(coord, opponentPawn)) {
									if (takePawn.equals(zen)) {
										if (takePawn.rulesZen(coord, zen, opponentPawn, this.pawnListPlayer)) {
											possibleMouv.add(this.board[i][j]);
										}
									} else {
										possibleMouv.add(this.board[i][j]);
									}
								}
							}
						}
					}
				}
			}

		} else {
			int[] coord = new int[2];
			for(int i = 0; i<this.board.length; i++){
				for(int j = 0; j<this.board[i].length; j++){
					coord[0] = i;
					coord[1] = j;
					nonOccupeParProprePion = true;

					for (Pawn pawn : this.pawnListPlayer) {
						if (Arrays.equals(pawn.getPosition(), coord)) {
							nonOccupeParProprePion = false;
						}
					}

					if (nonOccupeParProprePion) {
						if (takePawn.rules1(coord)) {
							if (takePawn.rules2(coord, this.pawnListPlayer, opponentPawn)) {
								if (takePawn.rules3(coord, opponentPawn)) {
									possibleMouv.add(this.board[i][j]);
								}
							}
						}
					}
				}
			}
		}

		if(!(possibleMouv.isEmpty())) {
			int probaNextMove = randomNextMove.nextInt(possibleMouv.size());
			retSquare = possibleMouv.get(probaNextMove);
			ret[0] = retSquare.getX();
			ret[1] = retSquare.getY();
		}
		return ret;
	}

	public boolean play(Pawn p, ArrayList<Pawn> opponentPawnList, Pawn zen){
		return true;
	}

	public boolean play(Pawn p, ArrayList<Pawn> opponentPawnList){
		return true;
	}

	public boolean play(Pawn p, ArrayList<Pawn> opponentPawnList, Pawn zen, int[] coord){
		return true;
	}

	public boolean play(Pawn p, ArrayList<Pawn> opponentPawnList, int[] coord){
		return true;
	}

	/**
	 * Method for changing the selected pawn
	 * @param p the selected pawn
	 * @return takePawn : the new selected pawn
	 */
	public Pawn takeAnotherPawn(Pawn p){
		Pawn takePawn = null;
		int indTakePawn = -1;
		boolean tousUnVoisin = true;
		Random randomNextMove = new Random();
		Random randomTakePawn = new Random();

		do {
			int k = 0;
			ArrayList<Pawn> takeAlonePawn = new ArrayList<Pawn>();
			ArrayList<Integer> indTakeAlonePawn = new ArrayList<Integer>();
			for (Pawn myPawn : this.pawnListPlayer) {
				if(myPawn.getX() != 1000 && myPawn.getY() != 1000) {
					if (!(myPawn.hasNeighbor(pawnListPlayer))) {
						takeAlonePawn.add(myPawn);
						indTakeAlonePawn.add(k);
						tousUnVoisin = false;
					}
				}
				k++;
			}

			if(!takeAlonePawn.isEmpty()){
				int i = randomTakePawn.nextInt(takeAlonePawn.size());
				takePawn = takeAlonePawn.get(i);
				indTakePawn = indTakeAlonePawn.get(i);
			}

			if (tousUnVoisin) {
				int[] countVisited = new int[12];
				int i = 0;
				int moinsGrandeChaine = 10000;
				int indMoinsGrandeChaine = -1;

				for (Pawn myPawn : this.pawnListPlayer) {
					if(myPawn.getX() != 1000 && myPawn.getY() != 1000) {
						parcoursDFS(myPawn, this.pawnListPlayer, null);
						for (Pawn pawn : this.pawnListPlayer) {
							if (pawn.isVisite()) {
								countVisited[i]++;
							}
							pawn.setVisited(false);
						}
					}
					i++;
				}

				for (int j = 0; j < countVisited.length; j++) {
					if (countVisited[j] < moinsGrandeChaine) {
						moinsGrandeChaine = countVisited[j];
						indMoinsGrandeChaine = j;
					}
				}

				takePawn = this.pawnListPlayer.get(indMoinsGrandeChaine);
				indTakePawn = indMoinsGrandeChaine;
			}

		} while (takePawn == null && takePawn == p);

		return takePawn;
	}

}