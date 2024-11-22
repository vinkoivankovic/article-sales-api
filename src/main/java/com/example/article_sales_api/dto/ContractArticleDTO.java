package com.example.article_sales_api.dto;

public class ContractArticleDTO {
    private Long id;
    private String name;
    private String supplier;
    private Integer kolicina;

    public ContractArticleDTO(Long id, String name, String supplier, Integer kolicina) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.kolicina = kolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
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
