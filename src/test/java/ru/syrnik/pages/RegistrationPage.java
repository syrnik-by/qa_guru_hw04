package ru.syrnik.pages;

import com.codeborne.selenide.Condition;
import ru.syrnik.pages.components.CalendarComponent;
import ru.syrnik.pages.components.RegistrationResultModal;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    static private final String TITLE_TEXT = "Student Registration Form";
    static private final String URL = "/automation-practice-form";

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final RegistrationResultModal registrationResultModal = new RegistrationResultModal();

    public RegistrationPage openPage() {
        open(URL);
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        $("#firstName").setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        $("#lastName").setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        $("#userEmail").setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        $("#userNumber").setValue(value);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjectsInput(List<String> values) {
        for (String value :
                values) {
            $("#subjectsInput").setValue(value).pressEnter();
        }
        return this;
    }

    public RegistrationPage setHobbiesWrapper(List<String> values) {
        for (String value :
                values) {
            $("#hobbiesWrapper").$(byText(value)).click();
        }
        return this;
    }

    public RegistrationPage setUploadPicture(File file) {
        $("#uploadPicture").uploadFile(file);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        $("#currentAddress").setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        $("#state").click();
        $("#state").$(byText(value)).click();
        return this;
    }


    public RegistrationPage setCity(String value) {
        $("#city").click();
        $("#city").$(byText(value)).click();
        return this;
    }


    public RegistrationPage clickSubmit() {
        $("#submit").click();
        return this;
    }

    public RegistrationPage verifyResult(Map<String, String> map) {
        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible);
        for (Map.Entry<String, String> entry :
                map.entrySet()) {
            registrationResultModal.verifyResult(entry.getKey(), entry.getValue());
        }
        return this;
    }

}
