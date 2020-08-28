package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The view which allows you to load a game
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewLoadGame {
    private Container container;
    private ImageIcon logo;
    private ImageIcon loadImage;
    private JLabel logoLabel;
    private JLabel chargerLabel;
    private JButton returnButton;
    private JButton buttonMusic;
    private JPanel panelMusique;
    private JPanel leftPanel;
    private JPanel panelLogo;
    private JPanel panelRight;
    private ArrayList<String> fileToLoad;
    private boolean saveExist;
    private String[] listeGame;
    private JList<String> jList;

    /**
     * Constructor of ViewLoadGame
     * @param enLecture true if the music is playing else false
     */
    public ViewLoadGame(boolean enLecture){
        this.container = new Container();
        GridLayout gridLayout = new GridLayout(1,2);
        this.container.setLayout(gridLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.saveExist = true;

        this.leftPanel = new JPanel(new GridBagLayout());

        this.panelLogo = new JPanel(new BorderLayout());
        this.panelRight = new JPanel(new BorderLayout());
        this.panelMusique = new JPanel(new BorderLayout());

        if(enLecture) {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
        } else {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonClose.png")));
        }
        this.buttonMusic.setContentAreaFilled(false);
        this.buttonMusic.setBorderPainted(false);

        this.logo = new ImageIcon(getClass().getResource("/res/logoZen.png"));
        this.logoLabel = new JLabel(logo);

        this.loadImage = new ImageIcon(getClass().getResource("/res/chargerPartie.png"));
        this.chargerLabel = new JLabel(loadImage);

        this.returnButton = new JButton(new ImageIcon(getClass().getResource("/res/retour.png")));
        this.returnButton.setContentAreaFilled(false);
        this.returnButton.setBorderPainted(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.leftPanel.add(chargerLabel,gridBagConstraints);
        gridBagConstraints.gridy = 2;

        File repertoireLoad = new File("./ZenGame/save/");

        if (repertoireLoad.list() != null) {
            this.listeGame = repertoireLoad.list();
        }

        this.fileToLoad = new ArrayList<String>();

        if(this.listeGame != null && this.listeGame.length > 0){
            for (String value : this.listeGame) {
                this.fileToLoad.add(value);
            }
        } else {
            JTextArea noFile = new JTextArea("Aucune partie sauvegardée !");
            noFile.setBackground(new Color(230,44,45));
            noFile.setForeground(Color.WHITE);
            try {
                InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
                Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
                dosis = dosis.deriveFont(Font.BOLD, 40f);
                noFile.setFont(dosis);
            } catch (IOException | FontFormatException e) {
                System.err.println(e.getMessage());
            }
            this.saveExist = false;
            this.leftPanel.add(noFile,gridBagConstraints);
        }

        if(!(this.fileToLoad.isEmpty())) {
            String[] fileToLoadArray = new String[this.fileToLoad.size()];
            fileToLoadArray = fileToLoad.toArray(fileToLoadArray);
            this.jList = new JList<String>(fileToLoadArray);
            this.jList.setBackground(new Color(230,44,45));
            this.jList.setForeground(Color.WHITE);
            if(fileToLoadArray.length < 18) {
                if(fileToLoadArray.length < 10){
                    try {
                        InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
                        Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
                        dosis = dosis.deriveFont(Font.BOLD, 40f);
                        this.jList.setFont(dosis);
                    } catch (IOException | FontFormatException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    try {
                        InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
                        Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
                        dosis = dosis.deriveFont(Font.BOLD, 30f);
                        this.jList.setFont(dosis);
                    } catch (IOException | FontFormatException e) {
                        System.err.println(e.getMessage());
                    }
                }
            } else if(fileToLoadArray.length > 17 && fileToLoadArray.length < 27){
                try {
                    InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
                    Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
                    dosis = dosis.deriveFont(Font.BOLD, 20f);
                    this.jList.setFont(dosis);
                } catch (IOException | FontFormatException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        this.panelRight.setBackground(new Color(230,44,45));
        this.panelLogo.setBackground(new Color(230,44,45));
        this.leftPanel.setBackground(new Color(230,44,45));
        this.panelMusique.setBackground(new Color(230,44,45));

        this.panelMusique.add(buttonMusic, BorderLayout.EAST);
        this.panelLogo.add(logoLabel,BorderLayout.EAST);

        this.panelRight.add(panelLogo,BorderLayout.NORTH);
        this.panelRight.add(panelMusique,BorderLayout.SOUTH);

        if(this.jList != null) {
            leftPanel.add(jList, gridBagConstraints);
        }

        gridBagConstraints.gridy = 3;
        this.leftPanel.add(returnButton,gridBagConstraints);
        gridBagConstraints.gridy = 4;

        this.container.add(leftPanel);
        this.container.add(panelRight);

    }

    /**
     * Access method of the return button
     * @return returnButton : the button to return to the main menu
     */
    public JButton getReturnButton(){
        return this.returnButton;
    }

    /**
     * Access method of the container of the ViewLoadGame
     * @return container : the container of the ViewLoadGame
     */
    public Container getContainer() {
        return this.container;
    }

    /**
     * Access method of the listeGame
     * @return listeGame : the list of save
     */
    public String[] getListeGame() {
        return this.listeGame;
    }

    /**
     * Access method of the jList
     * @return jList : the JList of save
     */
    public JList<String> getjList() {
        return this.jList;
    }

    /**
     * Access method of saveExist boolean
     * @return saveExist : true if a save exists else false
     */
    public boolean saveExist(){
        return this.saveExist;
    }

    /**
     * Access method of the music button
     * @return buttonMusic : the music button
     */
    public JButton getButtonMusic() {
        return this.buttonMusic;
    }
}
