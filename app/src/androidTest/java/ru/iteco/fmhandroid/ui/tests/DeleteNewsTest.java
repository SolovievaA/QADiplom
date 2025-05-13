
package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.MainHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.MainHelper.getCurrentTime;

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
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;


@RunWith(AllureAndroidJUnit4.class)
public class DeleteNewsTest {
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
    @Description("Control Panel на странице News")
    @Story("Удаление новости")
    public void deleteNews() {

        String title = "массаж";
        String description = "новость";
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        creatingNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        creatingNewsSteps.clickSaveButton();
        controlPanelSteps.checkIfNewsWithTitle(title);
        controlPanelSteps.deleteRandomNews(title);
        controlPanelSteps.checkIfNoNewsWithTitle(title);

    }

}