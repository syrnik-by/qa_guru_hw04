package ru.syrnik.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface SelenideConfig extends Config {

    @Key("framework.baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("framework.browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("framework.browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("framework.browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("framework.remoteUrl")
    String getRemoteUrl();
}
