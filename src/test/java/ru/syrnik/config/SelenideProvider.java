package ru.syrnik.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SelenideProvider {

    private final SelenideConfig config;

    public SelenideProvider() {
        this.config = ConfigFactory.create(SelenideConfig.class, System.getProperties());
    }


    public void config() {

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();

        String remoteUrl = config.getRemoteUrl();
        if (remoteUrl != null)
            Configuration.remote = config.getRemoteUrl() + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

    }

}
