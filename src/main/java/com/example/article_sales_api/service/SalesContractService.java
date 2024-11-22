package com.example.article_sales_api.service;

import com.example.article_sales_api.dto.SalesContractDTO;
import com.example.article_sales_api.enums.SalesContractStatus;
import com.example.article_sales_api.model.Article;
import com.example.article_sales_api.model.ContractArticle;
import com.example.article_sales_api.model.SalesContract;
import com.example.article_sales_api.repository.ArticleRepository;
import com.example.article_sales_api.repository.SalesContractRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.article_sales_api.util.MapperUtils;

@Service
public class SalesContractService {

    @Autowired
    private SalesContractRepository salesContractRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<SalesContractDTO> getAllSalesContracts(String buyer, SalesContractStatus status) {
        List<SalesContract> contracts = salesContractRepository.findByBuyerAndStatus(buyer, status);
        return contracts.stream()
                .map(MapperUtils::toSalesContractDTO)
                .collect(Collectors.toList());
    }

    public Optional<SalesContractDTO> getSalesContractById(Long id) {
        Optional<SalesContract> contract = salesContractRepository.findById(id);
        return contract.map(MapperUtils::toSalesContractDTO);
    }

    public SalesContractDTO createSalesContract(SalesContractDTO salesContractDTO) {
        SalesContract salesContract = MapperUtils.toSalesContract(salesContractDTO);
        salesContract.setStatus(SalesContractStatus.CREATED);
        salesContract.setContractNumber(generateContractNumber());

        List<ContractArticle> contractArticles = salesContractDTO.getArtikli().stream()
                .map(articleDTO -> {
                    Article article = articleRepository.findById(articleDTO.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + articleDTO.getId()));

                    if (articleDTO.getKolicina() > article.getQuantity()) {
                        throw new IllegalArgumentException(
                                "Requested quantity (" + articleDTO.getKolicina() + ") exceeds available quantity (" + article.getQuantity() + ") for article with id: " + articleDTO.getId());
                    }

                    ContractArticle contractArticle = new ContractArticle();
                    contractArticle.setArticle(article);
                    contractArticle.setQuantity(articleDTO.getKolicina());

                    contractArticle.setSalesContract(salesContract);

                    return contractArticle;
                })
                .toList();
        salesContract.setContractArticles(contractArticles);
        salesContractRepository.save(salesContract);

        return MapperUtils.toSalesContractDTO(salesContract);
    }

    @Transactional
    public SalesContractDTO updateSalesContract(Long id, SalesContractDTO salesContractDTO) {
        SalesContract salesContract = salesContractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found with id: " + id));

        validateAndApplyUpdates(salesContract, salesContractDTO);

        SalesContract updatedContract = salesContractRepository.save(salesContract);
        return MapperUtils.toSalesContractDTO(updatedContract);
    }

    private void validateAndApplyUpdates(SalesContract salesContract, SalesContractDTO salesContractDTO) {
        switch (salesContract.getStatus()) {
            case CREATED:
                handleCreatedStatus(salesContract, salesContractDTO);
                break;
            case ORDERED:
                handleOrderedStatus(salesContract, salesContractDTO);
                break;
            case DELIVERED:
            case DELETED:
                ensureNoStatusChange(salesContractDTO);
                break;
            default:
                throw new IllegalArgumentException("Unknown status: " + salesContract.getStatus());
        }

        if (salesContractDTO.getKupac() != null) {
            salesContract.setBuyer(salesContractDTO.getKupac());
        }
        if (salesContractDTO.getBrojUgovora() != null) {
            salesContract.setContractNumber(salesContractDTO.getBrojUgovora());
        }
        if (salesContractDTO.getDatumAkontacije() != null) {
            salesContract.setDepositDate(salesContractDTO.getDatumAkontacije());
        }
    }

    private void handleCreatedStatus(SalesContract salesContract, SalesContractDTO salesContractDTO) {
        if (salesContractDTO.getRokIsporuke() != null) {
            salesContract.setDeliveryDate(salesContractDTO.getRokIsporuke());
        }
        if (salesContractDTO.getDatumAkontacije() != null) {
            salesContract.setDepositDate(salesContractDTO.getDatumAkontacije());
        }
        if (salesContractDTO.getStatus() == SalesContractStatus.ORDERED) {
            updateArticlesStock(salesContract);
            salesContract.setStatus(SalesContractStatus.ORDERED);
        } else if (salesContractDTO.getStatus() != null) {
            throw new IllegalArgumentException("You cannot change status to " + salesContractDTO.getStatus().getCroatianName());
        }
    }

    private void handleOrderedStatus(SalesContract salesContract, SalesContractDTO salesContractDTO) {
        if (salesContractDTO.getStatus() == SalesContractStatus.DELIVERED) {
            salesContract.setStatus(SalesContractStatus.DELIVERED);
        } else if (salesContractDTO.getStatus() != null) {
            throw new IllegalArgumentException("You cannot change status to " + salesContractDTO.getStatus().getCroatianName());
        }
    }

    private void ensureNoStatusChange(SalesContractDTO salesContractDTO) {
        if (salesContractDTO.getStatus() != null) {
            throw new IllegalArgumentException("You cannot change status from the current state.");
        }
    }

    private void updateArticlesStock(SalesContract salesContract) {
        for (ContractArticle contractArticle : salesContract.getContractArticles()) {
            Article article = contractArticle.getArticle();
            int newTotalQuantity = article.getQuantity() - contractArticle.getQuantity();

            if (newTotalQuantity < 0) {
                throw new IllegalArgumentException("Insufficient stock for article: " + article.getName());
            }

            article.setQuantity(newTotalQuantity);
            articleRepository.save(article);
        }
    }

    public boolean softDeleteSalesContract(Long id) {
        Optional<SalesContract> contract = salesContractRepository.findById(id);
        if (contract.isPresent()) {
            SalesContract salesContract = contract.get();
            salesContract.setStatus(SalesContractStatus.DELETED);
            salesContractRepository.save(salesContract);
            return true;
        }
        return false;
    }

    private String generateContractNumber() {
        int currentYear = LocalDate.now().getYear();
        int nextContractNumber = getNextContractNumber(currentYear);
        return nextContractNumber + "/" + currentYear;
    }

    public int getNextContractNumber(int year) {
        String lastContractNumber = salesContractRepository.findLastContractNumberForYear(year);

        if (lastContractNumber == null) {
            return 1;
        }

        String[] parts = lastContractNumber.split("/");
        int lastNumber = Integer.parseInt(parts[0]);

        return lastNumber + 1;
    }

    public void addArticleToSalesContract(Long contractId, Long articleId, int quantity) {
    }
}
