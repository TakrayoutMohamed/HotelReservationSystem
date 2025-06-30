package org.hotel_reservation_system;

import org.hotel_reservation_system.Exceptions.InvalidDateException;

import java.util.Date;

public class Booking {
    private final Room room;
    private final Users user;
    private final Integer id;
    private Date startDate;
    private Date endDate;
    private long totalPrice;

    public Booking(int id, Date startDate, Date endDate, Users usr, Room room) {
        this.id = id;
        this.user = usr;
        this.room = room;
        if (isValidDate(startDate, endDate)){
            throw new InvalidDateException("Error: the entered date is invalid to Book from CheckIn: "+ startDate + " to checkOut: "+endDate);
        }
        setEndDate(endDate);
        setStartDate(startDate);
    }

    private boolean isValidDate(Date checkIn, Date checkOut){
        return checkIn == null || checkOut == null || checkIn.after(checkOut);
    }

    public Date getStartDate() {
        return startDate;
    }

    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Room getRoom() {
        return room;
    }

    public Users getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString(){
        String str;
        str = "Booking : id: "+ getId() + " CheckIn: "+ getStartDate() + " CheckOut: "+ getEndDate()
                    + " \nFrom " + getUser()
                    + "\nFor " + getRoom()
                    + "\nwith total price: "+ getTotalPrice();
        return  str;
    }

}
