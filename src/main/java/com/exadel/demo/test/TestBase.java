package com.exadel.demo.test;

import com.exadel.demo.core.testrail.APIClient;
import com.exadel.demo.core.testrail.APIException;
import com.exadel.demo.core.utils.CustomTestListener;
import com.exadel.demo.core.utils.DriverFactory;
import com.exadel.demo.core.utils.PropertiesLoader;
import com.exadel.demo.core.utils.testrailUtils.TestRailListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Listeners({CustomTestListener.class, TestRailListener.class})
public class TestBase {

    protected RemoteWebDriver driver;
    public final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    protected PropertiesLoader propertiesLoader = new PropertiesLoader();
    protected Properties env;
    protected String browser;
    protected String url;
    protected String testRunId;

    @Parameters({"browser"})
    @BeforeSuite(alwaysRun = true)
    public void setEnvironment(@Optional String browserName, ITestContext context) {
        env = new Properties();
        env.setProperty("Base URL", propertiesLoader.getBasePage());
        env.setProperty("Product name", propertiesLoader.getProductName());

        XmlTest test = context.getCurrentXmlTest();
        test.setName(test.getName() + ": " + browserName);
        browser = browserName;
    }

    @BeforeClass(alwaysRun = true)
    public void testRunInit() throws IOException, APIException {

        APIClient client = new APIClient("https://dzmikhalchuk.testrail.net");
        client.setUser("dmitry.mikhalchuk@gmail.com");
        client.setPassword("NMZ8GFk0gl1caMLi9GoX");

        Map data = new HashMap();
        data.put("suit_id", 1);
        data.put("name", "Demo Test Run - " + browser.toUpperCase());
        data.put("include_all", true);
        JSONObject testRun = (JSONObject) client.sendPost("add_run/1", data);

        JSONArray runs = (JSONArray) client.sendGet("get_runs/1");
        JSONObject lastRun = (JSONObject) runs.get(0);
        testRunId = (String) lastRun.get("id");

    }

    @BeforeMethod
    @Parameters({"browser"})
    public void init(@Optional String browser) throws MalformedURLException {
        logger.info("Driver initialisation");
        driver = new DriverFactory().getDriver(browser);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanup() {
        logger.info("Close driver");
        driver.close();
    }

    @AfterSuite(alwaysRun = true)
    public void saveEnvironment() {
        File file = Paths.get(System.getProperty("user.dir"), "/target/allure-results").toAbsolutePath().toFile();
        if (!file.exists()) {
            logger.info("Created dirs: " + file.mkdirs());
        }
        try (FileWriter out = new FileWriter("./target/allure-results/environment.properties")) {
            env.store(out, "Environment variables for report");
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    //text/plain, text/html, text/css, text/javascript
    @Attachment(value = "JSON schema", type = "text/plain")
    public byte[] jsonSchemaToCheck(String jsonFilename) throws IOException {
        //init array with file length
        File myJsonFile = new File(Paths.get("").toAbsolutePath().toString() +
                File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + jsonFilename);
        byte[] bytesArray = new byte[(int) myJsonFile.length()];
        FileInputStream fis = new FileInputStream(myJsonFile);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();

        return bytesArray;
    }
}
