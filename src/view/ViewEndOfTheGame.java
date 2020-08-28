package view;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The end game view
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewEndOfTheGame {
    private Container container;
    private JTextArea endText;
    private JButton returnMainMenu;

    /**
     * The constructor of the ViewEndOfTheGame
     * @param s the result of the game
     */
    public ViewEndOfTheGame(String s){
        this.container = new Container();
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.container.setLayout(gridBagLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.returnMainMenu = new JButton(new ImageIcon(getClass().getResource("/res/retourneraumenu.png")));
        this.returnMainMenu.setContentAreaFilled(false);
        this.returnMainMenu.setBorderPainted(false);

        this.endText = new JTextArea(s);
        this.endText.setBackground(new Color(230,44,45));
        this.endText.setForeground(Color.WHITE);
        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 40f);
            this.endText.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 100;
        this.container.add(endText,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.container.add(returnMainMenu,gridBagConstraints);

    }

    /**
     * Access method of the container of the ViewEndOfTheGame
     * @return container : the container of the ViewEndOfTheGame
     */
    public Container getContain(){
        return this.container;
    }

    /**
     * Access method of the returnMainMenu button
     * @return returnMainMenu : the button to return to the menu
     */
    public JButton getReturnMainMenu(){
        return this.returnMainMenu;
    }
}
