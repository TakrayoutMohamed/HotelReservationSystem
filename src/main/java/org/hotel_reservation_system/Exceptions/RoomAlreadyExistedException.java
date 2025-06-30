package org.hotel_reservation_system.Exceptions;

public class RoomAlreadyExistedException extends RuntimeException {
    public RoomAlreadyExistedException(String message) {
        super(message);
    }
}
