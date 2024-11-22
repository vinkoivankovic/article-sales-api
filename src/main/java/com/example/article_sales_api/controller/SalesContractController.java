package com.example.article_sales_api.controller;

import com.example.article_sales_api.dto.SalesContractDTO;
import com.example.article_sales_api.enums.SalesContractStatus;
import com.example.article_sales_api.service.SalesContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales_contracts")
public class SalesContractController {

    @Autowired
    private SalesContractService salesContractService;

    @GetMapping
    public ResponseEntity<List<SalesContractDTO>> getAllSalesContracts(
            @RequestParam(required = false) String buyer,
            @RequestParam(required = false) String status) {
        List<SalesContractDTO> contracts = salesContractService.getAllSalesContracts(buyer, SalesContractStatus.valueOf(status));
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesContractDTO> getSalesContractById(@PathVariable Long id) {
        Optional<SalesContractDTO> contract = salesContractService.getSalesContractById(id);
        return contract.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SalesContractDTO> createSalesContract(@RequestBody SalesContractDTO salesContractDto) {
        SalesContractDTO createdContract = salesContractService.createSalesContract(salesContractDto);
        return ResponseEntity.status(201).body(createdContract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesContractDTO> updateSalesContract(
            @PathVariable Long id, @RequestBody SalesContractDTO salesContractDto) {
        SalesContractDTO updatedContract = salesContractService.updateSalesContract(id, salesContractDto);
        return updatedContract != null ? ResponseEntity.ok(updatedContract) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteSalesContract(@PathVariable Long id) {
        boolean deleted = salesContractService.softDeleteSalesContract(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
