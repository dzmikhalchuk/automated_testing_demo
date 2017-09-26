package com.exadel.demo.core.utils.testrailUtils;

import com.exadel.demo.core.testrail.APIClient;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestRailConnector {

    public static void main(String[] args) throws Exception
    {
        APIClient client = new APIClient("https://dzmikhalchuk.testrail.net");
        client.setUser("dmitry.mikhalchuk@gmail.com");
        client.setPassword("NMZ8GFk0gl1caMLi9GoX");
        Map data = new HashMap();
        data.put("status_id", 5);
        data.put("comment", "This test worked fine!");
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/1", data);
    }
}
