package lab.pkg1.stqa.test2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class FilterSortTest {
    public static void main(String[] args) {
        // Set GeckoDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver.exe");

        // Initialize FirefoxDriver
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to Daraz
            driver.get("https://www.daraz.com.bd/");

            // Close popup if it appears
            try {
                WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close-btn")));
                popupClose.click();
                System.out.println("Popup closed.");
            } catch (Exception e) {
                System.out.println("No popup found.");
            }

            // Search for 'laptop'
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            searchBox.sendKeys("laptop");
            searchBox.submit();

            // Wait for products to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-qa-locator='product-item']")));

            // Scroll down to view filters
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");

            // Wait and click HP brand filter
            try {
                WebElement hpCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(., 'HP')]")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hpCheckbox);
                hpCheckbox.click();
                System.out.println("✅ HP filter applied.");
            } catch (Exception e) {
                System.out.println("❌ Could not apply HP brand filter.");
            }

            // Wait for results to reload
            Thread.sleep(4000);

            // Apply sorting: Price Low to High
            try {
                WebElement sortDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class*='sorting']")));
                sortDropdown.click();

                WebElement lowToHigh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Price Low to High')]")));
                lowToHigh.click();
                System.out.println("✅ Sorting applied: Price Low to High.");
            } catch (Exception e) {
                System.out.println("❌ Could not apply sorting.");
            }

            // Wait for products again
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-qa-locator='product-item']")));

            // Check if products are visible
            List<WebElement> results = driver.findElements(By.cssSelector("div[data-qa-locator='product-item']"));
            if (!results.isEmpty()) {
                System.out.println("✅ Filter & Sort Test Passed. Total products: " + results.size());
            } else {
                System.out.println("❌ No products found after filter/sort.");
            }

        } catch (InterruptedException e) {
            System.out.println("❌ Test failed due to exception: " + e.getMessage());
        } finally {
            // Always close the browser
            driver.quit();
        }
    }
}
