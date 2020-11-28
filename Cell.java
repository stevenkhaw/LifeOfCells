/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the Cell class for PA7. It is an abstract class. It 
 * contains a simple constructor method, copier, getter, and a method to deem
 * this cell as "dead". It's main purpose is to be inherited by other objects. 
 */

import java.util.List;

/**
 * The Cell class is an abstract class that all other objects will extend from.
 * This class is composed of a constructor, copy, get, and a method that will 
 * label this cell as dead. There are 3 instance variables: currRow, currCol, 
 * both for location of the Cell in a 2D array; and mass.
 * implements Comparable<Cell>
 */
public abstract class Cell {

    //INSTANCE VARIABLES
    public int currRow;
    public int currCol;
    public int mass;
    
    /**
     * Construct a Cell object and sets each instance variable to its inputs
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public Cell(int currRow, int currCol, int mass) {
        
        this.currRow = currRow;
        this.currCol = currCol;
        this.mass = mass;

        //Checks validitiy of inputs, sets to 0 if invalid
        if (currRow < 0) {
            this.currRow = 0;
        } 

        if (currCol < 0) {
            this.currCol = 0;
        } 

        if (mass < 0) {
            this.mass = 0;
        } 
    }

    /**
     * Copier method that copies instance variables from one Cell object to 
     * current Cell object
     * 
     * @param otherCell Cell object being copied
     */
    public Cell(Cell otherCell) {

        this.currRow = otherCell.currRow;
        this.currCol = otherCell.currCol;
        this.mass = otherCell.mass;

        //Checks validity of inputs, sets to 0 if invalid 
        if (otherCell.currRow < 0) {
            this.currRow = 0;
        } 

        if (otherCell.currCol < 0) {
            this.currCol = 0;
        } 

        if (otherCell.mass < 0) {
            this.mass = 0;
        } 
    }

    /**
     * Sets instance variables as -1, deems Cell object as dead;
     */
    public void apoptosis() {
        this.currRow = -1;
        this.currCol = -1;
        this.mass = -1;
    }

    /**
     * Returns instance variable currRow
     * 
     * @return int value of currRow instance variable
     */
    public int getCurrRow() {
        return this.currRow;
    }

    /**
     * Returns instance variable currCol
     * 
     * @return int value of currCol instance variable
     */
    public int getCurrCol() {
        return this.currCol;
    }

    /**
     * Returns instance variable mass
     * 
     * @return int value of mass instance value
     */
    public int getMass() {
        return this.mass;
    }

    /**
     * Abstract method that will be defined in other classes that inherit the 
     * Cell object
     * 
     * @param neighbors List of Cell objects being tested
     * @return Dependant on other classes' inheritance
     */
    public abstract boolean checkApoptosis(List<Cell> neighbors);

    /* public int compareTo(Cell otherCell) {
        if (this.mass > otherCell.mass) {
            return 1;
        } else if (this.mass < otherCell.mass) {
            return -1;
        } else {
            return 0;
        }
    }

    public abstract Cell newCellCopy();
} */