package com.example.tradereportingengine;

import com.example.tradereportingengine.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private EventService eventService;

    @Value("${events.directory}")
    private String eventsDirectory;

    @Override
    public void run(String... args) throws Exception {
        File dataDirectory = new File(eventsDirectory);
        File[] xmlFiles = dataDirectory.listFiles((dir, name) -> name.matches("^event\\d+\\.xml$"));

        if (xmlFiles != null) {
            Arrays.stream(xmlFiles).parallel().forEach(xmlFile -> {
                log.info("Parsing and saving events from file: " + xmlFile.getName());
                eventService.parseAndSaveEvents(xmlFile);
            });
        } else {
            log.warn("No XML files found in the events directory.");
        }
    }
}