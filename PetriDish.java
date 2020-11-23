public class PetriDish {
    
    //INSTANCE VARIABLE
    public Cell[][] dish;

    //MAGIC NUMBERS
    private static final String EMPTY_STRING = " ";
    private static final String NULL_STRING = "null";
    private static final String STATIONARY_STRING = "CellStationary";
    private static final String MOVE_UP_STRING = "CellMoveUp";
    private static final String DIVIDE_STRING = "CellDivide";
    private static final String MOVE_TOGGLE_STRING = "CellMoveToggle";
    private static final String MOVE_DIAGONAL_STRING = "CellMoveDiagonal";
    private static final String MOVE_CHILD_STRING = "CellMoveToggleChild";
    private static final String VERTICAL_BAR = "|";
    private static final String NEW_LINE = "\n";

    public PetriDish(String[][] board) {

        //CONSTANTS
        final int VERTICAL_SIZE = board.length;
        final int HORIZONTAL_SIZE = board[0].length;


        this.dish = new Cell[VERTICAL_SIZE][HORIZONTAL_SIZE];

        for (int i = 0; i < VERTICAL_SIZE; i++) {
            for (int j = 0; j < HORIZONTAL_SIZE; j++) {
                
                //Split each element in board into two Strings
                String[] boardContent = board[i][j].split(EMPTY_STRING); 
                String boardCell = boardContent[0];
                int boardMass = -1;

                if (boardContent.length == 2) {
                    boardMass = Integer.parseInt(boardContent[1]);
                }                

                switch (boardCell) {
                    case (NULL_STRING):
                        this.dish[i][j] = null;
                        break;
                    case (STATIONARY_STRING):
                        this.dish[i][j] = new CellStationary(i,j,boardMass);
                        break;
                    case (MOVE_UP_STRING):
                        this.dish[i][j] = new CellMoveUp(i,j,boardMass);
                        break;
                    case (DIVIDE_STRING):
                        this.dish[i][j] = new CellDivide(i,j,boardMass);
                        break;
                    case (MOVE_TOGGLE_STRING):
                        this.dish[i][j] = new CellMoveToggle(i,j,boardMass);
                        break;
                    case (MOVE_DIAGONAL_STRING):
                        this.dish[i][j] = new CellMoveDiagonal(i,j,boardMass);
                        break;
                    case (MOVE_CHILD_STRING):
                        this.dish[i][j] = new 
                                CellMoveToggleChild(i,j,boardMass);
                        break;
                }
            }
        }
    }
    
    
    public String toString() {
        
        //Creation of HORIZONTAL_BARS magic number, dependant on dish[0].length
        StringBuilder hb = new StringBuilder();

        for (int x = 0; x < dish[0].length; x++) {
            hb.append("--");
        }

        hb.append("-\n");

        //MAGIC NUMBER  
        final String HORIZONTAL_BARS = hb.toString();

        StringBuilder sb = new StringBuilder();
        sb.append(HORIZONTAL_BARS);

        for(int i = 0; i < dish.length; i++) {
            sb.append(VERTICAL_BAR);

            for(int j = 0; j < dish[0].length; j++) {
                sb.append(dish[i][j] == null ? EMPTY_STRING : 
                        dish[i][j].toString());
                sb.append(VERTICAL_BAR);
            }

            sb.append(NEW_LINE);
            sb.append(HORIZONTAL_BARS);
        }

        return sb.toString();
    }
}
