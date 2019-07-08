package ru.rbc.kskabort;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import com.google.common.base.Stopwatch;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class BannersTest {
    private static final int NumOfWindows = 2;
    private static final String stand_url = "https://feature-rbcadt-1319-auto-ctr.stands.v10.rbcnews.rbc.ru/";
    private static final String url = "https://rbc.ru";

    private static StopWatch stopwatch = new StopWatch();
    private static ArrayList<WebDriver> drivers = new ArrayList<>(3);

    private static WebDriver chrome_1;
    private static WebDriver chrome_2;
/*    private static WebDriver chrome_3;
    private static WebDriver chrome_4;
    private static WebDriver chrome_5;*/
/*    private static WebDriver firefox_1;
    private static WebDriver firefox_2;*/

    @BeforeSuite (description = "setup", alwaysRun = true)
    public static void setup() throws IOException, AWTException {
        System.setProperty ("webdriver.chrome.driver", "C:/Users/kskabort/Documents/webdrivers/chrome_driver/chromedriver.exe");
        //System.setProperty("webdriver.firefox.marionette","C:/Users/kskabort/Documents/webdrivers/geckodriver-v0.24.0-win64/geckodriver.exe");
        chrome_1 = new ChromeDriver();
        chrome_1.manage().window().maximize();
        TabActions.WindowSwap("left");
        chrome_2 = new ChromeDriver();
        chrome_2.manage().window().maximize();
    }

    @Test
    public static void test1() throws AWTException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            chrome_1.get(url);
            stopwatch.start(); //старт таймера
            setWebDriver(chrome_1);
            $(Banners.FirstRight).waitUntil(visible, 30000);
            System.out.println("Ну чтож нашли мы его, попался родненький!" + "\nВремя: " + stopwatch.getTime() + '\n');
            stopwatch.stop();

            chrome_2.get(url);
            stopwatch.start(); //старт таймера
            setWebDriver(chrome_2);
            $(Banners.FirstRight).waitUntil(visible, 30000);
            System.out.println("Ну чтож нашли мы его, СНОВА, попался родненький!" + "\nВремя: " + stopwatch.getTime() + '\n');
            stopwatch.stop();

            sleep(2000);
            chrome_1.findElement(By.cssSelector(Banners.FirstRight)).click();
            chrome_2.findElement(By.cssSelector(Banners.FirstRight)).click();
            CloseOneRight(chrome_1);
            CloseOneRight(chrome_2);
            sleep(1000);

        }
    }

    @AfterSuite
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
