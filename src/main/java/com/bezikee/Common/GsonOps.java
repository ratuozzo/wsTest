package com.bezikee.Common;

import com.bezikee.DataAccessLayer.User.UserBean;
import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;


public class GsonOps {



    public static String toJson(Object input) {
        Gson gson = new Gson();
        return gson.toJson(input);
    }

    public static <T> T fromJson(Reader reader, Type typeOf ) {
        Gson gson = new Gson();
        return gson.fromJson(reader,typeOf);
    }
}
