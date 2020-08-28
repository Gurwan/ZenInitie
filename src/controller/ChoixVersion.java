package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class allows to choose between the graphic version and the text version.
 * The class extends JFrame in order to display a JOptionPane
 * @author DELAUNAY Gurwan 1C1
 */
public class ChoixVersion extends JFrame {

    /**
     * The constructeur of ChoixVersion displays a JOptionPane which asks the user which version of the game he wants to launch
     */
    public ChoixVersion(){

        JOptionPane jOptionPane = new JOptionPane();
        String choix[] = {"Version console","Version graphique"};
        int ret = jOptionPane.showOptionDialog(null, "Quelle version du jeu souhaitez-vous lancer ?", "Zen Game",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,choix,choix[0]);

        if(ret!=JOptionPane.CLOSED_OPTION){
            if(ret==0){
                ConsoleVersion consoleVersion = new ConsoleVersion();
            } else {
                GraphicVersion graphicVersion = new GraphicVersion();
            }
        } else {
            System.exit(1);
        }

    }
}
