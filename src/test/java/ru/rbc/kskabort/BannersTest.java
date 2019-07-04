package ru.rbc.kskabort;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import ru.rbc.kskabort.Banners.*;

import java.util.ArrayList;

public class BannersTest {

    @BeforeSuite (description = "setup", alwaysRun = true)
    public static void setup() {
        System.setProperty ("webdriver.chrome.driver", "C:/Users/kskabort/Documents/webdrivers/chrome_driver/chromedriver.exe");
        System.setProperty("webdriver.firefox.marionette","C:/Users/kskabort/Documents/webdrivers/geckodriver-v0.24.0-win64/geckodriver.exe");
        Configuration.browser = CHROME;
        Configuration.reopenBrowserOnFail = true;
        Configuration.startMaximized = true;

    }

    @Test
    public static void test1() {
        for (int i=0; i<100; i++) {
            open("https://rbc.ru");
            sleep(1000);
            if (i<50)
                $(Banners.Billboard).click();
            sleep(1000);
        }
    }
}
