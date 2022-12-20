package page.ticktick;

import control.Button;
import control.Label;
import org.openqa.selenium.By;

public class MenuSection {

    public Button userLoggedButton = new Button(By.xpath("//img[@class='w-[32px] h-[32px] rounded-[6px]']"));
    //public Button taskSectionButton = new Button(By.xpath(""));
    public Label getList(){
        Label listCreated = new Label(By.xpath("//img[@class='w-[32px] h-[32px] rounded-[6px]']"));
        return listCreated;
    }

    public Label DeleteButton(){
        Label deleteButton = new Label(By.xpath("//div[13]/div/div/div[2]/ul/li[6]/a"));
        return deleteButton;
    }

}
