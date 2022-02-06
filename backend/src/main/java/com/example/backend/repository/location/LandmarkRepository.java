package com.example.backend.repository.location;

import com.example.backend.domain.location.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    List<Landmark> getLandmarksByCityId(Long id);
}