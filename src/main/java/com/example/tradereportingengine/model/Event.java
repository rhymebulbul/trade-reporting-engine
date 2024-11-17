package com.example.tradereportingengine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String buyerParty;
    private String sellerParty;
    private Double premiumAmount;
    private String premiumCurrency;

    // Getters
    public UUID getId() {
        return id;
    }

    public String getBuyerParty() {
        return buyerParty;
    }

    public String getSellerParty() {
        return sellerParty;
    }

    public Double getPremiumAmount() {
        return premiumAmount;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setBuyerParty(String buyerParty) {
        this.buyerParty = buyerParty;
    }

    public void setSellerParty(String sellerParty) {
        this.sellerParty = sellerParty;
    }

    public void setPremiumAmount(Double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }
}