import java.util.List;

public class CellMoveUp extends Cell {
    
    //MAGIC NUMBER
    private String CELL_MOVEUP_STRING = "^";

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
        if (neighbors.size() != 4) {
            return true;
        }

        return false;
    }
}
