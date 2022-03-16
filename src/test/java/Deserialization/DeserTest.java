package Deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeserTest{

    @Test
    public void successfulRequest(){
        Map<String, Object> successRequestMap = new HashMap<>();

        successRequestMap.put("email", "eve.holt@reqres.in");
        successRequestMap.put("password", "pistol");

        JSONObject successRequest = new JSONObject(successRequestMap);

        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(successRequest.toJSONString())
                .when()
                .post("https://reqres.in/api/register");

        validateResponse(response);
    }

    @Test
    public void unsuccessfulRequest(){
        Map<String, Object> unsuccessfulRequestMap = new HashMap<>();

        unsuccessfulRequestMap.put("email", "sydney@fife");

        JSONObject unsuccessfulRequest = new JSONObject(unsuccessfulRequestMap);

        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(unsuccessfulRequest.toJSONString())
                .when()
                .post("https://reqres.in/api/register");

        validateResponse(response);
    }

    @Test
    public void jacksonRequest() throws JsonProcessingException {
        Map<String, Object> successRequestMap = new HashMap<>();

        successRequestMap.put("name", "morpheus");
        successRequestMap.put("job", "leader");

        JSONObject successRequest = new JSONObject(successRequestMap);

        Response response = RestAssured.
                given()
                .contentType(ContentType.JSON)
                .and()
                .body(successRequest.toJSONString())
                .when()
                .post("https://reqres.in/api/users");

        UsersResponse usersResponse = response.body().as(UsersResponse.class);

        //System.out.println(new ObjectMapper().writeValueAsString(usersResponse));
    }

    public void validateResponse(Response response){
        if(response.statusCode() == 200) {
            SuccessfulResponse successfulResponse = response.body().as(SuccessfulResponse.class);

            Assert.assertEquals(4, successfulResponse.id);
            Assert.assertEquals("QpwL5tke4Pnpja7X4", successfulResponse.token);

        } else if(response.statusCode() == 400) {
            UnsuccessfulResponse unsuccessfulResponse = response.body().as(UnsuccessfulResponse.class);

            Assert.assertEquals("Missing password", unsuccessfulResponse.error);
        }
    }

}
