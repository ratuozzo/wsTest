package com.bezikee.Common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateOps{

    public static Timestamp convertToMysql(String input){

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dateAux = null;
        Timestamp out = null;
        try {
            dateAux = sdf1.parse(input);
            out = new Timestamp(dateAux.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return out;
    }



}
