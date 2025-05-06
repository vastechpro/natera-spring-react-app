package com.vastechpro.app.util;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.List;

public class RssFeedUtil {
    /**
     * This method returns the creator string from the RSS item creator module
     * @param item
     * @return
     */
    public static String getCreator(SyndEntry item) {
        DCModule creatorModule = (DCModule) item.getModules().get(0);
        return creatorModule.getCreator();
    }

    /**
     * This method returns the image url from RSS item foreign markup
     * @param item
     * @return
     */
    public static String getImageUrl(SyndEntry item) {
        List<Element> foreignMarkup = item.getForeignMarkup();
        String imageUrl = null;
        for (Element elem: foreignMarkup) {
            if (elem.getName().equals("content")) {
                for (Attribute attr: elem.getAttributes()) {
                    if (attr.getName().equals("url")) {
                        imageUrl = attr.getValue();
                    }
                }
            }
        }
        return imageUrl;
    }
}
