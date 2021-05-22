package tests;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.DriverHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.AttachmentHelper.*;
import static helpers.DriverHelper.configureDriver;

public class TestBase {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = TestData.getApiUrl();
        configureDriver();
    }

    @AfterEach
    public void addAttachments() {
        if (WebDriverRunner.driver().hasWebDriverStarted()) {
            attachScreenshot("Last screenshot");
            attachPageSource();
            if (Configuration.browser.equals(Browsers.CHROME))
                attachAsText("Browser console logs", DriverHelper.getConsoleLogs());
            if (DriverHelper.isVideoOn())
                attachVideo(DriverHelper.getSessionId());
            WebDriverRunner.closeWebDriver();
        }
    }
}