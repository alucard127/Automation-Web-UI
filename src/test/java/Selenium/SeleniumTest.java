package Selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTest {

    WebDriver driver;
    Actions action;

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
        //driver = new ChromeDriver(options);

        driver = new ChromeDriver();

        action = new Actions(driver);


        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // page load wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("http://ticktick.com/");
    }

    @AfterEach
    public void cleanup()
    {
        driver.quit();
    }

    @Test
    public void verifyCRUDProject() throws InterruptedException {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // LOG IN
        driver.findElement(By.xpath("//a[text()='Sign In']")).click();
        driver.findElement(By.id("emailOrPhone")).sendKeys("robertofrancoascuy@gmail.com");
        driver.findElement(By.id("password")).sendKeys("StrongPassword");
        driver.findElement(By.className("button__3eXSs")).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='w-[32px] h-[32px] rounded-[6px]']")));
            // verify if I am logged in
        Assertions.assertTrue(driver.findElement(By.xpath("//img[@class='w-[32px] h-[32px] rounded-[6px]']")).isDisplayed(),"ERROR login was incorrect");

        // CREATE LIST
        driver.findElement(By.className("icon-list-add")).click();
        driver.findElement(By.id("edit-project-name")).sendKeys("automation");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//button/p[text()='automation']")).isDisplayed(),"ERROR: the list was not created");

        // EDIT LIST
        // right click
        WebElement link = driver.findElement(By.xpath("//p[text()='automation']"));
        action.contextClick(link).perform();

        driver.findElement(By.xpath("//div[13]/div/div/div[2]/ul/li[1]/a")).click();
        driver.findElement(By.id("edit-project-name")).sendKeys("Updated");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//button/p[text()='automationUpdated']")).isDisplayed(),"ERROR: the list was not updated");


        // DELETE LIST
        // right click
        WebElement link2 = driver.findElement(By.xpath("//p[text()='automationUpdated']"));
        action.contextClick(link2).perform();

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[13]/div/div/div[2]/ul/li[6]/a")));
        driver.findElement(By.xpath("//div[13]/div/div/div[2]/ul/li[6]/a")).click();
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        Thread.sleep(2000);
        //Assertions.assertFalse(driver.findElement(By.xpath("//button/p[text()='automationUpdated']")).isDisplayed(),"ERROR: the list was not deleted");
    }


}
