package site.nomoreparties.stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;

import java.util.List;

public class MainPage extends BasePage{
    private final By buttonInputMainPage = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By buttonPlaceOrder = By.xpath(".//button[text() = 'Оформить заказ']");

    private final By buttonPersonalAcc = By.xpath(".//p[text() = 'Личный Кабинет']");

    private final By labelAssembleTheBurger = By.xpath(".//h1[text() = 'Соберите бургер']");

    private final By ingredientsHeader = By.xpath(".//div[contains(@class,'tab_tab__1SPyG')]");



    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickButtonInputMainPage(){
        driver.findElement(buttonInputMainPage).click();
    }
    @Step("Клик по кнопке Личный кабинет")
    public void clickButtonPersonalAcc(){
        driver.findElement(buttonPersonalAcc).click();
    }

    @Step("Проверить видимость кнопки Оформить заказ")
    public boolean checkBtnOrder(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonPlaceOrder)).isEnabled();
    }

    @Step("Взять надпись Соберите бургер")
    public String getTextLabel(){
        return driver.findElement(labelAssembleTheBurger).getText();
    }

    @Step("Взять список элементов Булки, Соусы, Начинки")
    public List<WebElement> getIngredientsHeader(){
        return driver.findElements(ingredientsHeader);
    }





}
