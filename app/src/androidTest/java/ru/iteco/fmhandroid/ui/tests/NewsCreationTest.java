package ru.iteco.fmhandroid.ui.tests;

import android.view.View;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ru.iteco.fmhandroid.ui.data.MainHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentTime;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.Description;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LogSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@RunWith(AllureAndroidJUnit4.class)
public class NewsCreationTest {
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
    private View decorView;

    @Before
    public void setUp() {
        downloadSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            logSteps.validLogIn();
            mainSteps.mainScreenLoad();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    @Description("Control Panel на странице News")
    @Story("Добавление новости с заполненными значениями")
    public void addNewsValid() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String titleText = "Благодарность Тимофееву";
        String description = "Благодарим";

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();

        creatingNewsSteps.createNews(randomCategory(), titleText, publicationDate,
                publicationTime, description);
        creatingNewsSteps.clickSaveButton();

        controlPanelSteps.checkIfNewsWithTitle(titleText);
    }

    @Test
    @Description("Control Panel на странице News")
    @Story("Добавление новости с незаполненным полем и появлением ошибки при попытке его сохранения")
    public void addEmptyNews() {

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.clickSaveButton();

        creatingNewsSteps.checkToastMessageText("Fill empty fields", decorView);
    }


    @Test
    @Description("Control Panel на странице News")
    @Story("Отмена создания новости")
    public void cancelCreateNews() {

        String title = "Просьба к персоналу";

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.clickCancelButton();
        creatingNewsSteps.clickOKButton(title);
        controlPanelSteps.checkThatControlPanelContentIsFull();
    }

}