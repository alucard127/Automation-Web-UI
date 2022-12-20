package browser;

public class FactoryBrowser {
    /**
     * @return the browser type
     */

    public static IBrowser make(String browserType){
        IBrowser browser; // SOLID, L

        switch (browserType.toLowerCase()) {
            case "chrome":
                browser =  new Chrome();
                break;
            case "headless":
                browser = new Headless();
                break;
            default:
                browser =  new Firefox();
                break;
        }
        return browser;
    }
}
