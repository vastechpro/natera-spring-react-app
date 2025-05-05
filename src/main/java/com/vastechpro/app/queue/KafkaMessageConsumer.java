package com.vastechpro.app.queue;

import com.vastechpro.app.exception.AppServiceException;
import com.vastechpro.app.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageConsumer {

    @Autowired
    private NewsService newsConsumerService;

    @KafkaListener(topics = "nyt.rss.articles", groupId = "natera-group-id")
    public void listen(String message) throws AppServiceException {
        log.info("Received message: {}", message);
        newsConsumerService.saveNewsItem(message);
    }
}