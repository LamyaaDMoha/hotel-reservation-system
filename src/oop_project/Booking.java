package oop_project;

/**
 * Represents a booking in the hotel reservation system.
 * A booking connects a customer with a reserved room
 * and stores the booking dates and current status.
 */
public class Booking {

    private Customer customer;
    private ReservationItem item;
    private String bookingDate;
    private String checkInDate;
    private String status;

    /**
     * Creates an empty booking.
     */
    public Booking() {
    }

    /**
     * Creates a booking with customer, room, dates, and status.
     *
     * @param customer customer who made the booking
     * @param item reserved room
     * @param bookingDate date the booking was created
     * @param checkInDate customer check-in date
     * @param status current booking status
     */
    public Booking(Customer customer, ReservationItem item,
                   String bookingDate, String checkInDate, String status) {
        this.customer = customer;
        this.item = item;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ReservationItem getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the booking information in a readable format.
     *
     * @return formatted booking details
     */
    @Override
    public String toString() {
        String result = "";

        result += "------------ [BOOKING] ------------\n";
        result += "Customer: " + customer.getName() + "\n";
        result += "Item Title: " + item.getTitle() + "\n";
        result += "Booking Date: " + bookingDate + "\n";
        result += "Check-in Date: " + checkInDate + "\n";
        result += "Status: " + status + "\n";
        result += "------------------------------------";

        return result;
    }
}