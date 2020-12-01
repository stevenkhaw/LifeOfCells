/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the PetriDish class for PA7. Its main function is to 
 * populate an instance variable full of objects that are determined by the 
 * given input. There is one instance variable for the 2D array of Cell objects
 * being populated. There are 11 magic number constants; 8 of which are 
 * Strings being tested for, 2 of which are used when printing out the 2D array
 * and another for the bounds being tested.
 */

import java.util.List;
import java.util.ArrayList;

/**
 * This class's main purpose is to populate a 2D array of objects as well as 
 * be able to print this array out. There is one instance variable for the 
 * 2D array of Cells.
 */
public class PetriDish {
    
    //INSTANCE VARIABLE
    public Cell[][] dish;
    public List<Movable> movables;
    public List<Divisible>divisbles;

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
    private static final int BOARD_LENGTH_CHECK = 2;

    /**
     * Populates instance variable dependant on input of 2D array of Strings.
     * Contains two constants for the input board's row and column count.
     * 
     * @param board String 2D array being tested
     */
    public PetriDish(String[][] board) {

        //CONSTANTS
        final int VERTICAL_SIZE = board.length;
        final int HORIZONTAL_SIZE = board[0].length;


        this.dish = new Cell[VERTICAL_SIZE][HORIZONTAL_SIZE];

        //Iterate through 2D array input
        for (int i = 0; i < VERTICAL_SIZE; i++) {
            for (int j = 0; j < HORIZONTAL_SIZE; j++) {
                
                //Split each element in board into two Strings
                String[] boardContent = board[i][j].split(EMPTY_STRING);
                String boardCell = boardContent[0]; 
                int boardMass = -1;

                if (boardContent.length == BOARD_LENGTH_CHECK) {
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
    
    /**
     * Prints out string representation of each object from the instance 
     * variable
     * 
     * @return String output of class's instance variable's string 
     */
    public String toString() {
        
        //--START-- Creation of HORIZONTAL_BARS magic number
        StringBuilder hb = new StringBuilder();

        for (int x = 0; x < dish[0].length; x++) {
            hb.append("--");
        }

        hb.append("-\n");
        //--END-- Creation of HORIZONTAL_BARS magic number

        //MAGIC NUMBER  
        final String HORIZONTAL_BARS = hb.toString();

        //Creation of StringBuilder used to append to
        StringBuilder sb = new StringBuilder();
        sb.append(HORIZONTAL_BARS);

        //Iterates through and appends every value followed by a vertical bar
        for (int i = 0; i < dish.length; i++) {
            sb.append(VERTICAL_BAR);

            for (int j = 0; j < dish[0].length; j++) {
                sb.append(dish[i][j] == null ? EMPTY_STRING : 
                        dish[i][j].toString());
                sb.append(VERTICAL_BAR);
            }

            sb.append(NEW_LINE);
            sb.append(HORIZONTAL_BARS);
        }

        return sb.toString();
    }

    public List<Cell> getNeighborsOf(int row, int col) {
        List<Cell> neighboringList = new ArrayList<Cell>();

        int rowStart = row - 1;
        int colStart = col - 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (dish[rowStart + i][colStart + j] == null) {
                    return null;
                }
                if (i != 1 & j != 1) {
                    neighboringList.add(dish[rowStart + i][colStart + j]);
                }
            }
        }

        return neighboringList;
    }
    public void move() {
        
    }

    public void divide() {

    }

    public void update() {

    }

    public void iterate() {

    }

    public void simulate() {

    }
}
