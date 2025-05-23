

package lab.pkg1.stqa.test2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTest {
    public static void main(String[] args) throws InterruptedException { 
    
// Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver.exe");

        // Initialize a new ChromeDriver instance
        WebDriver driver = new FirefoxDriver();

        try {
            // Navigate to Daraz Bangladesh homepage
            driver.get("https://www.daraz.com.bd/");
            driver.manage().window().maximize();

            // Wait briefly for the page to load (an explicit wait is recommended for robust tests)
            Thread.sleep(3000);

            // Example: Locate the search box by its name or other attribute
            // Note: Depending on how the webpage is built, you might need to inspect and update the element locator.
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("laptop");
            searchBox.submit();

            // Wait to observe the search results
            Thread.sleep(5000);
            
            // You can include additional steps to verify search results if needed.
            // For example, check if the resulting page URL or title contains expected keywords.
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);

        } catch (InterruptedException e) {
        } finally {
            // Close the browser once the test is done
            driver.quit();
        }
    }
}
