package oop_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Main logic class for the hotel reservation system.
 * Manages rooms, customers, bookings, searching, sorting,
 * check-in, and check-out operations.
 */
public class ReservationSystem {

    private String systemName;
    private ArrayList<ReservationItem> catalog;
    private TreeSet<Customer> customers;

    /**
     * Creates an empty reservation system.
     *
     * @param systemName name of the system
     */
    public ReservationSystem(String systemName) {
        this.systemName = systemName;
        this.catalog = new ArrayList<>();
        this.customers = new TreeSet<>();
    }

    /**
     * Adds a room to the catalog.
     *
     * @param item room to add
     */
    public void addItem(ReservationItem item) {
        if (item != null) {
            catalog.add(item);
        }
    }

    /**
     * Registers a customer in the system.
     *
     * @param customer customer to register
     */
    public void registerCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

    /**
     * Searches for a reservation item by ID.
     *
     * @param itemId room ID
     * @return matching room
     * @throws ReservationNotFoundException if the room does not exist
     */
    public ReservationItem findItemById(Integer itemId)
            throws ReservationNotFoundException {

        for (ReservationItem item : catalog) {
            if (item.getItemId() != null
                    && item.getItemId().equals(itemId)) {
                return item;
            }
        }

        throw new ReservationNotFoundException(
                "Room with ID " + itemId + " was not found."
        );
    }

    /**
     * Searches for a customer by ID.
     *
     * @param customerId customer ID
     * @return matching customer
     * @throws ReservationNotFoundException if the customer does not exist
     */
    public Customer findCustomerById(Integer customerId)
            throws ReservationNotFoundException {

        for (Customer customer : customers) {
            if (customer.getId() != null
                    && customer.getId().equals(customerId)) {
                return customer;
            }
        }

        throw new ReservationNotFoundException(
                "Customer with ID " + customerId + " was not found."
        );
    }

    /**
     * Checks a customer into a room.
     *
     * @param customer customer making the reservation
     * @param itemId room ID
     * @param bookingDate date the reservation was created
     * @param checkInDate customer check-in date
     * @return created booking, or null if room is already occupied
     * @throws ReservationNotFoundException if room does not exist
     */
    public Booking checkInToRoom(
            Customer customer,
            Integer itemId,
            String bookingDate,
            String checkInDate)
            throws ReservationNotFoundException {

        ReservationItem item = findItemById(itemId);

        boolean success = item.checkIn();

        if (!success) {
            System.out.println(
                    "[ERROR] Room " + itemId + " is already occupied."
            );
            return null;
        }

        Booking booking = new Booking(
                customer,
                item,
                bookingDate,
                checkInDate,
                "Active"
        );

        customer.addBooking(booking);

        System.out.println(
                "[OK] Reservation completed successfully."
        );

        System.out.println(
                "[OK] " + customer.getName()
                        + " checked in to room "
                        + itemId + "."
        );

        return booking;
    }

    /**
     * Checks a customer out of a room.
     *
     * @param customer customer leaving the room
     * @param itemId room ID
     * @throws ReservationNotFoundException if room does not exist
     */
    public void checkOutFromRoom(
            Customer customer,
            Integer itemId)
            throws ReservationNotFoundException {

        ReservationItem item = findItemById(itemId);

        boolean success = item.checkOut();

        if (success) {

            for (Booking booking : customer.getBookings()) {

                if (booking.getItem().equals(item)
                        && booking.getStatus().equalsIgnoreCase("Active")) {

                    booking.setStatus("Cancelled");
                }
            }

            System.out.println(
                    "[OK] Check-out completed successfully."
            );

            System.out.println(
                    "[OK] Room " + itemId + " is now available."
            );

        } else {

            System.out.println(
                    "[INFO] Room " + itemId + " is already available."
            );
        }
    }

    /**
     * Prints all bookings for a customer.
     *
     * @param customer customer whose bookings will be displayed
     */
    public void printBookingsForCustomer(Customer customer) {

        System.out.println(
                "\n>>> Bookings for " + customer.getName()
        );

        System.out.println("----------------------------------------");

        if (customer.getBookings().isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        int index = 1;

        for (Booking booking : customer.getBookings()) {

            System.out.println("\nBooking #" + index);
            System.out.println(booking);

            index++;
        }
    }

    /**
     * Sorts rooms alphabetically by title.
     */
    public void sortCatalogByTitle() {
        Collections.sort(catalog);
    }

    /**
     * Prints all rooms in the catalog.
     */
    public void printCatalog() {

        System.out.println(
                "\n>>> " + systemName + " - Room Catalog"
        );

        System.out.println("----------------------------------------");

        if (catalog.isEmpty()) {
            System.out.println("No rooms have been added yet.");
            return;
        }

        sortCatalogByTitle();

        int index = 1;

        for (ReservationItem item : catalog) {

            System.out.println("\nRoom #" + index);
            System.out.println(item);

            index++;
        }
    }
}