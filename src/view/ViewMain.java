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
 * The main view of the game
 */
public class ViewMain {
    private Container container;
    private ImageIcon logo;
    private JLabel logoLabel;
    private JPanel panelButton;
    private JPanel panelLogo;
    private JPanel panelRight;
    private JPanel panelMusique;
    private JButton newPartieButton;
    private JButton loadPartieButton;
    private JButton settingsButton;
    private JButton quitButton;
    private JButton buttonMusic;

    /**
     * Constructor of ViewMain
     * @param enLecture true if the music is playing else false
     */
    public ViewMain(boolean enLecture){
        this.container = new Container();
        GridLayout gridLayout = new GridLayout(1,2);
        this.container.setLayout(gridLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.logo = new ImageIcon(getClass().getResource("/res/logoZen.png"));
        this.logoLabel = new JLabel(logo);

        if(enLecture) {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonOpen.png")));
        } else {
            this.buttonMusic = new JButton(new ImageIcon(getClass().getResource("/res/sonClose.png")));
        }
        this.buttonMusic.setContentAreaFilled(false);
        this.buttonMusic.setBorderPainted(false);

        this.newPartieButton = new JButton(new ImageIcon(getClass().getResource("/res/newPartie.png")));
        this.newPartieButton.setContentAreaFilled(false);
        this.newPartieButton.setBorderPainted(false);

        this.loadPartieButton = new JButton(new ImageIcon(getClass().getResource("/res/loadGame.png")));
        this.loadPartieButton.setContentAreaFilled(false);
        this.loadPartieButton.setBorderPainted(false);

        this.settingsButton = new JButton(new ImageIcon(getClass().getResource("/res/settings.png")));
        this.settingsButton.setContentAreaFilled(false);
        this.settingsButton.setBorderPainted(false);

        this.quitButton = new JButton(new ImageIcon(getClass().getResource("/res/quitter.png")));
        this.quitButton.setContentAreaFilled(false);
        this.quitButton.setBorderPainted(false);

        this.panelButton = new JPanel(new GridBagLayout());

        this.panelLogo = new JPanel(new BorderLayout());
        this.panelRight = new JPanel(new BorderLayout());
        this.panelMusique = new JPanel(new BorderLayout());

        this.panelRight.setBackground(new Color(230,44,45));
        this.panelLogo.setBackground(new Color(230,44,45));
        this.panelButton.setBackground(new Color(230,44,45));
        this.panelMusique.setBackground(new Color(230,44,45));

        this.panelMusique.add(buttonMusic, BorderLayout.EAST);

        this.panelLogo.add(logoLabel,BorderLayout.EAST);

        this.panelRight.add(panelLogo,BorderLayout.NORTH);
        this.panelRight.add(panelMusique,BorderLayout.SOUTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.panelButton.add(newPartieButton,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.panelButton.add(loadPartieButton,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.panelButton.add(settingsButton,gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.panelButton.add(quitButton,gridBagConstraints);
        gridBagConstraints.gridy = 5;

        this.container.add(panelButton);
        this.container.add(panelRight);
    }

    /**
     * Access method of the container of the ViewMain
     * @return container : the container of the ViewMain
     */
    public Container getContainer() {
        return this.container;
    }

    /**
     * Access method of the newPartieButton
     * @return newPartieButton : the button to launch a new game
     */
    public JButton getNewPartieButton(){
        return this.newPartieButton;
    }

    /**
     * Access method of the loadPartieButton
     * @return loadPartieButton : the button to load a game
     */
    public JButton getLoadPartieButton(){
        return this.loadPartieButton;
    }

    /**
     * Access method of the settingsButton
     * @return settingsButton : the button to go to the settings
     */
    public JButton getSettingsButton(){
        return this.settingsButton;
    }

    /**
     * Access method of the quitButton
     * @return quitButton : the button to quit the game
     */
    public JButton getQuitButton(){
        return this.quitButton;
    }

    /**
     * Access method of the buttonMusic
     * @return buttonMusic : the button to start or stop music
     */
    public JButton getButtonMusic() {
        return this.buttonMusic;
    }
}
