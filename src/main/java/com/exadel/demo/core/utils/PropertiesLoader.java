package com.exadel.demo.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader  {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String PROP_FILE_CONFIG = "config.properties";

    protected String userEmail;
    protected String userPassword;
    protected String basePage;
    protected String productName;
    protected String productCategory;

    public PropertiesLoader() {
        try {
            dataPropertiesLoader();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    private void dataPropertiesLoader() throws IOException {
        Properties prop = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_CONFIG);
        if (inputStream == null) {
            throw new FileNotFoundException("Property file '" + PROP_FILE_CONFIG + "' not found");
        }

        prop.load(inputStream);

        userEmail = prop.getProperty("user.email");
        userPassword = prop.getProperty("user.password");
        basePage = System.getProperty("URL", prop.getProperty("base.page"));
        productName = System.getProperty("productName", prop.getProperty("product.name"));
        productCategory = System.getProperty("productCategory", prop.getProperty("product.category"));

        inputStream.close();
    }

    public String getUserEmail() { return userEmail; }

    public String getUserPassword() { return userPassword; }

    public String getBasePage() { return basePage; }

    public String getProductName() { return productName; }

    public String getProductCategory() { return productCategory; }
}
