package de.fhro.apt.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Common wait helper.
 *
 * @author Veli Döngelci
 */
public class WaitHelper {
    private WebDriverWait wait;

    /**
     * Constructor
     *
     * @param driver webdriver
     */
    public WaitHelper(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Waits until url contains expected value.
     *
     * @param expected value
     * @return true, if url contains expected value
     */
    public boolean waitUntilUrlContains(String expected) {
        return wait.until(ExpectedConditions.urlContains(expected));
    }

    /**
     * Waits until url contains expected value.
     *
     * @param expected value
     * @return true, if url contains expected value
     */
    public boolean waitUntilElementsTextContains(WebElement element, String expected) {
        return wait.until(ExpectedConditions.textToBePresentInElementValue(element, expected));
    }
}
