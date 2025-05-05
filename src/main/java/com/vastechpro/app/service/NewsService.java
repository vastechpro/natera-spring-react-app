package com.vastechpro.app.service;

import com.vastechpro.app.exception.AppServiceException;
import com.vastechpro.app.model.RssFeedItemDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    void saveNewsItem(String newsItem) throws AppServiceException;

    List<RssFeedItemDTO> getAllNews(Pageable paging) throws AppServiceException;
}
