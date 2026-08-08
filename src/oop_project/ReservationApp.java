package oop_project;

import java.util.Scanner;

/**
 * Runs the interactive Hotel Reservation System.
 */
public class ReservationApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ReservationSystem system =
                new ReservationSystem("Hotel Reservation System");

        System.out.println("========================================");
        System.out.println("        HOTEL RESERVATION SYSTEM        ");
        System.out.println("========================================");

        boolean running = true;

        while (running) {

            showMenu();

            int choice = readInteger(
                    "Enter your choice (0-6): "
            );

            System.out.println();

            switch (choice) {

                case 1:
                    addRoom(system);
                    break;

                case 2:
                    registerCustomer(system);
                    break;

                case 3:
                    makeReservation(system);
                    break;

                case 4:
                    checkOut(system);
                    break;

                case 5:
                    system.printCatalog();
                    break;

                case 6:
                    showCustomerBookings(system);
                    break;

                case 0:
                    running = false;
                    System.out.println(
                            "Thank you for using the Hotel Reservation System."
                    );
                    break;

                default:
                    System.out.println(
                            "[ERROR] Please enter a number from 0 to 6."
                    );
            }

            System.out.println();
        }

        scanner.close();
    }

    /**
     * Displays the main menu.
     */
    private static void showMenu() {

        System.out.println("\n============== MAIN MENU ==============");
        System.out.println("1. Add Hotel Room");
        System.out.println("2. Register Customer");
        System.out.println("3. Make Reservation / Check-In");
        System.out.println("4. Check-Out");
        System.out.println("5. View All Rooms");
        System.out.println("6. View Customer Bookings");
        System.out.println("0. Exit");
        System.out.println("=======================================");
    }

    /**
     * Adds a new hotel room.
     */
    private static void addRoom(ReservationSystem system) {

        System.out.println("---------- ADD HOTEL ROOM ----------");

        int roomId = readInteger(
                "Enter room ID (example: 101): "
        );

        System.out.print(
                "Enter room title (example: Sea View Room): "
        );
        String title = scanner.nextLine();

        System.out.print(
                "Enter hotel name (example: Jeddah Sea Hotel): "
        );
        String hotelName = scanner.nextLine();

        System.out.print(
                "Enter room type (Single / Double / Suite): "
        );
        String roomType = scanner.nextLine();

        int nights = readInteger(
                "Enter number of nights (example: 3): "
        );

        HotelReservation room =
                new HotelReservation(
                        roomId,
                        title,
                        hotelName,
                        roomType,
                        nights
                );

        system.addItem(room);

        System.out.println(
                "[OK] Room added successfully."
        );
    }

    /**
     * Registers a new customer.
     */
    private static void registerCustomer(
            ReservationSystem system) {

        System.out.println("---------- REGISTER CUSTOMER ----------");

        int customerId = readInteger(
                "Enter customer ID (example: 1001): "
        );

        System.out.print(
                "Enter customer's full name: "
        );
        String name = scanner.nextLine();

        System.out.print(
                "Enter customer's email: "
        );
        String email = scanner.nextLine();

        Customer customer =
                new Customer(
                        customerId,
                        name,
                        email
                );

        system.registerCustomer(customer);

        System.out.println(
                "[OK] Customer registered successfully."
        );
    }

    /**
     * Creates a reservation and checks the customer into a room.
     */
    private static void makeReservation(
            ReservationSystem system) {

        System.out.println("---------- MAKE RESERVATION ----------");

        int customerId = readInteger(
                "Enter customer ID: "
        );

        int roomId = readInteger(
                "Enter room ID: "
        );

        System.out.print(
                "Enter booking date (YYYY-MM-DD, example: 2026-08-08): "
        );
        String bookingDate = scanner.nextLine();

        System.out.print(
                "Enter check-in date (YYYY-MM-DD, example: 2026-08-10): "
        );
        String checkInDate = scanner.nextLine();

        try {

            Customer customer =
                    system.findCustomerById(customerId);

            system.checkInToRoom(
                    customer,
                    roomId,
                    bookingDate,
                    checkInDate
            );

        } catch (ReservationNotFoundException e) {

            System.out.println(
                    "[ERROR] " + e.getMessage()
            );
        }
    }

    /**
     * Checks a customer out of a room.
     */
    private static void checkOut(
            ReservationSystem system) {

        System.out.println("---------- CHECK-OUT ----------");

        int customerId = readInteger(
                "Enter customer ID: "
        );

        int roomId = readInteger(
                "Enter room ID to check out from: "
        );

        try {

            Customer customer =
                    system.findCustomerById(customerId);

            system.checkOutFromRoom(
                    customer,
                    roomId
            );

        } catch (ReservationNotFoundException e) {

            System.out.println(
                    "[ERROR] " + e.getMessage()
            );
        }
    }

    /**
     * Displays all bookings for a specific customer.
     */
    private static void showCustomerBookings(
            ReservationSystem system) {

        System.out.println("---------- CUSTOMER BOOKINGS ----------");

        int customerId = readInteger(
                "Enter customer ID: "
        );

        try {

            Customer customer =
                    system.findCustomerById(customerId);

            system.printBookingsForCustomer(customer);

        } catch (ReservationNotFoundException e) {

            System.out.println(
                    "[ERROR] " + e.getMessage()
            );
        }
    }

    /**
     * Reads an integer safely from the user.
     */
    private static int readInteger(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "[ERROR] Please enter numbers only."
                );
            }
        }
    }
}