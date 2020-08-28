package controller;

import model.Configuration;
import model.Pawn;
import view.ZenGameMainView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Game MouseListener
 * This class implements MouseListener and allows to move the pawns on the board
 * @author DELAUNAY Gurwan 1C1
 */
public class MouseListenerZenGame implements MouseListener {

    private ZenGameMainView zenGameMainView;
    private GraphicControl graphicControl;
    private Configuration configuration;

    /**
     * The constructor of MouseListenerZenGame
     * @param zenGameMainView the view components of the game
     * @param configuration the configuration of the game
     */
    public MouseListenerZenGame(ZenGameMainView zenGameMainView, Configuration configuration){
        this.zenGameMainView = zenGameMainView;
        this.configuration = configuration;
        this.graphicControl = new GraphicControl();
    }

    /**
     * This method is executed when the user clicks on a component.
     * This method allows you to move the pawns on the board.
     * @param mouseEvent click action on a component
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        int row = zenGameMainView.getViewInGame().getTable().getSelectedRow();
        int col = zenGameMainView.getViewInGame().getTable().getSelectedColumn();

        int[] coord = new int[2];
        coord[0] = row;
        coord[1] = col;

        // si double clique alors le pion sur la case est selectionné
        if(mouseEvent.getClickCount() == 2) {
            if (configuration.getGamePlay().selectPawn(row, col) != null) {
                graphicControl.setSelectedPawn(configuration.getGamePlay().selectPawn(row, col));
            }
            // si clique alors le pion est déplacé sur cette case si le mouvement respecte les règles du jeu
        } else if(mouseEvent.getClickCount() == 1) {
            if (graphicControl.getSelectedPawn() != null) {
                boolean mouv = false;
                int[] currentCoord = null;

                if (graphicControl.getSelectedPawn().getPosition() != null) {
                    currentCoord = graphicControl.getSelectedPawn().getPosition();
                }

                if(zenGameMainView.getMode() == 2) {
                    if (graphicControl.getSelectedPawn() != null) {
                        if (configuration.getGamePlay().getZen() != null) {
                            if (configuration.getGamePlay().getPlayerOne().equals(configuration.getGamePlay().getCurrentPlayer())) {
                                mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerTwo().getPawns(), configuration.getGamePlay().getZen(), coord);
                            } else {
                                mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerOne().getPawns(), configuration.getGamePlay().getZen(), coord);
                            }
                        } else {
                            if (configuration.getGamePlay().getPlayerOne().equals(configuration.getGamePlay().getCurrentPlayer())) {
                                mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerTwo().getPawns(), coord);
                            } else {
                                mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerOne().getPawns(), coord);
                            }
                        }
                    }
                } else {
                    if(configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerOne())) {
                        if (graphicControl.getSelectedPawn() != null) {
                            if (configuration.getGamePlay().getZen() != null) {
                                if (configuration.getGamePlay().getPlayerOne().equals(configuration.getGamePlay().getCurrentPlayer())) {
                                    mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerTwo().getPawns(), configuration.getGamePlay().getZen(), coord);
                                } else {
                                    mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerOne().getPawns(), configuration.getGamePlay().getZen(), coord);
                                }
                            } else {
                                if (configuration.getGamePlay().getPlayerOne().equals(configuration.getGamePlay().getCurrentPlayer())) {
                                    mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerTwo().getPawns(), coord);
                                } else {
                                    mouv = configuration.getGamePlay().getCurrentPlayer().play(graphicControl.getSelectedPawn(), configuration.getGamePlay().getPlayerOne().getPawns(), coord);
                                }
                            }
                        }
                    }
                }

                if (currentCoord != null) {
                    if (mouv) {
                        boolean nouveauTour = true;

                        configuration.getGamePlay().getBoardGame()[currentCoord[0]][currentCoord[1]].setOccupy(false);

                        if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerOne())) {
                            for (Pawn pawn : configuration.getGamePlay().getPlayerTwo().getPawns()) {
                                if (graphicControl.getSelectedPawn().getX() == pawn.getX() && graphicControl.getSelectedPawn().getY() == pawn.getY()) {
                                    for (Pawn pawn1 : configuration.getGamePlay().getPlayerTwo().getPawns()) {
                                        if (!pawn.isVisite() && pawn1.isVisite()) {
                                            configuration.getGamePlay().removePawn(pawn);
                                            configuration.getGamePlay().setMatchNul(true);
                                        } else {
                                            configuration.getGamePlay().removePawn(pawn);
                                        }
                                    }
                                }
                            }

                        } else {
                            for (Pawn pawn : configuration.getGamePlay().getPlayerOne().getPawns()) {
                                if (graphicControl.getSelectedPawn().getX() == pawn.getX() && graphicControl.getSelectedPawn().getY() == pawn.getY()) {
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

                        }

                        if(configuration.getGamePlay().getZen() != null) {
                            if (!(configuration.getGamePlay().getZen().equals(graphicControl.getSelectedPawn()))) {
                                if (configuration.getGamePlay().getZen().getX() == graphicControl.getSelectedPawn().getX() && configuration.getGamePlay().getZen().getY() == graphicControl.getSelectedPawn().getY()) {
                                    configuration.getGamePlay().removePawn(configuration.getGamePlay().getZen());
                                }
                            }
                        }

                        configuration.getGamePlay().getBoardGame()[graphicControl.getSelectedPawn().getX()][graphicControl.getSelectedPawn().getY()].setOccupy(true);

                        if (configuration.getGamePlay().isDraw()) {
                            nouveauTour = false;
                        } else if (configuration.getGamePlay().win(configuration.getGamePlay().getPlayerOne())) {
                            nouveauTour = false;
                        } else if (configuration.getGamePlay().win(configuration.getGamePlay().getPlayerTwo())) {
                            nouveauTour = false;
                        }

                        if (nouveauTour) {

                            for (Pawn p1 : configuration.getGamePlay().getPlayerOne().getPawns()) {
                                if (p1.getX() == 1000 && p1.getY() == 1000) {
                                    p1.setVisited(true);
                                }
                            }

                            for (Pawn p2 : configuration.getGamePlay().getPlayerTwo().getPawns()) {
                                if (p2.getX() == 1000 && p2.getY() == 1000) {
                                    p2.setVisited(true);
                                }
                            }

                            if (configuration.getGamePlay().getZen() != null) {
                                configuration.getGamePlay().getZen().setVisited(false);
                            }

                            configuration.getGamePlay().changeCurrentPlayer();
                            zenGameMainView.launchViewInGame(configuration);

                            boolean iaPlayed = graphicControl.iaPlay(zenGameMainView,configuration);

                            if(iaPlayed){
                                if (configuration.getGamePlay().isDraw()) {
                                    nouveauTour = false;
                                } else if (configuration.getGamePlay().win(configuration.getGamePlay().getPlayerOne())) {
                                    nouveauTour = false;
                                } else if (configuration.getGamePlay().win(configuration.getGamePlay().getPlayerTwo())) {
                                    nouveauTour = false;
                                }

                                if (nouveauTour) {

                                    for (Pawn p1 : configuration.getGamePlay().getPlayerOne().getPawns()) {
                                        if (p1.getX() == 1000 && p1.getY() == 1000) {
                                            p1.setVisited(true);
                                        }
                                    }

                                    for (Pawn p2 : configuration.getGamePlay().getPlayerTwo().getPawns()) {
                                        if (p2.getX() == 1000 && p2.getY() == 1000) {
                                            p2.setVisited(true);
                                        }
                                    }

                                    if (configuration.getGamePlay().getZen() != null) {
                                        configuration.getGamePlay().getZen().setVisited(false);
                                    }

                                    configuration.getGamePlay().changeCurrentPlayer();
                                    zenGameMainView.launchViewInGame(configuration);
                                } else {
                                    configuration.getGamePlay().setNbToursJoues(configuration.getGamePlay().getNbToursJoues()+1);
                                    if(configuration.getGamePlay().getNbToursJoues() == configuration.getGamePlay().getTurns()){
                                        String endText = null;
                                        if(configuration.getGamePlay().win(configuration.getGamePlay().getCurrentPlayer())) {
                                            if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerOne())) {
                                                configuration.getGamePlay().setVictoryPlayerOne(configuration.getGamePlay().getVictoryPlayerOne() + 1);
                                            } else {
                                                configuration.getGamePlay().setVictoryPlayerTwo(configuration.getGamePlay().getVictoryPlayerTwo() + 1);
                                            }
                                        }
                                        if (configuration.getGamePlay().getTurns() == 1) {
                                            if (configuration.getGamePlay().getVictoryPlayerOne() > configuration.getGamePlay().getVictoryPlayerTwo()) {
                                                endText = "Bravo à " + configuration.getGamePlay().getPlayerOne().getName() + " qui remporte la partie";
                                            }
                                        } else {
                                            if (configuration.getGamePlay().getVictoryPlayerOne() > configuration.getGamePlay().getVictoryPlayerTwo()) {
                                                endText = "Bravo à " + configuration.getGamePlay().getPlayerOne().getName() + " qui remporte la partie avec " + configuration.getGamePlay().getVictoryPlayerOne() + " point(s)";
                                            } else if (configuration.getGamePlay().getVictoryPlayerOne() < configuration.getGamePlay().getVictoryPlayerTwo()) {
                                                endText = "Bravo à " + configuration.getGamePlay().getPlayerTwo().getName() + " qui remporte la partie avec " + configuration.getGamePlay().getVictoryPlayerOne() + " point(s)";
                                            } else {
                                                endText = "Aucun vainqueur ! Chaque joueur aura remporté " + configuration.getGamePlay().getVictoryPlayerTwo() + " tours chacun";
                                            }
                                        }

                                        if(endText == null){
                                            endText = "Fin de la partie";
                                        }

                                        zenGameMainView.launchEndOfTheGame(endText);
                                    } else {
                                        ArrayList<String> indication = new ArrayList<String>();
                                        if (configuration.getGamePlay().win(configuration.getGamePlay().getCurrentPlayer())) {
                                            if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerOne())) {
                                                configuration.getGamePlay().setVictoryPlayerOne(configuration.getGamePlay().getVictoryPlayerOne() + 1);
                                                configuration.getGamePlay().setPerdant(configuration.getGamePlay().getPlayerTwo());
                                                String result = "Victoire de " + configuration.getGamePlay().getPlayerOne().getName();
                                                indication.add(result);
                                            } else {
                                                configuration.getGamePlay().setVictoryPlayerTwo(configuration.getGamePlay().getVictoryPlayerTwo() + 1);
                                                configuration.getGamePlay().setPerdant(configuration.getGamePlay().getPlayerOne());
                                                String result = "Victoire de " + configuration.getGamePlay().getPlayerTwo().getName();
                                                indication.add(result);
                                            }
                                        } else {
                                            String result = "Match nul !";
                                            indication.add(result);
                                        }

                                        String score = "Score :";
                                        indication.add(score);
                                        String scorePlayerUn =  configuration.getGamePlay().getPlayerOne().getName() + " : " + configuration.getGamePlay().getVictoryPlayerOne() + " point(s)";
                                        indication.add(scorePlayerUn);
                                        String scorePlayerTwo =  configuration.getGamePlay().getPlayerTwo().getName() + " : " + configuration.getGamePlay().getVictoryPlayerTwo() + " point(s)";
                                        indication.add(scorePlayerTwo);
                                        String nextTurn = "On passe au tour numéro " + (configuration.getGamePlay().getNbToursJoues() + 1);
                                        indication.add(nextTurn);



                                        zenGameMainView.launchNextTurn(indication);
                                    }
                                }
                            }

                        } else {
                            configuration.getGamePlay().setNbToursJoues(configuration.getGamePlay().getNbToursJoues()+1);
                            if(configuration.getGamePlay().getNbToursJoues() == configuration.getGamePlay().getTurns()){
                                String endText = null;
                                if(configuration.getGamePlay().win(configuration.getGamePlay().getCurrentPlayer())) {
                                    if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerOne())) {
                                        configuration.getGamePlay().setVictoryPlayerOne(configuration.getGamePlay().getVictoryPlayerOne() + 1);
                                    } else {
                                        configuration.getGamePlay().setVictoryPlayerTwo(configuration.getGamePlay().getVictoryPlayerTwo() + 1);
                                    }
                                }
                                if (configuration.getGamePlay().getTurns() == 1) {
                                    if (configuration.getGamePlay().getVictoryPlayerOne() > configuration.getGamePlay().getVictoryPlayerTwo()) {
                                        endText = "Bravo à " + configuration.getGamePlay().getPlayerOne().getName() + " qui remporte la partie";
                                    }
                                } else {
                                    if (configuration.getGamePlay().getVictoryPlayerOne() > configuration.getGamePlay().getVictoryPlayerTwo()) {
                                        endText = "Bravo à " + configuration.getGamePlay().getPlayerOne().getName() + " qui remporte la partie avec " + configuration.getGamePlay().getVictoryPlayerOne() + " point(s)";
                                    } else if (configuration.getGamePlay().getVictoryPlayerOne() < configuration.getGamePlay().getVictoryPlayerTwo()) {
                                        endText = "Bravo à " + configuration.getGamePlay().getPlayerTwo().getName() + " qui remporte la partie avec " + configuration.getGamePlay().getVictoryPlayerOne() + " point(s)";
                                    } else {
                                        endText = "Aucun vainqueur ! Chaque joueur aura remporté " + configuration.getGamePlay().getVictoryPlayerTwo() + " tours chacun";
                                    }
                                }

                                if(endText == null){
                                    endText = "Fin de la partie";
                                }

                                zenGameMainView.launchEndOfTheGame(endText);
                            } else {
                                ArrayList<String> indication = new ArrayList<String>();
                                if (configuration.getGamePlay().win(configuration.getGamePlay().getCurrentPlayer())) {
                                    if (configuration.getGamePlay().getCurrentPlayer().equals(configuration.getGamePlay().getPlayerOne())) {
                                        configuration.getGamePlay().setVictoryPlayerOne(configuration.getGamePlay().getVictoryPlayerOne() + 1);
                                        configuration.getGamePlay().setPerdant(configuration.getGamePlay().getPlayerTwo());
                                        String result = "Victoire de " + configuration.getGamePlay().getPlayerOne().getName();
                                        indication.add(result);
                                    } else {
                                        configuration.getGamePlay().setVictoryPlayerTwo(configuration.getGamePlay().getVictoryPlayerTwo() + 1);
                                        configuration.getGamePlay().setPerdant(configuration.getGamePlay().getPlayerOne());
                                        String result = "Victoire de " + configuration.getGamePlay().getPlayerTwo().getName();
                                        indication.add(result);
                                    }
                                } else {
                                    String result = "Match nul !";
                                    indication.add(result);
                                }

                                String score = "Score :";
                                indication.add(score);
                                String scorePlayerUn =  configuration.getGamePlay().getPlayerOne().getName() + " : " + configuration.getGamePlay().getVictoryPlayerOne() + " point(s)";
                                indication.add(scorePlayerUn);
                                String scorePlayerTwo =  configuration.getGamePlay().getPlayerTwo().getName() + " : " + configuration.getGamePlay().getVictoryPlayerTwo() + " point(s)";
                                indication.add(scorePlayerTwo);
                                String nextTurn = "On passe au tour numéro " + (configuration.getGamePlay().getNbToursJoues() + 1);
                                indication.add(nextTurn);



                                zenGameMainView.launchNextTurn(indication);
                            }
                        }

                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * configuration access method
     * @return configuration : the configuration of the game
     */
    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * Method for modifying the configuration
     * @param configuration the configuration of the game
     */
    public void setConfiguration(Configuration configuration){
        this.configuration = configuration;
    }
}
