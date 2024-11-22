package com.example.article_sales_api.util;

import com.example.article_sales_api.dto.ContractArticleDTO;
import com.example.article_sales_api.dto.SalesContractDTO;
import com.example.article_sales_api.enums.SalesContractStatus;
import com.example.article_sales_api.model.Article;
import com.example.article_sales_api.model.ContractArticle;
import com.example.article_sales_api.model.SalesContract;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperUtilsTest {

    @Test
    void testToSalesContract() {
        ContractArticleDTO contractArticleDTO = new ContractArticleDTO(1L, "Test Article", "Supplier A", 10);
        SalesContractDTO salesContractDTO = new SalesContractDTO(
                1L, "Buyer A", "1234", LocalDate.now(),
                LocalDate.now().plusDays(10), SalesContractStatus.CREATED,
                List.of(contractArticleDTO)
        );
        salesContractDTO.setArtikli(List.of(contractArticleDTO));
        SalesContract result = MapperUtils.toSalesContract(salesContractDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Buyer A", result.getBuyer());
        assertEquals("1234", result.getContractNumber());
        assertEquals(SalesContractStatus.CREATED, result.getStatus());
        assertEquals(1, result.getContractArticles().size());
    }
}
