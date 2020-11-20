import java.util.List;

public class CellDivide extends Cell{

    //INSTANCE VARIABLE
    public int direction;

    //MAGIC NUMBER
    public String CELL_DIVIDE_STRING = "+";
    public int CELL_DIVIDE_BOUND = 3;

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
