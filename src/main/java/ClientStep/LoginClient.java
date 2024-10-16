package ClientStep;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Credentials;

import static Constant.EndpointConstant.LOGIN_USER;
import static io.restassured.RestAssured.given;

public class LoginClient {
@Step("User LogIN")
    public Response loginUser(Credentials credentials) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(credentials)
                .when()
                .post(LOGIN_USER);
    }
}