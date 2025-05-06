package com.vastechpro.app.model;

import com.rometools.rome.feed.synd.SyndEntry;
import com.vastechpro.app.util.RssFeedUtil;
import lombok.*;

import java.util.Date;

/**
 * Data transfer object for news items
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RssFeedItemDTO {

    private String id;
    private String title;
    private String link;
    private String description;
    private String creator;
    private Date pubDate;
    private String imageUrl;

    /**
     * This method builds a data transfer object from RSS feed item
     * @param item
     * @return
     */
    public static RssFeedItemDTO from(SyndEntry item) {
        return RssFeedItemDTO.builder()
                .id(item.getTitle())
                .title(item.getTitle())
                .link(item.getLink())
                .description(item.getDescription().getValue())
                .creator(RssFeedUtil.getCreator(item))
                .pubDate(item.getPublishedDate())
                .imageUrl(RssFeedUtil.getImageUrl(item))
                .build();
    }
}
