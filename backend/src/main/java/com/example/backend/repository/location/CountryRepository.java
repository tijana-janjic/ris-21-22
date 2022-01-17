package com.example.backend.repository.location;

import com.example.backend.domain.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}