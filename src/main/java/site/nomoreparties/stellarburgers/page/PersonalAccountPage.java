package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAccountPage extends BasePage{

    private final By labelProfile = By.cssSelector("[href = '/account/profile']");
    private final By constructor = By.xpath(".//p[text() = 'Конструктор']");
    private final By logo = By.xpath(".//div/a");
    private final By buttonLogOut = By.xpath(".//button[text() = 'Выход']");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getLabel(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(labelProfile)).getText();
    }

    public MainPage clickConstructor(){
        driver.findElement(constructor).click();
        return new MainPage(driver);
    }

    public MainPage clickLogo(){
        driver.findElement(logo).click();
        return new MainPage(driver);
    }

    public LoginPage clickBtnLogOut(){
        driver.findElement(buttonLogOut).click();
        return new LoginPage(driver);
    }

}
