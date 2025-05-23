package lab.pkg1.stqa.test2;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginandRegistration {

    public static void main(String[] args) throws InterruptedException {
        // ✅ Set GeckoDriver path (for Firefox)
       System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver.exe");

        // ✅ Launch Firefox browser
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // ✅ Open Daraz website
        driver.get("https://www.daraz.com.bd/");
        Thread.sleep(4000);

        // ✅ Close popup if it appears
        try {
            WebElement popupClose = driver.findElement(By.cssSelector(".close-btn"));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("No popup found.");
        }

        // ✅ Click Login/Register
        try {
            WebElement loginButton = driver.findElement(By.cssSelector("a[href*=login]"));
            loginButton.click();
            Thread.sleep(5000); // wait for modal
        } catch (InterruptedException e) {
            System.out.println("❌ Could not open login modal.");
            driver.quit();
            return;
        }

        // ✅ Switch to login iframe
        try {
            WebElement loginIframe = driver.findElement(By.cssSelector("iframe[id^='login-iframe']"));
            driver.switchTo().frame(loginIframe);
            System.out.println("✅ Switched to login iframe.");
        } catch (Exception e) {
            System.out.println("❌ Could not switch to login iframe.");
            driver.quit();
            return;
        }

        // ✅ Verify login form is visible
        try {
            WebElement loginForm = driver.findElement(By.name("loginKey"));
            if (loginForm.isDisplayed()) {
                System.out.println("✅ Login/Register modal opened successfully.");
            } else {
                System.out.println("❌ Login form not displayed.");
            }
        } catch (Exception e) {
            System.out.println("❌ Login form elements not found.");
        }

        // ✅ Close browser
        driver.quit();
    }
}
