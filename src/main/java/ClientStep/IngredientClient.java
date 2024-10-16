package ClientStep;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;

import static Constant.EndpointConstant.CREATE_USER;
import static Constant.EndpointConstant.INGREDIENT;
import static io.restassured.RestAssured.given;

public class IngredientClient {
    @Step(" check list ingredients")
    public static Response getIngredient() {
        return given().log().all()
                .get(INGREDIENT);
    }
}