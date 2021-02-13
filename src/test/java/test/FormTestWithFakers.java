package test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTestWithFakers {

    @Test
    void dataAfterSubmitForm() {
        Faker faker = new Faker();

        String[] genderList = new String[]{"Male", "Female", "Other"};
        String[] monthList = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] subjectList = new String[]{"English", "Chemistry", "Commerce"};
        String[] hobbiesList = new String[]{"Music", "Reading", "Sports"};

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                gender = genderList[(int) Math.floor(Math.random() * genderList.length)],
                userPhoneNumber = faker.number().digits(10),
                yearOfBirth = faker.number().numberBetween(1900, 2020) + "",
                monthOfBirth = monthList[(int) Math.floor(Math.random() * monthList.length)],
                dateOfBirth = faker.number().numberBetween(1, 29) + "",
                subject = subjectList[(int) Math.floor(Math.random() * subjectList.length)],
                hobbies = hobbiesList[(int) Math.floor(Math.random() * hobbiesList.length)],
                picture = "1.PNG",
                currentAddress = faker.address().fullAddress(),
                state = "NCR",
                city = "Delhi";


        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        // set gender checkbox
        $("#genterWrapper").$(byText(gender)).click();
        // set phoneNumber
        $("#userNumber").setValue(userPhoneNumber);
        // set date
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(byText(yearOfBirth)).click();
        $(".react-datepicker__month-select").click();
        $(byText(monthOfBirth)).click();
        $(byText(dateOfBirth)).click();
        //  Set subjects
        $("#subjectsInput").val(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
        // set hobbies
        $(byText(hobbies)).click();
        // upload picture
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
        //set address
        $("#currentAddress").setValue(currentAddress);
        // set state and city
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        //assert
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail), text(gender), text(userPhoneNumber), text(dateOfBirth + " " + monthOfBirth + "," + yearOfBirth), text(subject), text(hobbies), text(picture), text(currentAddress), text(state + " " + city));
    }
}
