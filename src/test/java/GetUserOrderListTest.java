import ClientStep.IngredientClient;
import ClientStep.OrderClient;
import ClientStep.UserClient;
import Step.OrderStep;
import Step.UserStep;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.OrderIngredient;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static Constant.EndpointConstant.URL;
import static Constant.RandomDataUser.*;

public class GetUserOrderListTest {
    private String accessToken;
    UserStep userStep = new UserStep();
    User user;
OrderStep orderStep=new OrderStep();
    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        Response createUser = UserClient.createUser(user);
        this.accessToken = userStep.getAccessToken(createUser);
       //создадим заказ для пользователя с ингредиентами
        Response orderIngred = IngredientClient.getIngredient();
        List<String> ingredients = new ArrayList<>(orderIngred.then().log().all().extract().path("data._id")); // Извлечение ID ингредиентов из ответа
        int fromIndex = 1; //
        int toIndex = 4;
        OrderIngredient order = new OrderIngredient(ingredients.subList(fromIndex, toIndex));
        OrderClient.createOrder(accessToken, order);
    }

    @Test
    @DisplayName("Getting a list of orders authorized user")
    @Description("Getting a list of orders with authorization token")
    public void getListOfOrdersAuthSuccess() {
      Response userAuthGetList = OrderClient.getOrderUserToken(accessToken);
      orderStep.checkGetOrdersListWithAuth(userAuthGetList);

    }

    @Test
    @DisplayName("Getting a list of orders not  authorized user")
    @Description("Getting a list of orders  without authorization token")
    public void getListOfOrdersNonAuthUnauthorized() {
        Response userNotAuthGetList = OrderClient.getOrderUserWithoutAuthorization();
        orderStep.checkGetOrdersListNonAuth(userNotAuthGetList);
    }
    @After
    public void deleteUser() {
        if (accessToken != null) {
            Response delete = UserClient.deleteUser(accessToken);
            delete.then().statusCode(202);
        }
    }
}
