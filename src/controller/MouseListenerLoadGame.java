package controller;

import model.Configuration;
import view.ZenGameMainView;

import javax.swing.JList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Load a save MouseListener
 * This class implements MouseListener and allows to load the desired save
 * @author DELAUNAY Gurwan 1C1
 */
public class MouseListenerLoadGame implements MouseListener {

    private ZenGameMainView zenGameMainView;
    private MouseListenerZenGame mouseListenerZenGame;

    /**
     * First constructor of MouseListenerLoadGame
     * @param zenGameMainView the view components of the game
     * @param mouseListenerZenGame the main MouseListener of the game
     */
    public MouseListenerLoadGame(ZenGameMainView zenGameMainView,MouseListenerZenGame mouseListenerZenGame){
        this.zenGameMainView = zenGameMainView;
        this.mouseListenerZenGame = mouseListenerZenGame;
    }

    /**
     * Second constructor of MouseListenerLoadGame
     * @param zenGameMainView the view components of the game
     */
    public MouseListenerLoadGame(ZenGameMainView zenGameMainView){
        this.zenGameMainView = zenGameMainView;
    }

    /**
     * This method is executed when the user clicks on a component
     * @param mouseEvent click action on a component
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JList list = (JList) mouseEvent.getSource();

        boolean isLoad = false;

        if (mouseEvent.getClickCount() == 2) {
            // si double clique sur une sauvegarde alors lance la sauvegarde
            if(list.equals(this.zenGameMainView.getViewLoadGame().getjList())){
                Configuration configuration = new Configuration();
                isLoad = configuration.loadGame(this.zenGameMainView.getViewLoadGame().getjList().getSelectedValue());
                if(isLoad){
                    zenGameMainView.launchViewInGame(configuration);
                }
                this.mouseListenerZenGame.setConfiguration(configuration);
            } else if(list.equals(this.zenGameMainView.getViewLoadGameInGame().getjList())){
                isLoad = this.mouseListenerZenGame.getConfiguration().loadGame(this.zenGameMainView.getViewLoadGameInGame().getjList().getSelectedValue());
                if(isLoad){
                    zenGameMainView.launchViewInGame(this.mouseListenerZenGame.getConfiguration());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
