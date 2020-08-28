package controller;

import view.ZenGameMainView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Game ItemListener
 * This class implements ItemListener and lets you know which item is selected
 * @author DELAUNAY Gurwan 1C1
 */
public class ItemListenerZenGame implements ItemListener {

    private ZenGameMainView zenGameMainView;

    /**
     * Constructor of the ItemListenerZenGame
     * @param zenGameMainView the view components of the game
     */
    public ItemListenerZenGame(ZenGameMainView zenGameMainView){
        this.zenGameMainView = zenGameMainView;
    }

    /**
     * This method lets you know which item is selected and do the right things
     * @param itemEvent action after click on an item
     */
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        JRadioButton jRadioButton = (JRadioButton) itemEvent.getItem();
        String s = jRadioButton.getText();

        if(s.equals("1")){
            zenGameMainView.setDifficultyIA(1);
        } else if(s.equals("2")){
            zenGameMainView.setDifficultyIA(2);
        } else if(s.equals("3")){
            zenGameMainView.setDifficultyIA(3);
        }
    }
}
