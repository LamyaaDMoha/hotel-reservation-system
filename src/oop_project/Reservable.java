package oop_project;

/**
 * Defines the basic reservation behavior for reservable items.
 */
public interface Reservable {

    /**
     * Attempts to check in and reserve the item.
     *
     * @return true if the item is successfully reserved,
     *         false if it is already reserved
     */
    boolean checkIn();

    /**
     * Attempts to check out and release the item.
     *
     * @return true if the item is successfully released,
     *         false if it is already available
     */
    boolean checkOut();
}

