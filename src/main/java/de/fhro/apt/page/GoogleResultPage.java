package de.fhro.apt.page;

import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Page object for google result page.
 *
 * @author Veli Döngelci
 */
public class GoogleResultPage extends AbsctractPage {

    /**
     * Constructor
     *
     * @param driver  webdriver
     */
    public GoogleResultPage(WebDriver driver) {
        super(driver, null);
    }

    /**
     * Gives search input element on result page.
     *
     * @return input element
     */
    public WebElement getSearchInputElement() {
        return this.getDriver().findElement(By.name("q"));
    }

    /**
     * Gives the search results.
     *
     * @return list of results
     */
    public List<WebElement> getSearchResultList() {
        List<WebElement> resultList = new ArrayList<WebElement>();
        List<WebElement> searchAreaList = this.getDriver().findElements(By.cssSelector("div.srg"));

        for (WebElement searchArea : searchAreaList) {
            resultList.addAll(searchArea.findElements(By.cssSelector("div.g")));
        }

        return resultList;
    }

    /**
     * Extracts a link element from search result element.
     *
     * @param searchResultElement element
     * @return link element
     */
    public WebElement getLinkElementBySearchResultElement(WebElement searchResultElement) {
        return searchResultElement.findElement(By.cssSelector("div > h3 > a"));
    }

    /**
     * Not supported method.
     */
    @Override
    public void openPage() {
        throw new UnsupportedCommandException("Unsupported Method");
    }

    /**
     * Not supported method.
     *
     * @param pageTitle expected
     * @throws NotFoundException
     */
    @Override
    public void openPageSafe(String pageTitle) throws NotFoundException {
        throw new UnsupportedCommandException("Unsupported Method");
    }
}
