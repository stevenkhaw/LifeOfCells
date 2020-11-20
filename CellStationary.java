
import java.util.List;

public class CellStationary extends Cell {
    
    //MAGIC NUMBER
    public String CELL_STATIONARY_STRING = ".";
    public int CELL_STATIONARY_UPPER_BOUND = 7;
    public int CELL_STATIONARY_LOWER_BOUND = 3;

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
