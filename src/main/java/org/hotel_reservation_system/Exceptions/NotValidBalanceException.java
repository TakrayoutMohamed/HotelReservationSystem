package org.hotel_reservation_system.Exceptions;

public class NotValidBalanceException extends RuntimeException {
    public NotValidBalanceException(String message) {
        super(message);
    }
}
