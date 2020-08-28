package view;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * The view which allows you to choose the game options before launching the game
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewRules {
    private Container container;
    private ImageIcon rules;
    private JLabel rulesLabel;
    private JButton retButton;

    /**
     * Constructor of the ViewRules
     */
    public ViewRules(){
        this.container = new Container();
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.container.setLayout(gridBagLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.rules = new ImageIcon(getClass().getResource("/res/regleJeu.png"));
        this.rulesLabel = new JLabel(rules);

        this.retButton = new JButton(new ImageIcon(getClass().getResource("/res/retour.png")));
        this.retButton.setContentAreaFilled(false);
        this.retButton.setBorderPainted(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.container.add(rulesLabel,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.container.add(this.retButton,gridBagConstraints);
    }

    /**
     * Access method of the container of the ViewRules
     * @return container : the container of the ViewRules
     */
    public Container getContain(){
        return this.container;
    }

    /**
     * Access method of the retButton
     * @return retButton : the button to return to the settings
     */
    public JButton getRetButton() {
        return this.retButton;
    }
}
