package com.vastechpro.app.service;

import com.vastechpro.app.exception.AppServiceException;

public interface NewsFeedProducerService {
    void fetchRssFeed() throws AppServiceException;
}

