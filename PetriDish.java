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
    public List<Divisible> divisibles;

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
    private static final int ROW_COL_BOUND = 3;

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

        //Initializes instance variables
        this.dish = new Cell[VERTICAL_SIZE][HORIZONTAL_SIZE];
        this.movables = new ArrayList<Movable>();
        this.divisibles = new ArrayList<Divisible>();

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

        //Checks for cells with Movable and Divisible interfaces
        for (int x = 0; x < dish.length; x++) {
            for (int y = 0; y < dish[0].length; y++) {
                //Adds Movable cells to instance variable
                if (dish[x][y] instanceof Movable) {
                    movables.add((Movable) dish[x][y]);
                }

                //Adds Divisible cells to instance variable
                if (dish[x][y] instanceof Divisible) {
                    divisibles.add((Divisible) dish[x][y]);
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

        //Check row and col valididity
        if (wrapRow(row) != row || wrapCol(col) != col) {
            return null;
        }

        int rowStart = row - 1;
        int colStart = col - 1;

        for (int i = 0; i < ROW_COL_BOUND; i++) {
            for (int j = 0; j < ROW_COL_BOUND; j++) {

                if (i != 1 && j != 1) {
                    continue;
                }

                if (dish[wrapRow(rowStart + i)][wrapCol(colStart + j)]
                                                                     != null) {
                    neighboringList.add(
                        dish[wrapRow(rowStart + i)][wrapCol(colStart + j)]);
                }
            }
        }

        return neighboringList;
    }

    public void move() {

        //Iterates through every element in dish
        for (int i = 0; i < dish.length; i++) {
            for (int j = 0; j < dish[0].length; j++) {

                //Runs if element has a Movable interface 
                if (dish[i][j] instanceof Movable) {
                    
                    //Gets new location of Movable cell
                    int[] newMoveLoc = ((Movable) dish[i][j]).getMove();

                    //New row and column after locations are wrapped
                    int newMoveRow = wrapRow(newMoveLoc[0]);
                    int newMoveCol = wrapCol(newMoveLoc[1]);

                    //Runs if new location has Movable cell
                    if (dish[newMoveRow][newMoveCol] instanceof Movable) {
                        
                        //Runs if new Movable cell has more mass
                        if (dish[newMoveRow][newMoveCol].mass < 
                                                            dish[i][j].mass) {
                            
                            //Kills lighter Movable cell
                            dish[newMoveRow][newMoveCol].apoptosis();
                            
                            //Replaces dead cell
                            dish[newMoveRow][newMoveCol] = dish[i][j];

                            //Updates the location instance variables 
                            dish[newMoveRow][newMoveCol].currRow = newMoveRow;
                            dish[newMoveRow][newMoveCol].currCol = newMoveCol;

                            //Removes Movable cell from previous location
                            dish[i][j] = null;

                        //Runs if new Movable cell has less mass
                        } else if (dish[newMoveRow][newMoveCol].mass > 
                                                            dish[i][j].mass) {
                            
                            //Kills lighter Movable cell
                            dish[i][j].apoptosis();

                            //Removes dead cell from dish
                            dish[i][j] = null;
                        
                        //Runs if cells are same mass
                        } else {
                            
                            //Kills both cells
                            dish[i][j].apoptosis();
                            dish[newMoveRow][newMoveCol].apoptosis();

                            //Removes dead cells from dish
                            dish[i][j] = null;
                            dish[newMoveRow][newMoveCol] = null;
                        }

                    //Runs if new location is empty
                    } else if (dish[newMoveRow][newMoveCol] == null) {

                        //Replaces null with Movable cell
                        dish[newMoveRow][newMoveCol] = dish[i][j];

                        //Updates the location instance variables 
                        dish[newMoveRow][newMoveCol].currRow = newMoveRow;
                        dish[newMoveRow][newMoveCol].currCol = newMoveCol;

                        //Removes Movable cell from previous location
                        dish[i][j] = null;

                    //Runs if new location has non-Movable cell
                    } else {

                        //Kills non-Movable cell 
                        dish[newMoveRow][newMoveCol].apoptosis();

                        //Replaces non-Movable cell with Movable cell
                        dish[newMoveRow][newMoveCol] = dish[i][j];

                        //Updates the location instance variables 
                        dish[newMoveRow][newMoveCol].currRow = newMoveRow;
                        dish[newMoveRow][newMoveCol].currCol = newMoveCol;

                        //Removes cell's previous location
                        dish[i][j] = null;
                    }
                }
            }
        }
    }

    public void divide() {

        //Iterates through every element in dish
        for (int i = 0; i < dish.length; i++) {
            for (int j = 0; j < dish[0].length; j++) {

                //Runs if element has a Divisible interface 
                if (dish[i][j] instanceof Divisible) {

                    //Gets new location of Divisible cell
                    int[] newDivLoc = ((Divisible) dish[i][j]).getDivision();

                    //New row and column after locations are wrapped
                    int newDivRow = wrapRow(newDivLoc[0]);
                    int newDivCol = wrapCol(newDivLoc[1]);

                    //Runs if new location is empty
                    if (dish[newDivRow][newDivCol] == null) {

                        //Divides new cell copy onto new location
                        dish[newDivRow][newDivCol] = 
                                    new CellDivide((CellDivide) dish[i][j]);

                        //Updates the location instance variables
                        dish[newDivRow][newDivCol].currRow = newDivRow;
                        dish[newDivRow][newDivCol].currCol = newDivCol;

                    //Runs if new location has Divisible cell
                    } else if (dish[newDivRow][newDivCol] 
                                                        instanceof Divisible){
                        
                        //Runs if new Divisible cell has greater mass
                        if (dish[newDivRow][newDivCol].mass < dish[i][j].mass){
                            
                            //Kills lighter Divisible cell
                            dish[newDivRow][newDivCol].apoptosis();

                            //Replaces dead cell with new cell divided copy
                            dish[newDivRow][newDivCol] = 
                                    new CellDivide((CellDivide) dish[i][j]);

                            //Updates the location instance variables
                            dish[newDivRow][newDivCol].currRow = newDivRow;
                            dish[newDivRow][newDivCol].currCol = newDivCol;
                    
                        //Runs if Divisible cells have same mass
                        } else if (dish[newDivRow][newDivCol].mass == 
                                                            dish[i][j].mass){
                            
                            //All cells in that location die
                            dish[newDivRow][newDivCol].apoptosis();

                            //Empties cell
                            dish[newDivRow][newDivCol] = null;
                        }
                    }
                }
            }
        }
    }

    public void update() {

        //Iterates through whole dish
        for (int i = 0; i < dish.length; i++) {
            for (int j = 0; j < dish[0].length; j++) {

                //Runs if dish element is not null/empty
                if (dish[i][j] != null) {

                    //Runs if dish is deemed dead dependant on neighbors
                    if (dish[i][j].checkApoptosis(getNeighborsOf(i,j))) {

                        //Deems cell as dead
                        dish[i][j].apoptosis();

                        //Empties cell location
                        dish[i][j] = null;
                    }
                
                //Runs if dish element is null/empty
                } else {
                    List<Cell> currNeighbors = getNeighborsOf(i,j);

                    if (currNeighbors.size() == 2 || 
                                                currNeighbors.size() == 3) {
                        
                        //Creates new cell copy of the first neighbor
                        dish[i][j] = currNeighbors.get(0).newCellCopy();

                        //Updates the location instance variables
                        dish[i][j].currRow = i;
                        dish[i][j].currCol = j;
                    }
                }

            }
        }
    }

    public void iterate() {
        move();
        divide();
        update();
    }

    /* public void simulate() {

    } */

    /**
     * Helper method that is used to wrap a row index. If no wrapping is 
     * needed, the number will not change. CAN ONLY BE USED WITH THE DISH 
     * INSTANCE VARIABLE.
     * 
     * @param row
     * @return int value of new wrapped/unwrapped row 
     */
    private int wrapRow(int row) {
        int newRow = row;
        
        if (row < 0) {
            newRow = dish.length + row;
        } else if (row > dish.length - 1) {
            newRow = row - dish.length;
        }

        return newRow;
    }

    /**
     * Helper method that is used to wrap a column index. If no wrapping is 
     * needed, the number will not change. CAN ONLY BE USED WITH THE DISH 
     * INSTANCE VARIABLE.
     * 
     * @param col
     * @return int value of new wrapped/unwrapped column 
     */
    private int wrapCol(int col) {
        int newCol = col;

        if (col < 0) {
            newCol = dish[0].length + col;
        } else if (col > dish[0].length - 1) {
            newCol = col - dish[0].length;
        }
        
        return newCol;
    }

    /* public static void main(String[] args) {
        String[][] petri = new String[][]{ 
            {"null", "null", "null", "null", "null"},
            {"null", "CellStationary 2", "CellDivide 5", "CellStationary 11", "null"}, 
            {"null", "CellMoveDiagonal 4", "CellMoveToggle 3", "CellMoveToggle 10", "CellStationary 4"}, 
            {"null", "null", "CellDivide 2", "CellMoveUp", "null"}
        };

        PetriDish yes = new PetriDish(petri);
        System.out.println(yes.toString());
    } */
} 
