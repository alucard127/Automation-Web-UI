package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import inicio_session.Inicio_Session;

import java.time.Duration;

public class Control {
    protected WebElement control;
    protected By locator;

    public Control(By locator){
        this.locator = locator;
    }

    protected void find(){
        control = Inicio_Session.getInstance().getBrowser().findElement(this.locator);
        // explicit wait
    }

    public void click(){
        this.find();
        control.click();
    }

    public  boolean isControlDisplayed(){
        try {
            this.find();
            return control.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Label
    public String getText(){
        this.find();
        return this.control.getText();
    }

    public void explicitWaitControlIsClickable(){
        WebDriverWait explicitWait = new WebDriverWait(Inicio_Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.elementToBeClickable(this.locator));
    }

}
