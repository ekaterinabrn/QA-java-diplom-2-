package ClientStep;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;

import static Constant.EndpointConstant.CREATE_USER;
import static Constant.EndpointConstant.DELETE_USER;
import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Create user")
    public static Response createUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER);
    }
    @Step("Delete user")
    public Response deleteUser(String token){
        return given().log().all()
                .header("Authorization", token)
                .delete(DELETE_USER);
    }
}
