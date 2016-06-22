package de.fhro.apt.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Common actions helper.
 *
 * @author Veli Döngelci
 */
public class ActionHelper {

    private WebDriver driver;
    private WaitHelper waitHelper;

    /**
     * Constructor
     *
     * @param driver webdriver
     */
    public ActionHelper(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(this.driver);
    }

    /**
     * Clicks element and waits until the url contains a value.
     *
     * @param element to be clicked
     * @param expected value
     * @return true, if page url has expected value
     */
    public boolean clickAndWaitUntilUrlContains(WebElement element, String expected) {
        element.click();
        return this.waitHelper.waitUntilUrlContains(expected);
    }

    /**
     * Clicks if only the element is displayed.
     *
     * @param element to be clicked
     * @return true, if click is successful
     */
    public boolean clickSafe(WebElement element) {
        if (element.isDisplayed()) {
            element.click();
            return true;
        }

        return false;
    }
}
