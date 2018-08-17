package com.demo.auth.dto;

import com.demo.util.JsonUtils;
import org.json.JSONObject;

public class AuthRequestDto {

    public String subjectId;
    public AuthAttributesDto attributes;

    // Defualt constructor required by Jackson
    public AuthRequestDto() {
    }

    public JSONObject buildFintechJson(String url) {
        JSONObject fintechJson = new JSONObject();
        fintechJson.put("url", url);
        JSONObject authJson = new JSONObject(JsonUtils.toJson(this));
        fintechJson.put("auth", authJson);
        return JsonUtils.addFintechEnvelope(fintechJson);
    }
}
