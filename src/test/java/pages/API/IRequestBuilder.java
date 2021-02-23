package pages.API;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public interface IRequestBuilder {

    static <T> Response getResource(String byParam, T paramValue, String endpoint) {
        return
                given()
                        .param(byParam, paramValue)

                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response();
    }

    static Response getResource(String endpoint) {
        return
                given()
                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response();
    }
}
