package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
public class Browser {
    private WebDriver webDriver;
    private String browserName;

    public Browser() {
        this.browserName = System.getProperty("browsers");
    }
    public WebDriver getWebDriver() {
        if (browserName == null) {
            browserName = "yandex";
        }
        switch (browserName) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                webDriver = new ChromeDriver();
                webDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
                webDriver.manage().window().maximize();
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                webDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
                webDriver.manage().window().maximize();
                break;

        }
        return webDriver;

    }
}
