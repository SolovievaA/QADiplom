package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pageobject.MissionPage;


public class MissionSteps {

    MissionPage missionPage = new MissionPage();

    public void elementsQuotesPageFull() {
        Allure.step("Проверка всех элементов страницы");
        missionPage.badge.check(matches(isDisplayed()));
        missionPage.title.check(matches(isDisplayed()));
        missionPage.ourMissionList.check(matches(isDisplayed()));
    }

    public void checkQuote(int number) {
        Allure.step("Развернуть и свернуть цитату");
        missionPage.quotesLayout.check(matches(isDisplayed()));
        missionPage.quotesLayout.perform(actionOnItemAtPosition(number, click()));
    }

    public void descriptionIsDisplay(String text) {
        Allure.step("Отображение дополнительной цитаты");
        missionPage.textMission(text).check(matches(isDisplayed()));
    }

}