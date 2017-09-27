package com.exadel.demo.core.utils.testrailUtils;

import com.exadel.demo.core.testrail.APIClient;
import com.exadel.demo.core.testrail.APIException;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof TestRailApiIds) {
            int status = 5;
            String caseId = ((TestRailApiIds) currentClass).getCase();
            String message = ((TestRailApiIds) currentClass).getMessage();
            String runId = ((TestRailApiIds) currentClass).getTetRunId();
            APIClient client = new APIClient("https://dzmikhalchuk.testrail.net");
            client.setUser("dmitry.mikhalchuk@gmail.com");
            client.setPassword("NMZ8GFk0gl1caMLi9GoX");
            String replaceMsg = "java.lang.AssertionError: ";

            JSONArray tests = null;
            try {
                tests = (JSONArray) client.sendGet("get_tests/" + runId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (APIException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < tests.size(); i++) {
                JSONObject obj = (JSONObject)tests.get(i);
                if (StringUtils.equalsIgnoreCase(obj.get("title").toString(), ((TestRailApiIds) currentClass).getTestName())) {
                    Map data = new HashMap();
                    data.put("status_id", 5);
                    data.put("comment", "Environment: " + message );
                    data.put("defects", result.getThrowable().toString().replaceAll(replaceMsg, ""));
                    try {
                        JSONObject r = (JSONObject) client.sendPost("add_result/" + obj.get("id"), data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (APIException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof TestRailApiIds) {
            int status = 1;
            String caseId = ((TestRailApiIds) currentClass).getCase();
            String message = ((TestRailApiIds) currentClass).getMessage();
            APIClient client = new APIClient("https://dzmikhalchuk.testrail.net");
            client.setUser("dmitry.mikhalchuk@gmail.com");
            client.setPassword("NMZ8GFk0gl1caMLi9GoX");

            JSONArray tests = null;
            try {
                tests = (JSONArray) client.sendGet("get_tests/" + ((TestRailApiIds) currentClass).getTetRunId());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (APIException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < tests.size(); i++) {
                JSONObject obj = (JSONObject)tests.get(i);
                if (StringUtils.equalsIgnoreCase(obj.get("title").toString(), ((TestRailApiIds) currentClass).getTestName())) {
                    Map data = new HashMap();
                    data.put("status_id", 1);
                    data.put("comment", "Environment: " + message );
                    try {
                        JSONObject r = (JSONObject) client.sendPost("add_result/" + obj.get("id"), data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (APIException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // do what you want to do
    }
}
