package com.example.backend.repository.location;

import com.example.backend.domain.location.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> getByCityId(Long id);
}