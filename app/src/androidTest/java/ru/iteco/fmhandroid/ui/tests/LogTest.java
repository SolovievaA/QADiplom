package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageobject.LogPage;
import ru.iteco.fmhandroid.ui.pageobject.MainPage;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@RunWith(AllureAndroidJUnit4.class)
public class LogTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    DownloadSteps downloadSteps = new DownloadSteps();
    LogPage logPage = new LogPage();
    LogSteps logSteps = new LogSteps();
    MainPage mainPage = new MainPage();
    MainSteps mainSteps = new MainSteps();
    private View decorView;

    @Before
    public void setUp() {
        downloadSteps.appDownload();
        try {
            logSteps.loadAuthorizationPage();
        } catch (
                Exception e) {
            mainSteps.logOut();
            logSteps.loadAuthorizationPage();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @After
    public void tearDown() {
        try {
            mainSteps.logOut();
        } catch (Exception ignored) {
        }
    }


    @Test
    @Description("Тестирование вкладки Авторизация")
    @Story("Авторизация пользоваления с зарегистрированными данными")
    public void successfulAuthorization() {
        logSteps.validLogIn();
        mainSteps.mainScreenLoad();
        mainSteps.checkThatMainBlockContentIsFull();
    }

    @Test
    @Description("Тестирование вкладки Авторизация ")
    @Story("Выход из учетной записи")
    public void shouldLogOut() {
        logSteps.validLogIn();
        mainSteps.mainScreenLoad();
        mainSteps.logOut();
        logSteps.authorizationPageIsFull();
    }

    @Test
    @Description("Тестирование вкладки Авторизация")
    @Story("Авторизация пользователя при незарегистрированном пароле")
    public void passwordFieldUnregisteredUser() {
        logSteps.notValidPass();
        logSteps.checkToastMessageText("Something went wrong. Try again later.", decorView);
        logPage.title.check(matches(isDisplayed()));
        mainPage.mainBadge.check(matches(not(isDisplayed())));
    }

    @Test
    @Description("Тестирование вкладки Авторизация")
    @Story("Авторизация пользователя при незарегистрированном логине")
    public void loginFieldUnregisteredUser() {
        logSteps.notValidLogIn();
        logSteps.checkToastMessageText("Something went wrong. Try again later.", decorView);
        logPage.title.check(matches(isDisplayed()));
        mainPage.mainBadge.check(matches(not(isDisplayed())));
    }

    @Test
    @Description("Тестирование вкладки Авторизация")
    @Story("Авторизация пользователя с пустыми полями Логин и Пароль")
    public void userFieldByEmptyData() {
        logSteps.emptyLogIn();
        logSteps.checkToastMessageText("Login and password cannot be empty", decorView);
        logPage.title.check(matches(isDisplayed()));
        mainPage.mainBadge.check(matches(not(isDisplayed())));
    }
}