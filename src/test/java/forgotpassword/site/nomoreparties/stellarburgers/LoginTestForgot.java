package forgotpassword.site.nomoreparties.stellarburgers;

import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import register.site.nomoreparties.stellarburgers.BaseTest;
import site.nomoreparties.stellarburgers.page.ForgotPasswordPage;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.RegisterPage;
import userAPI.User;
import userAPI.UserClient;
import userAPI.UserCredentials;
import userAPI.UserGenerator;

public class LoginTestForgot extends BaseTest {
    private UserClient userClient;
    private User user;
    private String token;

    //создание данных для теста
    @Before
    public void createTestData(){
        userClient = new UserClient();
        user = UserGenerator.withAllData();
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    //Удаление данных после теста
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

    @Test
    @Description("Проверка авторизации через кнопку в форме восстановления пароля")
    public void checkAuthOnFormRegistration(){
        userClient.create(user);
        MainPage mainPage = new MainPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        forgotPasswordPage.cliclHrefInput().login(user.getEmail(),user.getPassword());

        Assert.assertTrue(mainPage.checkBtnOrder());
    }
}
