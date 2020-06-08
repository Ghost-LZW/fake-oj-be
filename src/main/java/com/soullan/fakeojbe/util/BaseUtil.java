package com.soullan.fakeojbe.util;

import java.util.Collection;

public class BaseUtil {

    public static Boolean isEmpty(final String str) {
        return str == null || str.trim().length() == 0;
    }

    public static Boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static Boolean isEmpty(final Object object) {
        return object == null;
    }
}
