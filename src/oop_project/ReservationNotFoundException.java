package oop_project;

/**
 * Custom exception thrown when a reservation item
 * cannot be found in the catalog.
 */
public class ReservationNotFoundException extends Exception {

    /**
     * Creates the exception with a custom message.
     *
     * @param message error message
     */
    public ReservationNotFoundException(String message) {
        super(message);
    }
}