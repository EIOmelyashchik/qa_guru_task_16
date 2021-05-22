package restassured;

import endpoints.ApiEndpoint;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi {

    @Step("Get new session Cookie with authorization")
    public Map<String, String> cookies(String email, String password) {
        return
                given()
                        .filter(LogFilter.filters().withCustomTemplates())
                        .contentType(ContentType.URLENC)
                        .formParam("Email", email)
                        .formParam("Password", password)
                        .when()
                        .post(ApiEndpoint.LOGIN.getPath())
                        .then()
                        .log().all()
                        .statusCode(302)
                        .extract().cookies();
    }

    @Step("Get new session Cookie without authorization")
    public Map<String, String> cookiesWithoutLogIn() {
        return
                given()
                        .filter(LogFilter.filters().withCustomTemplates())
                        .contentType(ContentType.URLENC)
                        .when()
                        .get(ApiEndpoint.MAIN.getPath())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().cookies();
    }
}


