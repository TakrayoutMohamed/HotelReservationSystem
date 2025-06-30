package org.hotel_reservation_system.Exceptions;

public class NegativePricePerNightException extends RuntimeException {
    public NegativePricePerNightException(String message) {
        super(message);
    }
}
