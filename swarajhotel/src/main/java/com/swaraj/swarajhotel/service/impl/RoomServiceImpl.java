package com.swaraj.swarajhotel.service.impl;

import com.swaraj.swarajhotel.dto.Response;
import com.swaraj.swarajhotel.dto.RoomDTO;
import com.swaraj.swarajhotel.entity.Room;
import com.swaraj.swarajhotel.repo.RoomRepository;
import com.swaraj.swarajhotel.service.interfac.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements IRoomService {

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

        try {
            if (photo != null && !photo.isEmpty()) {
                String filename = "rooms/" + System.currentTimeMillis();
                String imageUrl = imageService.uploadImage(photo, filename);
                room.setRoomPhotoUrl(imageUrl);
                System.out.println("✅ Uploaded to Cloudinary: " + imageUrl);
            } else {
                room.setRoomPhotoUrl("/images/default-room.jpg");
                System.out.println("⚠️ No photo uploaded. Using default.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            room.setRoomPhotoUrl("/images/default-room.jpg");
            System.out.println("❌ Upload failed. Using default image.");
        }

        Room savedRoom = roomRepository.save(room);
        RoomDTO dto = convertToDTO(savedRoom);

        return new Response(201, "Room added successfully", dto);
    }

    @Override
    public Response getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> dtos = rooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new Response(200, "All rooms fetched", dtos);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public Response getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) return new Response(404, "Room not found", null);
        return new Response(200, "Room found", convertToDTO(room));
    }

    @Override
    public Response updateRoom(Long roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) return new Response(404, "Room not found", null);

        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        room.setRoomDescription(description);

        try {
            if (photo != null && !photo.isEmpty()) {
                String filename = "rooms/update_" + roomId;
                String imageUrl = imageService.uploadImage(photo, filename);
                room.setRoomPhotoUrl(imageUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Room updated = roomRepository.save(room);
        return new Response(200, "Room updated", convertToDTO(updated));
    }

    @Override
    public Response deleteRoom(Long roomId) {
        if (!roomRepository.existsById(roomId)) return new Response(404, "Room not found", null);
        roomRepository.deleteById(roomId);
        return new Response(200, "Room deleted successfully", null);
    }

    @Override
    public Response getAvailableRoomsByDataAndType(java.time.LocalDate checkInDate, java.time.LocalDate checkOutDate, String roomType) {
        List<Room> availableRooms = roomRepository.findAvailableRoomsByDateAndTypes(checkInDate, checkOutDate, roomType);
        List<RoomDTO> dtos = availableRooms.stream().map(this::convertToDTO).toList();
        return new Response(200, "Available rooms fetched", dtos);
    }

    @Override
    public Response getAllAvailableRooms() {
        List<Room> rooms = roomRepository.getAllAvailableRooms();
        List<RoomDTO> dtos = rooms.stream().map(this::convertToDTO).toList();
        return new Response(200, "Available rooms", dtos);
    }

    private RoomDTO convertToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setRoomType(room.getRoomType());
        dto.setRoomPrice(room.getRoomPrice());
        dto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        dto.setRoomDescription(room.getRoomDescription());
        return dto;
    }
}
