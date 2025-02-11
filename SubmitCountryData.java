//2. Submit Data to Retrieve Country Population

package com.api.automation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.json.JSONObject;
import org.json.JSONArray;

import static io.restassured.RestAssured.*;

public class SubmitCountryData {

    static
    {
        RestAssured.baseURI="https://countriesnow.space";
    }
    @Test
    public void testSubmitCountryData() {
        // API URL for the POST request
        String apiUrl = "api/v0.1/countries/population";

        // Define the request body
        String requestBody = "{\"country\":\"Nigeria\"}";

        // Send POST request
        Response res = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(apiUrl);

        String redirected_url = res.getHeader("Location");

        if (redirected_url != null) {
            String full_redirected_url = baseURI + redirected_url;
            System.out.println(full_redirected_url);


            Response redirectedRes = RestAssured.given()
                    .when()
                    .get(full_redirected_url);

            String responsebody=redirectedRes.asString();

            JSONObject jsonResponse=new JSONObject(responsebody);
            JSONArray populationCounts=jsonResponse.getJSONObject("data").getJSONArray("populationCounts");

            System.out.println("Year\t\tPopulation");
            for (int i = 0; i < populationCounts.length(); i++) {
                JSONObject populationData=populationCounts.getJSONObject(i);
                int year=populationData.getInt("year");
                int population=populationData.getInt("value");

                System.out.println(year+ "\t\t" +population);
            }
        }


    }
}
