package com.demo.tdata;

import com.demo.util.JsonUtils;
import com.google.gson.Gson;
import org.json.JSONObject;

public class TDataDto {

    public String emcert;
    public String payload;

    // Empty constructor required for Jackson
    TDataDto() {
    }

    public JSONObject buildFintechJson() {
        Gson gson = new Gson();
        JSONObject tData = new JSONObject(gson.toJson(this));
        return JsonUtils.addFintechEnvelope(tData);
    }

}
