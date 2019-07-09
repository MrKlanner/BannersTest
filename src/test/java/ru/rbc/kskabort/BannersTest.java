package ru.rbc.kskabort;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import org.openqa.selenium.WebDriver;

import java.awt.*;
//import java.io.IOException;
import java.util.ArrayList;

public class BannersTest {

    private static WebDriver chrome_1;
    private static WebDriver chrome_2;
    private static final int NumOfWindows = 2;
    //private static final String stand_url = "https://feature-rbcadt-1319-auto-ctr.stands.v10.rbcnews.rbc.ru/";
    private static final String url = "https://rbc.ru";


    /*    private static StopWatch stopwatch = new StopWatch();
    private static ArrayList<WebDriver> drivers = new ArrayList<>(3);
    Creating the JavascriptExecutor interface object by Type casting*/

    @BeforeSuite (description = "setup", alwaysRun = true)
    public static void setup() throws InterruptedException, AWTException {
        System.setProperty ("webdriver.chrome.driver", "C:/Users/kskabort/Documents/webdrivers/chrome_driver/chromedriver.exe");
        //System.setProperty("webdriver.firefox.marionette","C:/Users/kskabort/Documents/webdrivers/geckodriver-v0.24.0-win64/geckodriver.exe");
        chrome_1 = new ChromeDriver();
        chrome_1.manage().window().maximize();
        TabActions.WindowSwap("left");
        chrome_2 = new ChromeDriver();
        chrome_2.manage().window().maximize();
    }

    @Test
    public static void Desktop() throws AWTException, InterruptedException {
/*        JavascriptExecutor js1 = (JavascriptExecutor)chrome_1;
        JavascriptExecutor js2 = (JavascriptExecutor)chrome_1;*/
        int num_of_tests = 300;
        for (int i = 0; i < num_of_tests /2; i++) {
            
            chrome_1.get(url);
            TabActions.PressEscape(chrome_1);
            /*js1.executeScript("testcafe \"chrome:d:\\chrome_portable\\chrome.exe:emulation:device=iphone X\" tests/test.js");*/
            setWebDriver(chrome_1);
            $(Banners.FirstRightFrame).waitUntil(visible, 30000);
            try {Assert.assertTrue($(Banners.FirstRightFrame).isDisplayed());}
            catch (AssertionError c)
            {
                System.out.println(c.toString());
            }

            chrome_2.get(url);
            setWebDriver(chrome_2);
            $(Banners.FirstRightFrame).waitUntil(visible, 30000);
            try {Assert.assertTrue($(Banners.FirstRightFrame).isDisplayed());}
            catch (AssertionError c)
                {System.out.print(c.toString() + '\n');}

            //Отлов не кликабельности элемента
            try {chrome_1.findElement(By.cssSelector(Banners.FirstRightFrame)).click();
                chrome_2.findElement(By.cssSelector(Banners.FirstRightFrame)).click();}
            catch (Exception c1) {
                $(Banners.BillboardFrame).waitUntil(hidden, 10000);
                System.out.print(c1.toString() + '\n');
            }
            CloseOneRight(chrome_1);
            CloseOneRight(chrome_2);
            sleep(1000);

        }
    }

    @AfterTest
    public static void end() {
        chrome_1.quit();
        chrome_2.quit();
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

    /*protected static void networkThrotting(WebDriver driver) throws IOException {
        Map map = new HashMap();
        map.put("offline", false);
        map.put("latency", 5);
        map.put("download_throughput", 500);
        map.put("upload_throughput", 1024);
        CommandExecutor executor = ((ChromeDriver)driver).getCommandExecutor();
        Response response = (Response) executor.execute(
                new Command(((ChromeDriver)driver).getSessionId(),    "setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map)))
        );
    }*/

}
