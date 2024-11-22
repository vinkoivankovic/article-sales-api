package com.example.article_sales_api.dto;

import com.example.article_sales_api.enums.SalesContractStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalesContractDTO {

    private Long id;
    private String kupac;
    private String brojUgovora;
    private LocalDate datumAkontacije;
    private LocalDate rokIsporuke;
    private SalesContractStatus status;
    private List<ContractArticleDTO> artikli;

    public SalesContractDTO(){

    }

    public SalesContractDTO(
            Long id,
            String kupac,
            String brojUgovora,
            LocalDate datumAkontacije,
            LocalDate rokIsporuke,
            SalesContractStatus status,
            List<ContractArticleDTO> artikli
    ) {
        this.id = id;
        this.kupac = kupac;
        this.brojUgovora = brojUgovora;
        this.datumAkontacije = datumAkontacije;
        this.rokIsporuke = rokIsporuke;
        this.status = status;
        this.artikli = (artikli != null) ? artikli : new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public LocalDate getDatumAkontacije() {
        return datumAkontacije;
    }

    public void setDatumAkontacije(LocalDate datumAkontacije) {
        this.datumAkontacije = datumAkontacije;
    }

    public LocalDate getRokIsporuke() {
        return rokIsporuke;
    }

    public void setRokIsporuke(LocalDate rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public SalesContractStatus getStatus() {
        return status;
    }

    public void setStatus(SalesContractStatus status) {
        this.status = status;
    }

    public List<ContractArticleDTO> getArtikli() {
        return Objects.requireNonNullElseGet(this.artikli, ArrayList::new);
    }

    public void setArtikli(List<ContractArticleDTO> artikli) {
        this.artikli = (artikli != null) ? artikli : new ArrayList<>();
    }
}