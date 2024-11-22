package com.example.article_sales_api.repository;

import com.example.article_sales_api.model.ContractArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractArticleRepository extends JpaRepository<ContractArticle, Long> {
}