//package com.swaraj.swarajhotel.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.swaraj.swarajhotel.entity.Booking;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class UserDTO {
//
//    private Long id;
//    private String email;
//    private String name;
//    private String phoneNumber;
//    private String role;
//    private List<BookingDTO> bookings = new ArrayList<>();
//}

package com.swaraj.swarajhotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private List<BookingDTO> bookings = new ArrayList<>();

    // No-args constructor
    public UserDTO() {
    }

    // All-args constructor
    public UserDTO(Long id, String email, String name, String phoneNumber, String role, List<BookingDTO> bookings) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.bookings = bookings;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }
}
