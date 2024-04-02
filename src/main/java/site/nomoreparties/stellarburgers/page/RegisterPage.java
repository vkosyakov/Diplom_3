package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private final By name = By.name("name");
    private final By email = By.xpath(".//fieldset[2]//div/child::input");
    private final By password = By.xpath(".//fieldset[3]//div/child::input");
    private final By buttonRegiste = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By failedPass = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By hrefInput = By.cssSelector("[href = '/login']");



    public RegisterPage registerUser(String nameFaker, String emailFaker, String passwordFaker){
        driver.findElement(name).sendKeys(nameFaker);
        driver.findElement(email).sendKeys(emailFaker);
        driver.findElement(password).sendKeys(passwordFaker);
        driver.findElement(buttonRegiste).click();
        return this;
    }
    public boolean checkLabelFailePass(){
        return
        driver.findElement(failedPass).isDisplayed();
    }

    public LoginPage cliclHrefInput(){
        driver.findElement(hrefInput).click();
        return new LoginPage(driver);
    }



}
