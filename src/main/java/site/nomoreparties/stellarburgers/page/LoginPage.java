package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
private final By labelInput = By.xpath(".//h2[text() = 'Вход']");

    public boolean checkLabelInput(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(labelInput)).isDisplayed();
    }
}
