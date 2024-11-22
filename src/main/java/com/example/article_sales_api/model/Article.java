package com.example.article_sales_api.model;

import jakarta.persistence.*;

@Entity
@Table
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String supplier;

    private Integer totalQuantity;

    public Integer getQuantity() {
        return totalQuantity;
    }

    public void setQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}
