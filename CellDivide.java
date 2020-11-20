import java.util.List;

public class CellDivide extends Cell{

    //INSTANCE VARIABLE
    public int direction;

    //MAGIC NUMBER
    private String CELL_DIVIDE_STRING = "+";

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
        if (neighbors.size() == 3) {
            return true;
        }

        return false;
    }
}
