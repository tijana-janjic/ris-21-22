package com.example.backend.repository.travel;

import com.example.backend.domain.travel.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}