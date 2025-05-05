package com.vastechpro.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastechpro.app.exception.AppServiceException;
import com.vastechpro.app.model.RssFeedItemDTO;
import com.vastechpro.app.repository.NewsRepository;
import com.vastechpro.app.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void saveNewsItem(String newsItem) throws AppServiceException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RssFeedItemDTO rssFeedItem = objectMapper.readValue(newsItem, RssFeedItemDTO.class);
            if (rssFeedItem != null) {
                String id = rssFeedItem.getId();
                if (!newsRepository.checkNewsItem(id) && !DateUtil.isGreaterThan24Hours(rssFeedItem.getPubDate())) {
                    newsRepository.saveNewsItem(rssFeedItem);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppServiceException("Error reading RSS feed and saving" + e.getMessage());
        }
    }

    @Override
    public List<RssFeedItemDTO> getAllNews(Pageable paging) throws AppServiceException {
        return newsRepository.getAllNews(paging);
    }
}
