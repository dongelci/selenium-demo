package de.fhro.apt.page;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;

/**
 * Abstract base page object.
 *
 * @author Veli Döngelci
 */
public abstract class AbsctractPage {
    private String pageUrl;
    private WebDriver driver;

    /**
     * Constructor
     *
     * @param driver webdriver
     * @param pageUrl to be opened in browser
     */
    public AbsctractPage(WebDriver driver, String pageUrl) {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    /**
     * Gives webdriver.
     *
     * @return webdriver
     */
    protected WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Opens the page.
     */
    public void openPage() {
        this.driver.get(pageUrl);
    }

    /**
     * Opens the page with given page title.
     *
     * @param pageTitle expected
     * @throws NotFoundException
     */
    public void openPageSafe(String pageTitle) throws NotFoundException {
        this.driver.get(pageUrl);

        if (! this.driver.getTitle().equalsIgnoreCase(pageTitle)) {
            throw new NotFoundException("Unexpected page title");
        }
    }

    /**
     * Gives page title.
     *
     * @return page title
     */
    public String getPageTitle() {
        return this.driver.getTitle();
    }

    /**
     * Gives current url of the page.
     *
     * @return current url
     */
    public String getPageUrl() {
        return this.driver.getCurrentUrl();
    }
}
