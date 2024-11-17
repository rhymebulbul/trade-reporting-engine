package com.example.tradereportingengine;

import com.example.tradereportingengine.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DataLoaderTest {

    @Autowired
    private EventRepository eventRepository;

    @Value("${events.directory}")
    private String eventsDirectory;
    @Autowired
    private DataLoader dataLoader;

@BeforeEach
void setUp() {
    eventRepository.deleteAll();
    try {
        dataLoader.run();
    } catch (Exception e) {
        fail("Exception during DataLoader run: " + e.getMessage());
    }
}

    @Test
    void testDataLoader() throws Exception {
        File dataDirectory = new File(eventsDirectory);
        File[] xmlFiles = dataDirectory.listFiles((dir, name) -> name.matches("^event\\d+\\.xml$"));

        assertNotNull(xmlFiles);
        assertTrue(xmlFiles.length > 0);

        // Assuming DataLoader is triggered on application startup
        // Verify that events are loaded into the database
        assertEquals(xmlFiles.length, eventRepository.count());
    }
}