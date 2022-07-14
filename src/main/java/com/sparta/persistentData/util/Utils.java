package com.sparta.persistentData.util;

import java.text.DecimalFormat;

public class Utils {

    public static String formatSalary(double amount) {
        String str = "";
        if (amount < 1E3) {
            return String.valueOf(Math.round(amount));
        } else if (amount < 1E6) {
            amount = amount / 1E3;
            str = "k";
        } else if (amount < 1E9) {
            amount = amount / 1E6;
            str = "M";
        } else if (amount < 1E12) {
            amount = amount / 1E9;
            str = "B";
        } else if (amount < 1E15) {
            amount = amount / 1E12;
            str = "T";
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(amount) + str;
    }


}
