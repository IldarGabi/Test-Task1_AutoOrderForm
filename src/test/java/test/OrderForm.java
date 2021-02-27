package test;

import org.junit.jupiter.api.BeforeEach;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderForm {
    @BeforeEach
    void setup() {
        open("http://computer-database.gatling.io/computers");
    }

    @Test
    void orderAutoFormWithSelenide() {
        $(byText("Add a new computer")).click();
        String introducedDate = LocalDate.now().minusYears(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String discontinuedDate = LocalDate.now().minusYears(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String name = "Гена";
        $("[id=name]").setValue(name);
        $("[id=introduced]").setValue(introducedDate);
        $("[id=discontinued]").setValue(discontinuedDate);
        $("[id=company]").click();
        $$("[name=company]").first().find(withText("IB")).click();
        $("[type=submit]").click();
        $("[class='alert-message warning']").waitUntil(Condition.visible, 2000)
                .shouldHave(exactText("Done !  Computer " + name + " has been created "));
    }
}
