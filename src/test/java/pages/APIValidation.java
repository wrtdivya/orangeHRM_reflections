package pages;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import base.setupteardown;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Reporting;



public class APIValidation {

    private final String baseUrl = "https://reqres.in";

    //  FIX 2: Completely removed the @BeforeClass and @AfterClass blank setups
    
    public Response verifyEmployeeRecordOnBackend() {
        Reporting.addAllureLog("Executing Mock API Validation Layer via ReqRes service.");

        RestAssured.baseURI = baseUrl;

        Map<String, Object> requestPayload = new HashMap<>();
        //  FIX 3: Reference static fields directly using ClassName.variable
       requestPayload.put("name", setupteardown.firstName + " " + setupteardown.lastName);
        requestPayload.put("employeeId", setupteardown.employeeId);
        requestPayload.put("job", setupteardown.jobRole);
      
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestPayload)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201) // This will now pass smoothly with a 201 Created status code
                .extract()
                .response();

        Reporting.addAllureLog("API Response Payload Recieved: " + response.getBody().asString());
        
        return response;
    }
}