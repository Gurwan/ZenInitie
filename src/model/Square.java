package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Square class that makes up the game board
 * @author DELAUNAY Gurwan 1C1
 **/
public class Square implements Serializable {

	private int x;
	private int y;
	private boolean occupy;
	private Color color;
	private String sign;

	/**
	 * The constructor for square
	 * @param x the x-coordinate of the square
	 * @param y the y-coordinate of the square
	 * @param color the color of the square (brown or yellow for the graphic implementation)
	 */
	public Square(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.occupy = false;
		this.sign = null;
	}

	/**
	 * x access method
	 * @return x : the coordinate x of the square
	 **/
	public int getX() {
		return this.x;
	}

	/**
	 * Method of modification of the x-coordinate
	 * @param x a new x-coordinate for the square
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * y access method
	 * @return y : the coordinate y of the square
	 **/
	public int getY() {
		return this.y;
	}

	/**
	 * Method of modification of the y-coordinate
	 * @param y a new y-coordinate for the square
	 */
	public void setY(int y) {
		this.y = y;
	}

	/** Method used to know if a square is occupy or not
	 * @return true if the square is occupied else false
	 * */
	public boolean isOccupy() {
		return this.occupy;
	}

	/**
	 * Method changing the occupation on the square
	 * @param b a new boolean for the occupation on the square
	 */
	public void setOccupy(boolean b) {
		this.occupy = b;
	}

	/**
	 * color access method
	 * @return color : the color of the square
	 **/
	public Color getColor() {
		return this.color;
	}

	/**
	 * Method of modificatin of the color
	 * @param color a new color for the square
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * sign access method
	 * @return sign : the sign of the square
	 **/
	public String getSign() {
		return this.sign;
	}

	/**
	 * Method of modification of the sign
	 * @param s a new sign for the square
	 */
	public void setSign(String s) {
		if(s.equals("chinese") || s.equals("geometric")) {
			this.sign = s;
		} else {
			System.err.println("Wrong sign");
			// remplacer par Exception
		}
	}

}