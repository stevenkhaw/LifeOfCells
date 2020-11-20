
import java.util.List;

public class CellStationary extends Cell {
    
    //MAGIC NUMBER
    private String CELL_STATIONARY_STRING = ".";

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
        if (neighbors.size() >= 3 && neighbors.size() <= 7) {
            return true;
        }

        return false;
    }
}
