/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 */

import java.util.List;

public class CellMoveToggle extends CellMoveUp {
    
    //INSTANCE VARIABLE
    public boolean toggled;

    //MAGIC NUMBER
    private static String CELL_MOVE_TOGG_STRING_ON = "T";
    private static String CELL_MOVE_TOGG_STRING_OFF = "t";
    private static int CELL_MOVE_TOGG_UPPER_BOUND = 5;
    private static int CELL_MOVE_TOGG_LOWER_BOUND = 2;

    public CellMoveToggle(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        this.toggled = true;
    }

    public CellMoveToggle(CellMoveToggle otherCellMoveToggle) {
        super(otherCellMoveToggle);
    }
    
    public String toString() {
        if (this.toggled) {
            return CELL_MOVE_TOGG_STRING_ON;
        }

        return CELL_MOVE_TOGG_STRING_OFF;
    }

    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() < CELL_MOVE_TOGG_LOWER_BOUND || 
                neighbors.size() > CELL_MOVE_TOGG_UPPER_BOUND) {
            return true;
        }

        return false;
    }
}
