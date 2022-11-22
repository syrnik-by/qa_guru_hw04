package ru.syrnik.tests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutomationPracticeFormTests extends TestBase {

    @Test
    void fillFormTest() {

        String firstName = "Andrey";
        String lastName = "Syreyschikov";
        String userEmail = "andrey@syreyschikov.com";
        String gender = "Male";
        String userNumber = "9995553311";
        String day = "17";
        String month = "August";
        String year = "1991";
        List<String> subjects = new ArrayList<>();
        subjects.add("English");
        subjects.add("Maths");
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Sports");
        hobbies.add("Music");
        File uploadPicture = new File("src/test/resources/1.txt");
        String currentAddress = "addressCurrent";
        String state = "Haryana";
        String city = "Karnal";

        registrationPage.openPage()
                .setFirstName(firstName)
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
                .clickSubmit()
                .verifyResult(new HashMap<>() {{
                    put("Student Name", firstName + " " + lastName);
                    put("Student Email", userEmail);
                    put("Gender", gender);
                    put("Mobile", userNumber);
                    put("Date of Birth", day + " " + month + "," + year);
                    put("Subjects", subjects.toString().replace("[","").replace("]",""));
                    put("Hobbies", hobbies.toString().replace("[","").replace("]",""));
                    put("Picture", uploadPicture.getName());
                    put("Address", currentAddress);
                    put("State and City", state + " " + city);
                }});
    }
}
