package controller;

import model.Configuration;

import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class is executed when the user chooses to launch the text version of the game
 * @author DELAUNAY Gurwan 1C1
 */
public class ConsoleVersion {
    FileWriter configuration;
    File fileConfiguration;
    BufferedWriter bufferedConfiguration;
    String playerName1;
    String playerName2;
    Scanner scanner;

    /**
     * The constructor of ConsoleVersion ask the user the parameters of the game
     */
    public ConsoleVersion(){
        for(int i = 0;i<50;i++) {
            System.out.println("");
        }

        System.out.println("*********************************************");
        System.out.println("********** Bienvenue dans Zen Game **********");
        System.out.println("*********************************************");

        try {
            Files.createDirectories(Paths.get("./ZenGame/data"));
            Files.createDirectories(Paths.get("./ZenGame/save"));
            fileConfiguration = new File("./ZenGame/data/configuration.txt");
            configuration = new FileWriter(fileConfiguration);
            bufferedConfiguration = new BufferedWriter(configuration);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }


        scanner = new Scanner(System.in);
        String input;
        int newOrLoad;
        do {
            System.out.println("Veux tu :");
            System.out.println("1) Créer un nouvelle partie");
            System.out.println("2) Charger une partie");
            System.out.println("3) Supprimer mes sauvegardes");
            System.out.println("(Entre 1,2 ou 3 pour faire ton choix)");
            input = scanner.next();

            try{
                newOrLoad = Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.err.println("Il faut entrer un entier pas une lettre)");
                newOrLoad = -1;
            }
        } while (newOrLoad != 1 && newOrLoad != 2 && newOrLoad != 3);

        if(newOrLoad == 1) {

            int gamemodeChoix;
            String inputGM;

            do {
                System.out.println("Choisis le mode de jeu de ta partie :");
                System.out.println("1) Jouer contre l'ordinateur");
                System.out.println("2) Jouer avec un ami");
                System.out.println("(Entre 1 ou 2 pour faire ton choix)");
                inputGM = scanner.next();

                try{
                    gamemodeChoix = Integer.parseInt(inputGM);
                } catch (NumberFormatException e){
                    System.err.println("Il faut entrer un entier pas une lettre)");
                    gamemodeChoix = -1;
                }
            } while (gamemodeChoix != 1 && gamemodeChoix != 2);

            if (gamemodeChoix == 1) {
                System.out.println("Tu as choisi d'affronter l'ordinateur");
            } else {
                System.out.println("Tu as choisi d'affronter un ami");
            }

            try {
                bufferedConfiguration.write("gamemode : " + gamemodeChoix + " :");
            } catch (IOException e) {
                System.err.println("Game mode choice " + e.getMessage());
            }

            System.out.println("");

            if (gamemodeChoix == 1) {
                //choix de la difficulté de l'IA
                int difficultyIAChoix;
                String inputIA;

                do {
                    System.out.println("Choisis la difficulté de l'ordinateur :");
                    System.out.println("1) Débutant");
                    System.out.println("2) Confirmé");
                    System.out.println("3) Expert");
                    System.out.println("(Entre 1,2 ou 3 pour faire ton choix)");
                    inputIA = scanner.next();

                    try{
                        difficultyIAChoix = Integer.parseInt(inputIA);
                    } catch (NumberFormatException e){
                        System.err.println("Il faut entrer un entier pas une lettre)");
                        difficultyIAChoix = -1;
                    }
                } while (difficultyIAChoix != 1 && difficultyIAChoix != 2 && difficultyIAChoix != 3);

                if (difficultyIAChoix == 1) {
                    System.out.println("Tu as choisi le niveau débutant");
                } else if (difficultyIAChoix == 2) {
                    System.out.println("Tu as choisi le niveau confirmé");
                } else {
                    System.out.println("Tu as choisi le niveau expert");
                }

                try {
                    bufferedConfiguration.newLine();
                    bufferedConfiguration.write("difficulty : " + difficultyIAChoix + " :");
                } catch (IOException e) {
                    System.err.println("difficulty " + e.getMessage());
                }

                System.out.println("");
            }

            // choix du nombre de tours de la partie

            int numbersOfTurnsChoix;
            String inputTurns;

            do {
                System.out.println("Choisis le nombre de tours de la partie :");
                System.out.println("(Entre un nombre entre 1 et 10)");
                inputTurns = scanner.next();

                try{
                    numbersOfTurnsChoix = Integer.parseInt(inputTurns);
                } catch (NumberFormatException e){
                    System.err.println("Il faut entrer un entier pas une lettre)");
                    numbersOfTurnsChoix = -1;
                }
            } while (numbersOfTurnsChoix > 10 || numbersOfTurnsChoix < 1);


            if (numbersOfTurnsChoix == 1) {
                System.out.println("Tu as choisi de jouer un seul tour");
            } else {
                System.out.println("Tu as choisi de jouer " + numbersOfTurnsChoix + " tours");
            }

            try {
                bufferedConfiguration.newLine();
                bufferedConfiguration.write("turns : " + numbersOfTurnsChoix + " :");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            System.out.println("");

            if (gamemodeChoix == 1) {
                // choix du nom du joueur

                System.out.println("Choisis ton nom :");
                playerName1 = scanner.next();

                System.out.println("Tu as choisi de t'appeller " + playerName1);

                playerName2 = this.nomAleatoire();

                System.out.println("Ton adversaire sera " + playerName2);

            } else {
                // choix des noms des joueurs

                System.out.println("Choisis ton nom (Joueur 1) :");
                playerName1 = scanner.next();

                System.out.println("");

                do {
                    if (playerName1.equals(playerName2)) {
                        System.out.println("Joueur 2 choisis un autre nom que " + playerName1);
                    } else {
                        System.out.println("Choisis ton nom (Joueur 2) :");
                    }
                    playerName2 = scanner.next();
                } while (playerName1.equals(playerName2));


            }

            try {
                bufferedConfiguration.close();
                configuration.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            this.lancerJeu(scanner);
        } else if(newOrLoad == 2){
            // si l'utilisateur veut charger une partie
            String s;
            Configuration configuration = new Configuration(scanner);
            do {
                File repertoireLoad = new File("./ZenGame/save/");
                String[] listeGame = repertoireLoad.list();
                if(listeGame.length > 0){
                    System.out.println("Quelle partie voulez-vous charger :");
                    for (String value : listeGame) {
                        System.out.println(value);
                    }
                } else {
                    System.err.println("Aucune partie sauvegardée !");
                    System.exit(0);
                }
                s = scanner.next();
            } while (!(configuration.loadGame(s)));

        } else {
            // si l'utilisateur veut supprimer les sauvegardes du jeu
            File repertoireLoad = new File("./ZenGame/save/");
            String[] listeGame = repertoireLoad.list();
            int supprOrNot;
            if(listeGame.length > 0){
                System.out.println("Voulez-vous vraiment supprimer les sauvegardes suivantes :");
                for (String value : listeGame) {
                    System.out.println(value);
                }

                do {
                    System.out.println("1) Oui");
                    System.out.println("2) Non");
                    System.out.println("(Entre 1 ou 2 pour faire ton choix)");
                    supprOrNot = scanner.nextInt();
                } while (supprOrNot != 1 && supprOrNot != 2);

                if(supprOrNot == 2){
                    System.out.println("Les sauvegardes ne seront pas supprimées.");
                } else {
                    for(String value : listeGame){
                        File f = new File("./ZenGame/save/" + value);
                        f.delete();
                    }
                    System.out.println("Les sauvegardes sont supprimées.");
                }
                System.exit(0);

            } else {
                System.err.println("Aucune partie sauvegardée !");
                System.exit(0);
            }
        }
    }

    /**
     * The method allows to generate a random name for the AI if the game mode is human against AI
     * @return ret : a random name
     */
    private String nomAleatoire(){
        String ret;
        int randomName;
        String nameList[] = {"Leonard","Cristiano","Erwan","Florian","Duncan","Dylan","Pietro","Alexandre","Fanny","Maurin","Nicolas","Jasmine","Joni"};
        randomName = (int) (Math.random() * (nameList.length));
        ret = nameList[randomName];
        return ret;
    }

    /**
     * This method launches the game by creating a configuration instance
     * @param in the user input scanner
     */
    private void lancerJeu(Scanner in){
        // 0 signifie que la version du jeu est textuelle
        Configuration configuration = new Configuration("./ZenGame/data/configuration.txt",this.playerName1,this.playerName2,0,in);
    }
}
