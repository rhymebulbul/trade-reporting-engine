package com.example.tradereportingengine.service.specifications;

import com.example.tradereportingengine.model.Event;

public class SellerAndCurrencySpecification implements Specification<Event> {
    @Override
    public boolean isSatisfiedBy(Event event) {
        return ("EMU_BANK".equals(event.getSellerParty()) && "AUD".equals(event.getPremiumCurrency())) ||
               ("BISON_BANK".equals(event.getSellerParty()) && "USD".equals(event.getPremiumCurrency()));
    }
}