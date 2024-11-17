package com.example.tradereportingengine;

import com.example.tradereportingengine.controller.EventController;
import com.example.tradereportingengine.model.Event;
import com.example.tradereportingengine.service.ReportingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportingService reportingService;

    @Test
    public void testGetFilteredEvents() throws Exception {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setBuyerParty("Buyer");
        event.setSellerParty("Seller");
        event.setPremiumAmount(100.0);
        event.setPremiumCurrency("USD");

        when(reportingService.getFilteredEvents()).thenReturn(Collections.singletonList(event));

        mockMvc.perform(get("/api/v1/events/filtered"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'" + event.getId() + "','buyerParty':'Buyer','sellerParty':'Seller','premiumAmount':100.0,'premiumCurrency':'USD'}]"));
    }
}