package register.site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getenv("BROWSER");
        driver = getDriver(browser == null ? "chrome" : browser);
    }

    @After
    public void tearDownAndCleanUp(){
        driver.quit();

    }

    private WebDriver getDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "mozilla":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser");


        }
    }
}
