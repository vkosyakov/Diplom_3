package userAPI;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient{
   private static final String DELETE_PATH = "api/auth/user";
    private static final String LOGIN_PATH = "api/auth/login";
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
                        .delete(DELETE_PATH).then();
    }

    public String tockenConversion(String token){
        StringBuilder sb = new StringBuilder(token);
        sb.delete(0,7);
        return
        token = sb.toString();
    }
}
