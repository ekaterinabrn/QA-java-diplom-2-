package clientstep;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static constant.EndpointConstant.INGREDIENT;
import static io.restassured.RestAssured.given;

public class IngredientClient {
    @Step(" check list ingredients")
    public static Response getIngredient() {
        return given().log().all()
                .get(INGREDIENT);
    }
}
