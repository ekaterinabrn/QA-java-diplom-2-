import clientstep.IngredientClient;
import clientstep.OrderClient;
import clientstep.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.OrderIngredient;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import step.OrderStep;
import step.UserStep;

import java.util.ArrayList;
import java.util.List;

import static constant.EndpointConstant.URL;
import static constant.RandomDataUser.*;

public class CreateOrderWithAuthTest {
    private String accessToken;
    UserStep userStep = new UserStep();
    User user;
    OrderStep orderStep = new OrderStep();

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        Response createUser = UserClient.createUser(user);
        this.accessToken = userStep.getAccessToken(createUser);
    }

    @Test
    @DisplayName("Create  order with authorization and valid hash ingredient")
    public void createOderWithAuthorizationAndIngredient() {
        Response orderIngred = IngredientClient.getIngredient();
        List<String> ingredients = new ArrayList<>(orderIngred.then().log().all().extract().path("data._id")); // Извлечение ID ингредиентов из ответа
        int fromIndex = 1;
        int toIndex = 2;
        OrderIngredient order = new OrderIngredient(ingredients.subList(fromIndex, toIndex));
        Response orderCreateAuthAndIngredient = OrderClient.createOrder(accessToken, order);
        orderStep.orderSuccessCreateWithIngredients(orderCreateAuthAndIngredient);
    }

    @Test
    @DisplayName("Create  order with authorization and without ingredients")
    public void createOderWithAuthWithoutIngredient() {
        OrderIngredient orderIngredient = new OrderIngredient();
        Response orderCreateAuthNoIngredient = OrderClient.createOrder(accessToken, orderIngredient);
        orderStep.orderCreateWithoutIngredients(orderCreateAuthNoIngredient);
    }

    @Test
    @DisplayName("Create order with authorization  with wrong hash ingredients")
    public void createOderAuthWithWrongHashIngredient() {
        OrderIngredient order = new OrderIngredient(List.of("545422zxcWroNG"));
        Response orderCreateAuthWrongHash = OrderClient.createOrder(accessToken, order);
        orderStep.orderCreateWithWrongIngredientHash(orderCreateAuthWrongHash);
    }


    @After
    public void deleteUser() {
        if (accessToken != null) {
            Response delete = UserClient.deleteUser(accessToken);
            delete.then().statusCode(202);
        }


    }
}
