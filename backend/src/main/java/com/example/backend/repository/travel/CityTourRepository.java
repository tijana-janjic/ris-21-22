package com.example.backend.repository.travel;

import com.example.backend.domain.travel.CityTour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityTourRepository extends JpaRepository<CityTour, Long> {
}