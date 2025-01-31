package com.swaraj.swarajhotel.service.interfac;

import com.swaraj.swarajhotel.dto.Response;
import com.swaraj.swarajhotel.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IRoomService {

    Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice , String description);

    List<String> getAllRoomTypes();

    Response getAllRooms();

    Response deleteRoom(Long roomId);

    Response updateRoom(Long roomId , String roomType,String description ,BigDecimal roomPrice , MultipartFile photo);

    Response getRoomById(Long roomId);

    Response getAvailableRoomsByDataAndType(LocalDate checkInDate , LocalDate checkOutDate , String roomType);

    Response getAllAvailableRooms();
}
