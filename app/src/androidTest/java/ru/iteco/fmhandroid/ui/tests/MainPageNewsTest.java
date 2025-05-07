package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Description;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.MainNewsSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MainPageNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    DownloadSteps downloadSteps = new DownloadSteps();
    MainSteps mainSteps = new MainSteps();
    LogSteps logSteps = new LogSteps();
    MainNewsSteps mainNewsSteps = new MainNewsSteps();

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
    @Feature("Главная страница")
    @Description("Корректность отображения всех элементов на главной странице")
    public void shouldBeFullMainNewsPage() {

        mainSteps.checkThatMainBlockContentIsFull();
    }

    @Test
    @Feature("Главная страница")
    @Description("Переход в раздел News нажатием по кнопке All News")
    public void shouldOpenAllNews() {
        mainSteps.openAllNews();
        mainNewsSteps.checkThatNewsPageIsFull();
    }

    @Test
    @Feature("Главная страница")
    @Description("Сворачивание и разворачивание блока News")
    public void shouldOpenNews() {
        mainSteps.openNews();
    }

    @Test
    @Feature("Главная страница")
    @Description("Развернуть/свернуть отдельную новость на главной странице")
    public void expandNewsOnTheMainPage() {
        mainSteps.clickOneMainNewsItem(0);
    }

}