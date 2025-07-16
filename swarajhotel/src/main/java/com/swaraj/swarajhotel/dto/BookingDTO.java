//package com.swaraj.swarajhotel.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.swaraj.swarajhotel.entity.Room;
//import com.swaraj.swarajhotel.entity.User;
//
//import lombok.Data;
//import org.joda.time.LocalDate;
//
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class BookingDTO {
//
//    private  Long id;
//
//    private LocalDate checkInDate;
//
//    private LocalDate checkOutDate;
//
//    private int numOfAdults;
//
//    private int numOfChildren;
//
//    private int totalNumOfGuest;
//
//    private String bookingConfirmationCode;
//
//    private UserDTO user;
//
//    private RoomDTO room;
//}
//
//





package com.swaraj.swarajhotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumOfGuest;
    private String bookingConfirmationCode;
    private UserDTO user;
    private RoomDTO room;

    // No-args constructor
    public BookingDTO() {
    }

    // All-args constructor
    public BookingDTO(Long id, LocalDate checkInDate, LocalDate checkOutDate, int numOfAdults, int numOfChildren,
                      int totalNumOfGuest, String bookingConfirmationCode, UserDTO user, RoomDTO room) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.totalNumOfGuest = totalNumOfGuest;
        this.bookingConfirmationCode = bookingConfirmationCode;
        this.user = user;
        this.room = room;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public int getTotalNumOfGuest() {
        return totalNumOfGuest;
    }

    public void setTotalNumOfGuest(int totalNumOfGuest) {
        this.totalNumOfGuest = totalNumOfGuest;
    }

    public String getBookingConfirmationCode() {
        return bookingConfirmationCode;
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }
}
