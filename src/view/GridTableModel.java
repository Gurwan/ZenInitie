package view;

import model.Pawn;
import model.Square;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Method for creating the board for the graphic version
 * @author DELAUNAY Gurwan 1C1
 */
public class GridTableModel extends AbstractTableModel {

    private int noOfRows;
    private int noOfCols;
    private Square[][] plateau;
    private Pawn zen;
    private ArrayList<Pawn> pawnWhite;
    private ArrayList<Pawn> pawnBlack;
    private static final String PATH = "/res/";
    private String jauneChinois = "yellchine.png";
    private String jauneChinoisRed = "yellredchine.png";
    private String jauneChinoisWhite = "yellwhchine.png";
    private String jauneChinoisBlack = "yellblchine.png";
    private String marronChinois = "brchine.png";
    private String marronChinoisRed = "brredchine.png";
    private String marronChinoisWhite = "brwhchine.png";
    private String marronChinoisBlack = "brblchine.png";
    private String jauneGeometric = "yellgeo.png";
    private String jauneGeometricRed = "yellredgeo.png";
    private String jauneGeometricWhite = "yellwhgeo.png";
    private String jauneGeometricBlack = "yellblgeo.png";
    private String marronGeometric = "brgeo.png";
    private String marronGeometricRed = "brredgeo.png";
    private String marronGeometricWhite = "brwhgeo.png";
    private String marronGeometricBlack = "brblgeo.png";
    private String jaune = "yellow.png";
    private String jauneRed = "yellred.png";
    private String jauneBlanc = "yellwhite.png";
    private String jauneNoir = "yellblack.png";
    private String marron = "brown.png";
    private String marronRed = "brownred.png";
    private String marronBlanc = "brownwh.png";
    private String marronNoir = "brownblack.png";


    /**
     * The constructor of the GridTableModel
     * @param plateau the board game
     * @param zen the zen pawn
     * @param pawnWhite the list of white pawns
     * @param pawnBlack the list of black pawns
     */
    public GridTableModel(Square[][] plateau, Pawn zen, ArrayList<Pawn> pawnWhite, ArrayList<Pawn> pawnBlack){
        this.plateau = plateau;
        this.noOfRows = this.plateau.length;
        this.noOfCols = this.plateau[0].length;
        this.zen = zen;
        this.pawnWhite = pawnWhite;
        this.pawnBlack = pawnBlack;
    }

    /**
     * Method that returns the number of rows of the table
     * @return noOfRows : the number of rows of the table
     */
    @Override
    public int getRowCount() {
        return this.noOfRows;
    }

    /**
     * Method that returns the number of columns of the table
     * @return noOfCols : the number of columns of the table
     */
    @Override
    public int getColumnCount() {
        return this.noOfCols;
    }

