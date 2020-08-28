package controller;

import model.Configuration;
import model.Pawn;
import view.ZenGameMainView;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The GraphicControl class allows several actions during the graphic version of the game.
 * @author DELAUNAY Gurwan 1C1
 */
public class GraphicControl {

    private File fileConfiguration;
    private Pawn selectedPawn;

    /**
     * The constructor creates the save and configuration files if it does not exist
     */
    public GraphicControl() {
        try {
            Files.createDirectories(Paths.get("./ZenGame/data"));
            Files.createDirectories(Paths.get("./ZenGame/save"));
            this.fileConfiguration = new File("./ZenGame/data/configuration.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method removes game saves
     * @param zenGameMainView the view components of the game
     */
    public void deleteFiles(ZenGameMainView zenGameMainView) {
        try {
            JOptionPane.showMessageDialog(null, "Les sauvegardes sont supprimées", "Zen Game", JOptionPane.INFORMATION_MESSAGE);
            if (zenGameMainView.getViewLoadGame().getListeGame() != null) {
                for (String value : zenGameMainView.getViewLoadGame().getListeGame()) {
                    File f = new File("./ZenGame/save/" + value);
                    f.delete();
                }
            }
        } catch(NullPointerException e){
            System.err.println("Aucun fichier à supprimer");
        }
    }

    /**
     * This method allows to write in the configuration file the game mode.
     * @param i the gamemode
     */
    public void choixMode(int i) {
        try {
            this.fileConfiguration.delete();
            this.fileConfiguration = new File("./ZenGame/data/configuration.txt");
            FileWriter configuration = new FileWriter(fileConfiguration, true);
            configuration.write("gamemode : " + i + " :");

            try {
                configuration.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Game mode choice graphic version " + e.getMessage());
        }
    }

    /**
     * This method allows to write in the configuration file the difficulty of the AI
     * @param i the difficulty of the AI
     */
    public void choixDifficulty(int i) {
        try {
            FileWriter configuration = new FileWriter(fileConfiguration, true);
            configuration.write("\n");
            configuration.write("difficulty : " + i + " :");

            try {
                configuration.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("difficulty " + e.getMessage());
        }
    }

    /**
     * This method allows to write in the configuration file the number of turns of the game
     * @param i the number of turns of the game
     */
    public void choixNbTours(int i) {
        try {
            FileWriter configuration = new FileWriter(fileConfiguration, true);
            configuration.write("\n");
            configuration.write("turns : " + i + " :");

            try {
                configuration.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * The method allows to generate a random name for the AI if the game mode is human against AI
     * @return ret : a random name
     */
    public String nomAleatoire() {
        String ret;
        int randomName;
        String nameList[] = {"Leonard", "Cristiano", "Erwan", "Florian", "Duncan", "Dylan", "Pietro", "Alexandre", "Fanny", "Maurin", "Nicolas", "Jasmine", "Joni"};
        randomName = (int) (Math.random() * (nameList.length));
        ret = nameList[randomName];
        return ret;
    }

    /**
     * This method allows you to change the difficulty of the AI if it is wrong
     * @param zenGameMainView the view components of the game
     */
    public void setDifficultyIA(ZenGameMainView zenGameMainView) {
        if (zenGameMainView.getDifficultyIA() == 0) {
            this.choixDifficulty(1);
        } else {
            this.choixDifficulty(zenGameMainView.getDifficultyIA());
        }
    }

    /**
     * This method allows to play the AI
     * @param zenGameMainView the view components of the game
     * @param configuration the configuration of the game
     * @return ret : true if the AI played else false if the the AI hasn't played
     */
    public boolean iaPlay(ZenGameMainView zenGameMainView, Configuration configuration){
        boolean ret = false;
        if(zenGameMainView.getMode() == 1) {
            if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerTwo())) {
                int[] currentCoord = null;
                do {
                    if (configuration.getGamePlay().getZen() != null) {
                        this.setSelectedPawn(configuration.getGamePlay().getCurrentPlayer().play(configuration.getGamePlay().getPlayerOne().getPawns(), configuration.getGamePlay().getZen()));
                    } else {
                        this.setSelectedPawn(configuration.getGamePlay().getCurrentPlayer().play(configuration.getGamePlay().getPlayerOne().getPawns()));
                    }
                } while(this.getSelectedPawn().getPosition() == null);

                currentCoord = this.getSelectedPawn().getPosition();

                boolean nouveauTour = true;

                configuration.getGamePlay().getBoardGame()[currentCoord[0]][currentCoord[1]].setOccupy(false);

                for (Pawn pawn : configuration.getGamePlay().getPlayerOne().getPawns()) {
                    if (this.getSelectedPawn().getX() == pawn.getX() && this.getSelectedPawn().getY() == pawn.getY()) {
                        for (Pawn pawn1 : configuration.getGamePlay().getPlayerOne().getPawns()) {
                            if (!pawn.isVisite() && pawn1.isVisite()) {
                                configuration.getGamePlay().removePawn(pawn);
                                configuration.getGamePlay().setMatchNul(true);
                            } else {
                                configuration.getGamePlay().removePawn(pawn);
                            }
                        }
                    }
                }

                if(configuration.getGamePlay().getZen() != null) {
                    if (!(configuration.getGamePlay().getZen().equals(this.getSelectedPawn()))) {
                        if (configuration.getGamePlay().getZen().getX() == this.getSelectedPawn().getX() && configuration.getGamePlay().getZen().getY() == this.getSelectedPawn().getY()) {
                            configuration.getGamePlay().removePawn(configuration.getGamePlay().getZen());
                        }
                    }
                }

                configuration.getGamePlay().getBoardGame()[this.getSelectedPawn().getX()][this.getSelectedPawn().getY()].setOccupy(true);

                ret = true;
            }


        }
        return ret;
    }

    /**
     * This method plays the AI if the AI starts first in the game
     * @param zenGameMainView the view components of the game
     * @param configuration the configuration of the game
     */
    public void iaPlayInFirst(ZenGameMainView zenGameMainView, Configuration configuration){
        if(zenGameMainView.getMode() == 1) {
            if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerTwo())) {
                if(configuration.getGamePlay().isIaBegin()){
                    this.iaPlay(zenGameMainView,configuration);
                    configuration.getGamePlay().setIaBegin(false);
                    configuration.getGamePlay().changeCurrentPlayer();
                    zenGameMainView.launchViewInGame(configuration);
                }
            }
        }
    }

    /**
     * Method for modifying the selectedPawn
     * @param p the new selected Pawn
     */
    public void setSelectedPawn(Pawn p){
        this.selectedPawn = p;
    }

    /**
     * selectedPawn access method
     * @return selectedPawn : the pawn chosen by the player
     */
    public Pawn getSelectedPawn() {
        return this.selectedPawn;
    }
}



