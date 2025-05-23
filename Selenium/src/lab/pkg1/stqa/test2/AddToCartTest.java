package lab.pkg1.stqa.test2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class AddToCartTest {
    public static void main(String[] args) throws InterruptedException {
        // ✅ Set path to GeckoDriver for Firefox
       System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver.exe");

        // Launch Firefox browser
        WebDriver driver = new FirefoxDriver();

        try {
            driver.manage().window().maximize();

            // Explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Navigate to Daraz homepage
            driver.get("https://www.daraz.com.bd/");

            // Handle popup if it appears
            try {
                WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close-btn")));
                popupClose.click();
                System.out.println("✅ Popup closed.");
            } catch (Exception e) {
                System.out.println("ℹ️ No popup found or popup close button not clickable.");
            }

            // Search for "laptop"
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            searchBox.sendKeys("laptop");
            searchBox.submit();

            // Wait for search results
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-qa-locator='product-item']")));

            // ✅ Scroll to and click the first product using JavaScript
            WebElement firstProduct = driver.findElement(By.cssSelector("div[data-qa-locator='product-item'] a"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            Thread.sleep(1000); // Wait for smooth scroll
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            // Switch to new tab
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // Wait for "Add to Cart" button and click
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart-buy-now-btn")));
            addToCartBtn.click();

            // Optional wait for cart update
            Thread.sleep(3000);

            // ✅ Verify cart update
            try {
                WebElement cartCount = driver.findElement(By.cssSelector(".cart-num"));
                if (!cartCount.getText().equals("0")) {
                    System.out.println("✅ Product successfully added to cart.");
                } else {
                    System.out.println("❌ Cart count is still 0.");
                }
            } catch (Exception e) {
                System.out.println("⚠ Could not verify cart count.");
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
