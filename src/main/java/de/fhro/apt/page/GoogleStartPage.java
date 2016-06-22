package de.fhro.apt.page;

import de.fhro.apt.helper.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Veli Döngelci
 */
public class GoogleStartPage extends AbsctractPage{

    private ActionHelper actionHelper;

    /**
     * Constructor
     *
     * @param driver  webdriver
     */
    public GoogleStartPage(WebDriver driver) {
        super(driver, "http://www.google.de");
        this.actionHelper = new ActionHelper(driver);
    }

    /**
     * Gives input element on google
     *
     * @return input element
     */
    public WebElement getSearchInputElement() {
        return this.getDriver().findElement(By.name("q"));
    }

    /**
     * Gives "Google Search" submit button on google.
     *
     * @return submit button
     */
    public WebElement getSubmitButtonOnHomepage() {
        return this.getDriver().findElement(By.name("btnK"));
    }

    /**
     * Gives "Google Search" submit button on google.
     *
     * @return submit button
     */
    public WebElement getSubmitButtonAfterTyping() {
        return this.getDriver().findElement(By.name("btnG"));
    }

    /**
     * Searches a value on google.
     *
     * @param searchString search string
     */
    public void searchByValue(String searchString) {
        this.getSearchInputElement().sendKeys(searchString);
        this.actionHelper.clickSafe(this.getSubmitButtonAfterTyping());
    }
}
