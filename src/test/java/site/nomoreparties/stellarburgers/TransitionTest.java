package site.nomoreparties.stellarburgers;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import register.site.nomoreparties.stellarburgers.BaseTest;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.PersonalAccountPage;
import userAPI.User;
import userAPI.UserClient;
import userAPI.UserCredentials;
import userAPI.UserGenerator;

public class TransitionTest extends BaseTest {
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

    @After
    public void cleanUp() {
        ValidatableResponse response =
                userClient.login(UserCredentials.from(user));
        boolean success = response.extract().path("success");


        if (success) {
            token = response.extract().path("accessToken");
            userClient.delete(userClient.tockenConversion(token)).statusCode(202);
        }
    }

    @Test
    public void checkTransitionPersonalAccount(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.clickButtonInputMainPage();
        loginPage.login(user.getEmail(),user.getPassword());
        Assert.assertTrue(mainPage.checkBtnOrder());

        mainPage.clickButtonPersonalAcc();
        //Проверка нажписи профиль
        Assert.assertEquals("Профиль", personalAccountPage.getLabel());

    }
}
