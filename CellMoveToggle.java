/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the CellMoveToggle class for PA7 & PA8. It inherits from
 * the Cell class. Its main function is to be a specific cell that has its own
 * string representation and apoptosis methods.
 */

import java.util.List;

/**
 * This class inherits from Cell. This child class has its own string 
 * representation and method for checkApoptosis. There is one instance
 * variable for the boolean state of toggled. There are 4 magic number 
 * constants; two for the string representations of this object and another 
 * two for the bounds being tested. 
 */
public class CellMoveToggle extends CellMoveUp {
    
    //INSTANCE VARIABLE
    public boolean toggled;

    //MAGIC NUMBERS
    private static final String CELL_MOVE_TOGG_STRING_ON = "T";
    private static final String CELL_MOVE_TOGG_STRING_OFF = "t";
    private static final int CELL_MOVE_TOGG_UPPER_BOUND = 5;
    private static final int CELL_MOVE_TOGG_LOWER_BOUND = 2;

    /**
     * Calls parent function to initialzie object
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public CellMoveToggle(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        this.toggled = true;
    }

    /**
     * Calls parent function to initialzie object by copying from another
     * of the same object
     * 
     * @param otherCellMoveToggle CellMoveToggle object being copied
     */
    public CellMoveToggle(CellMoveToggle otherCellMoveToggle) {
        super(otherCellMoveToggle);
        this.toggled = otherCellMoveToggle.toggled;
    }
    
    /**
     * Returns specific string representation for this object
     * 
     * @return String representation for this object
     */
    @Override
    public String toString() {
        if (this.toggled) {
            return CELL_MOVE_TOGG_STRING_ON;
        }

        return CELL_MOVE_TOGG_STRING_OFF;
    }

    /**
     * Returns if cell is considered dead
     * 
     * @return boolean value dependant on objects specific death factors
     */
    @Override
    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() < CELL_MOVE_TOGG_LOWER_BOUND || 
                neighbors.size() > CELL_MOVE_TOGG_UPPER_BOUND) {
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
        return new CellMoveToggle(this);
    }
    
    /**
     * Moves cell dependant on toggled instance variable
     * 
     * @return int array for new location after cell movement
     */
    public int[] getMove() {
        int[] newPosition = new int[2];

        if (this.toggled) {
            newPosition[0] = this.currRow - 1;
            newPosition[1] = this.currCol;
            this.toggled = false;
        } else {
            newPosition[0] = this.currRow;
            newPosition[1] = this.currCol;
            this.toggled = true;
        }

        return newPosition;
    }
}
