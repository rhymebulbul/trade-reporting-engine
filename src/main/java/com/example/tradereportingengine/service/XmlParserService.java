package com.example.tradereportingengine.service;

import com.example.tradereportingengine.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlParserService {

    private static final Logger logger = LoggerFactory.getLogger(XmlParserService.class);

    @Value("${xmlparser.xpath.expression}")
    private String xpathExpression;

    @Value("${xmlparser.xpath.buyerParty}")
    private String buyerPartyXpath;

    @Value("${xmlparser.xpath.sellerParty}")
    private String sellerPartyXpath;

    @Value("${xmlparser.xpath.premiumAmount}")
    private String premiumAmountXpath;

    @Value("${xmlparser.xpath.premiumCurrency}")
    private String premiumCurrencyXpath;

    public List<Event> parseEvents(File xmlFile) {
        List<Event> events = new ArrayList<>();
        try {
            Document document = parseXmlFile(xmlFile);
            NodeList nodeList = evaluateXPath(document, xpathExpression);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Event event = parseEvent(node);
                if (event != null) {
                    events.add(event);
                    logger.info("Parsed event: {}", event);
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing events", e);
        }
        return events;
    }

    private Document parseXmlFile(File xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlFile);
    }

    private NodeList evaluateXPath(Document document, String expression) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        return (NodeList) xPath.evaluate(expression, document, XPathConstants.NODESET);
    }

    private Event parseEvent(Node node) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();

        String buyerParty = xPath.evaluate(buyerPartyXpath, node);
        String sellerParty = xPath.evaluate(sellerPartyXpath, node);
        String premiumAmountStr = xPath.evaluate(premiumAmountXpath, node);
        String premiumCurrency = xPath.evaluate(premiumCurrencyXpath, node);

        logger.info("Read properties: buyerParty={}, sellerParty={}, premiumAmount={}, premiumCurrency={}",
                buyerParty, sellerParty, premiumAmountStr, premiumCurrency);

        if (premiumAmountStr != null && !premiumAmountStr.isEmpty()) {
            Double premiumAmount = Double.valueOf(premiumAmountStr);

            Event event = new Event();
            event.setBuyerParty(buyerParty);
            event.setSellerParty(sellerParty);
            event.setPremiumAmount(premiumAmount);
            event.setPremiumCurrency(premiumCurrency);

            return event;
        } else {
            logger.warn("Skipped event due to empty premium amount: buyerParty={}, sellerParty={}", buyerParty, sellerParty);
            return null;
        }
    }
}