package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.DataGenerator.Registration.getUser;
import static ru.netology.DataGenerator.getRandomLogin;
import static ru.netology.DataGenerator.getRandomPassword;

public class AutoTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login active registered user")
    void shouldSuccessfullyLoginActiveRegisteredUser() {
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("h2").shouldHave(exactText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error if login not registered user")
    void shouldGetErrorIfLoginNotRegisteredUser() {
        var notRegisteredUser = getUser("active");
        $("[data-test-id='login'] input").setValue(notRegisteredUser.getLogin());
        $("[data-test-id='password'] input").setValue(notRegisteredUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
        @Test
        @DisplayName("Should get error if login blocked registered user")
        void shouldGetErrorIfLoginBlockedRegisteredUser() {
            var blockedUser = getRegisteredUser("blocked");
            $("[data-test-id='login'] input").setValue(blockedUser.getLogin());
            $("[data-test-id='password'] input").setValue(blockedUser.getPassword());
            $("button.button").click();
            $("[data-test-id='error-notification'] .notification__content")
                    .shouldHave(exactText("Ошибка! Пользователь заблокирован"))
                    .shouldBe(Condition.visible);
        }
    @Test
    @DisplayName("Should get error if wrong login registered user")
    void shouldGetErrorIWrongLoginRegisteredUser() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin=getRandomLogin();
        $("[data-test-id='login'] input").setValue(wrongLogin);
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
    @Test
    @DisplayName("Should get error if wrong password registered user")
    void shouldGetErrorIWrongPasswordRegisteredUser() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword =getRandomPassword ();
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(wrongPassword);
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
    }

