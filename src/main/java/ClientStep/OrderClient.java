package ClientStep;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.OrderIngredient;

import static Constant.EndpointConstant.*;
import static io.restassured.RestAssured.given;

public class OrderClient {
    @Step("Create Order with access token")
    public static Response createOrder(String accessToken,OrderIngredient orderIngredient) {
        return given().log().all()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .body(orderIngredient)
                .when()
                .post(CREATE_ORDER);

    }
    @Step("Create Order without access token")
    public static Response createOrderWithoutToken(OrderIngredient orderIngredient){
        return given().log().all()
                .header("Content-type", "application/json")
                .body(orderIngredient)
                .when()
                .post(CREATE_ORDER);
    }
    @Step("Check orders with access token user")
    public Response getOrderUserToken(String accessToken){
        return given().log().all()
                .header("Authorization", accessToken)
                .get(CREATE_ORDER);
    }

    @Step("Check orders without access token user")
    public Response getOrderUserWithoutAuthorization(){
        return given().log().all()
                .get(CREATE_ORDER);
    }

}