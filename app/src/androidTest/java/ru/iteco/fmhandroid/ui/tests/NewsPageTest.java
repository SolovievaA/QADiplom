package ru.iteco.fmhandroid.ui.tests;


import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentTime;
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
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.MainNewsSteps;
@RunWith(AllureAndroidJUnit4.class)

public class NewsPageTest {
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
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    CreatingNewsSteps creatingNewsSteps = new CreatingNewsSteps();
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
    @Feature("Страница News")
    @Description("Переход на страницу Control Panel")
    public void shouldOpenControlPanelPage() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.checkThatControlPanelContentIsFull();
    }


    @Test
    @Feature("Страница News")
    @Description("Открытие фильтра новостей и выбор категории из имеющегося списка")
    public void shouldFilter() {
        String category = "День рождения";
        String title = "Профсоюз";
        String description = "Свободное место";
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.createNews(category, title, publicationDate,
                publicationTime, description);
        creatingNewsSteps.clickSaveButton();
        mainNewsSteps.openNewsFilter();
        mainNewsSteps.filterNewsCategoryField(category);
        mainNewsSteps.clickFilterButton();

    }

    @Test
    @Feature("Страница News")
    @Description("Разворачивание каждой новости")
    public void expandNewsOnTheMainNewsPage() {
        mainSteps.openNewsPage();
        mainNewsSteps.clickOneNewsItem(0);
    }
}
