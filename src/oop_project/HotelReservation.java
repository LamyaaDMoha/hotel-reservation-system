package oop_project;

/**
 * Represents a hotel room in the reservation system.
 */
public class HotelReservation extends ReservationItem {

    private String hotelName;
    private String roomType;
    private int nights;

    /**
     * Creates an empty hotel reservation.
     */
    public HotelReservation() {
        super();
    }

    /**
     * Creates a hotel reservation with room details.
     *
     * @param itemId unique room ID
     * @param title room title
     * @param hotelName hotel name
     * @param roomType room type
     * @param nights number of nights
     */
    public HotelReservation(Integer itemId, String title,
                            String hotelName, String roomType, int nights) {
        super(itemId, title);
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.nights = nights;
    }

    /**
     * Returns the hotel reservation details.
     *
     * @return formatted reservation details
     */
    @Override
    public String getDetails() {
        String result = "";

        result += "------ [HOTEL RESERVATION] ------\n";
        result += "ID: " + itemId + "\n";
        result += "Title: " + title + "\n";
        result += "Hotel: " + hotelName + "\n";
        result += "Room: " + roomType + "\n";
        result += "Nights: " + nights + "\n";
        result += "Available: " + available + "\n";
        result += "----------------------------------";

        return result;
    }
}