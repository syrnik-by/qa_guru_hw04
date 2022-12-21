package ru.syrnik.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.syrnik.models.State;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static ru.syrnik.utils.RandomUtils.getRandomCityByState;
import static ru.syrnik.utils.RandomUtils.getRandomState;

public class AutomationPracticeFormTests extends TestBase {

    Faker faker = new Faker();

    @Test
    void fillFormTest() throws IOException {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        String gender = "Male";
        String userNumber = faker.phoneNumber().subscriberNumber(10);

        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");
        String[] date = dateFormat.format(faker.date().birthday()).split(" ");
        String day = date[0];
        String month = date[1];
        String year = date[2];

        List<String> subjects = new ArrayList<>();
        subjects.add("English");
        subjects.add("Maths");
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Sports");
        hobbies.add("Music");
        File uploadPicture = new File("src/test/resources/documents/1.txt");
        String currentAddress = faker.address().fullAddress();

        State randomState = getRandomState();
        String state = randomState.getName();
        String city = getRandomCityByState(randomState);

        step("Open registrations form", () -> {
            registrationPage.openPage();
        });

        step("Fill registrations form", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .setBirthDate(day, month, year)
                    .setSubjectsInput(subjects)
                    .setHobbiesWrapper(hobbies)
                    .setUploadPicture(uploadPicture)
                    .setCurrentAddress(currentAddress)
                    .setState(state)
                    .setCity(city)
                    .clickSubmit();
        });
        step("Check results form", () -> {
            registrationPage.verifyResult(new HashMap<>() {{
                put("Student Name", firstName + " " + lastName);
                put("Student Email", userEmail);
                put("Gender", gender);
                put("Mobile", userNumber);
                put("Date of Birth", day + " " + month + "," + year);
                put("Subjects", subjects.toString().replace("[", "").replace("]", ""));
                put("Hobbies", hobbies.toString().replace("[", "").replace("]", ""));
                put("Picture", uploadPicture.getName());
                put("Address", currentAddress);
                put("State and City", state + " " + city);
            }});
        });
    }
}
