package com.example.backend.repository.person;

import com.example.backend.domain.person.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, String> {
}