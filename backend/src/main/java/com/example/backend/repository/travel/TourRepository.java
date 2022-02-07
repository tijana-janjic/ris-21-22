package com.example.backend.repository.travel;

import com.example.backend.domain.travel.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {


    List<Tour> findByAgentEmail(String email);
    List<Tour> findByGuideEmail(String email);

    List<Tour> findByClientEmailAndDeadlineBefore(String email, LocalDateTime today);
    List<Tour> findByClientEmailAndDeadlineAfter(String email, LocalDateTime today);

    List<Tour> findByDeadlineAfter(LocalDateTime today);

}