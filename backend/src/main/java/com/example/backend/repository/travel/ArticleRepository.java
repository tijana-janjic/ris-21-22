package com.example.backend.repository.travel;

import com.example.backend.domain.travel.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByAgentEmail(String email);
}