package controller;

import model.Configuration;
import view.ZenGameMainView;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Game Listener Button
 * This class implements ActionListener and allows you to perform an action following a click on a button.
 * @author DELAUNAY Gurwan 1C1
 */
public class ButtonListenerZenGame implements ActionListener {
    private ZenGameMainView zenGameMainView;
    private JButton button;
    private int mode;
    private GraphicControl graphicControl;
    private Configuration configuration;

    /**
     * Constructor of the ButtonListenerZenGame
     * @param zenGameMainView the view components of the game
     */
    public ButtonListenerZenGame(ZenGameMainView zenGameMainView) {
        this.zenGameMainView = zenGameMainView;
        graphicControl = new GraphicControl();
    }

    /**
     * This method lets you know which button the user clicks on and then performs the associated action.
     * The method checks that each component isn't null so as not to cause a Null Pointer Exception at the click of a button
     * @param actionEvent the action takes when the user clicks a button
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        button = (JButton) actionEvent.getSource();

        if (this.zenGameMainView.getViewMain() != null && button.equals(this.zenGameMainView.getViewMain().getNewPartieButton())) {
            this.zenGameMainView.newGame();
        } else if (this.zenGameMainView.getViewMain() != null && button.equals(this.zenGameMainView.getViewMain().getLoadPartieButton())) {
            this.zenGameMainView.loadGame();
        } else if (this.zenGameMainView.getViewMain() != null && button.equals(this.zenGameMainView.getViewMain().getSettingsButton())) {
            this.zenGameMainView.settings();
        } else if (this.zenGameMainView.getViewMain() != null && button.equals(this.zenGameMainView.getViewMain().getQuitButton())) {
            this.zenGameMainView.quitGame();
        } else if(this.zenGameMainView.getViewMain() != null && button.equals(this.zenGameMainView.getViewMain().getButtonMusic())){
            if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                this.zenGameMainView.getMusicPlayer().stopMusic();
                this.zenGameMainView.getViewMain().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
            } else {
                this.zenGameMainView.getMusicPlayer().relaunchMusic();
                this.zenGameMainView.getViewMain().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
            }
        } else if (this.zenGameMainView.getViewNewGame() != null && button.equals(this.zenGameMainView.getViewNewGame().getUnVsUnButton())) {
            this.zenGameMainView.launchNewGame(2);
            this.mode = 2;
        } else if (this.zenGameMainView.getViewNewGame() != null && button.equals(this.zenGameMainView.getViewNewGame().getIAButton())) {
            this.zenGameMainView.launchNewGame(1);
            this.mode = 1;
        } else if (this.zenGameMainView.getViewNewGame() != null && button.equals(this.zenGameMainView.getViewNewGame().getReturnButton())) {
            this.zenGameMainView.returnToMain();
        } else if(this.zenGameMainView.getViewNewGame() != null && button.equals(this.zenGameMainView.getViewNewGame().getButtonMusic())){
                if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                    this.zenGameMainView.getMusicPlayer().stopMusic();
                    this.zenGameMainView.getViewNewGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
                } else {
                    this.zenGameMainView.getMusicPlayer().relaunchMusic();
                    this.zenGameMainView.getViewNewGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
                }
        } else if(this.zenGameMainView.getViewLoadGame() != null && button.equals(this.zenGameMainView.getViewLoadGame().getReturnButton())) {
            this.zenGameMainView.returnToMain();
        } else if(this.zenGameMainView.getViewLoadGame() != null &&button.equals(this.zenGameMainView.getViewLoadGame().getButtonMusic())){
            if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                this.zenGameMainView.getMusicPlayer().stopMusic();
                this.zenGameMainView.getViewLoadGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
            } else {
                this.zenGameMainView.getMusicPlayer().relaunchMusic();
                this.zenGameMainView.getViewLoadGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
            }
        } else if(this.zenGameMainView.getViewSettings() != null && button.equals(this.zenGameMainView.getViewSettings().getReturnButton())){
            this.zenGameMainView.returnToMain();
        } else if (this.zenGameMainView.getViewSettings() != null && button.equals(this.zenGameMainView.getViewSettings().getRulesButton())) {
            this.zenGameMainView.showRules();
        } else if (this.zenGameMainView.getViewSettings() != null && button.equals(this.zenGameMainView.getViewSettings().getDeleteFilesButton())) {
            this.graphicControl.deleteFiles(zenGameMainView);
        } else if(this.zenGameMainView.getViewSettings() != null && button.equals(this.zenGameMainView.getViewSettings().getButtonMusic())){
            if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                this.zenGameMainView.getMusicPlayer().stopMusic();
                this.zenGameMainView.getViewSettings().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
            } else {
                this.zenGameMainView.getMusicPlayer().relaunchMusic();
                this.zenGameMainView.getViewSettings().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
            }
        } else if(this.zenGameMainView.getViewRules() != null && button.equals(this.zenGameMainView.getViewRules().getRetButton())){
            this.zenGameMainView.returnToSettings();
        } else if (this.zenGameMainView.getViewLaunchNewGame() != null && button.equals(this.zenGameMainView.getViewLaunchNewGame().getLancerPartieButton())){

            this.graphicControl.setDifficultyIA(zenGameMainView);

            int i = this.zenGameMainView.getViewLaunchNewGame().getNbToursCB().getSelectedIndex();
            i = i+1;
            this.graphicControl.choixNbTours(i);

            String playerName1 = this.zenGameMainView.getViewLaunchNewGame().getName1Input().getText();
            String playerName2 = null;

            if (zenGameMainView.getMode() == 2) {
                playerName2 = this.zenGameMainView.getViewLaunchNewGame().getName2Input().getText();
            }

            if(playerName2 != null) {
                configuration = new Configuration("./ZenGame/data/configuration.txt", playerName1, playerName2, 1);
            } else {
                configuration = new Configuration("./ZenGame/data/configuration.txt", playerName1, this.graphicControl.nomAleatoire(), 1);
            }

            this.zenGameMainView.launchViewInGame(configuration);
        } else if(this.zenGameMainView.getViewLaunchNewGame() != null && button.equals(this.zenGameMainView.getViewLaunchNewGame().getButtonMusic())){
            if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                this.zenGameMainView.getMusicPlayer().stopMusic();
                this.zenGameMainView.getViewLaunchNewGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
            } else {
                this.zenGameMainView.getMusicPlayer().relaunchMusic();
                this.zenGameMainView.getViewLaunchNewGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
            }
        } else if(this.zenGameMainView.getViewInGame() != null && button.equals(this.zenGameMainView.getViewInGame().getSauvegarder())){
            this.zenGameMainView.sauvegarderPartie();
        } else if (this.zenGameMainView.getViewInGame() != null && button.equals(this.zenGameMainView.getViewInGame().getCharger())) {
            this.zenGameMainView.loadGameInGame();
        } else if (this.zenGameMainView.getViewInGame() != null && button.equals(this.zenGameMainView.getViewInGame().getQuitter())) {
            this.zenGameMainView.quitGame();
        } else if(this.zenGameMainView.getViewInGame() != null && button.equals(this.zenGameMainView.getViewInGame().getButtonMusic())){
            if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                this.zenGameMainView.getMusicPlayer().stopMusic();
                this.zenGameMainView.getViewInGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
            } else {
                this.zenGameMainView.getMusicPlayer().relaunchMusic();
                this.zenGameMainView.getViewInGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
            }
        } else if (this.zenGameMainView.getViewLoadGameInGame() != null && button.equals(this.zenGameMainView.getViewLoadGameInGame().getReturnButton())) {
            this.zenGameMainView.returnToGame();
        } else if(this.zenGameMainView.getViewLoadGameInGame() != null && button.equals(this.zenGameMainView.getViewLoadGameInGame().getButtonMusic())){
            if(this.zenGameMainView.getMusicPlayer().isEstEnLecture()) {
                this.zenGameMainView.getMusicPlayer().stopMusic();
                this.zenGameMainView.getViewLoadGameInGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonClose.png")));
            } else {
                this.zenGameMainView.getMusicPlayer().relaunchMusic();
                this.zenGameMainView.getViewLoadGameInGame().getButtonMusic().setIcon(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
            }
        } else if (this.zenGameMainView.getViewSaveGame() != null && button.equals(this.zenGameMainView.getViewSaveGame().getReturnButton())) {
            this.zenGameMainView.returnToGame();
        } else if (this.zenGameMainView.getViewSaveGame() != null && button.equals(this.zenGameMainView.getViewSaveGame().getSauvegarderButton())) {
            if(this.zenGameMainView.getMouseList().getConfiguration() != null) {
                this.zenGameMainView.getMouseList().getConfiguration().saveGame(this.zenGameMainView.getViewSaveGame().getNomSauvegarde().getText());
            }
            this.zenGameMainView.returnToGame();
        } else if(this.zenGameMainView.getViewNextTurn() != null && button.equals(this.zenGameMainView.getViewNextTurn().getNextTurnButton())){
            this.zenGameMainView.getMouseList().getConfiguration().getGamePlay().newTurn();
            this.zenGameMainView.launchViewInGame(this.zenGameMainView.getMouseList().getConfiguration());
        } else if(this.zenGameMainView.getViewEndOfTheGame() != null && button.equals(this.zenGameMainView.getViewEndOfTheGame().getReturnMainMenu())){
            this.zenGameMainView.returnToMain();
        }
    }

}
