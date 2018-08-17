package com.demo.auth.dto;

import com.demo.util.JsonUtils;
import org.json.JSONObject;

public class AuthResponseDto {

    public String authId;
    public String authType;
    public String subjectId;
    public long timestamp;
    public AuthAttributesDto authAttributes;

    // Defualt constructor required by Jackson
    public AuthResponseDto() {
    }

    public JSONObject buildFintechJson(String url) {
        JSONObject fintechJson = new JSONObject();
        fintechJson.put("url", url);
        JSONObject authJson = new JSONObject(JsonUtils.toJson(this));
        fintechJson.put("auth", authJson);
        return JsonUtils.addFintechEnvelope(fintechJson);
    }


}
