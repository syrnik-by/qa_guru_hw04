import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest(){

        open("/automation-practice-form");

        $("#firstName").setValue("Andrey");
        $("#lastName").setValue("Syreyschikov");
        $("#userEmail").setValue("andrey@syreyschikov.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("9995553311");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("1991");
        $(byText("17")).click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbies-checkbox-1").parent().click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1.txt"));
        $("#currentAddress").setValue("addressCurrent");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Karnal")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible);
        $(".modal-body").shouldHave(text("Andrey"),
                text("Andrey"),
                text("Syreyschikov"),
                text("andrey@syreyschikov.com"),
                text("Male"),
                text("9995553311"),
                text("17 August,1991"),
                text("English"),
                text("Sports"),
                text("1.txt"),
                text("addressCurrent"),
                text("Haryana"),
                text("Karnal"));

        $("#closeLargeModal").click();
    }
}
