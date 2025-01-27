package com.swaraj.swarajhotel.repo;

import com.swaraj.swarajhotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<String> 
}
