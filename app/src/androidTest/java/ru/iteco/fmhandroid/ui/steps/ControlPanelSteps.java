package ru.iteco.fmhandroid.ui.steps;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.MainHelper.elementWaiting;

import io.qameta.allure.kotlin.Allure;


import ru.iteco.fmhandroid.ui.pageobject.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pageobject.MainNewsPage;

public class ControlPanelSteps {

    ControlPanelPage controlPanelPage = new ControlPanelPage();
    MainNewsPage mainNewsPage = new MainNewsPage();


    public void openControlPanelPage() {
        Allure.step("Переход в панель управления со страницы Новостей");
        mainNewsPage.controlPanelButton.perform(click());
        elementWaiting(mainNewsPage.newsImageMatcher, 5000);
    }

    public void checkThatControlPanelContentIsFull() {
        Allure.step("Проверка панели управления на полный контент");
        elementWaiting(controlPanelPage.addNewsImageMatcher, 7000);
        controlPanelPage.badge.check(matches(isDisplayed()));
        controlPanelPage.sortButton.check(matches(isDisplayed()));
        controlPanelPage.filterButton.check(matches(isDisplayed()));
        controlPanelPage.addNewsButton.check(matches(isDisplayed()));
    }


    public void openNewsFilter() {
        Allure.step("Открыть расширенный фильтр");
        controlPanelPage.filterButton.perform(click());
    }

    public void openCreateNewsButton() {
        Allure.step("Нажать кнопку создания новости");
        controlPanelPage.addNewsButton.perform(click());
    }


    public void deleteRandomNews(String title) {
        Allure.step("Удалить новость с указанным заголовком");
        controlPanelPage.deleteNewsButton(title).perform(click());
        controlPanelPage.buttonToOkDeleteNews.perform(click());
    }

    public void clickEditNews(String newsTitle) {
        Allure.step("Нажать кнопку Корректировка новости");
        controlPanelPage.editNewsButton(newsTitle).perform(click());
    }

    public void checkIfNewsWithTitle(String title) {
        Allure.step("Проверка наличия новости с указанным заголовком");
        onView(allOf(withText(title), isDisplayed())).check(matches(isDisplayed()));

    }

    public void checkIfNoNewsWithTitle(String title) {
        Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(allOf(withText(title), isDisplayed())).check(doesNotExist());
        //onView(Matchers.allOf(withId(R.id.news_item_title_text_view), withText(title))).check(doesNotExist());

    }

    public void clickSortNewsButton() {
        Allure.step("Нажать кнопку сортировки");
        controlPanelPage.sortButton.perform(click());
    }




}