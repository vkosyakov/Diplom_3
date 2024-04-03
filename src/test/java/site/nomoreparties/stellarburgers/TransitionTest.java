package site.nomoreparties.stellarburgers;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import register.site.nomoreparties.stellarburgers.BaseTest;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.PersonalAccountPage;
import userAPI.User;
import userAPI.UserClient;
import userAPI.UserCredentials;
import userAPI.UserGenerator;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransitionTest extends BaseTest {
    private UserClient userClient;
    private User user;
    private String token;
    @Before
    public void createTestData(){
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void cleanUp() {
        if (user != null) {
            ValidatableResponse response =
                    userClient.login(UserCredentials.from(user));
            boolean success = response.extract().path("success");

            if (success) {
                token = response.extract().path("accessToken");
                userClient.delete(userClient.tockenConversion(token)).statusCode(202);
            }
        }
    }
// переход в конструкртор из личного кабинета по кнопке "Конструктор"
    @Test
    public void checkTransitionPersonalAccountByConstructor(){
        userClient = new UserClient();
        user = UserGenerator.withAllData();
        userClient.create(user);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.clickButtonInputMainPage();
        loginPage.login(user.getEmail(),user.getPassword());
        Assert.assertTrue(mainPage.checkBtnOrder());

        //переход в личный кабиент
        mainPage.clickButtonPersonalAcc();
        //Проверка надписи профиль
        Assert.assertEquals("Профиль", personalAccountPage.getLabel());

        personalAccountPage.clickConstructor();
        Assert.assertEquals("Соберите бургер",mainPage.getTextLabel());
    }
    // переход в конструкртор из личного кабинета по логотипу
    @Test
    public void checkTransitionPersonalAccountByLogo(){
        userClient = new UserClient();
        user = UserGenerator.withAllData();
        userClient.create(user);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.clickButtonInputMainPage();
        loginPage.login(user.getEmail(),user.getPassword());
        Assert.assertTrue(mainPage.checkBtnOrder());

        //переход в личный кабиент
        mainPage.clickButtonPersonalAcc();
        //Проверка надписи профиль
        Assert.assertEquals("Профиль", personalAccountPage.getLabel());

        personalAccountPage.clickLogo();
        Assert.assertEquals("Соберите бургер",mainPage.getTextLabel());
    }

    @Test
    public void checkLogOutPersonalAccount(){
        userClient = new UserClient();
        user = UserGenerator.withAllData();
        userClient.create(user);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.clickButtonInputMainPage();
        loginPage.login(user.getEmail(),user.getPassword());
        Assert.assertTrue(mainPage.checkBtnOrder());

        //переход в личный кабиент
        mainPage.clickButtonPersonalAcc();
        //Проверка надписи профиль
        Assert.assertEquals("Профиль", personalAccountPage.getLabel());
        //Выход из личного кабинета проверка надписи "Вход"
        personalAccountPage.clickBtnLogOut();
        Assert.assertTrue(loginPage.checkLabelInput());
    }

    @Test
    public void checkTransitionIngredients(){
        MainPage mainPage = new MainPage(driver);

        List<WebElement> elements = mainPage.getIngredientsHeader();
        String classNameSelected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

           for(int i = 2; i == 0; i--){
               elements.get(i).click();
               Assert.assertEquals(elements.get(i).getAttribute("class"),classNameSelected);
           }
       }









    }



