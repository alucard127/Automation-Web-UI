package browser;

import org.openqa.selenium.WebDriver;

public interface IBrowser {
    /**
     * @return the browser name
     */

    WebDriver create();

}
