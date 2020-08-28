package controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class lets you start music
 * @author DELAUNAY Gurwan 1C1
 */
public class MusicPlayer {
    private String linkMusic;
    private Clip clip;
    private AudioInputStream audioInputStream;
    private boolean estEnLecture;
    public MusicPlayer(String s){
        this.linkMusic = s;
    }

    /**
     * This method allows to play the music
     */
    public void playMusic(){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(linkMusic));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            estEnLecture = true;
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method allows to stop the music
     */
    public void stopMusic(){
        if(clip != null) {
            clip.stop();
            estEnLecture = false;
        }
    }

    /**
     * This method allows to restart the music after the music stop
     */
    public void relaunchMusic(){
        if(clip != null) {
            clip.start();
            estEnLecture = true;
        }
    }

    /**
     * Lets you know if the music is on or not
     * @return estEnLecture : true if the music is on else false if the music is off
     */
    public boolean isEstEnLecture() {
        return this.estEnLecture;
    }

    /**
     * Method of modification of the estEnLecture attribute
     * @param b the new boolean estEnLecture
     */
    public void setEnLecture(boolean b){
        this.estEnLecture = b;
    }

}
