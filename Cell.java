/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

import java.util.List;

/**
 * 
 */
public abstract class Cell {

    //INSTANCE VARIABLES
    public int currRow;
    public int currCol;
    public int mass;
    
    /**
     * 
     * @param currRow
     * @param currCol
     * @param mass
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
     * 
     * @param otherCell
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
     * 
     */
    public void apoptosis() {
        this.currRow = -1;
        this.currCol = -1;
        this.mass = -1;
    }

    /**
     * 
     * @return
     */
    public int getCurrRow() {
        return this.currRow;
    }

    /**
     * 
     * @return
     */
    public int getCurrCol() {
        return this.currCol;
    }

    /**
     * 
     * @return
     */
    public int getMass() {
        return this.mass;
    }

    /**
     * 
     * @param neighbors
     * @return
     */
    public abstract boolean checkApoptosis(List<Cell> neighbors);
}