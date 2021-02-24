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

import org.testng.annotations.Test;
import pages.API.IEndpoint;
import pages.API.IRequestBuilder;


import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static pages.API.IEndpoint.IMMUNITY;

public class PokiTest {

    private static final String URL = "https://pokeapi.co";

    @Test
    public void bodyMatchTest() {
        RestAssured.get(URL + IEndpoint.POKEMON).
                then().
                statusCode(200).assertThat().
                body("count", Matchers.equalTo(1118),
                        "results.name", Matchers.hasItems("bulbasaur", "ivysaur"));
    }

    @Test
    public void bodyMatchTest2() {
        RestAssured.get(URL + IEndpoint.ABILITY).
                then().
                statusCode(200).assertThat().body("count", Matchers.equalTo(327),
                "results.name", Matchers.hasItems("immunity", "speed-boost"));
    }

    @Test
    public static void getResponseBody() {
        RestAssured.get(URL + IEndpoint.ABILITY)
                .then()
                .log()
                .all();

        RestAssured.get(URL + IEndpoint.ABILITY)
                .then()
                .log()
                .body();
    }

    @Test
    public static void getResponseStatus() {
        int statusCode = RestAssured.get(URL + IEndpoint.CHARACTERISTIC)
                        .getStatusCode();

        System.out.println("The response status is " + statusCode);

        RestAssured.get(URL + IEndpoint.CHARACTERISTIC).then().assertThat().statusCode(200);
    }

    @Test
    public static void getResponseTime() {
        System.out.println("The time taken to fetch the response " +
                RestAssured.get(URL + IMMUNITY)
                .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
    }

    @Test
    public static void verifyResponseContentType() {
        System.out.println("The content type of response " +
                RestAssured.get(URL)
                        .then()
                        .extract()
                        .contentType());

        RestAssured.get(URL).then().assertThat().contentType("text/html");
    }

    @Test
    public static void verifyResponseHeaders() {
        System.out.println("The headers in the response " +
                RestAssured.get(URL + IEndpoint.TYPE)
                        .then()
                        .extract()
                        .headers());
    }
}
