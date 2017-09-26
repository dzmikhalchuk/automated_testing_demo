package com.exadel.demo.core.utils.testrailUtils;

import com.exadel.demo.core.testrail.APIClient;
import com.exadel.demo.core.testrail.APIException;
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
            APIClient client = new APIClient("https://dzmikhalchuk.testrail.net");
            client.setUser("dmitry.mikhalchuk@gmail.com");
            client.setPassword("NMZ8GFk0gl1caMLi9GoX");
            String replaceMsg = "java.lang.AssertionError: ";
            Map data = new HashMap();
            data.put("status_id", status);
            data.put("comment", "Environment: " + message + ". Test Failed." + "Error message: " + result.getThrowable().toString().replaceAll(replaceMsg, ""));
            try {
                JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/" + caseId, data);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (APIException e) {
                e.printStackTrace();
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
            Map data = new HashMap();
            data.put("status_id", status);
            data.put("comment", "Environment: " + message + ". Test Passed");
            try {
                JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/" + caseId, data);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (APIException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // do what you want to do
    }
}
