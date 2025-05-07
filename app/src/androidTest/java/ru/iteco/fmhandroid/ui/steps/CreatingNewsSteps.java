package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import android.view.View;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;

import ru.iteco.fmhandroid.ui.pageobject.CreatingNewsPage;

public class CreatingNewsSteps {

    CreatingNewsPage creatingNewsPage = new CreatingNewsPage();

    public void fillInNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Category ");
        creatingNewsPage.categoryText.perform(replaceText(text));
    }

    public void fillInNewsTitleField(String text) {
        Allure.step("Ввод данных в поле Title");
        creatingNewsPage.titleText.perform(replaceText(text));
    }

    public void fillInPublicDateField(String text) {
        Allure.step("Ввод данных в поле Publication date");
        creatingNewsPage.publishDate.perform(replaceText(text));
    }

    public void fillInTimeField(String text) {
        Allure.step("Ввод данных в поле Time");
        creatingNewsPage.time.perform(replaceText(text));
    }

    public void fillInNewsDescriptionField(String text) {
        Allure.step("Ввод данных в поле Description");
        creatingNewsPage.descriptionText.perform(replaceText(text));
    }

    public void createNews(String category, String title, String publicationDate,
                           String publicationTime, String description) {
        Allure.step("Ввод данных для создания новости");
        fillInNewsCategoryField(category);
        fillInNewsTitleField(title);
        fillInPublicDateField(publicationDate);
        fillInTimeField(publicationTime);
        fillInNewsDescriptionField(description);
    }

    public void clickSaveButton() {
        Allure.step("Нажатие кнопки Save");
        creatingNewsPage.saveButton.perform(click());
    }


    public void clickCancelButton() {
        Allure.step("Нажатие кнопки Cancel");
        final ViewInteraction perform = creatingNewsPage.cancelButton.perform(click());
    }

    public void clickOKButton(String text) {
        Allure.step("Нажатие кнопки ОК в сообщении");
        creatingNewsPage.okButtonMessage.perform(click());
    }

    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}