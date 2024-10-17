import ClientStep.LoginClient;
import ClientStep.UserClient;
import Step.UserStep;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Credentials;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Constant.EndpointConstant.URL;
import static Constant.RandomDataUser.*;

public class LoginUserTest {
    private String accessToken;
    UserStep userStep = new UserStep();
    User user;

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        Response createUser = UserClient.createUser(user);
        this.accessToken = userStep.getAccessToken(createUser);
    }


    @DisplayName("User login is success")
    @Description("Success request return status code 200")
    @Test
    public void userLoginSuccess() {
        Credentials credentials = new Credentials(RANDOM_EMAIL, RANDOM_PASSWORD);
        Response loginResponse = LoginClient.loginUser(credentials);
        userStep.checkAnswerAfterSuccessLoginOrCreation(loginResponse);
    }

    @DisplayName("User Email is wrong")
    @Description("request return status code 401 and   message")
    @Test
    public void userEmailWrong() {
        Credentials credentials = new Credentials("wrong", RANDOM_PASSWORD);
        Response wrongEmail = LoginClient.loginUser(credentials);
        userStep.checkAnswerWrongEmailOrPassword(wrongEmail);
    }

    @DisplayName("User Password is wrong")
    @Description("request return status code 401 and   message")
    @Test
    public void userPasswordWrong() {
        Credentials credentials = new Credentials(RANDOM_EMAIL, "wrong");
        Response wrongPassword = LoginClient.loginUser(credentials);
        userStep.checkAnswerWrongEmailOrPassword(wrongPassword);
    }

    @Test
    @DisplayName("login courier  without wrong all data")
    @Description("login without data and request return status code 401")
    public void loginWrongAllData() {
        Credentials credentials = new Credentials("wrong", "wrong");
        Response wrongData = LoginClient.loginUser(credentials);
        userStep.checkAnswerWrongEmailOrPassword(wrongData);
    }


    @After
    public void deleteUser() {
        if (accessToken != null) {
            Response delete = UserClient.deleteUser(accessToken);
            delete.then().statusCode(202);
        }


    }
}
