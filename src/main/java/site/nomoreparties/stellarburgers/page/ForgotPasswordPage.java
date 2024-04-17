package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By hrefInput = By.cssSelector("[href='/login']");


    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке войти на странице восстановления пароля")
    public LoginPage cliclHrefInput(){
        driver.findElement(hrefInput).click();
        return new LoginPage(driver);
    }
}
