package com.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTestCucumber {
    WebDriver driver;
    Actions action;

    @Given("I am on the ticktick page")
    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        //driver = new ChromeDriver();
        action = new Actions(driver);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // page load wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://ticktick.com/");
    }

    @When("Log in page")
    @Test
    public void logInPage() {
        driver.findElement(By.xpath("//a[text()='Sign In']")).click();
        driver.findElement(By.id("emailOrPhone")).sendKeys("32perezjuan@gmail.com");
        driver.findElement(By.id("password")).sendKeys("StrongPassword");
        driver.findElement(By.className("button__3eXSs")).click();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit wait
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='w-[32px] h-[32px] rounded-[6px]']")));
    }
    @And("Verify if you are logged in")
    @Test
    public void verifyIfYouAreLoggedIn() {
        Assertions.assertTrue(driver.findElement(By.xpath("//img[@class='w-[32px] h-[32px] rounded-[6px]']")).isDisplayed(),"ERROR login was incorrect");
    }

    @When("Create List")
    @Test
    public void createList() {
        driver.findElement(By.className("icon-list-add")).click();
        driver.findElement(By.id("edit-project-name")).sendKeys("automation");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
    }
    @And("Verify if the list was created")
    @Test
    public void verifyIfTheListWasCreated() {
        Assertions.assertTrue(driver.findElement(By.xpath("//button/p[text()='automation']")).isDisplayed(),"ERROR: the list was not created");
    }

    @When("Edit List")
    @Test
    public void editList() throws InterruptedException {
        WebElement link = driver.findElement(By.xpath("//p[text()='automation']"));
        action.contextClick(link).perform();
        driver.findElement(By.xpath("//div[13]/div/div/div[2]/ul/li[1]/a")).click();
        driver.findElement(By.id("edit-project-name")).sendKeys("Updated");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Thread.sleep(2000);
    }

    @And("Verify if the list was edited")
    @Test
    public void verifyIfTheListWasEdited() {
        Assertions.assertTrue(driver.findElement(By.xpath("//button/p[text()='automationUpdated']")).isDisplayed(),"ERROR: the list was not updated");
    }

    @When("Delete List")
    @Test
    public void deleteList() throws InterruptedException {
        WebElement link2 = driver.findElement(By.xpath("//p[text()='automationUpdated']"));
        action.contextClick(link2).perform();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit wait
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[13]/div/div/div[2]/ul/li[6]/a")));

        driver.findElement(By.xpath("//div[13]/div/div/div[2]/ul/li[6]/a")).click();
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        Thread.sleep(2000);
    }

    @Then("Quit the browser")
    @AfterEach
    public void quitTheBrowser() {
        driver.quit();
    }
}
