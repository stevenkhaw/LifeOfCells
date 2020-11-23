/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

import java.util.List;

public class CellDivide extends Cell{

    //INSTANCE VARIABLE
    public int direction;

    //MAGIC NUMBER
    private static String CELL_DIVIDE_STRING = "+";
    private static int CELL_DIVIDE_BOUND = 3;

    public CellDivide(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        this.direction = 1;
    }

    public CellDivide(CellDivide otherCellDivide) {
        super(otherCellDivide);
    }
    public String toString() {
        return CELL_DIVIDE_STRING;
    }

    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() == CELL_DIVIDE_BOUND) {
            return true;
        }

        return false;
    }
}
