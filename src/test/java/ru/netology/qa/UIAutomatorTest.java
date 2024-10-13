package ru.netology.qa;


import io.appium.java_client.android.AndroidDriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;


import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UIAutomatorTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "poco");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testEmptyTest() {
        MainScreen button = new MainScreen(driver);

        String initialText = button.textToBeChanged.getText();
        button.userInput.sendKeys(" ");
        button.buttonChange.click();
        String result = button.textToBeChanged.getText();
        Assertions.assertEquals(initialText, result);
    }

    @Test
    public void NewActivityTest() {
        MainScreen button = new MainScreen(driver);

        String newText = "Hi there";
        button.userInput.sendKeys(newText);
        button.buttonActivity.click();
        button.activityText.isDisplayed();
        Assertions.assertEquals(newText, button.activityText.getText()) ;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
