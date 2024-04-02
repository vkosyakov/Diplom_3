package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAccountPage extends BasePage{

    private final By labelProfile = By.cssSelector("[href = '/account/profile']");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getLabel(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(labelProfile)).getText();
    }

}
