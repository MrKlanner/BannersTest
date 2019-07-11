package ru.rbc.kskabort;

/** Небольшой пример правильного использования Actions!
 * Actions actions = new Actions(driver);
 * actions
 *     .keyDown(Keys.LEFT_CONTROL)
 *     .click(first_WebElement)
 *     .click(second_WebElement)
 *     .click(third_WebElement)
 *     .keyUp(Keys.LEFT_CONTROL)
 *     .build()
 *     .perform(); */

import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;

public class TabActions {
    public static WebDriver driver;
/*
    private static Actions actions = new Actions(getWebDriver());
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
*/

    static void PressEscape(WebDriver driver1) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
/*        Actions actions = new Actions(driver1);
        {
            actions.sendKeys(Keys.ESCAPE)
                    .release()
                    .build()
                    .perform();
        }*/
    }

    static void New() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        /*actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();*/
        /*sleep(500);
        ((JavascriptExecutor)getWebDriver()).executeScript("window.open()");
        sleep(500);*/
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
    }

/*    static void Switch() throws AWTException, InterruptedException
    {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_TAB);
    }*/

    static void Close() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        //(JavascriptExecutor)getWebDriver()).executeScript("window.close()");
        //sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_F4);
    }

    static void SwitchToLast(int NumOfWindows) throws InterruptedException, AWTException {
        Robot robot = new Robot();
        //(JavascriptExecutor)getWebDriver()).executeScript("window.close()");
        //sleep(500);
        robot.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(500);
        for (int i=0; i<NumOfWindows-1; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(500);
        }
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    static void WindowSwap(String direction) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_WINDOWS);
        if (direction.equals("left")) {
            for (int i = 0; i < 3; i++) {
                robot.keyPress(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                Thread.sleep(500);
            }
        }
        else if (direction.equals("right")) {
            for (int i = 0; i < 3; i++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
        }
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
    }

/*    static void Open_in_new_tab() throws AWTException, InterruptedException
    {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }*/

}
