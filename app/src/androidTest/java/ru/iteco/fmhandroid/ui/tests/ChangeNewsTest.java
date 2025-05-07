package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.data.MainHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentTime;
import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.ext.junit.rules.ActivityScenarioRule;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.EditNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.FilterNewsSteps;

@RunWith(AllureAndroidJUnit4.class)
public class ChangeNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    DownloadSteps downloadSteps = new DownloadSteps();
    MainSteps mainSteps = new MainSteps();
    LogSteps logSteps = new LogSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    CreatingNewsSteps creatingNewsSteps = new CreatingNewsSteps();
    EditNewsSteps editNewsSteps = new EditNewsSteps();
    FilterNewsSteps filterNewsSteps = new FilterNewsSteps();

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
    @Feature("Control Panel на странице News")
    @Description("Поиск новости с использованием фильтра активна")
    public void filterNewsActive() {
        String title = "Благодарим";
        String description = "Найдены документы Иванова ИИ";
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        creatingNewsSteps.clickSaveButton();
        controlPanelSteps.openNewsFilter();
        filterNewsSteps.clickNotActiveCheckBox();
        filterNewsSteps.clickFilterButton();
        filterNewsSteps.checkNewsIsDisplay(publicationTime, false);

    }
    @Test
    @Feature("Control Panel на странице News")
    @Description("Поиск новости с использованием фильтра неактивна")
    public void filterNewsNotActive() {
        String title = "Важная информация";
        String description = "Просьба освободить помещение до 12.00";
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        creatingNewsSteps.clickSaveButton();
        controlPanelSteps.openNewsFilter();
        filterNewsSteps.clickActiveCheckBox();
        filterNewsSteps.clickFilterButton();
        filterNewsSteps.checkNewsIsDisplay(publicationTime, false);
    }

    @Test
    @Feature("Control Panel на странице News")
    @Description("Измениение статуса существующей новости")
    public void changeNewsStatus() {
        String title = "Сегодня вечером";
        String description = "Сегодня будет обьявлен новый победитель";
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        creatingNewsSteps.clickSaveButton();
        controlPanelSteps.clickEditNews(title);
        editNewsSteps.checkThatEditNewsPageContentIsFull();
        editNewsSteps.changeStatus();
        editNewsSteps.clickSaveButton();
        filterNewsSteps.checkNewsIsDisplay(publicationTime, false);
    }

    @Test
    @Feature("Главная страница")
    @Description("Выход из фильтра новостей в Control Panel без фильтрации")
    public void testCancelFilter() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openNewsFilter();
        filterNewsSteps.clickCancelButton();
        controlPanelSteps.checkThatControlPanelContentIsFull();
    }

    @Test
    @Feature("Control Panel на странице News")
    @Description("Сортировка новостей по дате публикации")
    public void testNewsOrderInControlPanel() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.clickSortNewsButton();
        controlPanelSteps.clickSortNewsButton();
        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText("07.05.2025")));
    }


}