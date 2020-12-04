/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the CellMoveToggleChild class for PA7. It inherits from 
 * the Cell class. Its main function is to be a specific cell that has its own 
 * string representation and apoptosis methods.
 */

import java.util.List;

/**
 * This class inherits from Cell. This child class has its own string 
 * representation and method for checkApoptosis. There is one instance variable
 * that keeps track of the number of cells alive. There is one magic number
 * constant that is the bounds being tested for this object.
 */
public class CellMoveToggleChild extends CellMoveToggle {

    //INSTANCE VARIABLE
    public static int numAlive = 0;

    //MAGIC NUMBER
    private static final int NUM_ALIVE_BOUND = 10;

    /**
     * Calls parent function to initialzie object
     * 
     * @param currRow int value for current row location 
     * @param currCol int value for current column location
     * @param mass int value for mass 
     */
    public CellMoveToggleChild(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        numAlive++;
    }

    /**
     * Calls parent function to initialzie object by copying from another
     * of the same object
     * 
     * @param otherCellMoveToggleChild CellMoveToggleChild object being copied
     */
    public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild) {
        super(otherCellMoveToggleChild);
        numAlive++;
    }

    /**
     * Calls parent class's apoptosis as well as decrements numAlive instance
     * variable
     * 
     * @return String representation for this object
     */
    @Override
    public void apoptosis() {
        super.apoptosis();
        numAlive--;
    }

    /**
     * Returns if cell is considered dead
     * 
     * @return boolean value dependant on objects specific death factors
     */
    @Override
    public boolean checkApoptosis(List<Cell> neighbors) {
        if (super.checkApoptosis(neighbors) && numAlive < NUM_ALIVE_BOUND) {
            return true;
        }

        return false;
    }

    public Cell newCellCopy() {
        return new CellMoveToggleChild(this);
    }
}
