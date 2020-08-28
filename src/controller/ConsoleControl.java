package controller;

import model.Configuration;
import model.Pawn;

import java.util.Scanner;

/**
 * The ConsoleControl class allows several actions during the text version of the game.
 * @author DELAUNAY Gurwan 1C1
 */
public class ConsoleControl {
    private Scanner scanner;
    private Configuration configuration;

    /**
     * ConsoleControl constructor recovers console user input scanner and game configuration
     * @param in the user input scanner
     * @param configuration the instance of configuration of the game
     */
    public ConsoleControl(Scanner in,Configuration configuration){
        this.scanner = in;
        this.configuration = configuration;
    }

    /**
     * This method allows you to ask the user for the coordinates of the pawn he wants to move
     * @return the coordinates of the pawn that the user wishes to move
     */
    public int[] askCoordPawn(){
        int[] ret;
        String input;
        String[] inputs;
        String lettre;
        int ind0 = 0;
        int ind1 = 0;

        System.out.println("Quel pion souhaite tu déplacer ?");

        do {
            System.out.println("(Entre son numéro et sa lettre (Exemple : 1-G))");
            input = this.scanner.next();

            if (input.toUpperCase().equals("QUITTER")) {
                this.quitter();
            } else if (input.toUpperCase().equals("SAUVEGARDER")) {
                this.sauvegarder();
            } else {

                inputs = input.split("-");

                try {
                    ind0 = Integer.parseInt(inputs[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Il faut entrer un entier pas une lettre");
                    ind0 = -1;
                }

                try {
                    lettre = inputs[1];
                    lettre = lettre.toUpperCase();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Il faut respecter le schéma (numero-lettre)");
                    lettre = "Z";
                }

                switch (lettre) {
                    case "A":
                        ind1 = 0;
                        break;
                    case "B":
                        ind1 = 1;
                        break;
                    case "C":
                        ind1 = 2;
                        break;
                    case "D":
                        ind1 = 3;
                        break;
                    case "E":
                        ind1 = 4;
                        break;
                    case "F":
                        ind1 = 5;
                        break;
                    case "G":
                        ind1 = 6;
                        break;
                    case "H":
                        ind1 = 7;
                        break;
                    case "I":
                        ind1 = 8;
                        break;
                    case "J":
                        ind1 = 9;
                        break;
                    case "K":
                        ind1 = 10;
                        break;
                    default:
                        ind1 = 11;
                        break;
                }

                ind0--;

            }

        } while (ind1 == 11 || ind0 > 10 || ind0 < 0);

        ret = new int[2];
        ret[0] = ind0;
        ret[1] = ind1;

        return ret;
    }

    /**
     * This method allows to ask the user for the coordinates of the square where he wants to move the selected pawn
     * @param p the selected pawn
     * @return the coordinates of the square where the user wants to move the selected pawn
     */
    public int[] askCoordMove(Pawn p){
        int[] ret;
        String input;
        String[] inputs;
        String lettre;
        int ind0 = 0;
        int ind1 = 0;
        System.out.println("Où souhaites tu le déplacer ?");

        do {
            System.out.println("(Entre un numéro et une lettre (Exemple : 1-G))");
            input = scanner.next();

            if (input.toUpperCase().equals("QUITTER")) {
                this.quitter();
            } else if (input.toUpperCase().equals("SAUVEGARDER")) {
                this.sauvegarder();
            } else {
                inputs = input.split("-");
                try {
                    ind0 = Integer.parseInt(inputs[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Il faut entrer un entier pas une lettre)");
                    ind0 = -1;
                }

                try {
                    lettre = inputs[1];
                    lettre = lettre.toUpperCase();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Il faut respecter le schéma (numero-lettre");
                    lettre = "Z";
                }

                switch (lettre) {
                    case "A":
                        ind1 = 0;
                        break;
                    case "B":
                        ind1 = 1;
                        break;
                    case "C":
                        ind1 = 2;
                        break;
                    case "D":
                        ind1 = 3;
                        break;
                    case "E":
                        ind1 = 4;
                        break;
                    case "F":
                        ind1 = 5;
                        break;
                    case "G":
                        ind1 = 6;
                        break;
                    case "H":
                        ind1 = 7;
                        break;
                    case "I":
                        ind1 = 8;
                        break;
                    case "J":
                        ind1 = 9;
                        break;
                    case "K":
                        ind1 = 10;
                        break;
                    default:
                        ind1 = 11;
                        break;
                }

                ind0--;

                if (ind1 == 11 || ind0 > 10 || ind0 < 0) {
                    System.out.println("Pour déplacer le pion il faut entrer des coordonnées existantes sur le plateau");
                }

                if (p.sameCoord(ind0, ind1)) {
                    System.out.println("Pour déplacer le pion il faut entrer des coordonnées différentes de sa position actuelle");
                }
            }
        } while (ind1 == 11 || ind0 > 10 || ind0 < 0 || p.sameCoord(ind0,ind1));

        ret = new int[2];
        ret[0] = ind0;
        ret[1] = ind1;

        return ret;
    }

    /**
     * This method asks the user following a problem in moving the Zen pawn if he wants to choose another pawn or not
     * @return changePion : if changePion = 1 then the user will be able to choose another pawn else if changePion = 2 he will be able to try again to move the Zen pawn
     */
    public int zenMove() {
        int changePion = -2;
        String input;
        do {
            System.out.println("Veux tu : ");
            System.out.println("1) Choisir un autre pion");
            System.out.println("2) Réessayer avec le pion Zen");
            input = scanner.next();
            if (input.toUpperCase().equals("QUITTER")) {
                this.quitter();
            } else if (input.toUpperCase().equals("SAUVEGARDER")) {
                this.sauvegarder();
            } else {
                try {
                    changePion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.err.println("Entre 1 ou 2");
                    changePion = -1;
                }
            }

        }  while (changePion != 1 && changePion != 2) ;

        return changePion;
    }

    /**
     * This method gives a name to a save and asks the user if he wants to continue playing or not
     */
    public void sauvegarder(){
        String s;
        String quitterOuContinuer;
        int choix;

        System.out.println("Quel nom veux-tu donner à ta partie :");
        s = scanner.next();
        configuration.saveGame(s);

        do {
            System.out.println("Souhaitez vous continuer la partie ou quitter le jeu ? :");
            System.out.println("1) Continuer");
            System.out.println("2) Quitter");
            quitterOuContinuer = scanner.next();
            try {
                choix = Integer.parseInt(quitterOuContinuer);
            } catch (NumberFormatException e){
                System.err.println("Entre 1 ou 2");
                choix = -1;
            }
        } while (choix != 1 && choix != 2);

        if(choix == 2){
            this.quitter();
        }
    }

    /**
     * This method closes the game
     */
    public void quitter(){
        System.exit(0);
    }

}
