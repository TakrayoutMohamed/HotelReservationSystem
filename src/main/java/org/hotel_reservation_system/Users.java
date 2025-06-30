package org.hotel_reservation_system;

import org.hotel_reservation_system.Exceptions.NotValidBalanceException;

public class Users {
    private final Integer id;
    private long balance;
    public Users(Integer id, Integer balance){
        setBalance(balance);
        this.id = id;
    }
    public Users(Users obj){
        this.id = obj.getId();
        setBalance(obj.getBalance());
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance){
        if (balance < 0)
            throw new NotValidBalanceException("Error: the starting balance of a user can not be negative");
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString(){
        return "User id: "+ getId() + " balance: "+ getBalance();
    }
}
