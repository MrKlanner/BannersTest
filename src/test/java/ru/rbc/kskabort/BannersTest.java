package ru.rbc.kskabort;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import ru.rbc.kskabort.Banners.*;
import ru.rbc.kskabort.TabActions.*;

import java.awt.*;
import java.util.ArrayList;

public class BannersTest {
    private static final int NumOfWindows = 2;
    private static WebDriver chrome_1;
    private static WebDriver chrome_2;
/*    private static WebDriver chrome_3;
    private static WebDriver chrome_4;
    private static WebDriver chrome_5;*/
/*    private static WebDriver firefox_1;
    private static WebDriver firefox_2;*/

    @BeforeSuite (description = "setup", alwaysRun = true)
    public static void setup() {
        System.setProperty ("webdriver.chrome.driver", "C:/Users/kskabort/Documents/webdrivers/chrome_driver/chromedriver.exe");
        System.setProperty("webdriver.firefox.marionette","C:/Users/kskabort/Documents/webdrivers/geckodriver-v0.24.0-win64/geckodriver.exe");
        chrome_1 = new ChromeDriver();
        chrome_2 = new ChromeDriver();
/*        chrome_3 = new ChromeDriver();
        chrome_4 = new ChromeDriver();
        chrome_5 = new ChromeDriver();*/
/*        firefox_1 = new FirefoxDriver();*/
        chrome_1.manage().window().maximize();
        chrome_2.manage().window().maximize();
/*        chrome_3.manage().window().maximize();
        chrome_4.manage().window().maximize();
        chrome_5.manage().window().maximize();*/
/*        firefox_1.manage().window().maximize();*/
/*        firefox_1.get("");
        firefox_2.get("");*/
        /*Configuration.browser = CHROME;
        Configuration.reopenBrowserOnFail = true;
        Configuration.startMaximized = true;*/
    }

    @Test
    public static void test1() throws AWTException, InterruptedException {
        String url = "https://rbc.ru";
        for (int i=0; i<100; i++) {
            chrome_1.get(url);
            chrome_2.get(url);
/*            chrome_3.get(url);
            chrome_4.get(url);
            chrome_5.get(url);*/
            sleep(2000);
/*            firefox_1.get(url);
            firefox_2.get(url);*/
            if (i<50){
                chrome_1.findElement(By.cssSelector(Banners.Billboard)).click();
                chrome_2.findElement(By.cssSelector(Banners.Billboard)).click();
/*                chrome_3.findElement(By.cssSelector(Banners.Billboard)).click();
                chrome_4.findElement(By.cssSelector(Banners.Billboard)).click();
                chrome_5.findElement(By.cssSelector(Banners.Billboard)).click();
                firefox_1.findElement(By.cssSelector(Banners.Billboard)).click();
                firefox_2.findElement(By.cssSelector(Banners.Billboard)).click();*/
                CloseOneRight(chrome_1);
                CloseOneRight(chrome_2);
            }
            sleep(1000);
/*            CloseOneRight(chrome_3);
            CloseOneRight(chrome_4);
            CloseOneRight(chrome_5);*/
        }
    }

    private static void CloseOneRight(WebDriver driver) throws AWTException, InterruptedException
    {
        TabActions.SwitchToLast(NumOfWindows);
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles()); // Получение списка вкладок
        if (tab.size() > 1) {
            driver.switchTo().window(tab.get(1));
            TabActions.Close();
            driver.switchTo().window(tab.get(0));
            tab.remove(1);
        }
    }

}
