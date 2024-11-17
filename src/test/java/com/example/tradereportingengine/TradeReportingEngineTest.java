package com.example.tradereportingengine;

import com.example.tradereportingengine.model.Event;
import com.example.tradereportingengine.repository.EventRepository;
import com.example.tradereportingengine.service.EventService;
import com.example.tradereportingengine.service.ReportingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TradeReportingEngineTest {

    private static final Logger log = LoggerFactory.getLogger(TradeReportingEngineTest.class);
    @Autowired
    private EventService eventService;

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private EventRepository eventRepository;

    @Value("${events.directory}")
    private String eventsDirectory;

    @BeforeEach
    void setUp() {
//        eventRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testParseAndSaveEvents() {
        eventRepository.deleteAll();
        File xmlFile = new File(eventsDirectory, "event0.xml");
        eventService.parseAndSaveEvents(xmlFile);

        List<Event> events = eventRepository.findAll();
        log.info("Events: " + events);
        for (int i = 0; i < events.size(); i++) {
            log.info(String.valueOf(events.get(i)));
        }
        assertEquals(1, events.size());
    }

    @Test
    void testGetFilteredEvents() {
        List<Event> filteredEvents = reportingService.getFilteredEvents();
        log.info("Filtered Events: " + filteredEvents);
        for (int i = 0; i < filteredEvents.size(); i++) {
            log.info(String.valueOf(filteredEvents.get(i)));
        }
        assertEquals(1, filteredEvents.size());

    }
}