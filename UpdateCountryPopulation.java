//3. Update Country Population
package com.api.automation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class UpdateCountryPopulation {

    @Test
    public void fetchAndModifyPopulation() {

        String apiUrl = "https://countriesnow.space/api/v0.1/countries/population/q?country=Nigeria";


        Response response = RestAssured.given()
                .get(apiUrl);

        if (response.getStatusCode() == 200) {

            JSONObject jsonResponse = new JSONObject(response.getBody().asString());

            JSONObject populationData = jsonResponse.getJSONObject("data");
            System.out.println(populationData);
            int Population = populationData.getJSONArray("populationCounts")
                    .getJSONObject(0)
                    .getInt("value");

            int updatedPopulation = Population + 100000;

            System.out.println("Country: Nigeria");
            System.out.println("Previous Population: " + Population);
            System.out.println("Updated Population: " + updatedPopulation);
        } else {
            System.out.println("Failed to fetch population. Status Code: " + response.getStatusCode());
        }
    }
}
