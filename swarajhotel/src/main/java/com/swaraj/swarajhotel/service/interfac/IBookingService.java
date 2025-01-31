package com.swaraj.swarajhotel.service.interfac;

import com.swaraj.swarajhotel.dto.Response;
import com.swaraj.swarajhotel.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);
}
