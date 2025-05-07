package ru.iteco.fmhandroid.ui.steps;

import ru.iteco.fmhandroid.ui.data.MainHelper;
import ru.iteco.fmhandroid.ui.pageobject.LogPage;
import static ru.iteco.fmhandroid.ui.data.MainHelper.elementWaiting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import io.qameta.allure.kotlin.Allure;

public class LogSteps {

    LogPage logPage = new LogPage();

    public void loadAuthorizationPage() {
        Allure.step("Загрузка вкладки авторизации");
        elementWaiting(logPage.loginButtonMatcher, 7000);
    }

    public void authorizationPageIsFull() {
        Allure.step("Наличие всех элементов страницы авторизации");
        logPage.title.check(matches(isDisplayed()));
        logPage.loginField.check(matches(isDisplayed()));
        logPage.passwordField.check(matches(isDisplayed()));
        logPage.loginButton.check(matches(isDisplayed()));
    }

    public void validLogIn() {
        Allure.step("Авторизация в приложении с зарегистрированными данными");
        MainHelper help = new MainHelper();
        logPage.loginField.perform(typeText(help.getValidUser().getLogin()), closeSoftKeyboard());
        logPage.passwordField.perform(typeText(help.getValidUser().getPassword()), closeSoftKeyboard());
        logPage.loginButton.perform(click());
    }

    public void notValidLogIn() {
        Allure.step("Авторизация в приложении с незарегистрированным логином");
        MainHelper helper = new MainHelper();
        logPage.loginField.perform(typeText(helper.getNotValidUser().getLogin()), closeSoftKeyboard());
        logPage.passwordField.perform(typeText(helper.getValidUser().getPassword()), closeSoftKeyboard());
        logPage.loginButton.perform(click());
    }

    public void notValidPass() {
        Allure.step("Авторизация в приложении с незарегистрированным паролем");
        MainHelper helper = new MainHelper();
        logPage.loginField.perform(typeText(helper.getValidUser().getLogin()), closeSoftKeyboard());
        logPage.passwordField.perform(typeText(helper.getNotValidUser().getPassword()), closeSoftKeyboard());
        logPage.loginButton.perform(click());
    }
    public void emptyLogIn() {
        Allure.step("Авторизация с пустыми данными");
        logPage.loginButton.perform(click());
    }

    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка текста сообщения");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}