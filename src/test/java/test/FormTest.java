package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @Test
    void dataAfterSubmitForm() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Bob");
        $("#lastName").setValue("Jones");
        $("#userEmail").setValue("Jones@ya.ru");
        $(".custom-control-label").click();
        $("#userNumber").setValue("8926123456");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(byText("1983")).click();
        $(".react-datepicker__month-select").click();
        $(byText("May")).click();
        $(byText("11")).click();

        $("#subjectsInput").setValue("Eng");
        $("#subjectsInput").pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1.PNG"));
        $("#currentAddress").setValue("Moscow, Main str");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("Bob"), text("Jones"), text("Jones@ya.ru"), text("Male"), text("8926123456"), text("11 May,1983"), text("English"), text("Music"), text("1.PNG"), text("Moscow, Main str"), text("NCR Delhi"));


    }
}
