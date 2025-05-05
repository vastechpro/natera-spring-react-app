package com.vastechpro.app.util;

import com.rometools.rome.feed.synd.SyndEntry;
import org.junit.Assert;
import org.junit.Test;

public class RssFeedUtilTest {
    @Test
    public void testGetCreator() throws Exception {
        SyndEntry entry = RssFeedTestUtil.getSyndEntryFromRssFeed();
        Assert.assertNotNull(entry);
        Assert.assertEquals("Eli Tan and Ulysses Ortega", RssFeedUtil.getCreator(entry));
    }

    @Test
    public void testGetImageUrl() throws Exception {
        SyndEntry entry = RssFeedTestUtil.getSyndEntryFromRssFeed();
        Assert.assertNotNull(entry);
        Assert.assertEquals("https://static01.nyt.com/images/2025/05/04/multimedia/OAKDALE-MEDIA-01-fbjg/OAKDALE-MEDIA-01-fbjg-mediumSquareAt3X.jpg", RssFeedUtil.getImageUrl(entry));
    }
}
