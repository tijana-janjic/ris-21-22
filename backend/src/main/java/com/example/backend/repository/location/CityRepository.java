package com.example.backend.repository.location;

import com.example.backend.domain.location.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}