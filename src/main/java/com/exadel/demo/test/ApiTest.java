package com.exadel.demo.test;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class ApiTest {

    //text/plain, text/html, text/css, text/javascript
    @Attachment(value = "JSON schema", type = "text/plain")
    public byte[] jsonSchemaToCheck(String jsonFilename) throws IOException {
        //init array with file length
        File myJsonFile = new File(Paths.get("").toAbsolutePath().toString() +
                File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + jsonFilename);
        byte[] bytesArray = new byte[(int) myJsonFile.length()];
        FileInputStream fis = new FileInputStream(myJsonFile);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();

        return bytesArray;
    }

    @Features("API")
    @Stories("RESPONSE")
    @Title("Verify response")
    @Test
    public void jsonValidationTest() throws Exception {

        String myRes = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"name\": \"igor\",\n" +
                "      \"password\": \"passd\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"vasya\",\n" +
                "      \"password\": \"wrongpass\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": 123,\n" +
                "  \"success\": true\n" +
                "}";

        verifyJsonSchema(myRes, "test_schema2.json");
    }

    @Step("checking compliance of response with JSON schema")
    private void verifyJsonSchema(String responseAsString, String jsonFilename) throws IOException {

        jsonSchemaToCheck(jsonFilename);
        assertThat(responseAsString, matchesJsonSchemaInClasspath(jsonFilename));
    }
}
