import java.util.List;

public class CellMoveDiagonal extends CellMoveUp {
    
    //INSTANCE VARIABLE
    public boolean orientedRight;
    public int diagonalMoves;

    //MAGIC NUMBERS
    public String CELL_MOVE_DIAG_STRING_ON = "/";
    public String CELL_MOVE_DIAG_STRING_OFF = "\\";
    public int CELL_MOVE_DIAG_BOUND = 3;

    public CellMoveDiagonal(int currRow, int currCol, int mass) {
        super(currRow, currCol, mass);
        this.orientedRight = true;
        this.diagonalMoves = 0;
    }

    public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal) {
        super(otherCellMoveDiagonal);
    }

    public String toString() {
        if (this.orientedRight) {
            return CELL_MOVE_DIAG_STRING_ON;
        }

        return CELL_MOVE_DIAG_STRING_OFF;
    }

    public boolean checkApoptosis(List<Cell> neighbors) {
        if (neighbors.size() > CELL_MOVE_DIAG_BOUND) {
            return true;
        }

        return false;
    }
}
