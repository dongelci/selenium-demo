package de.fhro.apt;

import de.fhro.apt.helper.WaitHelper;
import de.fhro.apt.page.GoogleResultPage;
import de.fhro.apt.page.GoogleStartPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 * Tests for google.
 *
 * @author Veli Döngelci
 */
public class GoogleTest {
    //The 'browser' itself
    private WebDriver driver;
    private GoogleStartPage googleStartPage;
    private GoogleResultPage googleResultPage;
    private WaitHelper waitHelper;

    @BeforeTest
    public void setup() {
        ChromeDriverManager.getInstance().setup();
        //Start the browser (firefox for now)
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.waitHelper = new WaitHelper(this.driver);
        this.googleStartPage = new GoogleStartPage(this.driver);
        this.googleResultPage = new GoogleResultPage(this.driver);

        //This adds implicit timeouts to the driver (instead of clickAndWait())
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openPage() {
        try {
            this.googleStartPage.openPageSafe("Google");
        }catch (NotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchElementsShouldBeVisible() {
        assertTrue(this.googleStartPage.getSearchInputElement().isDisplayed());
        assertTrue(this.googleStartPage.getSubmitButtonOnHomepage().isDisplayed());
    }

    @Test
    public void testSearchShouldTriggerAndShowResult() {
        this.googleStartPage.searchByValue("Gerd Beneken");
        assertTrue(waitHelper.waitUntilUrlContains("Gerd+Beneken"));

        assertTrue(waitHelper.waitUntilElementsTextContains(googleResultPage.getSearchInputElement(), "Gerd Beneken"));
        WebElement firstResultElement = this.googleResultPage.getSearchResultList().get(0);
        WebElement firstResultLinkElement = this.googleResultPage.getLinkElementBySearchResultElement(firstResultElement);
        assertTrue(firstResultLinkElement.getText().equals("Prof. Dr. Gerd Beneken - Hochschule Rosenheim"));
    }

    @AfterMethod
    public void finalJobs(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String filename = testResult.getName() + ".jpg";
            String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/";
            FileUtils.copyFile(scrFile, new File(screenshotPath + filename));
        }
    }

    @AfterTest
    public void closeSelenium() {
        //Shutdown the browser
        driver.quit();
    }
}
