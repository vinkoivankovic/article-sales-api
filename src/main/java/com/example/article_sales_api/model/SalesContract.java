package com.example.article_sales_api.model;

import com.example.article_sales_api.enums.SalesContractStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class SalesContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyer;

    private String contractNumber;

    private LocalDate depositDate;

    private LocalDate deliveryDate;

    @Enumerated(EnumType.ORDINAL)  // Save as an integer in the database
    private SalesContractStatus status;

    @OneToMany(mappedBy = "salesContract", cascade = CascadeType.ALL)
    private List<ContractArticle> contractArticles;

    public SalesContract() {
    }

    public SalesContract(
            Long id,
            String buyer,
            String contractNumber,
            LocalDate depositDate,
            LocalDate deliveryDate,
            SalesContractStatus status,
            List<ContractArticle> articles) {
        this.id = id;
        this.buyer = buyer;
        this.contractNumber = contractNumber;
        this.depositDate = depositDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.contractArticles = (articles != null) ? articles : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public SalesContractStatus getStatus() {
        return status;
    }

    public void setStatus(SalesContractStatus status) {
        this.status = status;
    }

    public List<ContractArticle> getContractArticles() {
        return Objects.requireNonNullElseGet(this.contractArticles, ArrayList::new);
    }

    public void setContractArticles(List<ContractArticle> contractArticles) {
        this.contractArticles = contractArticles;
    }
}
