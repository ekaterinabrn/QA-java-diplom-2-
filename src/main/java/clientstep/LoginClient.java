package clientstep;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Credentials;

import static constant.EndpointConstant.LOGIN_USER;
import static io.restassured.RestAssured.given;

public class LoginClient {
@Step("User LogIN")
    public static  Response loginUser(Credentials credentials) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(credentials)
                .when()
                .post(LOGIN_USER);
    }
}