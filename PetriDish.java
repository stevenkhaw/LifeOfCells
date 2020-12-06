/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the PetriDish class for PA7 & PA8. Its main function is
 * to populate an instance variable full of objects that are determined by the
 * given input as well as move, divide, and update them. There is also a 
 * simulate method that lets the user interface the game and call certain 
 * methods to maniupate the game board/dish. There is one instance 
 * variable for the 2D array of Cell objects being populated and another 2 
 * for the list of Moveable and Divisible interface cells. There are 17 
 * magic number constants. 
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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
    private static final String EXIT_KEY = "exit";
    private static final String MOVE_KEY = "move";
    private static final String DIVIDE_KEY = "divide";
    private static final String UPDATE_KEY = "update";
    private static final String ITERATE_KEY = "iterate";
    private static final String INVALID_MESSAGE = "invalid";

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

                //If split array contains 2 elements as expected
                if (boardContent.length == BOARD_LENGTH_CHECK) {
                    boardMass = Integer.parseInt(boardContent[1]); 
                }                

                //Adds new cell object onto disk element dependant on string
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

    /**
     * Gets the cells neighboring the input row, col. Returns list of cells
     * surrounding tested cell in order of NW, N, NE, W, E, SW, S, SE.
     * 
     * @param row int value representing row location being tested
     * @param col int value representing column location being tested
     * @return ArrayList of Cell objects surrouding tested location
     */
    public List<Cell> getNeighborsOf(int row, int col) {

        //Initializes return variable
        List<Cell> neighboringList = new ArrayList<Cell>();

        //Check row and col valididity
        if (wrapRow(row) != row || wrapCol(col) != col) {
            return null;
        }

        //Sets start values for the for-loop below
        int rowStart = row - 1;
        int colStart = col - 1;

        //Iterates a 3x3 area with the input row and col element in the middle
        for (int i = 0; i < ROW_COL_BOUND; i++) {
            for (int j = 0; j < ROW_COL_BOUND; j++) {

                //Skips checking the middle element
                if (i == 1 && j == 1) {
                    continue;
                }

                //Runs if wrapped element is not null
                if (dish[wrapRow(rowStart + i)][wrapCol(colStart + j)]
                                                                     != null) {
                    neighboringList.add(
                        dish[wrapRow(rowStart + i)][wrapCol(colStart + j)]);
                }
            }
        }

        return neighboringList;
    }

    /**
     * Moves all cell objects that have the Movable interface. Cell moves
     * dependant on mass, and status of element the new cell is moving to. 
     */
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

        //Update movables list instance variable 
        movables.clear();

        //Iterate through dish and add any cells that are movable to list
        for (int u = 0; u < dish.length; u++) {
            for (int v = 0; v < dish[0].length; v++) {
                if (dish[u][v] instanceof Movable) {
                    movables.add((Movable) dish[u][v]);
                }
            }
        }
    }

    /**
     * Divides all cells that have a Divisible interface. Divides cells 
     * dependant on factors like mass and status of the location where 
     * the new cell is moving to.
     */
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

        //Update divisibles list instance variable 
        divisibles.clear();

        //Iterate through dish and add any cells that are divisible to list
        for (int u = 0; u < dish.length; u++) {
            for (int v = 0; v < dish[0].length; v++) {
                if (dish[u][v] instanceof Divisible) {
                    divisibles.add((Divisible) dish[u][v]);
                }
            }
        }
    }

    /**
     * Calls checkApoptosis on every cell simultaneously as well as adds a new
     * cell depending on its neighbors.
     */
    public void update() {

        //Initializing deep copy variable
        Cell[][] copyDish = new Cell[dish.length][dish[0].length];

        //Creates deep copy of original
        for (int s = 0; s < dish.length; s++) {
            for (int t = 0; t < dish[0].length; t++) {
                copyDish[s][t] = dish[s][t];
            }
        }

        //Iterates through whole initial dish 
        for (int i = 0; i < dish.length; i++) {
            for (int j = 0; j < dish[0].length; j++) {

                //Runs if initial dish element is not null/empty
                if (dish[i][j] != null) {

                    //Runs if initial dish is dead from neighbors
                    if (dish[i][j].checkApoptosis(getNeighborsOf(i,j))) {

                        //Deems cell as dead for return
                        copyDish[i][j].apoptosis();

                        //Empties cell location for return
                        copyDish[i][j] = null;
                    }
                }
            }
        } 

        //Iterates through whole initial dish 
        for (int x = 0; x < dish.length; x++) {
            for (int y = 0; y < dish[0].length; y++) {

                //Runs if initial dish element is null/empty
                if (dish[x][y] == null) {

                    //Variable used to check amount of neighbors of element
                    List<Cell> currNeighbors = getNeighborsOf(x,y);

                    if (currNeighbors.size() == 2 || 
                                                currNeighbors.size() == 3) {
                        
                        //Creates new copy of the first neighbor for return
                        copyDish[x][y] = currNeighbors.get(0).newCellCopy();

                        //Updates the location instance variables for return
                        copyDish[x][y].currRow = x;
                        copyDish[x][y].currCol = y;
                    }
                }
            }
        }

        //Iterates through and updates the dish 
        for (int a = 0; a < dish.length; a++) {
            for (int b = 0; b < dish[0].length; b++) {
                dish[a][b] = copyDish[a][b];
            }
        }

        //Update movables and divisibles list instance variable 
        movables.clear();
        divisibles.clear();

        /**
         * Iterate through dish and add any cells that are movable/divisible
         * to their respective instance variable list
         */
        for (int u = 0; u < dish.length; u++) {
            for (int v = 0; v < dish[0].length; v++) {
                if (dish[u][v] instanceof Divisible) {
                    divisibles.add((Divisible) dish[u][v]);
                } else if (dish[u][v] instanceof Movable) {
                    movables.add((Movable) dish[u][v]);
                }
            }
        }
    }

    /**
     * Runs move(), divide(), and update(), in that order. 
     */
    public void iterate() {
        move();
        divide();
        update();
    }
    
    /**
     * Simulates game environment. Prints out board as well as applies any 
     * move, divide, update, or iterate method called on the board.
     */
    public void simulate() {
        Scanner sc = new Scanner(System.in);
        System.out.println(this);

        while(sc.hasNextLine()){
            String line = sc.nextLine();

            if(line.equals(EXIT_KEY)){
                break;
            }

            //Checks user input and calls respective methods
            switch(line){
                case MOVE_KEY:
                    move();
                    break;
                case DIVIDE_KEY:
                    divide();
                    break;
                case UPDATE_KEY:
                    update();
                    break;
                case ITERATE_KEY:
                    iterate();
                    break;
                default:
                    System.out.println(INVALID_MESSAGE);
                    break;
            }

            //Prints board/dish
            System.out.println(this);
        }

        sc.close();
    }

    /**
     * Helper method that is used to wrap a row index. If no wrapping is 
     * needed, the number will not change. CAN ONLY BE USED WITH THE DISH 
     * INSTANCE VARIABLE.
     * 
     * @param row int value of row index
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
     * @param col int value of column index
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

    public static void main(String[] args) {
        String[][] petri = new String[][]{ 
            {"null", "null", "null", "null", "null"},
            {"null", "CellStationary 2", "CellDivide 5", "CellStationary 11", "null"}, 
            {"null", "CellMoveDiagonal 4", "CellMoveToggle 3", "CellMoveToggle 10", "CellStationary 3"},
            {"null", "null", "CellDivide 2", "CellMoveUp 4", "null"}
        };
        PetriDish yes = new PetriDish(petri);
        yes.simulate();
    }
} 
