package register.site.nomoreparties.stellarburgers;


import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page.LoginPage;
import site.nomoreparties.stellarburgers.page.MainPage;
import site.nomoreparties.stellarburgers.page.RegisterPage;
import userAPI.User;
import userAPI.UserClient;
import userAPI.UserCredentials;
import userAPI.UserGenerator;

public class RegisterTest extends BaseTest{
    private UserClient userClient;
    private User user;
    private String token;

    //создание данных для теста
    @Before
    public void createTestData(){
        userClient = new UserClient();
        user = UserGenerator.withAllData();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    //Успешная регистрация пользователя
    @Test
    public void testSuccessfulRegistration(){
       //проверяем что после регистрации появляется надпись "Вход"
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registerPage.registerUser(user.getName(),user.getEmail(),user.getPassword());
        Assert.assertTrue(loginPage.checkLabelInput());
    }

    @Test
    public void testFailedPassword(){
        Faker faker = new Faker();
        user.setPassword(faker.internet().password(1,5));
        RegisterPage registerPage = new RegisterPage(driver);
        Assert.assertTrue(registerPage.registerUser(user.getName(),user.getEmail(),user.getPassword()).checkLabelFailePass());
    }

    // тест авторизации через форму регистрации
    @Test
    public void checkAuthOnFormRegistration(){
        userClient.create(user);
        RegisterPage registerPage = new RegisterPage(driver);
        MainPage mainPage = new MainPage(driver);
        registerPage.cliclHrefInput().login(user.getEmail(),user.getPassword());
        Assert.assertTrue(mainPage.checkBtnOrder());
    }

    //Удаление пользователя после теста
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