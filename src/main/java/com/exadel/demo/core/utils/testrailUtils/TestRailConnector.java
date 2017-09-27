package com.exadel.demo.core.utils.testrailUtils;

import com.exadel.demo.core.testrail.APIClient;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestRailConnector {

    public static void main(String[] args) throws Exception
    {
        APIClient client = new APIClient("https://dzmikhalchuk.testrail.net");
        client.setUser("dmitry.mikhalchuk@gmail.com");
        client.setPassword("NMZ8GFk0gl1caMLi9GoX");
//        Map data = new HashMap();
//        data.put("status_id", 5);
//        data.put("comment", "This test worked fine!");
//        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/1", data);

//        int[] array = {1, 2};
//        Map data = new HashMap();
//        data.put("suit_id", 1);
//        data.put("name", "Demo Test Run");
//        data.put("case_ids", array);
//        JSONObject r = (JSONObject) client.sendPost("add_run/1", data);

        JSONArray runs = (JSONArray) client.sendGet("get_runs/1");
        JSONObject lastRun = (JSONObject) runs.get(0);
        System.out.println(lastRun.get("id"));

        JSONArray tests = (JSONArray) client.sendGet("get_tests/11");
        JSONObject test1 = (JSONObject)tests.get(0);
        System.out.println(test1.get("id"));

        JSONObject test2 = (JSONObject)tests.get(1);
        System.out.println(test2.get("title"));
        for (int i = 0; i < tests.size(); i++) {
            JSONObject obj = (JSONObject)tests.get(i);
            if (StringUtils.equalsIgnoreCase((String)obj.get("title"), "Verify product elements")) {
                Map data = new HashMap();
                data.put("status_id", 5);
                data.put("comment", "Test failed");
                data.put("defects", "Error");
                JSONObject r = (JSONObject) client.sendPost("add_result/" + obj.get("id"), data);
            }
        }
//        System.out.println(test2);

//        Map data = new HashMap();
//        data.put("status_id", 5);
//        data.put("comment", "This test worked fine!");
//        data.put("defects", "Error");
//        JSONObject r = (JSONObject) client.sendPost("add_result/" + test2.get("id"), data);


    }
}