    /**
     * Method for defining an image for each square
     * @param r the row of the square
     * @param c the column of the square
     * @return result : the image of the square
     */
    @Override
    public Object getValueAt(int r, int c) {
        Object result = new Object();
        result = null;
        String pawnColor = null;
        Square square = plateau[r][c];
        Color brown = new Color(102, 51, 0);
        Color yellow = new Color(255, 204, 0);

        if(this.zen != null) {
            if (this.zen.sameCoord(r, c)) {
                pawnColor = "red";
            }
        }

        for(Pawn pb : this.pawnWhite){
            if(pb.sameCoord(r,c)){
                pawnColor = "blanc";
            }
        }

        for(Pawn pn : this.pawnBlack){
            if(pn.sameCoord(r,c)){
                pawnColor = "noir";
            }
        }

        if (!(square.isOccupy()) && square.getColor().getRGB() == yellow.getRGB() && square.getSign() == null) {
            result = new ImageIcon(getClass().getResource(PATH + jaune));
        } else if (!(square.isOccupy()) && square.getColor().getRGB() == brown.getRGB() && square.getSign() == null) {
            result = new ImageIcon(getClass().getResource(PATH + marron));
        } else if(square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign() == null && Objects.equals(pawnColor, "red")) {
            result = new ImageIcon(getClass().getResource(PATH + jauneRed));
        } else if(square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign() == null && Objects.equals(pawnColor, "red")) {
            result = new ImageIcon(getClass().getResource(PATH + marronRed));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign() == null && Objects.equals(pawnColor, "blanc")){
            result = new ImageIcon(getClass().getResource(PATH + jauneBlanc));
         } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign() == null && Objects.equals(pawnColor, "noir")){
            result = new ImageIcon(getClass().getResource(PATH + jauneNoir));
        } else if (square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign() == null && Objects.equals(pawnColor, "blanc")){
            result = new ImageIcon(getClass().getResource(PATH + marronBlanc));
        } else if (square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign() == null && Objects.equals(pawnColor, "noir")) {
            result = new ImageIcon(getClass().getResource(PATH + marronNoir));
        } else if(square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("chinese") && Objects.equals(pawnColor, "red")){
            result = new ImageIcon(getClass().getResource(PATH + marronChinoisRed));
        } else if (square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("geometric") && Objects.equals(pawnColor, "red")){
            result = new ImageIcon(getClass().getResource(PATH + marronGeometricRed));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("chinese") && Objects.equals(pawnColor, "red")){
            result = new ImageIcon(getClass().getResource(PATH + jauneChinoisRed));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("geometric") && Objects.equals(pawnColor, "red")){
            result = new ImageIcon(getClass().getResource(PATH + jauneGeometricRed));
        } else if (!(square.isOccupy()) && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("chinese")){
            result = new ImageIcon(getClass().getResource(PATH + marronChinois));
        } else if (!(square.isOccupy()) && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("geometric")) {
            result = new ImageIcon(getClass().getResource(PATH + marronGeometric));
        } else if (!(square.isOccupy()) && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("chinese")){
            result = new ImageIcon(getClass().getResource(PATH + jauneChinois));
        } else if (!(square.isOccupy()) && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("geometric")){
            result = new ImageIcon(getClass().getResource(PATH + jauneGeometric));
        } else if(square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("chinese") && Objects.equals(pawnColor, "blanc")){
            result = new ImageIcon(getClass().getResource(PATH + marronChinoisWhite));
        } else if (square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("geometric") && Objects.equals(pawnColor, "blanc")){
            result = new ImageIcon(getClass().getResource(PATH + marronGeometricWhite));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("chinese") && Objects.equals(pawnColor, "blanc")){
            result = new ImageIcon(getClass().getResource(PATH + jauneChinoisWhite));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("geometric") && Objects.equals(pawnColor, "blanc")) {
            result = new ImageIcon(getClass().getResource(PATH + jauneGeometricWhite));
        } else if(square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("chinese") && Objects.equals(pawnColor, "noir")){
            result = new ImageIcon(getClass().getResource(PATH + marronChinoisBlack));
        } else if (square.isOccupy() && square.getColor().getRGB() == brown.getRGB() && square.getSign().equals("geometric") && Objects.equals(pawnColor, "noir")){
            result = new ImageIcon(getClass().getResource(PATH + marronGeometricBlack));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("chinese") && Objects.equals(pawnColor, "noir")){
            result = new ImageIcon(getClass().getResource(PATH + jauneChinoisBlack));
        } else if (square.isOccupy() && square.getColor().getRGB() == yellow.getRGB() && square.getSign().equals("geometric") && Objects.equals(pawnColor, "noir")) {
            result = new ImageIcon(getClass().getResource(PATH + jauneGeometricBlack));
        }

        return result;
    }

    /**
     * Method returning the value of the column
     * @param c the column
     * @return the value of the column
     */
    public String getColumnName(int c){
        return String.valueOf(c);
    }

    /**
     * Method returning the class of the column
     * @param c the column
     * @return the class of the column
     */
    public Class getColumnClass(int c){
        return this.getValueAt(0,c).getClass();
    }
}
