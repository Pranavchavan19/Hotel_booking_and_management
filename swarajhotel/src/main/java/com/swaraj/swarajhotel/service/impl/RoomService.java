//package com.swaraj.swarajhotel.service.impl;
//
//import com.swaraj.swarajhotel.dto.Response;
//import com.swaraj.swarajhotel.service.interfac.IRoomService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class RoomService implements IRoomService {
//
//    @Override
//    public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
//        return null;
//    }
//
//    @Override
//    public List<String> getAllRoomTypes() {
//        return Arrays.asList("Single", "Double", "Suite");
//    }
//
//    @Override
//    public Response getAllRooms() {
//        return null;
//    }
//
//    @Override
//    public Response deleteRoom(Long roomId) {
//        return null;
//    }
//
//    @Override
//    public Response updateRoom(Long roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
//        return null;
//    }
//
//    @Override
//    public Response getRoomById(Long roomId) {
//        return null;
//    }
//
//    @Override
//    public Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
//        return null;
//    }
//
//    @Override
//    public Response getAllAvailableRooms() {
//        return null;
//    }
//}










package com.swaraj.swarajhotel.service.impl;

import com.swaraj.swarajhotel.dto.Response;
import com.swaraj.swarajhotel.dto.RoomDTO;
import com.swaraj.swarajhotel.entity.Room;
import com.swaraj.swarajhotel.repo.RoomRepository;
import com.swaraj.swarajhotel.service.interfac.IRoomService;
import com.swaraj.swarajhotel.service.interfac.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        room.setRoomDescription(description);

        if (photo != null && !photo.isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String uploadedUrl = imageService.uploadImage(photo, filename);
            if (uploadedUrl != null) {
                room.setRoomPhotoUrl(uploadedUrl);
            }
        }

        Room saved = roomRepository.save(room);
        RoomDTO roomDTO = mapToDTO(saved);

        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("Room added successfully");
        response.setRoom(roomDTO);
        return response;
    }

    @Override
    public Response updateRoom(Long roomId, String roomType, String description, BigDecimal roomPrice, MultipartFile photo) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            return new Response(404, "Room not found", null);
        }

        Room room = optionalRoom.get();
        room.setRoomType(roomType);
        room.setRoomDescription(description);
        room.setRoomPrice(roomPrice);

        if (photo != null && !photo.isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String uploadedUrl = imageService.uploadImage(photo, filename);
            if (uploadedUrl != null) {
                room.setRoomPhotoUrl(uploadedUrl);
            }
        }

        Room updated = roomRepository.save(room);
        RoomDTO roomDTO = mapToDTO(updated);

        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("Room updated successfully");
        response.setRoom(roomDTO);
        return response;
    }

    @Override
    public List<String> getAllRoomTypes() {
        List<String> staticTypes = Arrays.asList("Single", "Double", "Suite");

        List<String> dbTypes = roomRepository.findDistinctRoomTypes();

        Set<String> combined = new LinkedHashSet<>();
        combined.addAll(staticTypes);
        combined.addAll(dbTypes);

        return new ArrayList<>(combined);
    }

    @Override
    public Response getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOList.add(mapToDTO(room));
        }

        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("All rooms fetched successfully");
        response.setRoomList(roomDTOList);
        return response;
    }

    @Override
    public Response deleteRoom(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            roomRepository.deleteById(roomId);
            return new Response(200, "Room deleted successfully", null);
        }
        return new Response(404, "Room not found", null);
    }

    @Override
    public Response getRoomById(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            return new Response(404, "Room not found", null);
        }

        RoomDTO roomDTO = mapToDTO(room.get());
        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("Room fetched successfully");
        response.setRoom(roomDTO);
        return response;
    }

    @Override
    public Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        List<Room> rooms = roomRepository.findAvailableRoomsByDateAndTypes(checkInDate, checkOutDate, roomType);
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOList.add(mapToDTO(room));
        }
        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("Available rooms fetched");
        response.setRoomList(roomDTOList);
        return response;
    }

    @Override
    public Response getAllAvailableRooms() {
        List<Room> rooms = roomRepository.getAllAvailableRooms();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOList.add(mapToDTO(room));
        }
        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("Available rooms fetched");
        response.setRoomList(roomDTOList);
        return response;
    }

    // Helper: Convert Room to RoomDTO
    private RoomDTO mapToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setRoomType(room.getRoomType());
        dto.setRoomPrice(room.getRoomPrice());
        dto.setRoomDescription(room.getRoomDescription());
        dto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        return dto;
    }
}
