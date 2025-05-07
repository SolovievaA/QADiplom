package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.MainHelper.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pageobject.MainPage;


public class MainSteps {

    MainPage mainPage = new MainPage();

    public void mainScreenLoad() {
        Allure.step("Загрузка страницы");
        elementWaiting(mainPage.allNewsTextMatcher, 5000);
    }

    public void checkThatMainBlockContentIsFull() {
        Allure.step("Проверка main на полный контент");
        elementWaiting(mainPage.allNewsTextMatcher, 7000);
        mainPage.menuButton.check(matches(isDisplayed()));
        mainPage.ourMissionButton.check(matches(isDisplayed()));
        mainPage.profileButton.check(matches(isDisplayed()));
        mainPage.titleOfNewsContainer.check(matches(isDisplayed()));
        mainPage.allNewsButton.check(matches(isDisplayed()));
        mainPage.collapseAllNewsButton.check(matches(isDisplayed()));
    }

    public void openNewsPage() {
        Allure.step("Открытие раздела News");
        mainPage.menuButton.perform(click());
        mainPage.newsOfMenu.perform(click());
    }

    public void openNewsMainPage() {
        Allure.step("Открытие раздела News");
        mainPage.menuButton.perform(click());
        mainPage.mainOfMenu.perform(click());
    }

    public void openAboutPage() {
        Allure.step("Открытие раздела About");
        mainPage.menuButton.perform(click());
        mainPage.aboutOfMenu.perform(click());
    }

    public void openQuotesPage() {
        Allure.step("Открытие раздела Love is all");
        mainPage.ourMissionButton.perform(click());
    }


    public void logOut() {
        Allure.step("Выход из профиля");
        mainPage.profileButton.perform(click());
        mainPage.logOutButton.perform(click());
    }

    public void openAllNews() {
        Allure.step("Открыть все новости");
        mainPage.allNewsButton.perform(click());
    }

    public void openNews() {
        Allure.step("Свернуть/развернуть блок новости");
        mainPage.collapseAllNewsButton.perform(click());
    }

    public void clickOneMainNewsItem(int index) {
        Allure.step("Свернуть/развернуть новость");
        MainPage.childNewsButton.perform(actionOnItemAtPosition(index, click()));
    }
}