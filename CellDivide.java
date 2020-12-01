/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the CellDivide class for PA7. It inherits from the 
 * Cell class. Its main function is to be a specific cell that has its own 
 * string representation and apoptosis methods.
 */

import java.util.List;

/**
 * This class inherits from Cell. This child class has its own string 
 * representation and method for checkApoptosis. There is one instance variable
 * for the direction of the object. There are 2 magic number constants; one 
 * for the string representation of this object and another for the bounds 
 * being tested.
 */
public class CellDivide extends Cell{

    //INSTANCE VARIABLE
    public int direction;

    //MAGIC NUMBERS
    private static final String CELL_DIVIDE_STRING = "+";
    private static final int CELL_DIVIDE_BOUND = 3;

    /**
     * Calls parent function to initialzie object
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public CellDivide(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        this.direction = 1;
    }

    /**
     * Calls parent function to initialzie object by copying from another
     * of the same object
     * 
     * @param otherCellDivide CellDivide object being copied
     */
    public CellDivide(CellDivide otherCellDivide) {
        super(otherCellDivide);
        this.direction = otherCellDivide.direction;
    }

    /**
     * Returns specific string representation for this object
     * 
     * @return String representation for this object
     */
    @Override
    public String toString() {
        return CELL_DIVIDE_STRING;
    }

    /**
     * Returns if cell is considered dead
     * 
     * @return boolean value dependant on objects specific death factors
     */
    @Override
    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() == CELL_DIVIDE_BOUND) {
            return true;
        }

        return false;
    }
}
