package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By hrefInput = By.cssSelector("[href = '/login']");
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage cliclHrefInput(){
        driver.findElement(hrefInput).click();
        return new LoginPage(driver);
    }
}
