package com.example.article_sales_api.repository;

import com.example.article_sales_api.enums.SalesContractStatus;
import com.example.article_sales_api.model.SalesContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesContractRepository extends JpaRepository<SalesContract, Long> {
    List<SalesContract> findByBuyerAndStatus(String buyer, SalesContractStatus status);

    @Query(value = "SELECT s.contract_Number FROM Sales_Contract s WHERE s.contract_Number LIKE %:year ORDER BY CAST(SPLIT_PART(s.contract_Number, '/', 1) AS INTEGER) DESC LIMIT 1", nativeQuery = true)
    String findLastContractNumberForYear(@Param("year") int year);
}