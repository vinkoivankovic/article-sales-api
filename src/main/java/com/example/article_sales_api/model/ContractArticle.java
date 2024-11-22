package com.example.article_sales_api.model;

import jakarta.persistence.*;

@Entity
@Table
public class ContractArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_contract_id")
    private SalesContract salesContract;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesContract getSalesContract() {
        return salesContract;
    }

    public void setSalesContract(SalesContract salesContract) {
        this.salesContract = salesContract;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
