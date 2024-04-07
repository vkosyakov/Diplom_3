package login.site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import register.site.nomoreparties.stellarburgers.BaseTest;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import userAPI.User;
import userAPI.UserClient;
import userAPI.UserCredentials;
import userAPI.UserGenerator;

public class LoginTest extends BaseTest {
    private UserClient userClient;
    private User user;
    private String token;
    @Before
    public void createTestData(){
        userClient = new UserClient();
        user = UserGenerator.withAllData();
        userClient.create(user);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Проверка авторизации по кнопке «Войти в аккаунт» на главной")
    public void checkBtnInputToAccount(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickButtonInputMainPage();

         loginPage.login(user.getEmail(),user.getPassword());

        Assert.assertTrue(mainPage.checkBtnOrder());
    }

    @Description("Проверка авторизации через кнопку «Личный кабинет»")
    @Test
    public void checkBtnPersonaAccount(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickButtonPersonalAcc();
        loginPage.login(user.getEmail(),user.getPassword());

        Assert.assertTrue(mainPage.checkBtnOrder());
    }


    @After
    public void cleanUp(){
        ValidatableResponse response =
                userClient.login(UserCredentials.from(user));
        boolean success = response.extract().path("success");


        if (success) {
            token = response.extract().path("accessToken");
            userClient.delete(userClient.tockenConversion(token)).statusCode(202);
        }
    }

}
