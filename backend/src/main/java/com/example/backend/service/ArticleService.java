package com.example.backend.service;

import com.example.backend.domain.travel.Article;
import com.example.backend.repository.travel.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article addNewArticle(Article article) {
        return articleRepository.save(article);
    }
}
