package test.API;

import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.API.IEndpoint;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static pages.API.IEndpoint.IMMUNITY;

public class APITest {


    private static final String URL = "https://pokeapi.co";

    private static class Pokemon {
        String name;
        String url;
    }

    private static class Pokemons {
        int count;
        String next;
        String previous;
        List<Pokemon> results;
    }

    @Test
    public void httpTest() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet("https://pokeapi.co/api/v2/pokemon");

            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            CloseableHttpResponse response = httpClient.execute(request);
            try {
                Assert.assertEquals(response.getCode(), 200);

                HttpEntity entity = response.getEntity();
                Assert.assertNotNull(entity);

                // simple check
                String jsonString = EntityUtils.toString(entity);
                Assert.assertTrue(jsonString.contains("\"count\":1118"));

                // regular check
                Pokemons pokemons = new Gson().fromJson(jsonString, Pokemons.class);
                Assert.assertEquals(pokemons.count, 1118);
                Assert.assertEquals(pokemons.results.size(), 20);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    @Test
    public void restAssuredTest() {
        when().get("https://pokeapi.co/api/v2/pokemon").
                then().
                statusCode(200).
                body("count", Matchers.equalTo(1118),
                        "results.name", Matchers.hasItems("bulbasaur", "ivysaur"));
    }

    @Test
    public void restAssuredTest1() {
        when().get("https://pokeapi.co/api/v2/pokemon").
                then().
                statusCode(200).assertThat().body("count", Matchers.equalTo(1118),
                "results.name", Matchers.hasItems("pidgey", "metapod"));
    }

    @Test
    public void restAssuredTest2() {
        when().get("https://pokeapi.co/api/v2/ability/").
                then().
                statusCode(200).assertThat().body("count", Matchers.equalTo(327),
                "results.name", Matchers.hasItems("immunity", "speed-boost"));
    }

    @Test
    public static void getResponseBody() {
        RestAssured.get("https://pokeapi.co/api/v2/ability/")
                .then()
                .log()
                .all();


        RestAssured.get("https://pokeapi.co/api/v2/ability/")
                .then()
                .log()
                .body();
    }

    @Test
    public static void getResponseStatus() {
        int statusCode =
                RestAssured.get(URL + IEndpoint.CHARACTERISTIC)
                        .getStatusCode();

        System.out.println("The response status is " + statusCode);

        RestAssured.get(URL + IEndpoint.CHARACTERISTIC).then().assertThat().statusCode(200);
    }

    //Response time is to measure the performance of the application. Note that the time taken for your
// call may take more or less time depending on your internet speed, the performance of the API
// at that time, server load, and other factors impacting the time.
    @Test
    public static void getResponseTime() {
        System.out.println("The time taken to fetch the response " +
                RestAssured.get(URL + IMMUNITY)
                .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
    }

    //At times getting the content-type is essential for ensuring there are no security gaps for any
// cross-origin threats or just to ensure the content passed is as per the standards of the API.
    @Test
    public static void getResponseContentType() {
        System.out.println("The content type of response " +
                RestAssured.get(URL)
                        .then()
                        .extract()
                        .contentType());
    }

    //Quite a few times, you would need to use the authorization token, or a session cookie for
    // the subsequent request, and mostly, these details are returned as headers of the response.
    @Test
    public static void getResponseHeaders() {
        System.out.println("The headers in the response " +
                RestAssured.get(URL + IEndpoint.TYPE)
                        .then()
                        .extract()
                        .headers());
    }
/*
    @Test
    public static void getSpecificPartOfResponseBody(){

        ArrayList<String> amounts = when().get(URL + TYPE)
                .then()
                .extract()
                .path("url.name.AMOUNT") ;
        int sumOfAll=0;
        for(String a:amounts){

            System.out.println("The amount value fetched is "+ a);
            sumOfAll = sumOfAll + Integer.valueOf(a);
        }
        System.out.println("The total amount is "+sumOfAll);

    }*/
}
