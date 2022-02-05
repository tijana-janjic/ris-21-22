package com.example.backend.repository;

import com.example.backend.domain.travel.Travelogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelogueRepository extends JpaRepository<Travelogue, Long> {
}