package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{
    private final By buttonInputMainPage = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By buttonPlaceOrder = By.xpath(".//button[text() = 'Оформить заказ']");

    private final By buttonPersonalAcc = By.xpath(".//p[text() = 'Личный Кабинет']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonInputMainPage(){
        driver.findElement(buttonInputMainPage).click();
    }

    public void clickButtonPersonalAcc(){
        driver.findElement(buttonPersonalAcc).click();
    }


    public boolean checkBtnOrder(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonPlaceOrder)).isEnabled();
    }



}
