package com.example.article_sales_api.service;

import com.example.article_sales_api.model.Article;
import com.example.article_sales_api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article increaseQuantity(Long id, int quantity) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));

        article.setQuantity(article.getQuantity() + quantity);

        return articleRepository.save(article);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));

        if (updatedArticle.getName() != null) {
            article.setName(updatedArticle.getName());
        }

        if (updatedArticle.getQuantity() != null) {
            if (updatedArticle.getQuantity() < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative.");
            }
            article.setQuantity(updatedArticle.getQuantity());
        }

        if (updatedArticle.getSupplier() != null) {
            article.setSupplier(updatedArticle.getSupplier());
        }

        return articleRepository.save(article);
    }
}