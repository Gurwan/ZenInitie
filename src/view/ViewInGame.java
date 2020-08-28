package view;

import model.Configuration;
import model.Pawn;
import model.Square;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The InGame view
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewInGame {
    private Container container;
    private JPanel panelButton;
    private JPanel panelPlateau;
    private JPanel panelMusique;
    private JButton sauvegarder;
    private JButton charger;
    private JButton quitter;
    private JButton buttonMusic;
    private JTextArea indication;
    private JTable table;
    private GridTableModel gridTableModel;

    /**
     * The constructor of the ViewInGame
     * @param configuration configuration of the game
     * @param enLecture true if the music is playing else false
     */
    public ViewInGame(Configuration configuration,boolean enLecture){
        this.container = new Container();
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.container.setLayout(gridBagLayout);
        GridBagConstraints bagConstraints = new GridBagConstraints();
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        if(enLecture) {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
        } else {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonClose.png")));
        }

        this.buttonMusic.setContentAreaFilled(false);
        this.buttonMusic.setBorderPainted(false);

        this.sauvegarder = new JButton(new ImageIcon(getClass().getResource("/res/sauvegarder.png")));
        this.sauvegarder.setContentAreaFilled(false);
        this.sauvegarder.setBorderPainted(false);

        this.charger = new JButton(new ImageIcon(getClass().getResource("/res/charger.png")));
        this.charger.setContentAreaFilled(false);
        this.charger.setBorderPainted(false);

        this.quitter = new JButton(new ImageIcon(getClass().getResource("/res/quitter.png")));
        this.quitter.setContentAreaFilled(false);
        this.quitter.setBorderPainted(false);

        this.indication = new JTextArea(configuration.getGamePlay().getIndicationGUI());
        this.indication.setBackground(new Color(230,44,45));
        this.indication.setForeground(Color.WHITE);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 20f);
            this.indication.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        Square[][] board = configuration.getGamePlay().getBoardGame();
        Pawn zen = configuration.getGamePlay().getZen();
        ArrayList<Pawn> pawnWhite = configuration.getGamePlay().getPlayerOne().getPawns();
        ArrayList<Pawn> pawnBlack = configuration.getGamePlay().getPlayerTwo().getPawns();

        this.gridTableModel = new GridTableModel(board, zen, pawnWhite, pawnBlack);
        this.table = new JTable(this.gridTableModel);
        this.table.setShowGrid(true);
        this.table.setRowHeight(75);

        this.panelMusique = new JPanel(new BorderLayout());
        this.panelMusique.setBackground(new Color(230,44,45));
        this.panelMusique.add(buttonMusic, BorderLayout.EAST);

        this.panelButton = new JPanel(new GridBagLayout());
        this.panelButton.setBackground(new Color(230,44,45));

        this.panelPlateau = new JPanel(new BorderLayout());
        this.panelPlateau.setBackground(new Color(230,44,45));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.panelButton.add(sauvegarder,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.panelButton.add(charger,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.panelButton.add(quitter,gridBagConstraints);
        gridBagConstraints.gridy = 6;
        this.panelButton.add(panelMusique,gridBagConstraints);
        this.container.add(indication,bagConstraints);
        bagConstraints.gridy = 2;
        this.panelPlateau.add(table,BorderLayout.SOUTH);
        bagConstraints.weightx = 0.6;
        this.container.add(panelPlateau,bagConstraints);
        bagConstraints.weightx = 0.4;
        this.container.add(panelButton,bagConstraints);
    }

    /**
     * Access method of the container of the ViewInGame
     * @return container : the container of the ViewInGame
     */
    public Container getContain(){
        return this.container;
    }

    /**
     * Access method of the sauvegarder button
     * @return sauvegarder : the button for access to the ViewSaveGame
     */
    public JButton getSauvegarder(){
        return this.sauvegarder;
    }

    /**
     * Access method of the charger button
     * @return charger : the button for access to the ViewLoadGame
     */
    public JButton getCharger(){
        return this.charger;
    }

    /**
     * Access method of the quitter button
     * @return quitter : the button for quit the game
     */
    public JButton getQuitter(){
        return this.quitter;
    }

    /**
     * Access method of the table
     * @return table : the graphic board game
     */
    public JTable getTable() {
        return this.table;
    }

    /**
     * Access method of the music button
     * @return buttonMusic : the button for stop or start the music
     */
    public JButton getButtonMusic() {
        return this.buttonMusic;
    }
}
