package com.example.tradereportingengine.service; //// EventServiceIntegrationTest.java
//package com.example.tradereportingengine.service;
//
//import com.example.tradereportingengine.model.Event;
//import com.example.tradereportingengine.repository.EventRepository;
//import org.junit.jupiter.api.BeforeEach;
import com.example.tradereportingengine.model.Event;
import com.example.tradereportingengine.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceIntegrationTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Value("${events.directory}")
    private String eventsDirectory;

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

    @Test
    void testParseAndSaveEvents() {
        File xmlFile = new File(eventsDirectory, "event0.xml");
        eventService.parseAndSaveEvents(xmlFile);

        List<Event> events = eventRepository.findAll();
        assertNotNull(events);
        assertFalse(events.isEmpty());
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("LEFT_BANK", event.getBuyerParty());
        assertEquals("EMU_BANK", event.getSellerParty());
        assertEquals(100.0, event.getPremiumAmount());
        assertEquals("AUD", event.getPremiumCurrency());
    }
}