
package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.Description;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.MissionSteps;

@RunWith(AllureAndroidJUnit4.class)
public class TransitBetweenSectionsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    DownloadSteps downloadSteps = new DownloadSteps();
    MainSteps mainSteps = new MainSteps();
    LogSteps logSteps = new LogSteps();
    MainNewsSteps mainNewsSteps = new MainNewsSteps();
    AboutSteps aboutSteps = new AboutSteps();
    MissionSteps missionSteps = new MissionSteps();

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
    @Description("Переход по разделам приложения")
    @Story("Панель перехода в раздел меню about")
    public void sectionsAbout() {
        mainSteps.openAboutPage();
        aboutSteps.checkAboutPageElementFull();
    }

    @Test
    @Description("Переход по разделам приложения")
    @Story("Панель перехода в раздел меню News")
    public void sectionsNews() {
        mainSteps.openNewsPage();
        mainNewsSteps.newsListLoad();
        mainNewsSteps.checkThatNewsPageIsFull();
    }
    @Test
    @Description("Переход по разделам приложения")
    @Story("Панель перехода в раздел меню Love is all")
    public void sectionsMission() {
        mainSteps.openNewsPage();
        mainSteps.openQuotesPage();
        missionSteps.elementsQuotesPageFull();
    }

    @Test
    @Description("Переход по разделам приложения")
    @Story("Панель перехода в раздел меню main")
    public void sectionsMain() {
        mainSteps.openNewsPage();
        mainSteps.openNewsMainPage();
        mainSteps.checkThatMainBlockContentIsFull();
    }


}
