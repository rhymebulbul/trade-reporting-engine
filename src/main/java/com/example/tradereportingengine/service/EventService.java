package com.example.tradereportingengine.service;

import com.example.tradereportingengine.model.Event;
import com.example.tradereportingengine.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private XmlParserService xmlParserService;

    @Transactional
    public void parseAndSaveEvents(File xmlFile) {
        try {
            List<Event> events = xmlParserService.parseEvents(xmlFile);
            if (!events.isEmpty()) {
                eventRepository.saveAll(events);
                logger.info("Saved {} events to the database", events.size());
            } else {
                logger.warn("No events to save to the database");
            }
        } catch (Exception e) {
            logger.error("Error parsing and saving events", e);
        }
    }
}