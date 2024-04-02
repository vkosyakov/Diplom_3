package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
private final By labelInput = By.xpath(".//h2[text() = 'Вход']");
    private final By email = By.xpath(".//fieldset[1]//div/child::input");
    private final By password = By.xpath(".//fieldset[2]//div/child::input");
    private final By buttonInput = By.xpath(".//button[text() = 'Войти']");

    public boolean checkLabelInput(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(labelInput)).isDisplayed();
    }

    public LoginPage login(String emailFaker, String passwordFaker){
        driver.findElement(email).sendKeys(emailFaker);
        driver.findElement(password).sendKeys(passwordFaker);
        driver.findElement(buttonInput).click();
        return this;
    }
}
