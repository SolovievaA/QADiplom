package ru.iteco.fmhandroid.ui.steps;

import static ru.iteco.fmhandroid.ui.data.MainHelper.elementWaiting;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pageobject.DownloadPage;

public class DownloadSteps {

    DownloadPage downloadPage = new DownloadPage();

    public void appDownload() {
        Allure.step("Загрузка приложения");
        elementWaiting(downloadPage.splashImageMatcher, 10000);
    }
}