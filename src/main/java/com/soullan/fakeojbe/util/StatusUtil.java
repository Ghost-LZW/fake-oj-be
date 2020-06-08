package com.soullan.fakeojbe.util;

public class StatusUtil extends BaseUtil {
    public static String getRealStatus(Integer aim) {
        switch (aim) {
            case 0: return "WA";
            case 1: return "AC";
            default: return "UnKnow Error";
        }
    }
}
