package restassured;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request {

    @Step("Send POST request: endpoint - {endpoint}")
    public static Response post(ContentType contentType, Object object, String endpoint, int statusCode,
                                Map<String, String> cookies) {
        return
                given()
                        .filter(LogFilter.filters().withCustomTemplates())
                        .contentType(contentType)
                        .body(object)
                        .cookies(cookies)
                        .log().uri()
                        .log().body()
                        .when()
                        .post(endpoint)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().response();
    }
}
