package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import androidx.test.espresso.intent.Intents;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import io.qameta.allure.kotlin.Description;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
@RunWith(AllureAndroidJUnit4.class)

public class AboutPageTest {
    DownloadSteps downloadSteps = new DownloadSteps();
    AboutSteps aboutSteps = new AboutSteps();
    LogSteps logSteps = new LogSteps();
    MainSteps mainSteps = new MainSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    @Before
    public void setUp() {
        downloadSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            logSteps.validLogIn();
            mainSteps.mainScreenLoad();
        }
    }

    @Test
    @Description("Страница About")
    @DisplayName("Корректность отображения элементов страницы About")
    public void fullElementsAboutPage() {
        mainSteps.openAboutPage();
        aboutSteps.checkAboutPageElementFull();
    }

    @Test
    @Description("Страница About")
    @DisplayName("Просмотр ссылки Политика конфиденциальности на странице")
    public void linkPrivacyPolicy() {
        mainSteps.openAboutPage();
        Intents.init();
        aboutSteps.goToPrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy/"));
        Intents.release();
    }

    @Test
    @Description("Страница About")
    @DisplayName("Просмотр ссылки Пользовательское соглашение на странице")
    public void linkUserAgreement() {
        mainSteps.openAboutPage();
        Intents.init();
        aboutSteps.goToTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        Intents.release();
    }

    @Test
    @Description("Страница About")
    @DisplayName("Возврат на главный экран приложения")
    public void backMainPage() {
        mainSteps.openAboutPage();
        aboutSteps.backMain();
        mainSteps.mainScreenLoad();
        mainSteps.checkThatMainBlockContentIsFull();
    }
}