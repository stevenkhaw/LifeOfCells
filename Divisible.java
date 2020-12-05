/**
 * NAME: Steven Khaw
 * ID: A16669117
 * EMAIL: skhaw@ucsd.edu
 * 
 * This file contains the Divisible interface for PA7 & PA8. Its main purpose
 * is to be an interface for Divisible type cells.
 */

/**
 * This class is an interface that identifies cells as a Divisible cell and 
 * contains an abstract method that Divisible cells will override.
 */
public interface Divisible {

    /**
     * Abstract method that will be defined in other classes that implements 
     * the Movable interface.
     * 
     * @return Dependant on other classes' implementation
     */
    public abstract int[] getDivision();
}
