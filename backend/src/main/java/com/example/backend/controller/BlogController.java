package com.example.backend.controller;

import com.example.backend.domain.travel.Article;
import com.example.backend.dto.ArticleDto;
import com.example.backend.dto.NewArticleDto;
import com.example.backend.mappers.TourMapper;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    private final TourService tourService;
    private final ArticleService articleService;
    private final TourMapper tourMapper;
    private final TokenUtils tokenUtils;


    @Autowired
    public BlogController(TourService tourService, ArticleService articleService, LocationService locationService, PersonService personService, ModelMapper modelMapper, TokenUtils tokenUtils) {
        this.tourService = tourService;
        this.articleService = articleService;
        this.tokenUtils = tokenUtils;
        this.tourMapper = new TourMapper(locationService, personService, tourService, modelMapper);
    }

    @GetMapping
    public Set<ArticleDto> getAllArticles() {
        List<Article> set = tourService.getAllArticles();
        return set.stream().map(tourMapper::articleToDto).collect(Collectors.toSet());
    }

    @GetMapping("agent/articles")
    public Set<ArticleDto> getAllArticlesByAgent(HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        List<Article> set = tourService.getAllArticlesByAgent(email);
        return set.stream().map(tourMapper::articleToDto).collect(Collectors.toSet());
    }

    @PostMapping("/create-article")
    public ResponseEntity<Long> addArticle(@RequestBody NewArticleDto articleDto, HttpServletRequest request){
        String token = request.getCookies()[0].getValue();
        String email = tokenUtils.getEmailFromToken(token);
        Article article = articleService.addNewArticle(tourMapper.dtoToArticle(articleDto, email));
        return new ResponseEntity<>(article.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-article")
    public ResponseEntity<Long> deleteArticle(@RequestParam Long id){
        Article article = tourService.getArticleById(id);
        tourService.deleteArticle(article);
        return new ResponseEntity<>(article.getId(), HttpStatus.OK);
    }

}
