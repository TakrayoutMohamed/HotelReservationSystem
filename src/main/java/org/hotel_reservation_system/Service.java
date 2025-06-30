package org.hotel_reservation_system;

import org.hotel_reservation_system.Exceptions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class Service {
    ArrayList<Room> rooms;
    ArrayList<Users> users;
    ArrayList<Booking> bookings;
    public Service()
    {
        rooms = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        try{
            if (rooms.stream().anyMatch(room -> room.getId().equals(roomNumber)))
                throw new RoomAlreadyExistedException("Error : Room already existed try an other id");
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut){
        try{
            Optional<Users> user = users.stream().filter(obj -> obj.getId().equals(userId)).findFirst();
            Optional<Room> room = rooms.stream().filter(obj -> obj.getId().equals(roomNumber)).findFirst();
            if (user.isEmpty())
                throw new UserNotFoundException("Error: there are no users with id: "+ userId);
            if (room.isEmpty())
                throw new RoomNotFoundException("Error: there are no rooms with id: "+ roomNumber);
            long totalPrice = getNumberOfDays(checkIn, checkOut) * room.get().getPricePerNight();
            if (user.get().getBalance() < totalPrice)
                throw new NotEnoughBalanceException("Error: you do not have enough balance to book the room with id: "+ room.get().getId()+ " it's price is "+ totalPrice + " and you have "+ user.get().getBalance());
            if (!isRoomAvailable(roomNumber, checkIn, checkOut))
                throw new RoomUnavailableException("Error: the room with id: "+ room.get().getId() +" you trying to book already booked");
            System.out.println("get NumberOfDays : "+ getNumberOfDays(checkIn, checkOut));
            System.out.println("room.get().getPricePerNight() : "+ room.get().getPricePerNight());
            System.out.println("Total price : "+ totalPrice);
            bookings.add(new Booking(bookings.size() + 1, checkIn, checkOut, new Users(user.get()), new Room(room.get())));
            bookings.getLast().setTotalPrice(totalPrice);
            user.get().setBalance(user.get().getBalance() - totalPrice);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    void printAll(){
        System.out.println("Rooms : ---------------------------------------");
        if (rooms.isEmpty())
            System.out.println("There are no rooms yet !!!!");
        else {
            for (int i = rooms.size() - 1; i >= 0; i--){
                System.out.println(rooms.get(i));
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Bookings : ---------------------------------------");
        if (bookings.isEmpty())
            System.out.println("There are no bookings yet !!!!");
        else {
            for (int i = bookings.size() - 1; i >= 0; i--){
                System.out.println(bookings.get(i));
            }
        }
        System.out.println("-----------------------------------------------");
    }

    void setUser(int userId, int balance) {
        try {
            if (users.stream().anyMatch(user -> user.getId().equals(userId)))
                throw new RoomAlreadyExistedException("Error : User already existed try an other id");
            users.add(new Users(userId, balance));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    void printAllUsers(){
        System.out.println("Users : ---------------------------------------");
        if (users.isEmpty())
            System.out.println("There are no users yet !!!!");
        else {
            for (int i = users.size() - 1; i >= 0; i--){
                System.out.println(users.get(i));
            }
        }
        System.out.println("-----------------------------------------------");
    }

    private Long getNumberOfDays(Date startDate, Date endDate){
        return (TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS));
    }

    private boolean isRoomAvailable(int RoomNumber, Date newCheckIn, Date newCheckOut){
        Optional<Booking> bookingOptional = bookings.stream().filter(obj -> obj.getRoom().getId().equals(RoomNumber)).findAny();
        if (bookingOptional.isEmpty())
            return true;
        return bookingOptional.stream().noneMatch(obj -> newCheckIn.before(obj.getEndDate()) && newCheckOut.after(obj.getStartDate()));
    }

}
