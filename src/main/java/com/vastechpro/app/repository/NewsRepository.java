package com.vastechpro.app.repository;

import com.vastechpro.app.exception.AppServiceException;
import com.vastechpro.app.model.RssFeedItemDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsRepository {
    List<RssFeedItemDTO> getAllNews(Pageable paging) throws AppServiceException;

    void saveNewsItem(RssFeedItemDTO newsItem) throws AppServiceException;

    Boolean checkNewsItem(String id) throws AppServiceException;
}
