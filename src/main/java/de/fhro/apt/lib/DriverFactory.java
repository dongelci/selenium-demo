package de.fhro.apt.lib;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Selenium Webdriver
 *
 * @author Veli Döngelci
 */
public class DriverFactory {
    public static final int CHROME = 1;
    public static final int FIREFOX = 2;
    public static final int OPERA = 3;

    private static WebDriver driver = null;

    /**
     * Starts a webdriver with given browser type
     *
     * @param browserCode browser code
     * @return webdriver
     */
    public static WebDriver startDriverByBrowser(int browserCode) {
        // avoid new instance if already defined
        if (driver != null) {
            return driver;
        }

        switch(browserCode) {
            case FIREFOX:
                throw new UnsupportedCommandException("Firefox not implemented!");
            case OPERA:
                throw new UnsupportedCommandException("Opera not implemented!");
            case CHROME:
            default:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        //This adds implicit timeouts to the driver (instead of clickAndWait())
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    /**
     * Gives an instance of webdriver
     *
     * @return instance
     */
    public static WebDriver getDriverInstance() {
        return driver;
    }

    /**
     * Closes selenium webdriver
     */
    public static void closeWebdriver() {
        driver.close();
    }
}
