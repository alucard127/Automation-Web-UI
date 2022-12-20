package page.ticktick;

import control.Button;
import control.Label;
import org.openqa.selenium.By;

public class ListSection {

    public Button addNewListButton = new Button(By.className("icon-list-add"));

    public void clickOnList(String listName){
        Label listCreated = new Label(By.xpath("//button/p[text()='"+listName+"']"));
        listCreated.click();
    }

    public boolean isListDisplayedInList(String listName){
        Label listCreated = new Label(By.xpath("//button/p[text()='"+listName+"']"));
        return listCreated.isControlDisplayed();
    }

    public Label getList(String listName){
        Label listCreated = new Label(By.xpath("//button/p[text()='"+listName+"']"));
        return listCreated;
    }

}
