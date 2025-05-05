package com.vastechpro.app.util;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.File;

public class RssFeedTestUtil {
    public static SyndEntry getSyndEntryFromRssFeed() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new File("./src/test/resources/rss_feed.xml")));
        return feed.getEntries().get(0);
    }
}
