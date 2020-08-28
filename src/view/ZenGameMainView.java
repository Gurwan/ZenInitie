package view;

import controller.GraphicControl;
import controller.MouseListenerZenGame;
import controller.MusicPlayer;
import controller.ButtonListenerZenGame;
import controller.MouseListenerLoadGame;
import controller.ItemListenerZenGame;
import controller.FocusListenerZenGame;

import model.Configuration;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.ArrayList;

/**
 * Game frame class
 * @author DELAUNAY Gurwan 1C1
 */
public class ZenGameMainView extends JFrame {

    private ViewMain viewMain;
    private ViewNewGame viewNewGame;
    private ViewLoadGame viewLoadGame;
    private ViewLoadGame viewLoadGameInGame;
    private ViewSettings viewSettings;
    private ViewLaunchNewGame viewLaunchNewGame;
    private ViewInGame viewInGame;
    private ViewSaveGame viewSaveGame;
    private ViewRules viewRules;
    private ViewEndOfTheGame viewEndOfTheGame;
    private ViewNextTurn viewNextTurn;
    private Container viewMainC;
    private Container viewInGameContainer;
    private Container viewSettingsContainer;
    private GraphicControl graphicControl;
    private MouseListenerZenGame mouseList;
    private MusicPlayer musicPlayer;
    private int difficultyIA;
    private int mode;

    /**
     * Constructor of ZenGameMainView
     */
    public ZenGameMainView(){
        this.graphicControl = new GraphicControl();
        musicPlayer = new MusicPlayer("/res/musique.wav");
        musicPlayer.playMusic();
        musicPlayer.setEnLecture(true);
        this.initializeMain();
    }

