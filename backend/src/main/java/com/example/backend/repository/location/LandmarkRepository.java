package com.example.backend.repository.location;

import com.example.backend.domain.location.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

}