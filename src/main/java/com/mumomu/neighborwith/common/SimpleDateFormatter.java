package com.mumomu.neighborwith.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SimpleDateFormatter {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String formatDateToString(Date date){
        return formatter.format(date);
    }
}
