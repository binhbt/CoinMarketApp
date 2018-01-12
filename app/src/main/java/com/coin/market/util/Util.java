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
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(num);
        return formattedNumber;
    }
}
