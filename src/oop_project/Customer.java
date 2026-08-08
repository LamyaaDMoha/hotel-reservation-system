package oop_project;

import java.util.ArrayList;

/**
 * Represents a customer in the hotel reservation system.
 * A customer can have multiple bookings.
 */
public class Customer implements Comparable<Customer> {

    private Integer id;
    private String name;
    private String email;
    private ArrayList<Booking> bookings;

    /**
     * Creates an empty customer with an empty bookings list.
     */
    public Customer() {
        bookings = new ArrayList<>();
    }

    /**
     * Creates a customer with basic information.
     *
     * @param id customer ID
     * @param name customer name
     * @param email customer email
     */
    public Customer(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bookings = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Adds a booking to this customer.
     *
     * @param booking booking to add
     */
    public void addBooking(Booking booking) {
        if (booking != null) {
            bookings.add(booking);
        }
    }

    /**
     * Checks whether the customer has at least one active booking.
     *
     * @return true if an active booking exists
     */
    public boolean hasActiveBookings() {
        for (Booking b : bookings) {
            if (b.getStatus().equalsIgnoreCase("Active")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates a copy of this customer.
     *
     * @return copied customer
     */
    public Customer clone() {
        Customer copy = new Customer(this.id, this.name, this.email);
        copy.bookings = new ArrayList<>(this.bookings);
        return copy;
    }

    /**
     * Returns the customer information in a readable format.
     *
     * @return formatted customer details
     */
    @Override
    public String toString() {
        String result = "";

        result += "------------ [CUSTOMER] ------------\n";
        result += "ID: " + id + "\n";
        result += "Name: " + name + "\n";
        result += "Email: " + email + "\n";
        result += "------------------------------------";

        return result;
    }

    /**
     * Compares customers by name for sorting.
     */
    @Override
    public int compareTo(Customer other) {
        if (other == null || other.name == null) {
            return 1;
        }

        if (this.name == null) {
            return -1;
        }

        return this.name.compareToIgnoreCase(other.name);
    }

    /**
     * Two customers are considered equal if they have the same ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;

        return id != null && id.equals(other.id);
    }
}