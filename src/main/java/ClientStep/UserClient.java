package ClientStep;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;

import static Constant.EndpointConstant.CREATE_USER;
import static Constant.EndpointConstant.DELETE_PATCH_USER;
import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
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
    public static Response deleteUser(@Param(mode = HIDDEN) String accessToken){
        return given().log().all()
                .header("Authorization", accessToken)
                .delete(DELETE_PATCH_USER);
    }
    @Step("Changing user data with auth")
    public static Response updateDataUserAuth(User user, String accessToken){
        return given().log().all()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(user)
                .when()
                .patch(DELETE_PATCH_USER);}

    @Step("Changing user data without auth")
    public static Response updateDataUserWithoutAuth(User user){
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .patch(DELETE_PATCH_USER);
    }
}
