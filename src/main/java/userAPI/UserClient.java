package userAPI;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient{
   private static final String DELETE_PATH = "api/auth/user";
    private static final String LOGIN_PATH = "api/auth/login";
    private static final String REGISTER_PATH = "api/auth/register";

    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER_PATH).then();

    }
    public ValidatableResponse login(UserCredentials credentials) {
        return
                given()
                        .spec(getSpec())
                        .body(credentials)
                        .when()
                        .post(LOGIN_PATH).then();

    }

    public ValidatableResponse delete(String accessToken) {
        return
                given()
                        .spec(getSpec())
                        .auth().oauth2(accessToken)
                        .when()
                        .delete(DELETE_PATH).then().log().all();
    }

    public String tockenConversion(String token){
        StringBuilder sb = new StringBuilder(token);
        sb.delete(0,7);
        return
        token = sb.toString();
    }
}
