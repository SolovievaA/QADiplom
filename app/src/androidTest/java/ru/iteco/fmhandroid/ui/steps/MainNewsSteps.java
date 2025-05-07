package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.MainHelper.elementWaiting;
import io.qameta.allure.kotlin.Allure;

import ru.iteco.fmhandroid.ui.pageobject.MainNewsPage;

public class MainNewsSteps {
    MainNewsPage mainNewsPage = new MainNewsPage();


    public void newsListLoad() {
        Allure.step("Загрузка списка новостей");
        elementWaiting(mainNewsPage.recyclerMatcher, 7000);
    }

    public void checkThatNewsPageIsFull() {
        Allure.step("Проверка блока Новостей на полный контент");
        mainNewsPage.badge.check(matches(isDisplayed()));
        mainNewsPage.title.check(matches(isDisplayed()));
        mainNewsPage.sort.check(matches(isDisplayed()));
        mainNewsPage.filter.check(matches(isDisplayed()));
        mainNewsPage.controlPanelButton.check(matches(isDisplayed()));
        mainNewsPage.allNewsBlock.check(matches(isDisplayed()));
    }


    public void openNewsFilter() {
        Allure.step("Открыть расширенный фильтр");
        mainNewsPage.filter.perform(click());
    }
    public void clickFilterButton() {
        Allure.step("Нажатие кнопки Filter");
        mainNewsPage.filterButton.perform(click());
    }
    public void filterNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Category ");
        MainNewsPage.categoryText.perform(replaceText(text));
    }

    public void clickOneNewsItem(int index) {
        Allure.step("Свернуть/развернуть новость");
        mainNewsPage.childNewsButton.perform(actionOnItemAtPosition(index, click()));
    }



}