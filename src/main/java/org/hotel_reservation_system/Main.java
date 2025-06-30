package org.hotel_reservation_system;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Service service = new Service();
        service.setRoom(1, RoomType.STANDARD_SUIT, 1000);
        service.setRoom(2, RoomType.JUNIOR_SUIT, 2000);
        service.setRoom(3, RoomType.MASTER_SUIT, 3000);
        service.setUser(1, 5000);
        service.setUser(2, 10000);
        try {
            service.bookRoom(1, 2, sdf.parse("30/06/2026"),sdf.parse("07/07/2026"));//7 days for 14000 should not pass
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            service.bookRoom(1, 2, sdf.parse("07/07/2026"),sdf.parse("30/06/2026"));// checkout before checkin . should not pass
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{

            service.bookRoom(1, 1, sdf.parse("07/07/2026"),sdf.parse("08/07/2026"));// 1 day for 1000 , should pass
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            service.bookRoom(1, 1, sdf.parse("07/07/2026"),sdf.parse("08/07/2026"));// 1 day but it already booked should not pass
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            service.bookRoom(2, 1, sdf.parse("07/07/2026"),sdf.parse("09/07/2026"));// 2 days but should not pass already booked
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        service.setRoom(1, RoomType.MASTER_SUIT, 10000); // should not set, it already existed
        service.printAll();
        service.printAllUsers();
    }
}