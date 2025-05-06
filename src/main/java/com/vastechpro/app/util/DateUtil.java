package com.vastechpro.app.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    /**
     * This method check whether given date is past 24hrs
     * @param date
     * @return
     */
    public static boolean isGreaterThan24Hours(Date date) {
        long timeDifference = System.currentTimeMillis() - date.getTime();
        long twentyFourHoursInMillis = TimeUnit.HOURS.toMillis(24);
        return timeDifference > twentyFourHoursInMillis;
    }
}
