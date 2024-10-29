import clientstep.IngredientClient;
import clientstep.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.OrderIngredient;
import org.junit.Before;
import org.junit.Test;
import step.OrderStep;

import java.util.ArrayList;
import java.util.List;

import static constant.EndpointConstant.URL;


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
    @Test
    @DisplayName("Create  order without authorization and without ingredients")
    public void createOderWithoutAuthWithoutIngredient() {
        OrderIngredient orderIngredient = new OrderIngredient();
        Response orderCreateNoAuthNoIngredient = OrderClient.createOrderWithoutToken( orderIngredient);
        orderStep.orderCreateWithoutIngredients(orderCreateNoAuthNoIngredient);
    }
    @Test
    @DisplayName("Create order without authorization  with wrong hash ingredients")
    public void createOderNoAuthWithWrongHashIngredient() {
        OrderIngredient order = new OrderIngredient(List.of("545422zxcWroNG"));
        Response orderCreateAuthWrongHash = OrderClient.createOrderWithoutToken(order);
        orderStep.orderCreateWithWrongIngredientHash(orderCreateAuthWrongHash);
    }

}
