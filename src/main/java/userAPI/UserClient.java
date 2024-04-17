package userAPI;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient{
   private static final String DELETE_PATH = "api/auth/user";
    private static final String LOGIN_PATH = "api/auth/login";
    private static final String REGISTER_PATH = "api/auth/register";

    @Step("Создание пользователя через API")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER_PATH).then();

    }
    @Step("Авторизация пользователя через API")
    public ValidatableResponse login(UserCredentials credentials) {
        return
                given()
                        .spec(getSpec())
                        .body(credentials)
                        .when()
                        .post(LOGIN_PATH).then();

    }

    @Step("Удаление пользователя через API")
    public ValidatableResponse delete(String accessToken) {
        return
                given()
                        .spec(getSpec())
                        .auth().oauth2(accessToken)
                        .when()
                        .delete(DELETE_PATH).then().log().all();
    }
    @Step("Конвертация токена")
    public String tockenConversion(String token){
        StringBuilder sb = new StringBuilder(token);
        sb.delete(0,7);
        return
        token = sb.toString();
    }
}
