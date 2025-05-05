package com.vastechpro.app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastechpro.app.exception.AppServiceException;
import com.vastechpro.app.model.RssFeedItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisRepository implements NewsRepository {

    private final String pubdateSortedSetKey = "pubdateSortedSet";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<RssFeedItemDTO> getAllNews(Pageable paging) throws AppServiceException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            long start = paging.getOffset();
            long end = start + paging.getPageSize() - 1;
            Set<Object> ids = redisTemplate.opsForZSet().reverseRange(pubdateSortedSetKey, start, end);
            List<RssFeedItemDTO> result = new ArrayList<>();
            if (ids != null && !ids.isEmpty()) {
                for (Object id : ids) {
                    Object valueJson = redisTemplate.opsForValue().get(id);
                    RssFeedItemDTO item = objectMapper.convertValue(valueJson, RssFeedItemDTO.class);
                    result.add(item);
                }
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppServiceException("Error fetching news items from Redis repository" + e.getMessage());
        }
    }

    @Override
    public void saveNewsItem(RssFeedItemDTO newsItem) throws AppServiceException {
        try {
            redisTemplate.opsForZSet().add(pubdateSortedSetKey, newsItem.getId(), newsItem.getPubDate().getTime());
            redisTemplate.opsForValue().set(newsItem.getId(), newsItem, 24, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppServiceException("Error saving news items to Redis repository" + e.getMessage());
        }
    }

    @Override
    public Boolean checkNewsItem(String id) throws AppServiceException {
        try {
            return redisTemplate.hasKey(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppServiceException("Error checking presence of news items in Redis repository" + e.getMessage());
        }
    }
}
