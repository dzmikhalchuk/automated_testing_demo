package com.exadel.demo.test;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

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


    @Test
    public void jsonValidationTest() throws Exception {

        String myRes = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"name\": \"igor\",\n" +
                "      \"password\": \"zazhigai\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"vasya\",\n" +
                "      \"password\": \"pupkin\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": 123,\n" +
                "  \"success\": true\n" +
                "}";
//        JsonNode SCHEMA_URI = JsonLoader.fromString(Files.readFile(new File("target/classes/test_schema.json")));
//        JsonNode good = JsonLoader.fromString(Files.readFile(new File("target/classes/test.json")));
//        JsonNode bad = JsonLoader.fromString(Files.readFile(new File("target/classes/test_broken.json")));
//        JsonNode bad2 = JsonLoader.fromString(Files.readFile(new File("target/classes/test_broken2.json")));
//        JsonNode bad3 = JsonLoader.fromString(Files.readFile(new File("target/classes/test_broken3.json")));
//        JsonNode bad4 = JsonLoader.fromString(Files.readFile(new File("target/classes/test_broken4.json")));
//
//        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//
//        JsonSchema schema = factory.getJsonSchema(SCHEMA_URI);
//
//        ProcessingReport report;
//
//        SoftAssert assertion = new SoftAssert();
//
//        report = schema.validate(good);
//        System.out.println(report);
//        Assert.assertTrue(report.isSuccess());
//
//        report = schema.validate(bad);
//        System.out.println(report);
//        report = schema.validate(bad2);
//        System.out.println(report);
//        Assert.assertTrue(report.isSuccess());
//
//        report = schema.validate(bad3);
//        System.out.println(report);
//        Assert.assertTrue(report.isSuccess());
//
//        report = schema.validate(bad4);
//        System.out.println(report);
//        Assert.assertTrue(report.isSuccess());
//
//        assertion.assertAll();

        verifyJsonSchema(myRes, "test_schema2.json");
    }

    @Step("checking compliance of response with JSON schema")
    private void verifyJsonSchema(String responseAsString, String jsonFilename) throws IOException {

        jsonSchemaToCheck(jsonFilename);
        assertThat(responseAsString, matchesJsonSchemaInClasspath(jsonFilename));
    }






}
