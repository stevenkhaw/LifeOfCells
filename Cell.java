/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

import java.util.List;

public abstract class Cell {

    //INSTANCE VARIABLES
    public int currRow;
    public int currCol;
    public int mass;
    
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

    public void apoptosis() {
        this.currRow = -1;
        this.currCol = -1;
        this.mass = -1;
    }

    public int getCurrRow() {
        return this.currRow;
    }

    public int getCurrCol() {
        return this.currCol;
    }

    public int getMass() {
        return this.mass;
    }

    public abstract boolean checkApoptosis(List<Cell> neighbors);
}