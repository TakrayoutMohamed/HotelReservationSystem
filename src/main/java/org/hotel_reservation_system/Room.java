package org.hotel_reservation_system;


import org.hotel_reservation_system.Exceptions.NegativePricePerNightException;

public class Room {
    private final Integer id;
    private RoomType type;
    private int pricePerNight;

    public Room(int id, RoomType type, int pricePerNight){
        this.id = id;
        setType(type);
        setPricePerNight(pricePerNight);
    }

    public Room(Room obj){
        this.id = obj.getId();
        this.setType(obj.getType());
        this.setPricePerNight(obj.pricePerNight);
    }

    public Integer getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        if (pricePerNight < 0)
            throw new NegativePricePerNightException("Error: price of a room per night should not be negative");
        this.pricePerNight = pricePerNight;
    }
    @Override
    public String toString(){
        return "Room id: "+ getId() + " type: "+ getType() + " price per night: "+ getPricePerNight();
    }
}
