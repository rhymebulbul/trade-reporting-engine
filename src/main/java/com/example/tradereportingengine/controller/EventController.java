package com.example.tradereportingengine.controller;

import com.example.tradereportingengine.model.Event;
import com.example.tradereportingengine.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/filtered")
    public List<Event> getFilteredEvents() {
        return reportingService.getFilteredEvents();
    }
}