package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * the view between two turns
 */
public class ViewNextTurn {
    private Container container;
    private JButton nextTurnButton;
    private JTextArea result;
    private JTextArea score;
    private JTextArea pointJoueur1;
    private JTextArea pointJoueur2;
    private JTextArea numeroNextTour;
    private JPanel text;

    /**
     * Constructor of ViewNextTurn
     * @param indication the list of indication to show between two turns
     */
    public ViewNextTurn(ArrayList<String> indication){
        this.container = new Container();
        GridLayout gridLayout = new GridLayout(2,1);
        this.container.setLayout(gridLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        this.nextTurnButton = new JButton(new ImageIcon(getClass().getResource("/res/prochaintour.png")));
        this.nextTurnButton.setContentAreaFilled(false);
        this.nextTurnButton.setBorderPainted(false);

        this.text = new JPanel(new GridBagLayout());
        this.text.setBackground(new Color(230,44,45));

        this.result = new JTextArea(indication.get(0));
        this.result.setBackground(new Color(230, 44, 45));
        this.result.setForeground(Color.WHITE);
        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 30f);
            this.result.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.score = new JTextArea(indication.get(1));
        this.score.setBackground(new Color(230, 44, 45));
        this.score.setForeground(Color.WHITE);
        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 30f);
            this.score.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.pointJoueur1 = new JTextArea(indication.get(2));
        this.pointJoueur1.setBackground(new Color(230, 44, 45));
        this.pointJoueur1.setForeground(Color.WHITE);
        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 30f);
            this.pointJoueur1.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.pointJoueur2 = new JTextArea(indication.get(3));
        this.pointJoueur2.setBackground(new Color(230, 44, 45));
        this.pointJoueur2.setForeground(Color.WHITE);
        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 30f);
            this.pointJoueur2.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.numeroNextTour = new JTextArea(indication.get(4));
        this.numeroNextTour.setBackground(new Color(230, 44, 45));
        this.numeroNextTour.setForeground(Color.WHITE);
        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 30f);
            this.numeroNextTour.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 30;
        this.text.add(result,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.text.add(score,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.text.add(pointJoueur1,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.text.add(pointJoueur2,gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.text.add(numeroNextTour,gridBagConstraints);

        this.container.add(text);
        this.container.add(nextTurnButton);

    }

    /**
     * Access method of the container of the ViewNextTurn
     * @return container : the container of the ViewNextTurn
     */
    public Container getContain(){
        return this.container;
    }

    /**
     * Access method of the button of the nextTurnButton
     * @return nextTurnButton : the button to go to the next turn
     */
    public JButton getNextTurnButton(){
        return this.nextTurnButton;
    }
}
