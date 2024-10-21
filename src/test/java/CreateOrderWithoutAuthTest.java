import ClientStep.IngredientClient;
import ClientStep.OrderClient;
import Step.OrderStep;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.OrderIngredient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static Constant.EndpointConstant.URL;


public class CreateOrderWithoutAuthTest {
        OrderStep orderStep=new OrderStep();
        @Before
        public void setUp() {
            RestAssured.baseURI = URL;
           }
    @Test
    @DisplayName("Create  order with ingredient and  without authorization")
    public void createOderWithoutAuthorizationWithIngredient() {
        Response orderIngred = IngredientClient.getIngredient();
        List<String> ingredients = new ArrayList<>(orderIngred.then().log().all().extract().path("data._id")); // Извлечение ID ингредиентов из ответа
        int fromIndex = 1;
        int toIndex = 2;
        OrderIngredient order = new OrderIngredient(ingredients.subList(fromIndex, toIndex));
        Response orderWithIngredientNoAuth = OrderClient.createOrderWithoutToken(order);
        orderStep.orderSuccessCreateWithIngredients(orderWithIngredientNoAuth);
    }

//
//    @Test
//    @DisplayName("Creating an order without authorization without ingredients")
//    public void createOderNonAuthWithoutIngredientsBadRequest() {
//        order = new Order();
//        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithoutToken(order);
//        orderSteps.checkAnswerWithoutIngredients(responseCreateAuth);
//    }
//
//    @Test
//    @DisplayName("Creating an order without authorization without ingredients")
//    public void createOderNonAuthWithWrongHashInternalServerError() {
//        order = new Order(List.of("123zxc"));
//        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithoutToken(order);
//        orderSteps.checkAnswerWithWrongHash(responseCreateAuth);}

}
