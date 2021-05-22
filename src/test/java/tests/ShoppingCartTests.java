package tests;

import restassured.AuthApi;
import restassured.Request;
import cookie.CookiesManger;
import com.codeborne.selenide.Selenide;
import endpoints.ApiEndpoint;
import endpoints.UiEndpoint;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.is;

@DisplayName("Verify adding products to the shopping cart")
public class ShoppingCartTests extends TestBase {
    private final CookiesManger cookiesManger = new CookiesManger(new AuthApi().cookiesWithoutLogIn());

    @Test
    @Owner("omelyashchik")
    @Feature("Api")
    @DisplayName("Verify successful adding products to the shopping cart via Api")
    public void checkShoppingCartApi() {
        final int countProducts = 2;
        Response response = Request.post(ContentType.URLENC,
                "addtocart_13.EnteredQuantity=" + countProducts,
                ApiEndpoint.ADD_TO_CART.addPath("/details/13/1"),
                200,
                cookiesManger.getCookies());

        Allure.step("Check that response contains correct number of products", () ->
                response.then()
                        .body("success", is(true))
                        .body("updatetopcartsectionhtml", is(String.format("(%s)", countProducts))));
    }

    @Test
    @Owner("omelyashchik")
    @Feature("Api and Ui")
    @DisplayName("Verify successful adding product to the shopping cart via Api and Ui")
    public void checkShoppingCartApiAndUi() {
        final int countProducts = 3;
        Request.post(ContentType.URLENC,
                "addtocart_13.EnteredQuantity=" + countProducts,
                ApiEndpoint.ADD_TO_CART.addPath("/details/13/1"),
                200,
                cookiesManger.getCookies());

        cookiesManger.setCookiesWebDriver();

        step("Open site", () ->
                Selenide.open(UiEndpoint.CART.getPath()));

        step("Check that the shopping cart contains correct number of products", () ->
                $(".cart-qty").shouldHave(text(String.valueOf(countProducts))));

        step("Check that the shopping cart contains correct name of product", () ->
                $(".order-summary-content .product").shouldHave(text("Computing and Internet")));
    }
}
