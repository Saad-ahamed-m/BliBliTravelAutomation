package com.quinbay.bliblitravel.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Selenium {
    public WebDriver driver;
    public Selenium() throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver","/Users/saadahamed/Desktop/Projects/UIAutomation/BlibliTravel/src/test/resources/driver/chromedriver");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.blibli.com/travel");
    }
}

