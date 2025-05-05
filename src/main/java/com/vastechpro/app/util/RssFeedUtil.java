package com.vastechpro.app.util;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.List;

public class RssFeedUtil {
    public static String getCreator(SyndEntry item) {
        DCModule creatorModule = (DCModule) item.getModules().get(0);
        return creatorModule.getCreator();
    }

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
