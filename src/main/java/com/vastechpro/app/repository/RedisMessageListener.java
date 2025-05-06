package com.vastechpro.app.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * This method is called to handle key expiration from Redis cache
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        log.info("removing expired key {} from sorted set", expiredKey);
        try {
            redisTemplate.opsForZSet().remove(RedisRepository.pubdateSortedSetKey, expiredKey);
        } catch (Exception e) {
            log.error("Failed to remove expired key {} from sorted set", expiredKey, e);
        }
    }
}
