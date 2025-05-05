package com.vastechpro.app.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static boolean isGreaterThan24Hours(Date date) {
        long timeDifference = System.currentTimeMillis() - date.getTime();
        long twentyFourHoursInMillis = TimeUnit.HOURS.toMillis(24);
        return timeDifference > twentyFourHoursInMillis;
    }
}
