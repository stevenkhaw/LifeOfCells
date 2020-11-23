/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

import java.util.List;

public class CellMoveToggleChild extends CellMoveToggle {

    //INSTANCE VARIABLE
    public static int numAlive = 0;

    //MAGIC NUMBERS
    private static int NUM_ALIVE_BOUND = 10;

    public CellMoveToggleChild(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        numAlive++;
        
    }

    public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild) {
        super(otherCellMoveToggleChild);
        numAlive++;
    }

    public void apoptosis() {
        super.apoptosis();
        numAlive--;
    }

    public boolean checkApoptosis(List<Cell> neighbors) {
        if (super.checkApoptosis(neighbors) && numAlive < NUM_ALIVE_BOUND) {
            return true;
        }

        return false;
    }
}
