package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.Box;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The view which allows you to choose the game options before launching the game
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewLaunchNewGame {

    private Container container;
    private ImageIcon logo;
    private JLabel logoLabel;
    private JLabel nbToursLabel;
    private JLabel name1Label;
    private JLabel name2Label;
    private JLabel difficultyLabel;
    private JButton buttonMusic;
    private JButton lancerPartieButton;
    private ImageIcon choiceNbTurns;
    private ImageIcon name1;
    private ImageIcon name2;
    private ImageIcon difficulty;
    private JTextField name1Input;
    private JTextField name2Input;
    private JRadioButton difficulty1;
    private JRadioButton difficulty2;
    private JRadioButton difficulty3;
    private ButtonGroup buttonGroupDifficulty;
    private JComboBox<Integer> nbToursCB;
    private Box boxDifficulty;
    private JPanel panelLogo;
    private JPanel panelRight;
    private JPanel panelMusique;
    private JPanel leftPanel;

    /**
     * Constructor of ViewLaunchNewGame
     * @param i 1 if the game mode is HA else HH
     * @param enLecture true if the music is playing else false
     */
    public ViewLaunchNewGame(int i,boolean enLecture){
        if(i == 1){
            this.newGameVsIA(enLecture);
        } else if(i == 2){
            this.newPartieVsPlayer(enLecture);
        }
    }

    /**
     * The method that triggers if the mode is HH
     * @param enLecture true if the music is playing else false
     */
    public void newPartieVsPlayer(boolean enLecture){
        this.container = new Container();
        GridLayout gridLayout = new GridLayout(1,2);
        this.container.setLayout(gridLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.leftPanel = new JPanel(new GridBagLayout());

        this.panelLogo = new JPanel(new BorderLayout());
        this.panelRight = new JPanel(new BorderLayout());
        this.panelMusique = new JPanel(new BorderLayout());

        this.logo = new ImageIcon(getClass().getResource("/res/logoZen.png"));
        this.logoLabel = new JLabel(logo);

        this.choiceNbTurns = new ImageIcon(getClass().getResource("/res/nombredetours.png"));
        this.nbToursLabel = new JLabel(choiceNbTurns);

        if(enLecture) {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
        } else {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonClose.png")));
        }
        this.buttonMusic.setContentAreaFilled(false);
        this.buttonMusic.setBorderPainted(false);

        Integer[] integerTab = new Integer[10];

        for(int i = 0; i<integerTab.length; i++){
            integerTab[i] = i+1;
        }

        this.nbToursCB = new JComboBox<>(integerTab);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 20f);
            this.nbToursCB.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.name1 = new ImageIcon(getClass().getResource("/res/nomjoueurun.png"));
        this.name1Label = new JLabel(name1);

        this.name2 = new ImageIcon(getClass().getResource("/res/nomdujoueurdeux.png"));
        this.name2Label = new JLabel(name2);

        this.name1Input = new JTextField("Camille");
        this.name1Input.setColumns(15);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 12f);
            this.name1Input.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.name2Input = new JTextField("Roger");
        this.name2Input.setColumns(15);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 12f);
            this.name2Input.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.lancerPartieButton = new JButton(new ImageIcon(getClass().getResource("/res/commencerlapartie.png")));
        this.lancerPartieButton.setContentAreaFilled(false);
        this.lancerPartieButton.setBorderPainted(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.leftPanel.add(nbToursLabel,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.leftPanel.add(nbToursCB,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.leftPanel.add(name1Label,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.leftPanel.add(name1Input,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.leftPanel.add(name2Label,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.leftPanel.add(name2Input,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.leftPanel.add(lancerPartieButton,gridBagConstraints);

        this.panelLogo.setBackground(new Color(230,44,45));
        this.panelRight.setBackground(new Color(230,44,45));
        this.leftPanel.setBackground(new Color(230,44,45));
        this.panelMusique.setBackground(new Color(230,44,45));

        this.panelMusique.add(buttonMusic, BorderLayout.EAST);

        this.panelLogo.add(logoLabel,BorderLayout.EAST);

        this.panelRight.add(panelLogo,BorderLayout.NORTH);
        this.panelRight.add(panelMusique,BorderLayout.SOUTH);

        this.container.add(leftPanel);
        this.container.add(panelRight);
    }

    /**
     * The method that triggers if the mode is HA
     * @param enLecture true if the music is playing else false
     */
    public void newGameVsIA(boolean enLecture){
        this.container = new Container();
        GridLayout gridLayout = new GridLayout(1,2);
        this.container.setLayout(gridLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.leftPanel = new JPanel(new GridBagLayout());

        this.panelMusique = new JPanel(new BorderLayout());
        this.panelLogo = new JPanel(new BorderLayout());
        this.panelRight = new JPanel(new BorderLayout());

        this.logo = new ImageIcon(getClass().getResource("/res/logoZen.png"));
        this.logoLabel = new JLabel(logo);

        this.difficulty = new ImageIcon(getClass().getResource("/res/difficulteIA.png"));
        this.difficultyLabel = new JLabel(difficulty);

        this.difficulty1 = new JRadioButton("1",true);
        this.difficulty2 = new JRadioButton("2");
        this.difficulty3 = new JRadioButton("3");

        if(enLecture) {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
        } else {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonClose.png")));
        }
        this.buttonMusic.setContentAreaFilled(false);
        this.buttonMusic.setBorderPainted(false);

        this.difficulty1.setBackground(new Color(230,44,45));
        this.difficulty1.setForeground(Color.WHITE);

        this.difficulty2.setBackground(new Color(230,44,45));
        this.difficulty2.setForeground(Color.WHITE);

        this.difficulty3.setBackground(new Color(230,44,45));
        this.difficulty3.setForeground(Color.WHITE);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 20f);
            this.difficulty1.setFont(dosis);
            this.difficulty2.setFont(dosis);
            this.difficulty3.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.buttonGroupDifficulty = new ButtonGroup();
        this.buttonGroupDifficulty.add(difficulty1);
        this.buttonGroupDifficulty.add(difficulty2);
        this.buttonGroupDifficulty.add(difficulty3);

        this.boxDifficulty = Box.createVerticalBox();
        this.boxDifficulty.add(difficulty1);
        this.boxDifficulty.add(difficulty2);
        this.boxDifficulty.add(difficulty3);

        this.choiceNbTurns = new ImageIcon(getClass().getResource("/res/nombredetours.png"));
        this.nbToursLabel = new JLabel(choiceNbTurns);

        Integer[] integerTab = new Integer[10];

        for(int i = 0; i<integerTab.length; i++){
            integerTab[i] = i+1;
        }

        this.nbToursCB = new JComboBox<>(integerTab);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 20f);
            this.nbToursCB.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.name1 = new ImageIcon(getClass().getResource("/res/nomjoueurun.png"));
        this.name1Label = new JLabel(name1);

        this.name1Input = new JTextField("Lucy");
        this.name1Input.setColumns(15);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 12f);
            this.name1Input.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }


        this.lancerPartieButton = new JButton(new ImageIcon(getClass().getResource("/res/commencerlapartie.png")));
        this.lancerPartieButton.setContentAreaFilled(false);
        this.lancerPartieButton.setBorderPainted(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.leftPanel.add(difficultyLabel,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.leftPanel.add(boxDifficulty,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.leftPanel.add(nbToursLabel,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.leftPanel.add(nbToursCB,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.leftPanel.add(name1Label,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        this.leftPanel.add(name1Input,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.leftPanel.add(lancerPartieButton,gridBagConstraints);

        this.panelRight.setBackground(new Color(230,44,45));
        this.panelLogo.setBackground(new Color(230,44,45));
        this.leftPanel.setBackground(new Color(230,44,45));
        this.panelMusique.setBackground(new Color(230,44,45));

        this.panelLogo.add(logoLabel,BorderLayout.EAST);
        this.panelMusique.add(buttonMusic, BorderLayout.EAST);

        this.panelRight.add(panelLogo,BorderLayout.NORTH);
        this.panelRight.add(panelMusique,BorderLayout.SOUTH);

        this.container.add(leftPanel);
        this.container.add(panelRight);
    }

    /**
     * Access method of the container of the ViewLaunchNewGame
     * @return container : the container of the ViewLaunchNewGame
     */
    public Container getContainer() {
        return this.container;
    }

    /**
     * Access method of the lancerPartie button
     * @return lancerPartieButton : the button to launch the game
     */
    public JButton getLancerPartieButton() {
        return this.lancerPartieButton;
    }

    /**
     * Access method of the name1Input text field
     * @return name1Input : the JTextField where the user enters his name
     */
    public JTextField getName1Input() {
        return this.name1Input;
    }

    /**
     * Access method of the name2Input text field
     * @return name2Input : the JTextField where the user enters the name of this mate
     */
    public JTextField getName2Input() {
        return this.name2Input;
    }

    /**
     * Access method of the choice of number of turns
     * @return nbToursCB : the choice of number of turns
     */
    public JComboBox<Integer> getNbToursCB() {
        return this.nbToursCB;
    }

    /**
     * Access method of the difficulty one choice
     * @return difficulty1 : the difficulty one choice
     */
    public JRadioButton getDifficulty1() {
        return this.difficulty1;
    }

    /**
     * Access method of the difficulty two choice
     * @return difficulty2 : the difficulty two choice
     */
    public JRadioButton getDifficulty2() {
        return this.difficulty2;
    }

    /**
     * Access method of the difficulty three choice
     * @return difficulty3 : the difficulty three choice
     */
    public JRadioButton getDifficulty3() {
        return this.difficulty3;
    }

    /**
     * Access method of the music button
     * @return buttonMusic : the music button
     */
    public JButton getButtonMusic() {
        return this.buttonMusic;
    }
}
