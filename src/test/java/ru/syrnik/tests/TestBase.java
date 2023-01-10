package ru.syrnik.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.syrnik.config.SelenideProvider;
import ru.syrnik.helpers.Attach;
import ru.syrnik.pages.RegistrationPage;

public class TestBase {

    public RegistrationPage registrationPage = new RegistrationPage();
    private static final SelenideProvider selenideProvider = new SelenideProvider();

    @BeforeAll
    static void beforeAll() {
        selenideProvider.config();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
