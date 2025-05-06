package com.vastechpro.app.service;

import com.vastechpro.app.exception.AppServiceException;

/**
 * Interface for news feed producer
 */
public interface NewsFeedProducerService {
    void fetchRssFeed() throws AppServiceException;
}

