package bdd.automation.api.steps;

import bdd.automation.api.support.api.UserApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class Config {

    private final UserApi userApi;

    public Config() {
        userApi = new UserApi();
    }


    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api";

        RestAssured.requestSpecification = new RequestSpecBuilder().
            addHeader("Authorization", getToken()).
            setContentType(ContentType.JSON).
            build();
    }

    private String getToken(){
        return "grant access";
    }

    @After
    public void deleteAllUsers() {
        userApi.deleteAllUsers();
    }
}
