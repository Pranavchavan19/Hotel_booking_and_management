package com.swaraj.swarajhotel.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.joda.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {


    private  Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numOfAdults;

    private int numOfChildren;

    private int totalNumOfGuest;

    private String bookingConfirmationCode;

    private UserDTO user;

    private RoomDTO room;
}
