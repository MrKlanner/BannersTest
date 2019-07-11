package ru.rbc.kskabort;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

//import org.openqa.selenium.remote.DesiredCapabilities;

public class BannersTest {

    public static int num_of_tests = 100; //количество тестов которых нужно выполнить
    public static int tests_num = 0; //количестов пройденных тестов
    public static int i = 0;
    private static WebDriver chrome_1;
    //private static WebDriver chrome_2;
    private static final int NumOfWindows = 1;
    //private static final String stand_url = "https://feature-rbcadt-1319-auto-ctr.stands.v10.rbcnews.rbc.ru/";
    private static final String url = "https://rbc.ru";
    private static String LocationOfDriver(String WHERE)
    {
        switch (WHERE) {
            case "HOME":
                return "D:/Users/Kosta/Documents/chrome_driver/chromedriver.exe";
            case "JOB_MY_PC":
                return "C:/Users/kskabort/Documents/webdrivers/chrome_driver/chromedriver.exe";
            case "JOB_TV":
                return "C:/Users/pvcs/Documents/webdrivers/chrome_driver/chromedriver.exe";
            default:
                return "WAT DO YO WHANT FOR ME?!";
        }
    }


    /*    private static StopWatch stopwatch = new StopWatch();
    private static ArrayList<WebDriver> drivers = new ArrayList<>(3);
    Creating the JavascriptExecutor interface object by Type casting*/

    @BeforeSuite (description = "setup", alwaysRun = true)
    public static void setup() /*throws InterruptedException, AWTException*/ {
        System.setProperty ("webdriver.chrome.driver", LocationOfDriver("HOME"));
        //System.setProperty("webdriver.firefox.marionette","C:/Users/kskabort/Documents/webdrivers/geckodriver-v0.24.0-win64/geckodriver.exe");
        chrome_1 = new ChromeDriver();
        chrome_1.manage().window().maximize();
        chrome_1.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
/*        //TabActions.WindowSwap("left");
        chrome_2 = new ChromeDriver();
        chrome_2.manage().window().maximize();*/
    }

    @Test (skipFailedInvocations=true, retryAnalyzer = RetryAnalyzer.class)
    public static void Desktop() throws AWTException, InterruptedException {
/*        JavascriptExecutor js1 = (JavascriptExecutor)chrome_1;
        JavascriptExecutor js2 = (JavascriptExecutor)chrome_1;*/
        for (i = 0; i < num_of_tests/2; i++) {
            chrome_1.get(url);
            setWebDriver(chrome_1);
            $(Banners.FirstRightFrame).waitUntil(visible, (long)3000);
            ((JavascriptExecutor)chrome_1).executeScript("arguments[0].scrollIntoView();",chrome_1.findElement(By.cssSelector(Banners.FirstRight)));
            try {Assert.assertTrue($(Banners.FirstRightFrame).isDisplayed());}
            catch (AssertionError c)
            {throw new Error(ConsoleColors.RED +"Элемент не отображается на странице:\n"+ ConsoleColors.RESET + c.toString() + "\n\n");}

/*            chrome_2.get(url);
            setWebDriver(chrome_2);
            $(Banners.FirstRightFrame).waitUntil(visible, 30000);
            try {Assert.assertTrue($(Banners.FirstRightFrame).isDisplayed());}
            catch (AssertionError c)
                {System.out.print(c.toString() + '\n');}*/

            //Отлов не кликабельности элемента
            try {chrome_1.findElement(By.cssSelector(Banners.FirstRightFrame)).click();
                /*chrome_2.findElement(By.cssSelector(Banners.FirstRightFrame)).click();*/}
            catch (Exception c1) {
                $(Banners.BillboardFrame).waitUntil(hidden, 10000);
                throw new Error(ConsoleColors.RED +"Элемент не найден:\n"+ ConsoleColors.RESET + c1.toString() + "\n\n");
            }
            CloseOneRight(chrome_1);
            /*CloseOneRight(chrome_2);*/
            sleep(1000);
        }
    }

    @AfterTest
    public static void End() throws InterruptedException, AWTException {

        chrome_1.quit();
        //chrome_2.quit();
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
