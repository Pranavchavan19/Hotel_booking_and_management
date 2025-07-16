//package com.swaraj.swarajhotel.dto;
//
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Data;
//import org.joda.time.LocalDate;
//
//import java.util.List;
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Response {
//
//
//
//    private int statusCode;
//    private String message;
//
//    private String token;
//    private String role;
//    private String expirationTime;
//    private String bookingConfirmationCode;
//
//    private UserDTO user;
//    private RoomDTO room;
//    private BookingDTO booking;
//    private List<UserDTO> userList;
//    private List<RoomDTO> roomList;
//    private List<BookingDTO> bookingList;
//
//}




package com.swaraj.swarajhotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;
    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;

    private UserDTO user;
    private RoomDTO room;
    private BookingDTO booking;
    private List<UserDTO> userList;
    private List<RoomDTO> roomList;
    private List<BookingDTO> bookingList;

    // ✅ No-args constructor
    public Response() {
    }

    // ✅ All-args constructor with auto-binding to the correct field
    public Response(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;

        if (data instanceof UserDTO) {
            this.user = (UserDTO) data;
        } else if (data instanceof RoomDTO) {
            this.room = (RoomDTO) data;
        } else if (data instanceof BookingDTO) {
            this.booking = (BookingDTO) data;
        } else if (data instanceof List<?>) {
            List<?> list = (List<?>) data;
            if (!list.isEmpty()) {
                Object first = list.get(0);
                if (first instanceof UserDTO) {
                    this.userList = (List<UserDTO>) list;
                } else if (first instanceof RoomDTO) {
                    this.roomList = (List<RoomDTO>) list;
                } else if (first instanceof BookingDTO) {
                    this.bookingList = (List<BookingDTO>) list;
                }
            }
        }
    }

    // ✅ Getters and Setters

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
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

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public List<RoomDTO> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomDTO> roomList) {
        this.roomList = roomList;
    }

    public List<BookingDTO> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingDTO> bookingList) {
        this.bookingList = bookingList;
    }
}
