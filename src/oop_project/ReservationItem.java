package oop_project;

/**
 * Abstract parent class for all reservable items in the hotel reservation system.
 */
public abstract class ReservationItem
        implements Reservable, Comparable<ReservationItem> {

    protected Integer itemId;
    protected String title;
    protected boolean available = true;

    /**
     * Creates an empty reservation item.
     */
    public ReservationItem() {
    }

    /**
     * Creates a reservation item with an ID and title.
     *
     * @param itemId item ID
     * @param title item title
     */
    public ReservationItem(Integer itemId, String title) {
        this.itemId = itemId;
        this.title = title;
        this.available = true;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    /**
     * Reserves the item if it is currently available.
     *
     * @return true if the item is successfully reserved,
     *         false if it is already reserved
     */
    @Override
    public boolean checkIn() {
        if (available) {
            available = false;
            return true;
        }

        return false;
    }

    /**
     * Releases the item if it is currently reserved.
     *
     * @return true if the item is successfully released,
     *         false if it is already available
     */
    @Override
    public boolean checkOut() {
        if (!available) {
            available = true;
            return true;
        }

        return false;
    }

    /**
     * Two reservation items are equal if they have the same ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ReservationItem other = (ReservationItem) obj;

        return itemId != null && itemId.equals(other.itemId);
    }

    /**
     * Compares reservation items by title.
     */
    @Override
    public int compareTo(ReservationItem other) {
        if (other == null || other.title == null) {
            return 1;
        }

        if (this.title == null) {
            return -1;
        }

        return this.title.compareToIgnoreCase(other.title);
    }

    /**
     * Returns the detailed information for the reservation item.
     *
     * @return formatted item details
     */
    public abstract String getDetails();

    @Override
    public String toString() {
        return getDetails();
    }
}
