package clientstep;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.OrderIngredient;

import static constant.EndpointConstant.CREATE_ORDER;
import static io.restassured.RestAssured.given;

public class OrderClient {
    @Step("Create Order with access token")
    public static Response createOrder(String accessToken,OrderIngredient orderIngredient) {
        return given().log().all()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
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
    public  static  Response getOrderUserToken(String accessToken){
        return given().log().all()
                .header("Authorization", accessToken)
                .get(CREATE_ORDER);
    }

    @Step("Check orders without access token user")
    public static Response getOrderUserWithoutAuthorization(){
        return given().log().all()
                .get(CREATE_ORDER);
    }

}
