package com.example.tradereportingengine.service;

import com.example.tradereportingengine.model.Event;
import com.example.tradereportingengine.repository.EventRepository;
import com.example.tradereportingengine.service.specifications.AndSpecification;
import com.example.tradereportingengine.service.specifications.NotAnagramSpecification;
import com.example.tradereportingengine.service.specifications.SellerAndCurrencySpecification;
import com.example.tradereportingengine.service.specifications.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getFilteredEvents() {
        List<Event> events = eventRepository.findAll();

        Specification<Event> sellerAndCurrencySpec = new SellerAndCurrencySpecification();
        Specification<Event> notAnagramSpec = new NotAnagramSpecification();
        Specification<Event> combinedSpec = new AndSpecification<>(sellerAndCurrencySpec, notAnagramSpec);

        return events.stream()
                .filter(combinedSpec::isSatisfiedBy)
                .collect(Collectors.toList());
    }
}