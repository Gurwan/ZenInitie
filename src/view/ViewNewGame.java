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
 * The view to choose the gamemode
 */
public class ViewNewGame {
    private Container container;
    private ImageIcon logo;
    private JLabel logoLabel;
    private JPanel panelButton;
    private JPanel panelMusique;
    private JPanel panelLogo;
    private JPanel panelRight;
    private JButton unVsUnButton;
    private JButton iAButton;
    private JButton returnButton;
    private JButton buttonMusic;

    /**
     * Constructor of ViewNewGame
     * @param enLecture true if the music is playing else false
     */
    public ViewNewGame(boolean enLecture){
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

        this.unVsUnButton = new JButton(new ImageIcon(getClass().getResource("/res/unVsun.png")));
        this.unVsUnButton.setContentAreaFilled(false);
        this.unVsUnButton.setBorderPainted(false);

        this.iAButton = new JButton(new ImageIcon(getClass().getResource("/res/solocontreIA.png")));
        this.iAButton.setContentAreaFilled(false);
        this.iAButton.setBorderPainted(false);

        this.returnButton = new JButton(new ImageIcon(getClass().getResource("/res/retour.png")));
        this.returnButton.setContentAreaFilled(false);
        this.returnButton.setBorderPainted(false);

        this.panelButton = new JPanel(new GridBagLayout());

        this.panelMusique = new JPanel(new BorderLayout());
        this.panelLogo = new JPanel(new BorderLayout());
        this.panelRight = new JPanel(new BorderLayout());

        this.panelLogo.setBackground(new Color(230,44,45));
        this.panelRight.setBackground(new Color(230,44,45));
        this.panelButton.setBackground(new Color(230,44,45));
        this.panelMusique.setBackground(new Color(230,44,45));

        this.panelMusique.add(buttonMusic, BorderLayout.EAST);

        this.panelLogo.add(logoLabel,BorderLayout.EAST);

        this.panelRight.add(panelLogo,BorderLayout.NORTH);
        this.panelRight.add(panelMusique,BorderLayout.SOUTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.panelButton.add(unVsUnButton,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.panelButton.add(iAButton,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.panelButton.add(returnButton,gridBagConstraints);
        gridBagConstraints.gridy = 4;

        this.container.add(panelButton);
        this.container.add(panelRight);

    }

    /**
     * Access method of the container of the ViewNewGame
     * @return container : the container of the ViewNewGame
     */
    public Container getContain(){
        return this.container;
    }

    /**
     * Access method of the unVsUnButton button
     * @return unVsUnButton : the button to launch the game with the HH game mode
     */
    public JButton getUnVsUnButton(){
        return this.unVsUnButton;
    }

    /**
     * Access method of the iAButton button
     * @return iAButton : the button to launch the game with the HA game mode
     */
    public JButton getIAButton(){
        return this.iAButton;
    }

    /**
     * Access method of the return button
     * @return returnButton : the button to return to the main menu
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
