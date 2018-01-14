package com.coin.market.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by t430 on 1/11/2018.
 */

public class Util {
    public static String buildThumb(String id){
        return "https://files.coinmarketcap.com/static/img/coins/64x64/"+id+".png";
    }
    public static String getCurrentcyFormat(double num){
        if (num <1000){
/*            if (num>=1){
                String result= String.format("%.8f", num);
                result = result.endsWith("0") ? result.substring(0, result.length() - 1) : result;
                return result;
            }*/
            return String.format("%.8f", num);
        }
        NumberFormat formatter = new DecimalFormat("#,###.00");
        String formattedNumber = formatter.format(num);
        return formattedNumber;
    }
    public static String getChangeString(double change){
        if (change >0) return "+"+String.format("%.2f", change)+"%";
        return String.format("%.2f", change)+"%";
    }
    public static String formatDouble(double change){
        return String.format("%.2f", change);
    }
    public static String getNumberFormat(double num){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(num);
        return formattedNumber;
    }

}
