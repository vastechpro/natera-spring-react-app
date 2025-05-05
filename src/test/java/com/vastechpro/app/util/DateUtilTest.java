package com.vastechpro.app.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateUtilTest {
    @Test
    public void testIsGreaterThan24Hours() {
        Date now = new Date();
        Assert.assertFalse(DateUtil.isGreaterThan24Hours(now));
        Date past3Hours = new Date(now.getTime() - 3 * 60 * 60 * 1000);
        Assert.assertFalse(DateUtil.isGreaterThan24Hours(past3Hours));
        Date past24Hours = new Date(now.getTime() - 25 * 60 * 60 * 1000);
        Assert.assertTrue(DateUtil.isGreaterThan24Hours(past24Hours));
    }
}
