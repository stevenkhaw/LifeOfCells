/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the CellStationary class for PA7 & PA8. It inherits from
 * the Cell class. Its main function is to be a specific cell that has its own
 * string representation and apoptosis methods.
 */

import java.util.List;

/**
 * This class inherits from Cell. This child class has its own string 
 * representation and method for checkApoptosis. There are no instance 
 * variables but 3 constant magic numbers; one for the string representation
 * of this object and another for the upper and lower bounds its checking.
 */
public class CellStationary extends Cell {
    
    //MAGIC NUMBERS
    private static final String CELL_STATIONARY_STRING = ".";
    private static final int CELL_STATIONARY_UPPER_BOUND = 7;
    private static final int CELL_STATIONARY_LOWER_BOUND = 3;

    /**
     * Calls parent function to initialzie object
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public CellStationary(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
    }
 
    /**
     * Calls parent function to initialzie object by copying from another
     * of the same object
     * 
     * @param otherCellStationary CellStationary object being copied
     */
    public CellStationary(CellStationary otherCellStationary) {
        super(otherCellStationary);
    }

    /**
     * Returns specific string representation for this object
     * 
     * @return String representation for this object
     */
    @Override
    public String toString() {
        return CELL_STATIONARY_STRING;
    }

    /**
     * Returns if cell is considered dead 
     * 
     * @return boolean value dependant on objects specific death factors
     */
    @Override
    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() >= CELL_STATIONARY_LOWER_BOUND && 
                neighbors.size() <= CELL_STATIONARY_UPPER_BOUND) {
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
        return new CellStationary(this);
    }
}
