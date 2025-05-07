package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.pageobject.ControlPanelPage.scrollToAndCheckTextIsDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pageobject.FilterNewsPage;

public class FilterNewsSteps {

    FilterNewsPage filterNewsPage = new FilterNewsPage();

    public void checkThatFilterNewsBlockContentIsFull() {
        Allure.step("Проверка блока Фильтрации новостей на полный контент");
        filterNewsPage.titlePage.check(matches(isDisplayed()));
        filterNewsPage.categoryText.check(matches(isDisplayed()));
        filterNewsPage.dateStartText.check(matches(isDisplayed()));
        filterNewsPage.dateEndText.check(matches(isDisplayed()));
        filterNewsPage.filterActive.check(matches(isDisplayed()));
        filterNewsPage.filterNotActive.check(matches(isDisplayed()));
        filterNewsPage.filterButton.check(matches(isDisplayed()));
        filterNewsPage.cancelButton.check(matches(isDisplayed()));
    }
    public void checkNewsIsDisplay(String title, boolean expectedDisplayed) {
        Allure.step("Проверка, что новость с заголовком: отображается или не отображается");
        int newsConstraintLayout = 0;
        int newsTitleText = 0;
        boolean found = scrollToAndCheckTextIsDisplayed(newsConstraintLayout, newsTitleText, title);
        if (expectedDisplayed) {
            if (!found) {
                throw new AssertionError("News with title not found.");
            }
        } else {
            if (found) {
                throw new AssertionError("News with title is displayed, but it should not be.");
            }
        }
    }

    public void clickFilterButton() {
        Allure.step("Нажатие кнопки Filter");
        filterNewsPage.filterButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Нажатие кнопки Cancel");
        filterNewsPage.cancelButton.perform(click());
    }

    public void clickActiveCheckBox() {
        Allure.step("Нажать чекбокс - активна");
        filterNewsPage.filterActive.perform(click());
    }

    public void clickNotActiveCheckBox() {
        Allure.step("Нажать чекбокс - не активна");
        filterNewsPage.filterNotActive.perform(click());
    }

}