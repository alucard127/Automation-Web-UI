package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Chrome implements IBrowser{

    @Override
    public WebDriver create() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        //action = new Actions(driver);

        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // page load wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
}
