package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${driver}.properties",
        "classpath:config/remote_driver.properties"
})
public interface DriverConfig extends Config {

    @Key("web.remote.driver.url")
    String webRemoteDriverUrl();

    @Key("web.remote.driver.user")
    String webRemoteDriverUser();

    @Key("web.remote.driver.password")
    String webRemoteDriverPassword();

    @Key("video.storage")
    String videoStorage();

    @DefaultValue("chrome")
    @Key("web.browser")
    String webBrowser();

    @DefaultValue("90.0")
    @Key("web.browser.version")
    String webBrowserVersion();

    @DefaultValue("1920x1080")
    @Key("web.browser.size")
    String webBrowserSize();

    @Key("web.browser.mobile.view")
    String webBrowserMobileView();
}
