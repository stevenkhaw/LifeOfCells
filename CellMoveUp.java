/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

 import java.util.List;

public class CellMoveUp extends Cell {
    
    //MAGIC NUMBER
    private static final String CELL_MOVEUP_STRING = "^";
    private static final int CELL_MOVEUP_BOUND = 4;

    public CellMoveUp(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
    }

    public CellMoveUp(CellMoveUp otherCellMoveUp) {
        super(otherCellMoveUp);
    }
    public String toString() {
        return CELL_MOVEUP_STRING;
    }

    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() != CELL_MOVEUP_BOUND) {
            return true;
        }

        return false;
    }
}