    /**
     * Method putting the viewMain in the frame
     */
    public void initializeMain(){
        setTitle("Zen Game");
        BorderLayout borderLayout = new BorderLayout();
        this.viewMain = new ViewMain(musicPlayer.isEstEnLecture());
        getContentPane().setLayout(borderLayout);
        getContentPane().setBackground(new Color(230,44,45));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1920,1080));
        setResizable(true);

        this.viewMain.getNewPartieButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewMain.getLoadPartieButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewMain.getSettingsButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewMain.getQuitButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewMain.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        viewMainC = this.viewMain.getContainer();

        add(viewMainC);
        pack();
        setVisible(true);

    }

    /**
     * Method putting the viewNewGame in the frame
     */
    public void newGame(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewNewGame = new ViewNewGame(musicPlayer.isEstEnLecture());

        this.viewNewGame.getUnVsUnButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewNewGame.getIAButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewNewGame.getReturnButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewNewGame.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        Container viewNewGameC = this.viewNewGame.getContain();
        contain.add(viewNewGameC);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);

    }

    /**
     * Method putting the viewLoadGame in the frame
     */
    public void loadGame(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewLoadGame = new ViewLoadGame(musicPlayer.isEstEnLecture());

        this.viewLoadGame.getReturnButton().addActionListener(new ButtonListenerZenGame(this));
        if(this.viewLoadGame.saveExist()) {
            this.viewLoadGame.getjList().addMouseListener(new MouseListenerLoadGame(this));
        }
        this.viewLoadGame.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        Container viewLoadGameC = this.viewLoadGame.getContainer();

        contain.add(viewLoadGameC);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);

    }

    /**
     * Method putting the viewLoadGameInGame in the frame
     */
    public void loadGameInGame(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();


        this.viewLoadGameInGame = new ViewLoadGame(musicPlayer.isEstEnLecture());

        this.viewLoadGameInGame.getReturnButton().addActionListener(new ButtonListenerZenGame(this));
        if(this.viewLoadGameInGame.saveExist()) {
            this.viewLoadGameInGame.getjList().addMouseListener(new MouseListenerLoadGame(this, mouseList));
        }
        this.viewLoadGameInGame.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        Container viewLoadGameC = this.viewLoadGameInGame.getContainer();

        contain.add(viewLoadGameC);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method putting the viewSettings in the frame
     */
    public void settings(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewSettings = new ViewSettings(musicPlayer.isEstEnLecture());

        this.viewSettings.getRulesButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewSettings.getDeleteFilesButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewSettings.getReturnButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewSettings.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        this.viewSettingsContainer = this.viewSettings.getContain();

        contain.add(viewSettingsContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method for leaving the game
     */
    public void quitGame(){
        System.exit(0);
    }

    /**
     * Method putting the viewLaunchNewGame in the frame
     * @param i the gamemode 1 if the mode is HA 2 if the mode is HH
     */
    public void launchNewGame(int i){
        if(i != 0) {
            this.graphicControl.choixMode(i);
        }

        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.mode = i;

        this.viewLaunchNewGame = new ViewLaunchNewGame(i,musicPlayer.isEstEnLecture());

        this.viewLaunchNewGame.getLancerPartieButton().addActionListener(new ButtonListenerZenGame(this));

        if(i == 1) {
            this.viewLaunchNewGame.getDifficulty1().addItemListener(new ItemListenerZenGame(this));
            this.viewLaunchNewGame.getDifficulty2().addItemListener(new ItemListenerZenGame(this));
            this.viewLaunchNewGame.getDifficulty3().addItemListener(new ItemListenerZenGame(this));
            this.viewLaunchNewGame.getName1Input().addFocusListener(new FocusListenerZenGame(this));
        } else if(i == 2){
            this.viewLaunchNewGame.getName1Input().addFocusListener(new FocusListenerZenGame(this));
            this.viewLaunchNewGame.getName2Input().addFocusListener(new FocusListenerZenGame(this));
        }
        this.viewLaunchNewGame.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        Container viewLaunchNewGameContainer = this.viewLaunchNewGame.getContainer();

        contain.add(viewLaunchNewGameContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method putting the viewInGame in the frame
     * @param configuration the configuration of the game
     */
    public void launchViewInGame(Configuration configuration){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewInGame = new ViewInGame(configuration,musicPlayer.isEstEnLecture());

        this.mouseList = new MouseListenerZenGame(this,configuration);
        this.viewInGame.getTable().addMouseListener(mouseList);

        this.viewInGame.getSauvegarder().addActionListener(new ButtonListenerZenGame(this));
        this.viewInGame.getCharger().addActionListener(new ButtonListenerZenGame(this));
        this.viewInGame.getQuitter().addActionListener(new ButtonListenerZenGame(this));
        this.viewInGame.getButtonMusic().addActionListener(new ButtonListenerZenGame(this));

        graphicControl.iaPlayInFirst(this,configuration);

        viewInGameContainer = this.viewInGame.getContain();

        contain.add(viewInGameContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);

    }

    /**
     * Method putting the viewEndOfTheGame in the frame
     * @param s the result of the game
     */
    public void launchEndOfTheGame(String s){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewEndOfTheGame = new ViewEndOfTheGame(s);

        this.viewEndOfTheGame.getReturnMainMenu().addActionListener(new ButtonListenerZenGame(this));

        Container viewEndOfTheGameContainer = this.viewEndOfTheGame.getContain();

        contain.add(viewEndOfTheGameContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method putting the viewNextTurn in the frame
     * @param indication The results of the turn
     */
    public void launchNextTurn(ArrayList<String> indication){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewNextTurn = new ViewNextTurn(indication);

        this.viewNextTurn.getNextTurnButton().addActionListener(new ButtonListenerZenGame(this));

        Container viewNextTurnContainer = this.viewNextTurn.getContain();

        contain.add(viewNextTurnContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method of modification of the difficulty of the AI
     * @param i the difficulty of the AI
     */
    public void setDifficultyIA(int i){
        this.difficultyIA = i;
    }

    /**
     * Access method of the difficulty of the AI
     * @return difficultyIA : the difficulty of the AI
     */
    public int getDifficultyIA() {
        return this.difficultyIA;
    }

    /**
     * Method allows to put back the viewMain in the frame
     */
    public void returnToMain(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();
        contain.add(this.viewMainC);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method allows to put back the viewInGame in the frame
     */
    public void returnToGame(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();
        contain.add(this.viewInGameContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method allows to put back the viewSettings in the frame
     */
    public void returnToSettings(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();
        contain.add(this.viewSettingsContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);
    }

    /**
     * Method putting the viewRules in the frame
     */
    public void showRules(){
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewRules = new ViewRules();

        this.viewRules.getRetButton().addActionListener(new ButtonListenerZenGame(this));

        Container viewRulesContainer = this.viewRules.getContain();

        contain.add(viewRulesContainer);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);

    }

    /**
     * Method putting the viewSaveGame in the frame
     */
    public void sauvegarderPartie() {
        Container contain;
        contain = this.getContentPane();
        contain.removeAll();

        this.viewSaveGame = new ViewSaveGame();

        this.viewSaveGame.getSauvegarderButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewSaveGame.getReturnButton().addActionListener(new ButtonListenerZenGame(this));
        this.viewSaveGame.getNomSauvegarde().addFocusListener(new FocusListenerZenGame(this));

        Container viewSaveContain = this.viewSaveGame.getContainer();

        contain.add(viewSaveContain);
        contain.validate();
        contain.repaint();
        contain.setVisible(true);

    }

    /**
     * Access method of the viewMain
     * @return viewMain : the main view
     */
    public ViewMain getViewMain() {
        return this.viewMain;
    }

    /**
     * Access method of the viewNewGame
     * @return viewNewGame : the new game view
     */
    public ViewNewGame getViewNewGame() {
        return this.viewNewGame;
    }

    /**
     * Access method of the viewLoadGame
     * @return viewLoadGame : the load game view
     */
    public ViewLoadGame getViewLoadGame() {
        return this.viewLoadGame;
    }

    /**
     * Access method of the viewLoadGameInGame
     * @return viewLoadGameInGame : the load game in game view
     */
    public ViewLoadGame getViewLoadGameInGame() {
        return this.viewLoadGameInGame;
    }

    /**
     * Access method of the viewSettings
     * @return viewSettings : the settings view
     */
    public ViewSettings getViewSettings(){
        return this.viewSettings;
    }

    /**
     * Access method of the viewLaunchNewGame
     * @return viewLaunchNewGame : the launch new game view
     */
    public ViewLaunchNewGame getViewLaunchNewGame() {
        return this.viewLaunchNewGame;
    }

    /**
     * Access method of the viewInGame
     * @return viewInGame : the in game view
     */
    public ViewInGame getViewInGame(){
        return this.viewInGame;
    }

    /**
     * Access method of the viewSaveGame
     * @return viewSaveGame : the save game view
     */
    public ViewSaveGame getViewSaveGame() {
        return this.viewSaveGame;
    }

    /**
     * Access method of the viewRules
     * @return viewRules : the rules view
     */
    public ViewRules getViewRules() {
        return this.viewRules;
    }

    /**
     * Access method of the mode
     * @return mode : the gamemode
     */
    public int getMode(){
        return this.mode;
    }

    /**
     * Access method of the mouseList
     * @return mouseList : the mouse listener for the board game
     */
    public MouseListenerZenGame getMouseList(){
        return this.mouseList;
    }

    /**
     * Access method of the viewEndOfTheGame
     * @return viewEndOfTheGame : the end game view
     */
    public ViewEndOfTheGame getViewEndOfTheGame() {
        return this.viewEndOfTheGame;
    }

    /**
     * Access method of the viewNextTurn
     * @return viewNextTurn : the next turn view
     */
    public ViewNextTurn getViewNextTurn(){
        return this.viewNextTurn;
    }

    /**
     * Access method of the musicPlayer
     * @return musicPlayer : the music player
     */
    public MusicPlayer getMusicPlayer(){
        return this.musicPlayer;
    }
}
