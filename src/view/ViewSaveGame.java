package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
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
 * The view which allows you to save a game
 * @author DELAUNAY Gurwan 1C1
 */
public class ViewSaveGame {

    private Container container;
    private JTextField nomSauvegarde;
    private JButton sauvegarderButton;
    private JButton returnButton;
    private ImageIcon nameSaveFile;
    private JLabel nameSaveFileLabel;
    private JPanel sauvegarder;
    private JPanel retour;

    /**
     * Constructor of ViewSaveGame
     */
    public ViewSaveGame(){
        this.container = new Container();
        BorderLayout borderLayout = new BorderLayout();
        this.container.setLayout(borderLayout);
        this.container.setBackground(new Color(230,44,45));
        this.container.setPreferredSize(new Dimension(1920,1080));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.sauvegarder = new JPanel(new GridLayout(3,1));
        this.sauvegarder.setBackground(new Color(230,44,45));
        this.retour = new JPanel(new BorderLayout());
        this.retour.setBackground(new Color(230,44,45));

        this.nameSaveFile = new ImageIcon(getClass().getResource("/res/entreunnompourlapartie.png"));
        this.nameSaveFileLabel = new JLabel(nameSaveFile);

        this.nomSauvegarde = new JTextField("sauvegarder1538");
        this.nomSauvegarde.setColumns(10);

        try {
            InputStream is = getClass().getResourceAsStream("/res/dosis.ttf");
            Font dosis = Font.createFont(Font.TRUETYPE_FONT, is);
            dosis = dosis.deriveFont(Font.BOLD, 40f);
            this.nomSauvegarde.setFont(dosis);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }

        this.sauvegarderButton = new JButton(new ImageIcon(getClass().getResource("/res/sauvegarder.png")));
        this.sauvegarderButton.setContentAreaFilled(false);
        this.sauvegarderButton.setBorderPainted(false);

        this.returnButton = new JButton(new ImageIcon(getClass().getResource("/res/retour.png")));
        this.returnButton.setContentAreaFilled(false);
        this.returnButton.setBorderPainted(false);

        this.sauvegarder.add(nameSaveFileLabel);
        this.sauvegarder.add(nomSauvegarde);
        this.sauvegarder.add(sauvegarderButton);

        this.retour.add(returnButton,BorderLayout.WEST);

        this.container.add(sauvegarder,BorderLayout.CENTER);
        this.container.add(retour,BorderLayout.NORTH);

    }

    /**
     * Access method of the container of the ViewSaveGame
     * @return container : the container of the ViewSaveGame
     */
    public Container getContainer() {
        return this.container;
    }

    /**
     * Access method of the returnButton
     * @return returnButton : the button to return to the game
     */
    public JButton getReturnButton() {
        return this.returnButton;
    }

    /**
     * Access method of the sauvegarderButton
     * @return sauvegarderButton : the button to save the game
     */
    public JButton getSauvegarderButton(){
        return this.sauvegarderButton;
    }

    /**
     * Access method of the nomSauvegarde
     * @return nomSauvegarde : the jtextfield where the name of the game is saved
     */
    public JTextField getNomSauvegarde() {
        return this.nomSauvegarde;
    }
}
