package Step;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class OrderStep {
//    Создание заказа:
//    с авторизацией,
//    без авторизации,
//    с ингредиентами,
//    без ингредиентов,
//    с неверным хешем ингредиентов.
@Step("Check status code  200 after  success creation order with ingredients")
public void orderSuccessCreateWithIngredients(Response response) {
    response.then().statusCode(200).and().assertThat().body("success",equalTo(true));

}

    @Step("Check status code  400 after creation  order without ingredients")
public void orderCreateWithoutIngredients(Response response) {
   response.then().statusCode(400).and().assertThat().body("message",equalTo("Ingredient ids must be provided"));

}

    @Step("Check status code 500  after creation  order with the wrong ingredient hash")
    public void orderCreateWithWrongIngredientHash(Response response) {
        response.then()
                .statusCode(500);
    }
    @Step("Check status code Getting a list of orders from an authorized user")
    public void checkGetOrdersListWithAuth(Response response){
        response.then()
                .statusCode(200)
                .and().body("success", equalTo(true))
                .and().body("orders", notNullValue());
    }


    @Step("Check status code Getting a list of orders from  unauthorized user user")
    public void checkGetOrdersListNonAuth(Response response) {
        response.then().statusCode(401).and().assertThat().body("message",equalTo("You should be authorised"));
    }
}
