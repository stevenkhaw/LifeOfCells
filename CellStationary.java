/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

import java.util.List;

public class CellStationary extends Cell {
    
    //MAGIC NUMBER
    private static final String CELL_STATIONARY_STRING = ".";
    private static final int CELL_STATIONARY_UPPER_BOUND = 7;
    private static final int CELL_STATIONARY_LOWER_BOUND = 3;

    public CellStationary(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
    }

    public CellStationary(CellStationary otherCellStationary) {
        super(otherCellStationary);
    }
    public String toString() {
        return CELL_STATIONARY_STRING;
    }

    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() >= CELL_STATIONARY_LOWER_BOUND && 
                neighbors.size() <= CELL_STATIONARY_UPPER_BOUND) {
            return true;
        }

        return false;
    }
}
