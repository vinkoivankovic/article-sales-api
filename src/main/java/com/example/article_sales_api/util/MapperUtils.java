package com.example.article_sales_api.util;

import com.example.article_sales_api.dto.ContractArticleDTO;
import com.example.article_sales_api.dto.SalesContractDTO;
import com.example.article_sales_api.enums.SalesContractStatus;
import com.example.article_sales_api.model.ContractArticle;
import com.example.article_sales_api.model.SalesContract;

public class MapperUtils {
    public static ContractArticleDTO toContractArticleDTO(ContractArticle contractArticle) {
        return new ContractArticleDTO(
                contractArticle.getArticle().getId(),
                contractArticle.getArticle().getName(),
                contractArticle.getArticle().getSupplier(),
                contractArticle.getQuantity()
        );
    }

    public static ContractArticle toContractArticle(ContractArticleDTO contractArticleDTO) {
        ContractArticle contractArticle = new ContractArticle();
        contractArticle.setId(contractArticleDTO.getId());
        contractArticle.setQuantity(contractArticleDTO.getKolicina());
        return contractArticle;
    }

    public static SalesContractDTO toSalesContractDTO(SalesContract salesContract) {
        return new SalesContractDTO(
                salesContract.getId(),
                salesContract.getBuyer(),
                salesContract.getContractNumber(),
                salesContract.getDepositDate(),
                salesContract.getDeliveryDate(),
                salesContract.getStatus(),
                salesContract.getContractArticles()
                        .stream()
                        .map(MapperUtils::toContractArticleDTO)
                        .toList()
        );
    }

    public static SalesContract toSalesContract(SalesContractDTO salesContractDTO) {
        return new SalesContract(
                salesContractDTO.getId(),
                salesContractDTO.getKupac(),
                salesContractDTO.getBrojUgovora(),
                salesContractDTO.getDatumAkontacije(),
                salesContractDTO.getRokIsporuke(),
                salesContractDTO.getStatus(),
                salesContractDTO.getArtikli()
                        .stream()
                        .map(MapperUtils::toContractArticle)
                        .toList()
        );
    }
}