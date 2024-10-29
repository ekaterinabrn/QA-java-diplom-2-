import clientstep.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import step.UserStep;

import static constant.EndpointConstant.URL;
import static constant.RandomDataUser.*;

public class CreateUserTest {
    UserStep userStep = new UserStep();
    private String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
    }

    @Test
    @DisplayName("Creating new user")
    @Description("Creating new user with correct data and checkins status code")
    public void createUserPositive() {
        User user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        Response createUserTest = UserClient.createUser(user);
        userStep.checkAnswerAfterSuccessLoginOrCreation(createUserTest);
        this.accessToken = userStep.getAccessToken(createUserTest);
    }

    @Test
    @DisplayName("creating a user if the login is already in use")
    @Description("Creating user with  existing login checking the response")
    public void createUserWhenLoginAlreadyUsed() {
        User user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        Response createUser1Test = UserClient.createUser(user);
        this.accessToken = userStep.getAccessToken(createUser1Test);
        Response createUserTwo = UserClient.createUser(user);
        userStep.userCreationLoginAlreadyUsed(createUserTwo);
    }

    @Test
    @DisplayName("Creating  user without email")
    @Description("Creating  user without email and checking the response")
    public void createCourierWithoutEmailBadRequest() {
        User user = new User("", RANDOM_PASSWORD, RANDOM_NAME);
        Response createUserWithoutEmail = UserClient.createUser(user);
        userStep.userAfterCreationErr(createUserWithoutEmail);
    }

    @Test
    @DisplayName("Creating  user without password")
    @Description("Creating  user without password and checking the response")
    public void createUserWithoutPasswordBadRequest() {
        User user = new User(RANDOM_EMAIL, "", RANDOM_NAME);
        Response createUserWithoutPassword = UserClient.createUser(user);
        userStep.userAfterCreationErr(createUserWithoutPassword);
    }

    @Test
    @DisplayName("Creating  user without Name")
    @Description("Creating  user without Name and checking the response")
    public void createUserWithoutNameBadRequest() {
        User user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, "");
        Response createUserWithoutName = UserClient.createUser(user);
        userStep.userAfterCreationErr(createUserWithoutName);
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            Response delete = UserClient.deleteUser(accessToken);
            delete.then().statusCode(202);
        }


    }
}
