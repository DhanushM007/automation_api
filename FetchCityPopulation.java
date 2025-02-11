//1. Fetch Population of a Specific City
package com.api.automation;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class FetchCityPopulation {

    // Set the base URI for the API
    static {
        RestAssured.baseURI = "https://countriesnow.space";
    }

    @Test
    public void getcitypopulation(){
        Response response = given()
                .param("city", "Bangalore")
                .when()
                .get("api/v0.1/countries/population/cities/q")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Response:"+response.asPrettyString());

        String lagos_population=response.jsonPath().getString("data.populationCounts[0].value");

        System.out.println("Population of Lagos city:"+lagos_population);
    }

}
