package Step;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class UserStep {
@Step("Get access token")
    public String getAccessToken(Response response) {
    return response.then().extract().path("accessToken");
    }

    @Step("User status code 403 and body when create without Name or password or Email  ")
    public void userAfterCreationErr(Response response) {
        response.then().statusCode(403).and().assertThat().body("message", equalTo("Email, password and name are required fields"));
    }
    @Step("Status code 403  after creating user if already registered user")
    public void userCreationLoginAlreadyUsed(Response response) {
       response.then().statusCode(403).and().assertThat().body("message",equalTo("User already exists"));

}

   // Изменение данных пользователя:
  //  с авторизацией,
 //   без авторизации,

    @Step("User status code 200 after success login")
    public void checkAnswerAfterSuccessLogin(Response response) {
        response.then().statusCode(200).and().assertThat().body("success",equalTo(true));

    }

    @Step("User status code 401 and body,  after login with wrong data(email or password)")
   public void checkAnswerWrongEmailOrPassword(Response response) {
      response.then().statusCode(401).and().assertThat().body("message",equalTo("email or password are incorrect"));

   }

    @Step("check status code 401 and answer after changing user data without access token")
    public void changingUserDataWithoutToken(Response response) {
        response.then().statusCode(401).and().assertThat().body("message",equalTo("You should be authorised"));

    }
}
