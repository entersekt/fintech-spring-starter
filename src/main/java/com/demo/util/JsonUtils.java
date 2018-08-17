package com.demo.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class JsonUtils {

    private static final String FINTECH = "fintech";

    public static JSONObject addFintechEnvelope(JSONObject jsonValue) {
        JSONObject jsonEnvelope = new JSONObject();
        jsonEnvelope.put(FINTECH, jsonValue);
        return jsonEnvelope;
    }

    public static JSONObject removeFintechEnvelope(JSONObject jsonWithEnvelope) {
        return jsonWithEnvelope.getJSONObject(FINTECH);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object src) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return gson.toJson(src);
    }
}
