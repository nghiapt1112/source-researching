package com.demo.Icommerce.infrastructure.util;

import java.util.Date;

public final class DateTimeUtils {
    public static long nowAsMillis() {
        return new Date().getTime();
    }
}
