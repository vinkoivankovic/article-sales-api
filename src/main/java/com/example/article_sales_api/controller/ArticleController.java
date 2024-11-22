package com.example.article_sales_api.controller;

import com.example.article_sales_api.model.Article;
import com.example.article_sales_api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article savedArticle = articleService.addArticle(article);
        return ResponseEntity.ok(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable Long id,
            @RequestBody Article article) {
        Article updatedArticle = articleService.updateArticle(id, article);
        return ResponseEntity.ok(updatedArticle);
    }

    @PutMapping("/{id}/increaseQuantity")
    public ResponseEntity<Article> increaseQuantity(@PathVariable Long id, @RequestParam int quantity) {
        Article updatedArticle = articleService.increaseQuantity(id, quantity);
        return ResponseEntity.ok(updatedArticle);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }
}
