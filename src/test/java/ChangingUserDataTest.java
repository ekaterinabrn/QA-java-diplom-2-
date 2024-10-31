import clientstep.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import step.UserStep;

import static constant.EndpointConstant.URL;
import static constant.RandomDataUser.*;

@RunWith(Parameterized.class)
public class ChangingUserDataTest {
    private String accessToken;
    UserStep userStep = new UserStep();
    User user;
    private final String email;
    private final String password;
    private final String name;

    public ChangingUserDataTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters(name = "{index} : update {3}")
    public static Object[][] testData() {
        return new Object[][]{
                {RANDOM_EMAIL, RANDOM_PASSWORD, "newname"},
                {"new@maii.test", RANDOM_PASSWORD, RANDOM_NAME},
                {RANDOM_EMAIL, "newpass", RANDOM_NAME},

        };
    }


    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        Response createUser = UserClient.createUser(user);
        this.accessToken = userStep.getAccessToken(createUser);
    }

    @Test
    @DisplayName("Changing user data wit auth")
    @Description("Changing user data with auth and request return status code 200")
    public void updateLoginUser() {
        User newUser = new User(email, password, name);
        Response userToChangeData = UserClient.updateDataUserAuth(newUser, accessToken);
        userStep.changingUserDataWithToken(userToChangeData);
    }


    @Test
    @DisplayName("Changing user data without auth")
    @Description("Changing user data without auth and request return status code 401")
    public void changeUserDataWithoutAuth() {
        User userNoAuth = new User(email, password, name);
        Response changingNotAuthUser = UserClient.updateDataUserWithoutAuth(userNoAuth);
        userStep.changingUserDataWithoutToken(changingNotAuthUser);

    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            Response delete = UserClient.deleteUser(accessToken);
            delete.then().statusCode(202);
        }
    }

}
