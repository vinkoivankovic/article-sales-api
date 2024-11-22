package com.example.article_sales_api.repository;

import com.example.article_sales_api.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}