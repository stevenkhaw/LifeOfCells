/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the CellMoveUp class for PA7 & PA8. It inherits from the
 * Cell class. Its main function is to be a specific cell that has its own 
 * string representation and apoptosis methods.
 */

import java.util.List;
 
/**
 * This class inherits from Cell. This child class has its own string 
 * representation and method for checkApoptosis. There are no instance 
 * variables but 2 magic number constants; one for the string representation of
 * this object and another for the bound being tested.
 */
public class CellMoveUp extends Cell implements Movable {
    
    //MAGIC NUMBERS
    private static final String CELL_MOVEUP_STRING = "^";
    private static final int CELL_MOVEUP_BOUND = 4;

    /**
     * Calls parent function to initialzie object
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public CellMoveUp(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
    }

    /**
     * Calls parent function to initialzie object by copying from another
     * of the same object
     * 
     * @param otherCellMoveUp CellMoveUp object being copied
     */
    public CellMoveUp(CellMoveUp otherCellMoveUp) {
        super(otherCellMoveUp);
    }

    /**
     * Returns specific string representation for this object
     * 
     * @return String representation for this object
     */
    @Override
    public String toString() {
        return CELL_MOVEUP_STRING;
    }

    /**
     * Returns if cell is considered dead
     * 
     * @return boolean value dependant on objects specific death factors
     */
    @Override
    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() != CELL_MOVEUP_BOUND) {
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
        return new CellMoveUp(this);
    }

    /**
     * Moves cell up one
     * 
     * @return int array for new location after cell movement
     */
    public int[] getMove() {
        int[] newPosition = new int[2];

        newPosition[0] = this.currRow -1;
        newPosition[1] = this.currCol;

        return newPosition;
    }
}
 