package com.example.tradereportingengine.service.specifications;

import com.example.tradereportingengine.model.Event;

public class NotAnagramSpecification implements Specification<Event> {
    private final AnagramChecker anagramChecker;

    public NotAnagramSpecification() {
        this.anagramChecker = new AnagramChecker();
    }

    @Override
    public boolean isSatisfiedBy(Event event) {
        return !anagramChecker.areAnagrams(event.getBuyerParty(), event.getSellerParty());
    }
}