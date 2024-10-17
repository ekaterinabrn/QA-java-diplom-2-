import ClientStep.UserClient;
import Step.UserStep;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.junit.Before;
import org.junit.Test;

import static Constant.EndpointConstant.URL;
import static Constant.RandomDataUser.*;


public class Testme {

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
    }

   @Test
    public void test(){
       User user= new User(RANDOM_EMAIL,
       RANDOM_PASSWORD ,
       RANDOM_NAME);
        Response createtest= UserClient.createUser(user);
       // Response c=UserClient.createUser(user);
        UserStep userStep=new UserStep();
        //userStep.userCreationLoginAlreadyUsed(c);
       String tok =userStep.getAccessToken(createtest);
       Response de= UserClient.deleteUser(tok);
               de.then().statusCode(202);
//String t =userStep.getAccessToken(createtest); System.out.println(t);
}}
