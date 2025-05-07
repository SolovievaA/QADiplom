package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.MissionSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MissionTest {
    DownloadSteps downloadSteps = new DownloadSteps();
    LogSteps logSteps = new LogSteps();
    MainSteps mainSteps = new MainSteps();
    MissionSteps missionSteps = new MissionSteps();

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
        mainSteps.openQuotesPage();
    }

    @Test
    @Description("Страница Love is all")
    @Story("Проверка корректности элементов страницы")
    public void fullElementsQuotesPage() {

        missionSteps.elementsQuotesPageFull();
    }


    @Test
    @Description("Страница Love is all")
    @Story("Развернуть/свернуть цитату во вкладке")
    public void quoteFullDisplayed() {
        String quoteFullText = "Нет шаблона и стандарта, есть только дух, " +
                "который живет в разных домах по-разному. Но всегда он добрый, " +
                "любящий и помогающий.";
        missionSteps.checkQuote(1);
        missionSteps.descriptionIsDisplay(quoteFullText);
        missionSteps.checkQuote(1);
    }

}