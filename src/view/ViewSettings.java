package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * The view of settings
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewSettings {
    private Container container;
    private ImageIcon logo;
    private JLabel logoLabel;
    private JPanel panelButton;
    private JPanel panelRight;
    private JPanel panelLogo;
    private JPanel panelMusique;
    private JButton rulesButton;
    private JButton deleteFilesButton;
    private JButton returnButton;
    private JButton buttonMusic;

    /**
     * Constructor of ViewSettings
     * @param enLecture true if the music is playing else false
     */
    public ViewSettings(boolean enLecture){
        this.container = new Container();
        GridLayout gridLayout = new GridLayout(1,2);
        this.container.setLayout(gridLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.panelButton = new JPanel(new GridBagLayout());
        this.panelRight = new JPanel(new BorderLayout());
        this.panelLogo = new JPanel(new BorderLayout());
        this.panelMusique = new JPanel(new BorderLayout());

        this.logo = new ImageIcon(getClass().getResource("/res/logoZen.png"));
        this.logoLabel = new JLabel(logo);

        this.rulesButton = new JButton(new ImageIcon(getClass().getResource("/res/rules.png")));
        this.rulesButton.setContentAreaFilled(false);
        this.rulesButton.setBorderPainted(false);

        this.deleteFilesButton = new JButton(new ImageIcon(getClass().getResource("/res/supprimersauvegardes.png")));
        this.deleteFilesButton.setContentAreaFilled(false);
        this.deleteFilesButton.setBorderPainted(false);

        this.returnButton = new JButton(new ImageIcon(getClass().getResource("/res/retour.png")));
        this.returnButton.setContentAreaFilled(false);
        this.returnButton.setBorderPainted(false);

        if(enLecture) {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
        } else {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonClose.png")));
        }
        this.buttonMusic.setContentAreaFilled(false);
        this.buttonMusic.setBorderPainted(false);

        this.panelLogo.setBackground(new Color(230,44,45));
        this.panelRight.setBackground(new Color(230,44,45));
        this.panelButton.setBackground(new Color(230,44,45));
        this.panelMusique.setBackground(new Color(230,44,45));

        this.panelLogo.add(logoLabel,BorderLayout.EAST);
        this.panelMusique.add(buttonMusic, BorderLayout.EAST);
        this.panelRight.add(panelLogo,BorderLayout.NORTH);
        this.panelRight.add(panelMusique,BorderLayout.SOUTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.panelButton.add(rulesButton,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.panelButton.add(deleteFilesButton,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.panelButton.add(returnButton,gridBagConstraints);
        gridBagConstraints.gridy = 4;

        this.container.add(panelButton);
        this.container.add(panelRight);

    }

    /**
     * Access method of the container of the ViewSettings
     * @return container : the container of the ViewSettings
     */
    public Container getContain(){
        return this.container;
    }

    /**
     * Access method of the rulesButton
     * @return rulesButton : the button to access to the rules of the game
     */
    public JButton getRulesButton(){
        return this.rulesButton;
    }

    /**
     * Access method of the deleteFilesButton
     * @return deleteFilesButton : the button to delete the save files
     */
    public JButton getDeleteFilesButton(){
        return this.deleteFilesButton;
    }

    /**
     * Access method of the retButton
     * @return retButton : the button to return to the settings
     */
    public JButton getReturnButton(){
        return this.returnButton;
    }

    /**
     * Access method of the music button
     * @return buttonMusic : the music button
     */
    public JButton getButtonMusic() {
        return this.buttonMusic;
    }
}
