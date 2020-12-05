/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the CellMoveDiagonal class for PA7 & PA8. It inherits
 * from the Cell class. Its main function is to be a specific cell that has
 * its own string representation and apoptosis methods.
 */

import java.util.List;

/**
 * This class inherits from Cell. This child class has its own string 
 * representation and method for checkApoptosis. There are two instance 
 * variables; one for the boolean representation if this object is oriented 
 * right and other for how many diagonal moves this object should make. There 
 * are also 3 magic number constants; two for the string representations of 
 * this object and another for the bounds being tested
 */
public class CellMoveDiagonal extends CellMoveUp {
    
    //INSTANCE VARIABLES
    public boolean orientedRight;
    public int diagonalMoves;

    //MAGIC NUMBERS
    private static final String CELL_MOVE_DIAG_STRING_ON = "/";
    private static final String CELL_MOVE_DIAG_STRING_OFF = "\\";
    private static final int CELL_MOVE_DIAG_BOUND = 3;

    /**
     * Calls parent function to initialzie object
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public CellMoveDiagonal(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        this.orientedRight = true;
        this.diagonalMoves = 0;
    }

    /**
     * Calls parent function to initialzie object by copying from another
     * of the same object
     * 
     * @param otherCellMoveDiagonal otherCellMoveDiagonal object being copied
     */
    public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal) {
        super(otherCellMoveDiagonal);
        this.orientedRight = otherCellMoveDiagonal.orientedRight;
        this.diagonalMoves = otherCellMoveDiagonal.diagonalMoves;
    }

    /**
     * Returns specific string representation for this object
     * 
     * @return String representation for this object
     */
    @Override
    public String toString() {
        if (this.orientedRight) {
            return CELL_MOVE_DIAG_STRING_ON;
        }

        return CELL_MOVE_DIAG_STRING_OFF;
    }

    /**
     * Returns if cell is considered dead
     * 
     * @return boolean value dependant on objects specific death factors
     */
    @Override
    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() > CELL_MOVE_DIAG_BOUND) {
            return true;
        }

        return false;
    }

    /**
     * Creates a deep copy of this object
     * 
     * @return Deep copy of this object
     */
    public Cell newCellCopy() {
        return new CellMoveDiagonal(this);
    }

    /**
     * Moves cell dependant on orientedRight and diagonalMoves instance
     * variables
     * 
     * @return int array for new location after cell movement
     */
    public int[] getMove() {
        int[] newPosition = new int[2];

        if (this.orientedRight) {
            newPosition[0] = this.currRow - 1;
            newPosition[1] = this.currCol + 1;
        } else {
            newPosition[0] = this.currRow + 1;
            newPosition[1] = this.currCol - 1;
        }

        this.diagonalMoves++;

        if (this.diagonalMoves % 4 == 0) {
            if (this.orientedRight) {
                this.orientedRight = false;
            } else {
                this.orientedRight = true;
            }
        }

        return newPosition;
    }
}
