package controller;

import view.ZenGameMainView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

/**
 * Game FocusListener
 * This class implements FocusListener and lets you know when the user clicks in a JTextField.
 * This class allows to know if a JTextField remains empty and therefore to fill it automatically so that it does not remain empty.
 * @author DELAUNAY Gurwan 1C1
 */
public class FocusListenerZenGame implements FocusListener {

    private ZenGameMainView zenGameMainView;

    /**
     * Constructor of the FocusListener
     * @param zenGameMainView the view components of the game
     */
    public FocusListenerZenGame(ZenGameMainView zenGameMainView){
        this.zenGameMainView = zenGameMainView;
    }

    /**
     * This method is executed when the user clicks on a JTextField
     * @param focusEvent this is when the user clicks on a JTextField
     */
    @Override
    public void focusGained(FocusEvent focusEvent) {
        if(focusEvent.getSource().equals(this.zenGameMainView.getViewLaunchNewGame().getName1Input())){
            this.zenGameMainView.getViewLaunchNewGame().getName1Input().setText("");
        } else if(focusEvent.getSource().equals(this.zenGameMainView.getViewLaunchNewGame().getName2Input())){
            this.zenGameMainView.getViewLaunchNewGame().getName2Input().setText("");
        } else if(focusEvent.getSource().equals(this.zenGameMainView.getViewSaveGame().getNomSauvegarde())){
            this.zenGameMainView.getViewSaveGame().getNomSauvegarde().setText("");
        }

    }

    /**
     * This method is executed when the user exits on a JTextField
     * @param focusEvent this is when the user exits on a JTextField
     */
    @Override
    public void focusLost(FocusEvent focusEvent) {
        if(focusEvent.getSource().equals(this.zenGameMainView.getViewLaunchNewGame().getName1Input())){
            if(this.zenGameMainView.getViewLaunchNewGame().getName1Input().getText().equals("")) {
                this.zenGameMainView.getViewLaunchNewGame().getName1Input().setText("Liana");
            }
        } else if(focusEvent.getSource().equals(this.zenGameMainView.getViewLaunchNewGame().getName2Input())){
            if(this.zenGameMainView.getViewLaunchNewGame().getName2Input().getText().equals("")) {
                this.zenGameMainView.getViewLaunchNewGame().getName2Input().setText("Casper");
            }
        } else if(focusEvent.getSource().equals(this.zenGameMainView.getViewSaveGame().getNomSauvegarde())){
            if(this.zenGameMainView.getViewSaveGame().getNomSauvegarde().getText().equals("")) {
                Random r = new Random();
                String randomString = "save" + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a');
                this.zenGameMainView.getViewSaveGame().getNomSauvegarde().setText(randomString);
            }
        }

    }
}
