package model;

import controller.ConsoleControl;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/** PlayerHuman class inherited of the Player class
 * @author DELAUNAY Gurwan 1C1
 **/
public class PlayerHuman extends Player implements Serializable {

	/**
	 * Constructor
	 * @param name the name of the Player
	 * @param color the color of the pawns of the Player
	 */
	public PlayerHuman(String name, Color color, int version, Square[][] board, ConsoleControl consoleControl) {
		super(name,color,version,board,consoleControl);
	}

	/**
	 * Method allowing the humanplayer to play for the text version
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @param zen the zen pawn
	 * @return true if the player has played
	 */
	public boolean play(Pawn p,ArrayList<Pawn> opponentPawnList,Pawn zen) {
		boolean ret = true;
		if (this.version == 0) {
			int indPawn = 0;
			boolean peutYAller = false;
			boolean isZen = false;

			for (Pawn pawn : this.pawnListPlayer) {
				if (pawn.equals(p)) {
					indPawn = this.pawnListPlayer.indexOf(pawn);
				}
			}

			while (!peutYAller) {
				peutYAller = true;

				int[] coord = consoleControl.askCoordMove(p);

				for (Pawn pawn : this.pawnListPlayer) {
					if (Arrays.equals(pawn.getPosition(), coord)) {
						System.out.println("Déplacement non autorisé : tu ne peux pas te placer sur un de tes pions");
						peutYAller = false;
					}
				}

				if(peutYAller) {
					if (p.rules1(coord)) {
						if (p.rules2(coord,this.pawnListPlayer,opponentPawnList,zen)) {
							if (p.rules3(coord,opponentPawnList)) {
								if(p.equals(zen)){
									isZen = true;
									if(p.rulesZen(coord,zen,opponentPawnList,this.pawnListPlayer)){
										p.setPosition(coord[0],coord[1]);
									} else {
										System.out.println("Déplacement non autorisé : le pion Zen doit se trouver à côté d'un autre pion pour être déplacé et ne doit pas être remis à la même place après avoir bougé");

										int changePion = consoleControl.zenMove();

										if(changePion == 2) {
											peutYAller = false;
										} else {
											ret = false;
										}
									}
								} else {
									p.setPosition(coord[0],coord[1]);
								}
							} else {
								System.out.println("Déplacement non autorisé : tu ne dois pas passer au dessus d'un pion qui ne t'appartient pas");
								peutYAller = false;
							}
						} else {
							System.out.println("Déplacement non autorisé : tu dois déplacer ton pion d'autant de cases qu'il y a de pions sur la ligne de déplacement");
							peutYAller = false;
						}
					} else {
						System.out.println("Déplacement non autorisé : tu dois déplacer ton pion à l'horizontal, à la vertical ou en diagonale");
						peutYAller = false;
					}
				}

			}

			if(peutYAller && !isZen) {
				this.pawnListPlayer.set(indPawn, p);
			}

		}
		return ret;
	}

	/**
	 * Method allowing the humanplayer to play for the graphic version
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @param zen the zen pawn
	 * @param coord the coordinates of the pawn
	 * @return true if the player has played
	 */
	public boolean play(Pawn p,ArrayList<Pawn> opponentPawnList,Pawn zen,int[] coord) {
		boolean ret = true;
		boolean isZen = false;
		int indPawn = 0;

		for (Pawn pawn : this.pawnListPlayer) {
			if (pawn.equals(p)) {
				indPawn = this.pawnListPlayer.indexOf(pawn);
			}
		}

		for (Pawn pawn : this.pawnListPlayer) {
			if (Arrays.equals(pawn.getPosition(), coord)) {
				ret = false;
			}
		}

		if(ret) {
			if (p.rules1(coord)) {
				if (p.rules2(coord,this.pawnListPlayer,opponentPawnList,zen)) {
					if (p.rules3(coord,opponentPawnList)) {
						if(p.equals(zen)){
							isZen = true;
							if(p.rulesZen(coord,zen,opponentPawnList,this.pawnListPlayer)){
								p.setPosition(coord[0],coord[1]);
							} else {
								ret = false;
							}
						} else {
							p.setPosition(coord[0],coord[1]);
						}
					} else {
						ret = false;
					}
				} else {
					ret = false;
				}
			} else {
				ret = false;
			}
		}

		if(ret && !isZen) {
			this.pawnListPlayer.set(indPawn, p);
		}

		return ret;
	}

	/**
	 * Method allowing the humanplayer to play for the text version if the zen pawn is null
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @return true if the player has played
	 */
	public boolean play(Pawn p,ArrayList<Pawn> opponentPawnList) {
		boolean ret = true;
		if (this.version == 0) {
			int indPawn = 0;
			boolean peutYAller = false;

			for (Pawn pawn : this.pawnListPlayer) {
				if (pawn.equals(p)) {
					indPawn = this.pawnListPlayer.indexOf(pawn);
				}
			}

			while (!peutYAller) {
				peutYAller = true;

				int[] coord = consoleControl.askCoordMove(p);

				for (Pawn pawn : this.pawnListPlayer) {
					if (Arrays.equals(pawn.getPosition(), coord)) {
						System.out.println("Déplacement non autorisé : tu ne peux pas te placer sur un de tes pions");
						peutYAller = false;
					}
				}

				if(peutYAller) {
					if (p.rules1(coord)) {
						if (p.rules2(coord,this.pawnListPlayer,opponentPawnList)) {
							if (p.rules3(coord,opponentPawnList)) {
								p.setPosition(coord[0],coord[1]);
							} else {
								System.out.println("Déplacement non autorisé : tu ne dois pas passer au dessus d'un pion qui ne t'appartient pas");
								peutYAller = false;
							}
						} else {
							System.out.println("Déplacement non autorisé : tu dois déplacer ton pion d'autant de cases qu'il y a de pions sur la ligne de déplacement");
							peutYAller = false;
						}
					} else {
						System.out.println("Déplacement non autorisé : tu dois déplacer ton pion à l'horizontal, à la vertical ou en diagonale");
						peutYAller = false;
					}
				}

			}

			if(peutYAller) {
				this.pawnListPlayer.set(indPawn, p);
			}
		}
		return ret;
	}

	/**
	 * Method allowing the humanplayer to play for the graphic version if the zen pawn is null
	 * @param p a pawn
	 * @param opponentPawnList the list of the opponent pawn
	 * @param coord the coordinates of the pawn
	 * @return true if the player has played
	 */
	public boolean play(Pawn p,ArrayList<Pawn> opponentPawnList,int[] coord) {
		boolean ret = true;
		int indPawn = 0;

		for (Pawn pawn : this.pawnListPlayer) {
			if (pawn.equals(p)) {
				indPawn = this.pawnListPlayer.indexOf(pawn);
			}
		}

		for (Pawn pawn : this.pawnListPlayer) {
			if (Arrays.equals(pawn.getPosition(), coord)) {
				ret = false;
			}
		}

		if(ret) {
			if (p.rules1(coord)) {
				if (p.rules2(coord,this.pawnListPlayer,opponentPawnList)) {
					if (p.rules3(coord,opponentPawnList)) {
						p.setPosition(coord[0],coord[1]);
					} else {
						ret = false;
					}
				} else {
					ret = false;
				}
			} else {
				ret = false;
			}
		}

		if(ret) {
			this.pawnListPlayer.set(indPawn, p);
		}

		return ret;
	}

	public Pawn play(ArrayList<Pawn> opponentPawnList, Pawn zen){
		return null;
	}

	public Pawn play(ArrayList<Pawn> opponentPawnList){
		return null;
	}
}