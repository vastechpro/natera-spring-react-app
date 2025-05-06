package com.vastechpro.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.io.XmlReader;
import com.vastechpro.app.exception.AppServiceException;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.vastechpro.app.model.RssFeedItemDTO;
import com.vastechpro.app.queue.KafkaMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * RSS implementation of NewsFeedProducerService
 */
@Slf4j
@Service
public class NewsFeedProducerServiceImpl implements NewsFeedProducerService {

    @Value("${rss.feed.url}")
    private String rssFeedUrl;

    @Autowired
    private KafkaMessageProducer messageProducer;

    @Value("${rss.feed.queue.topic}")
    private String kafkaTopic;

    // cron expression is injected from properties
    @Override
    @Scheduled(cron = "${rss.feed.schedule}")
    public void fetchRssFeed() throws AppServiceException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(rssFeedUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                String rssFeed = response.getBody();
                SyndFeedInput input = new SyndFeedInput();
                assert rssFeed != null;
                SyndFeed feed = input.build(new XmlReader(new ByteArrayInputStream(rssFeed.getBytes(StandardCharsets.ISO_8859_1))));
                publishFeed(feed);
            } else {
                log.error("Failed to retrieve RSS feed. Status code: {}", response.getStatusCode());
                throw new AppServiceException("Failed to retrieve RSS feed. Status code:" + response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Error fetching RSS feed", e);
            throw new AppServiceException("Error fetching RSS feed: " + e.getMessage());
        }
    }

    private void publishFeed(SyndFeed feed) throws JsonProcessingException {
        List<String> items = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (SyndEntry entry : feed.getEntries()) {
            String itemJson = mapper.writeValueAsString(RssFeedItemDTO.from(entry));
            items.add(itemJson);
        }
        messageProducer.sendMessages(kafkaTopic, items);
    }
}