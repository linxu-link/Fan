package com.link.fan.utils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * copyright:TS
 * author:wujia
 * create:2019-10-21-14:42
 * email:wujia0916@thundersoft.com
 * description:
 */
public class JsonUtil {

    private static Gson sGson = new Gson();

    /**
     * change object to json.
     *
     * @param object object
     * @return json
     */
    public static String toJson(Object object) {
        try {
            return sGson.toJson(object);
        } catch (JsonSyntaxException | JsonIOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * change json to object.
     *
     * @param json  json
     * @param clazz object type
     * @param <T>   generic
     * @return object of json
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return sGson.fromJson(json, clazz);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * change json to object.
     *
     * @param json json
     * @param type object type
     * @param <T>  generic
     * @return object of json
     */
    public static <T> T fromJson(String json, Type type) {
        try {
            return sGson.fromJson(json, type);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
