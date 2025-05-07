package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pageobject.EditNewsPage;

public class EditNewsSteps {

    EditNewsPage editNewsPage = new EditNewsPage();

    public void checkThatEditNewsPageContentIsFull() {
        Allure.step("Проверка окна Редактирования новости на полный контент");
        editNewsPage.titlePage.check(matches(isDisplayed()));
        editNewsPage.categoryText.check(matches(isDisplayed()));
        editNewsPage.titleText.check(matches(isDisplayed()));
        editNewsPage.descriptionText.check(matches(isDisplayed()));
        editNewsPage.publishDate.check(matches(isDisplayed()));
        editNewsPage.time.check(matches(isDisplayed()));
        editNewsPage.switcher.check(matches(isDisplayed()));
        editNewsPage.saveButton.check(matches(isDisplayed()));
        editNewsPage.cancelButton.check(matches(isDisplayed()));
    }


    public void changeStatus() {
        Allure.step("Смена статуса новости");
        editNewsPage.switcher.perform(click());
    }

    public void clickSaveButton() {
        Allure.step("Нажатие кнопки Сохранить");
        editNewsPage.saveButton.perform(click());
    }


}