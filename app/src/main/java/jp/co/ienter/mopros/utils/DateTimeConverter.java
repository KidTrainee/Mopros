package jp.co.ienter.mopros.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeConverter {

    private String convertDateFormat(String origFormat, String resultFormat, String date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat (origFormat, Locale.JAPANESE);
        String result = "";
        try {
            Date d = sdf1.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat (resultFormat, Locale.JAPANESE);
            result = sdf2.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String convertHaisoDate(String normalDate) {
        return convertDateFormat("yyyy/MM/dd", "MM月dd日(EE)", normalDate);
    }
}
